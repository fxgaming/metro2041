package by.fxg.metro2041.common.item.gun;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import by.fxg.metro2041.common.damage.DamageSourceGun;
import by.fxg.metro2041.common.damage.EnumDSGType;
import by.fxg.metro2041.common.network.PacketParticle;
import by.fxg.metro2041.common.player.ExtendedData;
import by.fxg.metro2041.common.player.ExtendedPlayer;
import by.fxg.metro2041.util.Utils;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.block.Block;
import net.minecraft.block.BlockButton;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class AttackHelper {
	public static int blockID = 0;
	public float damage;
	public boolean penetrate;
	public float width;
	public boolean fire;
	public boolean gun;
	public boolean gunm;
	public boolean shotgun;
	public EntityPlayer playerField;
	public float dropOff;
	public ItemGun itemGun;
	Random rand;
	private int timesPlayed;
	public static Random rnd = new Random();

	public AttackHelper(Item item) {
		this.penetrate = true;
		this.width = 0.1F;
		this.fire = false;
		this.gun = false;
		this.gunm = false;
		this.shotgun = false;
		this.rand = new Random();
		if (item != null && item instanceof ItemGun) {
			ItemGun gun = (ItemGun) item;
			this.damage = gun.damage;
			this.gun = true;
			this.itemGun = (ItemGun) item;
			this.shotgun = gun.gunType == gun.gunType.Shotgun;
		}
	}

	public static void playSnap(World givenWorld, int givenX, int givenY, int givenZ) {
		givenWorld.playSoundEffect((double) givenX, (double) givenY, (double) givenZ, "metro:bullet_bounce" + rnd.nextInt(6), 1.0F, 1.0F);
		//givenWorld.playSoundEffect((double) givenX, (double) givenY, (double) givenZ, "deci:bullet.snap", 2.0F, 1.0F);
		//Decimation.getDecimation().getPacketChannel().sendToAllAround(new Message_BulletSnapFade(), new TargetPoint(0, (double) givenX, (double) givenY, (double) givenZ, 5.0D));
	}

	public static int randomBetween(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	public Vec3 getPosition(float par1, EntityPlayer p) {
		ExtendedData data = ExtendedPlayer.get(p).extData;
		float diff = data.stance == 1 ? 0.42F : (data.stance == 2 ? 0.92F : 0.0F);
		return Vec3.createVectorHelper(p.posX, p.posY + (double) p.getDefaultEyeHeight() - (double) diff, p.posZ);
	}

	public MovingObjectPosition rayTrace(ItemGun givenGun, double par1, float par3, EntityPlayer p, float recoil, float sRecoil) {
		Vec3 vec3 = this.getPosition(par3, p);
		Vec3 vec31 = this.getLook(givenGun, par3, p, 0.0F, sRecoil);
		Vec3 vec32 = vec3.addVector(vec31.xCoord * par1, vec31.yCoord * par1, vec31.zCoord * par1);
		return this.clip(vec3, vec32, p.worldObj, p);
	}

	public Vec3 getLook(ItemGun gun, float par1, EntityPlayer p, float recoil, float sRecoil) {
		float rY = p.rotationYaw - sRecoil * 1.5F;
		float rP = p.rotationPitch;
		Double d = Math.random();
		if (p.getHeldItem() != null && (gun.shotType.equals(EnumShotType.AUTOSHOT) || gun.shotType.equals(EnumShotType.SHOT) || gun.shotType.equals(EnumShotType.MULTISHOT)) && Utils.getNBT(p.getHeldItem()).getInteger("magazin") != -1 && Utils.getNBT(p.getHeldItem()).getInteger("ammo") > 0) {
			int maxShotgunSpread = 5;
			d = Math.random();
			rY += (float) (d < 0.5D ? randomBetween(0, maxShotgunSpread) : randomBetween(-maxShotgunSpread, 0));
			d = Math.random();
			rP += (float) (d < 0.5D ? randomBetween(0, maxShotgunSpread) : randomBetween(-maxShotgunSpread, 0));
		}

		float f1;
		float f2;
		float f3;
		float f4;
		if (par1 == 1.0F) {
			f1 = MathHelper.cos(-rY * 0.017453292F - 3.1415927F);
			f2 = MathHelper.sin(-rY * 0.017453292F - 3.1415927F);
			f3 = -MathHelper.cos(-rP * 0.017453292F);
			f4 = MathHelper.sin(-rP * 0.017453292F);
			return Vec3.createVectorHelper((double) (f2 * f3), (double) f4, (double) (f1 * f3));
		} else {
			f1 = p.prevRotationPitch + (rP - p.prevRotationPitch) * par1;
			f2 = p.prevRotationYaw + (rY - p.prevRotationYaw) * par1;
			f3 = MathHelper.cos(-f2 * 0.017453292F - 3.1415927F);
			f4 = MathHelper.sin(-f2 * 0.017453292F - 3.1415927F);
			float f5 = -MathHelper.cos(-f1 * 0.017453292F);
			float f6 = MathHelper.sin(-f1 * 0.017453292F);
			return Vec3.createVectorHelper((double) (f4 * f5), (double) f6, (double) (f3 * f5));
		}
	}

	public void attack(ItemGun theGun, EntityPlayer p, double range) {
		this.attack(theGun, p, range, 0.0F, 0.0F);
	}

	public void attack(ItemGun theGun, EntityPlayer p, double range, float recoil, float sRecoil) {
		this.attack(theGun, p, range, recoil, sRecoil, (List)null);
	}

	public boolean inWidth(Vec3 vec, Entity e, float am) {
		return vec.xCoord < e.posX + (double) am && vec.xCoord > e.posX - (double) am && vec.zCoord < e.posZ + (double) am && vec.zCoord > e.posZ - (double) am;
	}

	public void attack(ItemGun theGun, EntityPlayer attacker, double range, float recoil, float sRecoil, List list2) {
		float par1 = 1.0F;
		this.playerField = attacker;
		if (attacker != null) {
			ArrayList l3 = new ArrayList();
			World worldObj = attacker.worldObj;
			AxisAlignedBB boundingBox = attacker.boundingBox;
			double d1 = range;
			MovingObjectPosition objectMouseOver = this.rayTrace(theGun, range, par1, attacker, recoil, sRecoil);
			Vec3 vec3 = this.getPosition(par1, attacker);
			if (objectMouseOver != null) {
				d1 = objectMouseOver.hitVec.distanceTo(vec3);
			}

			Vec3 vec31 = this.getLook(theGun, par1, attacker, recoil, sRecoil);
			Vec3 vec32 = vec3.addVector(vec31.xCoord * d1, vec31.yCoord * d1, vec31.zCoord * d1);
			float f1 = list2 != null ? 2.0F : this.width;
			List list = worldObj.getEntitiesWithinAABBExcludingEntity(attacker, boundingBox.addCoord(vec31.xCoord * range, vec31.yCoord * range, vec31.zCoord * range).expand((double) f1, (double) f1, (double) f1));
			double d2 = d1;
			boolean isHeadshot = false;
			Iterator var26 = list.iterator();

			while (true) {
				while (true) {
					Entity entity;
					MovingObjectPosition movingobjectposition;
					do {
						do {
							do {
								do {
									do {
										if (!var26.hasNext()) {
											if (list2 == null && attacker != null && this.gun) {
												this.attack(theGun, attacker, range, recoil, sRecoil, l3);
											}
											return;
										}
										Object entityAsObject = var26.next();
										entity = (Entity) entityAsObject;
									} while ((!(entity instanceof EntityLivingBase) || entity.isDead || ((EntityLivingBase) entity).deathTime > 0 || ((EntityLivingBase) entity).getHealth() <= 0.0F));
								} while (!entity.canBeCollidedWith());

								boolean changed = false;
								boolean safe = false;
								if ((entity.height == 1.8F || entity.height == 1.9F) && entity.width == 0.6F) {
									entity.boundingBox.maxY += 0.20000000298023224D;
									changed = true;
								} else {
									changed = false;
								}

								if (list2 != null) {
									entity.boundingBox.maxY += (double) f1;
									entity.boundingBox.minY -= (double) f1;
								}

								entity.boundingBox.maxZ += (double) f1;
								entity.boundingBox.maxX += (double) f1;
								entity.boundingBox.minZ -= (double) f1;
								entity.boundingBox.minX -= (double) f1;
								float f2 = entity.getCollisionBorderSize();
								AxisAlignedBB axisalignedbb = entity.boundingBox.expand((double) f2, (double) f2, (double) f2);
								movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec32);
								float kaif = 0.3F;
								entity.boundingBox.maxZ -= (double) kaif;
								entity.boundingBox.maxX -= (double) kaif;
								entity.boundingBox.minZ += (double) kaif;
								entity.boundingBox.minX += (double) kaif;
								boolean inWidth = false;
								float f3 = entity.getCollisionBorderSize();
								AxisAlignedBB axisalignedbb2 = entity.boundingBox.expand((double) f3, (double) f3, (double) f3);
								MovingObjectPosition movingobjectposition2 = axisalignedbb2.calculateIntercept(vec3, vec32);
								inWidth = movingobjectposition2 != null;
								entity.boundingBox.maxZ += (double) kaif;
								entity.boundingBox.maxX += (double) kaif;
								entity.boundingBox.minZ -= (double) kaif;
								entity.boundingBox.minX -= (double) kaif;
								entity.boundingBox.maxZ -= (double) f1;
								entity.boundingBox.maxX -= (double) f1;
								entity.boundingBox.minZ += (double) f1;
								entity.boundingBox.minX += (double) f1;
								if (list2 != null) {
									entity.boundingBox.maxY -= (double) f1;
									entity.boundingBox.minY += (double) f1;
								}

								if (changed) {
									entity.boundingBox.maxY -= 0.20000000298023224D;
								}

								if (axisalignedbb.isVecInside(vec3)) {
									if (0.0D < d2 || d2 == 0.0D) {
										safe = true;
										d2 = 0.0D;
									}
								} else if (movingobjectposition != null) {
									double d3 = vec3.distanceTo(movingobjectposition.hitVec);
									if (d3 < d2 || d2 == 0.0D) {
										if (entity == attacker.ridingEntity && !entity.canRiderInteract()) {
											if (d2 == 0.0D) {
												safe = true;
											}
										} else {
											safe = true;
											d2 = d3;
										}

										if (entity.isDead) {
											safe = false;
										}
									}
								}
								if (!safe || attacker != null && entity == attacker.riddenByEntity) {
									entity = null;
								}
							} while (entity == null);
						} while (!(entity instanceof EntityLivingBase));
					} while (movingobjectposition == null);
					if (list2 != null && attacker != null) {
						if (!list2.contains(entity) && entity instanceof EntityPlayer && !this.shotgun) {
							;
						}
					} else if (entity != null && movingobjectposition != null) {
						l3.add(entity);
						float damage = this.damage;
						float dist = (float)((int)(Math.abs(attacker.posX - entity.posX) + Math.abs(attacker.posY - entity.posY) + Math.abs(attacker.posZ - entity.posZ)));
						float pen = 1.0F;
						ExtendedData data;
						EntityPlayer player;
//						if ((entity.height == 1.8F || entity.height == 1.9F) && entity.width == 0.6F) {
//							if (movingobjectposition.hitVec.yCoord < entity.posY + 0.8999999761581421D && entity instanceof EntityPlayer && this.rand.nextInt(2) == 0) {
//								data = DeciPlayerData.get((EntityPlayer) entity);
//								player = (EntityPlayer) entity;
//								if (givenPlayer != null/*&& !isSaveZone проверка на сз у игрока */) {
//									entity.worldObj.playSoundAtEntity(entity, "deci:mob.human.legbreak", 0.1F, 0.5F);
//									if (!player.capabilities.isCreativeMode && !data.isLegBroken()) {
//										double random = Math.random();
//										if (random < 0.1D) {
//											data.setConditionBrokenleg(true);
//											((EntityPlayerMP) entity).addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "You're legs have been broken!"));
//											//Поломка ног
//										}
//									}
//								}
//							}
//						}

						if (attacker != null) {
							if (!(entity instanceof EntityLivingBase)) {
								entity.attackEntityFrom(DamageSourceGun.causeGunDamage(attacker, EnumDSGType.GUNSHOT), damage);
								//уеб моба
							} else {
								EntityLivingBase attacked = (EntityLivingBase) entity;
								if (entity instanceof EntityPlayer) {
									player = (EntityPlayer) entity;
									ExtendedData playerData = ExtendedPlayer.get(player).extData;
								}

								attacked.attackEntityFrom(DamageSourceGun.causeGunDamage(attacker, EnumDSGType.GUNSHOT), damage);
								//уеб игрока

								++attacked.arrowHitTimer;
								attacked.hurtResistantTime = attacked.maxHurtResistantTime / 2;
								attacked.motionX = 0.0D;
								attacked.motionY = 0.0D;
								attacked.motionZ = 0.0D;
							}
						}
					}
				}
			}
		}
	}

	public MovingObjectPosition clip(Vec3 par1Vec3, Vec3 par2Vec3, World world, EntityPlayer thePlayer) {
		return this.rayTraceBlocks_do_do(par1Vec3, par2Vec3, world, thePlayer);
	}

	public float fac(float fac) {
		float f = 1.0F;

		for (int i = 1; (float) i <= fac; ++i) {
			f *= (float) i;
		}

		return f;
	}

	public MovingObjectPosition rayTraceBlocks_do_do(Vec3 par1Vec3, Vec3 par2Vec3, World world, EntityPlayer thePlayer) {
		boolean hasHitTarget = false;
		boolean isInWater = false;
		if (!Double.isNaN(par1Vec3.xCoord) && !Double.isNaN(par1Vec3.yCoord) && !Double.isNaN(par1Vec3.zCoord)) {
			if (!Double.isNaN(par2Vec3.xCoord) && !Double.isNaN(par2Vec3.yCoord) && !Double.isNaN(par2Vec3.zCoord)) {
				int i = MathHelper.floor_double(par2Vec3.xCoord);
				int j = MathHelper.floor_double(par2Vec3.yCoord);
				int k = MathHelper.floor_double(par2Vec3.zCoord);
				int l = MathHelper.floor_double(par1Vec3.xCoord);
				int i1 = MathHelper.floor_double(par1Vec3.yCoord);
				int j1 = MathHelper.floor_double(par1Vec3.zCoord);
				boolean hasPlayedOnce = false;
				boolean hasHitOnce = false;
				double ld = par1Vec3.xCoord;
				double i1d = par1Vec3.yCoord;
				double j1d = par1Vec3.zCoord;
				int k1 = 0;
				int l1 = world.getBlockMetadata(l, i1, j1);
				int block = world.getBlockId(l, i1, j1);
				if (block != 0 && Block.blocksList[block].canCollideCheck(l1, false)) {
					MovingObjectPosition movingobjectposition = Block.blocksList[block].collisionRayTrace(world, l, i1, j1, par1Vec3, par2Vec3);
					if (movingobjectposition != null) {
						return movingobjectposition;
					}
				}

				k1 = 200;
				float var61 = 0.0F;

				while (k1-- >= 0) {
					if (Double.isNaN(par1Vec3.xCoord) || Double.isNaN(par1Vec3.yCoord) || Double.isNaN(par1Vec3.zCoord)) {
						return null;
					}

					if (l == i && i1 == j && j1 == k) {
						return null;
					}

					boolean flag2 = true;
					boolean flag3 = true;
					boolean flag4 = true;
					double d0 = 999.0D;
					double d1 = 999.0D;
					double d2 = 999.0D;
					if (i > l) {
						d0 = (double) l + 1.0D;
					} else if (i < l) {
						d0 = (double) l + 0.0D;
					} else {
						flag2 = false;
					}

					if (j > i1) {
						d1 = (double) i1 + 1.0D;
					} else if (j < i1) {
						d1 = (double) i1 + 0.0D;
					} else {
						flag3 = false;
					}

					if (k > j1) {
						d2 = (double) j1 + 1.0D;
					} else if (k < j1) {
						d2 = (double) j1 + 0.0D;
					} else {
						flag4 = false;
					}

					double d3 = 999.0D;
					double d4 = 999.0D;
					double d5 = 999.0D;
					double d6 = par2Vec3.xCoord - par1Vec3.xCoord;
					double d7 = par2Vec3.yCoord - par1Vec3.yCoord;
					double d8 = par2Vec3.zCoord - par1Vec3.zCoord;
					if (flag2) {
						d3 = (d0 - par1Vec3.xCoord) / d6;
					}

					if (flag3) {
						d4 = (d1 - par1Vec3.yCoord) / d7;
					}

					if (flag4) {
						d5 = (d2 - par1Vec3.zCoord) / d8;
					}

					boolean flag5 = false;
					byte b0;
					if (d3 < d4 && d3 < d5) {
						if (i > l) {
							b0 = 4;
						} else {
							b0 = 5;
						}

						par1Vec3.xCoord = d0;
						par1Vec3.yCoord += d7 * d3;
						par1Vec3.zCoord += d8 * d3;
					} else if (d4 < d5) {
						if (j > i1) {
							b0 = 0;
						} else {
							b0 = 1;
						}

						par1Vec3.xCoord += d6 * d4;
						par1Vec3.yCoord = d1;
						par1Vec3.zCoord += d8 * d4;
					} else {
						if (k > j1) {
							b0 = 2;
						} else {
							b0 = 3;
						}

						par1Vec3.xCoord += d6 * d5;
						par1Vec3.yCoord += d7 * d5;
						par1Vec3.zCoord = d2;
					}

					Vec3 vec32 = Vec3.createVectorHelper(par1Vec3.xCoord, par1Vec3.yCoord, par1Vec3.zCoord);
					l = (int) (vec32.xCoord = (double) MathHelper.floor_double(par1Vec3.xCoord));
					ld = par1Vec3.xCoord;
					if (b0 == 5) {
						--l;
						--ld;
						++vec32.xCoord;
					}

					i1 = (int) (vec32.yCoord = (double) MathHelper.floor_double(par1Vec3.yCoord));
					i1d = par1Vec3.yCoord;
					if (b0 == 1) {
						--i1;
						--i1d;
						++vec32.yCoord;
					}

					j1 = (int) (vec32.zCoord = (double) MathHelper.floor_double(par1Vec3.zCoord));
					j1d = par1Vec3.zCoord;
					if (b0 == 3) {
						--j1;
						--j1d;
						++vec32.zCoord;
					}

					int j2 = world.getBlockMetadata(l, i1, j1);
					Block block1 = world.getBlockId(l, i1, j1) == 0 ? null : Block.blocksList[world.getBlockId(l, i1, j1)];
//					if (block1 instanceof BlockTarget && !hasHitTarget) {
//						hasHitTarget = true;
//						if (thePlayer.getDistance((double) l, (double) i1, (double) j1) > 9.0D) {
//							int foo = l + i1 + j1;
//							DeciPlayerData playerData = DeciPlayerData.get(thePlayer);
//							if (playerData.getTargetLastEntity() != foo) {
//								playerData.setTargetTimer(20);
//								playerData.setTargetCurrentScore(playerData.getTargetCurrentScore() + 1);
//								playerData.setTargetLastEntity(foo);
//								thePlayer.worldObj.playSoundEffect((double) l, (double) i1, (double) j1, "deci:block.target.ding", 2.0F, 1.0F);
//							}
//						} else {
//							thePlayer.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "You must be 9+ blocks away!"));
//						}
//					}

					if (block1 instanceof BlockButton) {
						block1.onBlockActivated(world, l, i1, j1, thePlayer, 1, 0.0F, 0.0F, 0.0F);
					}

					Material m = world.getBlockId(l, i1, j1) == 0 ? Material.air : Block.blocksList[world.getBlockId(l, i1, j1)].blockMaterial;
					float maxPen = (this.damage > 14.0F ? 1.0F : 0.5F) + (float) (this.fire ? 0 : 0);
					MovingObjectPosition mop = null;
					if (block1 != null) {
						mop = block1.collisionRayTrace(world, l, i1, j1, par1Vec3, par2Vec3);
						if (block1.blockID != 0 && mop != null && mop.hitVec != null) {
							int loop = 4;

							for (int i2 = 0; i2 < loop; ++i2) {
								PacketDispatcher.sendPacketToAllAround(mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord, 250.0D, world.provider.dimensionId, new PacketParticle("explode", mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord).makePacket());
							}

							PacketDispatcher.sendPacketToAllAround(mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord, 250.0D, world.provider.dimensionId, new PacketParticle("blockcrack_" + block1.blockID + "_" + world.getBlockMetadata(l, i1, j1), mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord).makePacket());
						}
					}

					if (block1 != null && !block1.isOpaqueCube() && !hasPlayedOnce) {
						if (m.equals(Material.water)) {
							hasPlayedOnce = true;
							world.playSoundEffect((double) l, (double) i1, (double) j1, "deci:bullet.bounce.water", 1.0F, 1.0F);
							isInWater = true;
						} else if (m.equals(Material.glass)) {
							hasPlayedOnce = true;
							world.playSoundEffect((double) l, (double) i1, (double) j1, "deci:bullet.bounce.glass", 1.0F, 1.0F);
							playSnap(world, l, i1, j1);
						} else if (m.equals(Material.iron)) {
							hasPlayedOnce = true;
							world.playSoundEffect((double) l, (double) i1, (double) j1, "deci:bullet.bounce.metal", 1.0F, 1.0F);
							playSnap(world, l, i1, j1);
						} else if (m.equals(Material.leaves)) {
							hasPlayedOnce = true;
							world.playSoundEffect((double) l, (double) i1, (double) j1, "deci:bullet.bounce.dirt", 1.0F, 1.0F);
							playSnap(world, l, i1, j1);
						} else if (m.equals(Material.air)) {
							isInWater = false;
						}

						block1 = null;
					}

					if (block1 != null && block1.blockID != 0 && (block1.canCollideCheck(j2, false) || m == Material.water)) {
						if (!hasHitOnce) {
							hasHitOnce = true;
							Random r = new Random();
							int Low = 0;
							int High = 10;
							int Result = r.nextInt(High - Low) + Low;
							if (blockID > 500) {
								blockID = 0;
							}
						}

						if (mop != null) {
							if ((m == Material.iron || m == Material.anvil || m == Material.wood || m == Material.rock || m == Material.ground || m == Material.grass || m == Material.sand || m == Material.water || m == Material.glass || block1 == Block.bedrock) && block1 != Block.blockGold && this.gun && !this.shotgun) {
								if (m == Material.iron) {
									world.playSoundEffect((double) l, (double) i1, (double) j1, "metro:bullet_metal", 0.5F, 1.0F);
								} else if (m == Material.wood) {
									world.playSoundEffect((double) l, (double) i1, (double) j1, "metro:bullet_wood", 0.5F, 1.0F);
								} else if (m == Material.rock) {
									world.playSoundEffect((double) l, (double) i1, (double) j1, "metro:bullet_stone", 1.0F, 1.0F);
								} else if (m == Material.grass || m == Material.ground || m == Material.sand) {
									world.playSoundEffect((double) l, (double) i1, (double) j1, "metro:bullet_dirt", 1.0F, 1.0F);
								} else if (m == Material.water) {
									world.playSoundEffect((double) l, (double) i1, (double) j1, "metro:bullet_water", 1.0F, 1.0F);
								} else if (m == Material.glass) {
									world.playSoundEffect((double) l, (double) i1, (double) j1, "metro:bullet_glass", 1.0F, 1.0F);
								}

								if (!isInWater) {
									playSnap(world, l, i1, j1);
								}
							}

							return mop;
						}
					}
				}

				return null;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}
