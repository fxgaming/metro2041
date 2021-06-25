package by.fxg.metro2041.common.item.gun;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import by.fxg.metro2041.Metro;
import by.fxg.metro2041.client.anim.GunAnimation;
import by.fxg.metro2041.client.model.VModel;
import by.fxg.metro2041.common.item.ItemManager;
import by.fxg.metro2041.common.item.VItem;
import by.fxg.metro2041.common.network.gun.PacketGunReload;
import by.fxg.metro2041.common.network.gun.PacketGunTrigger;
import by.fxg.metro2041.common.player.ExtendedData;
import by.fxg.metro2041.common.player.ExtendedPlayer;
import by.fxg.metro2041.util.Dual;
import by.fxg.metro2041.util.Utils;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * GLOBAL NBT VALUES
 *	time - before next shoot
 *	recoil
 *	getMagazin - returns ordinal of mag
 *	ammo[int] - type, count  
 */
public class ItemGun extends Item {
	public static float antiRecoil;
	private static boolean mouseDown;
	private static boolean mouseRDown;
	public EnumMagazinType magType;
	public String unlocalizedName;
	public EnumGunType gunType;
	public EnumShotType shotType;
	public EnumShotParts shotParts;
	//0 - Reload, 1 - Shot
	public GunAnimation[] animation;
	public GunAnimation currentAnimation;
	public GunAnimation currentAnimation2;
	
	// stats
	public float accuracy;
	public float recoil;
	public int reloadTime;
	public int damage;
	public long shootTime;

	// ticks

	private double tickFire;
	private double delayFire;
	private static boolean triggerHeld;
	private static boolean lastTriggerHeld;
	private static boolean reloadKey;
	private static boolean lastReloadKey;
	private static boolean firemodeKey;
	private static boolean lastFiremodeKey;
	private static boolean aimKey;
	private static boolean lastAimKey;
	private static int aimDelay;
	private int lastInventorySlot;
	private Random gunRandom = new Random();

	@SideOnly(Side.CLIENT)
	public VModel weaponModel;

	public ItemGun(int id, String unlocalizedName, EnumGunType egt, EnumShotType est, EnumShotParts esp) {
		super(id);
		this.unlocalizedName = unlocalizedName;
		this.gunType = egt;
		this.shotType = est;
		this.shotParts = esp;
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.setMaxStackSize(1);
		this.setFull3D();
	}

	public ItemGun setGun(int damage, int reloadTime, float accuracy, float recoil, long shootTime) {
		this.damage = damage;
		this.reloadTime = reloadTime;
		this.accuracy = accuracy;
		this.recoil = recoil;
		this.shootTime = shootTime;
		return this;
	}

	public ItemGun setMag(EnumMagazinType par1) {
		this.magType = par1;
		return this;
	}
	
	public ItemGun setAnimation(GunAnimation... par1) {
		this.animation = par1;
		return this;
	}

	public static boolean isIndoors(EntityLivingBase givenEntity) {
		return givenEntity.worldObj.getPrecipitationHeight(MathHelper.floor_double(givenEntity.posX), MathHelper.floor_double(givenEntity.posZ)) > MathHelper.floor_double(givenEntity.posY);
	}

//	@SideOnly(Side.CLIENT)
//	public void fireClient(ItemStack stack, EntityPlayer player) {
//		DeciPlayerData data = DeciPlayerData.get(player);
//		if (!data.getFiring()) {
//			if (this.gunStats.fireModes[fireMode] == GunStats.FireModes.auto || this.gunStats.fireModes[fireMode] == GunStats.FireModes.burst) {
//				data.setFiring(true);
//			}
//			decimation.getPacketChannel().sendToServer(new Message_Gun_Fire());
//		}
//
//		this.onFireGun(stack, player);
//		data.setDelay(this.gunStats.delay[fireMode]);
//		data.setSmoke(data.getSmoke() + 5);
//		this.handleRecoil(stack, player);
//	}

	public void fireServer(ItemStack i, EntityPlayer p) {
		if (Metro.isServer) {
			if (Utils.getNBT(i).getInteger("ammo") > 0 && Utils.getNBT(i).getLong("time") < System.currentTimeMillis()) {
				AttackHelper ah = new AttackHelper(this);
				float recoil = Utils.getNBT(i).getFloat("recoil");
				float pitchOff = (this.accuracy + recoil) * this.gunRandom.nextFloat() * (float) (this.gunRandom.nextInt(2) == 0 ? -1 : 1) / 10.0F;
				float yawOff = (this.accuracy + recoil) * this.gunRandom.nextFloat() * (float) (this.gunRandom.nextInt(2) == 0 ? -1 : 1) / 10.0F;
				double deltaX = (double) (-MathHelper.sin(p.rotationYaw / 180.0F * 3.1415927F));
				double deltaZ = (double) MathHelper.cos(p.rotationYaw / 180.0F * 3.1415927F);
				int modifier = 15;
				double deltaX1 = (double) (-MathHelper.sin((p.rotationYaw + (float) modifier) / 180.0F * 3.1415927F));
				double deltaZ1 = (double) MathHelper.cos((p.rotationYaw + (float) modifier) / 180.0F * 3.1415927F);
				ah.attack(this, p, 250.0D, pitchOff, yawOff);
				//p.worldObj.playSoundEffect(p.posX, p.posY, p.posZ, "metro:" + this.unlocalizedName + "_fire", 1.5F, 1.0F);
				//p.worldObj.playSoundEffect(p.posX, p.posY, p.posZ, "metro:" + this.unlocalizedName + "_fire_distant", 6.5F, 1.0F);
				Utils.getNBT(i).setLong("time", System.currentTimeMillis() + this.shootTime);
				Utils.getNBT(i).setFloat("recoil", recoil + this.recoil);
				Utils.getNBT(i).setInteger("ammo", Utils.getNBT(i).getInteger("ammo") - 1);
			}
		}
	}
	
	public void onUpdate(ItemStack i, World w, Entity e, int ii, boolean f) {
		EntityPlayer p = (EntityPlayer) e;
		if (p != null && p.getCurrentEquippedItem() != null && p.getCurrentEquippedItem().itemID == i.itemID && !w.isRemote) {
			int var1 = Utils.getNBT(i).getInteger("reload");
			if (var1 > 0) {
				--var1;
				Utils.getNBT(i).setInteger("reload", var1);
			}
			if (Utils.getNBT(i).getFloat("recoil") > 0.0F) {
				Utils.getNBT(i).setFloat("recoil", Utils.getNBT(i).getFloat("recoil") - (Utils.getNBT(i).getFloat("recoil") / 10.0F));
				if (Utils.getNBT(i).getFloat("recoil") < 0.0F) Utils.getNBT(i).setFloat("recoil", 0.0F);
			}
		}
	}

	public void onReload(EntityPlayer p, World w, ItemStack i) {
		if (Metro.isServer) {
			List<Dual<Integer, ItemStack>> mags = this.searchMags(p);
			if (Utils.getNBT(i).getInteger("reload") <= 0 && mags.size() == 0 ? (this.getMagazin(i) != -1) : true) {
				if (mags.size() > 0) {
					ItemStack mag = mags.get(0).right;
					ItemStack injMag = ItemMagazin.mags.containsKey(this.getMagazin(i)) ? new ItemStack(ItemMagazin.mags.get(this.getMagazin(i))) : null;
					if (this.getAmmo(i) > 0) {
						Utils.getNBT(injMag).setInteger("ammo", this.getAmmo(i));
						this.setAmmo(i, 0);
					}
					this.setMagazin(i, ((ItemMagazin)mag.getItem()).type.ordinal());
					this.setAmmo(i, Utils.getNBT(mag).getInteger("ammo"));
					p.inventory.mainInventory[mags.get(0).left] = injMag;
					Utils.getNBT(i).setInteger("reload", this.reloadTime);
					w.playSoundAtEntity(p, "metro:" + this.unlocalizedName + "_reload", 0.25F, 1.0F);
				} else {
					int slot = -1;
					for (int i$ = 0; i$ != p.inventory.mainInventory.length && slot == -1; i$++) if (p.inventory.mainInventory[i$] == null) slot = i$;
					ItemStack injMag = ItemMagazin.mags.containsKey(this.getMagazin(i)) ? new ItemStack(ItemMagazin.mags.get(this.getMagazin(i))) : null;
					if (this.getAmmo(i) > 0) {
						Utils.getNBT(injMag).setInteger("ammo", this.getAmmo(i));
					}
					this.setMagazin(i, -1);
					this.setAmmo(i, 0);
					if (slot == -1) {
						p.dropPlayerItem(injMag);
					} else {
						p.inventory.mainInventory[slot] = injMag;
					}
					Utils.getNBT(i).setInteger("reload", this.reloadTime / 2);
					w.playSoundAtEntity(p, "metro:" + this.unlocalizedName + "_unload", 0.25F, 1.0F);
					if (slot == -1 && !p.isSneaking()) {
						w.playSoundAtEntity(p, "metro:" + ((ItemMagazin)injMag.getItem()).type.toString() + "_drop", 0.25F, 0.75F);
					}
				}
			}
		}
	}

	public synchronized void onClientTick(World w, EntityPlayer p, ItemStack i) {
		Minecraft mc = FMLClientHandler.instance().getClient();
		if (i != null && i.getItem() instanceof ItemGun && mc.currentScreen == null) {
			lastTriggerHeld = triggerHeld;
			triggerHeld = Mouse.isButtonDown(0);
			lastReloadKey = reloadKey;
			reloadKey = Keyboard.isKeyDown(19);// R
			lastAimKey = aimKey;
			aimKey = Mouse.isButtonDown(1);
			ExtendedData data = ExtendedPlayer.get(p).extData;
			int aimDelayMax = 6;
			if (aimDelay <= aimDelayMax) {
				++aimDelay;
			}

			if (aimKey && !lastAimKey && aimDelay > aimDelayMax) {
				data.isAiming = !data.isAiming;
				aimDelay = 0;
			}

			if (reloadKey && !lastReloadKey) {
				this.onClientReload(p, i);
			}

			if (this.tickFire < this.delayFire) {
				++this.tickFire;
			}

			if (Utils.getNBT(i).getInteger("ammo") < 0 || Utils.getNBT(i).getInteger("reload") > 1) {
				if (this.tickFire >= this.delayFire && triggerHeld && !lastTriggerHeld) {
					Minecraft.getMinecraft().sndManager.playSoundFX("metro:" + this.unlocalizedName + ".empty", 1.0F, 1.0F);
					this.tickFire = 0.0D;
				}
				return;
			}

			if (this.shotType == EnumShotType.AUTOSHOT || this.shotType == EnumShotType.AUTO) {
				if (triggerHeld && this.tickFire >= this.delayFire) {
					this.onClientTriggerPulled(p, i);
					this.tickFire = 0.0D;
				}
			} else if (this.shotType == EnumShotType.SEMI || this.shotType == EnumShotType.SHOT || this.shotType == EnumShotType.MULTISHOT) {
				if (this.tickFire >= this.delayFire && triggerHeld && !lastTriggerHeld) {
					this.onClientTriggerPulled(p, i);
					this.tickFire = 0.0D;
				}
			}
		}
	}

	public void onClientTriggerPulled(EntityPlayer p, ItemStack i) {
		if (Utils.getNBT(i).getInteger("ammo") > 0) {
			if (this.animation[1] != null && Utils.getNBT(i).getLong("time") < System.currentTimeMillis()) {
				try {
					PacketDispatcher.sendPacketToServer(new PacketGunTrigger().makePacket());
					this.currentAnimation = this.animation[1].getClass().newInstance();
					Metro.instance.clientManager.clientAnimationManager.instance.setGunAnimation(this.currentAnimation);
				} catch (Exception e) {
				}
			}
		}
	}

	public void onClientReload(EntityPlayer p, ItemStack i) {
		if (Utils.getNBT(i).getInteger("reload") <= 0) {
			try {
				PacketDispatcher.sendPacketToServer(new PacketGunReload().makePacket());
				this.currentAnimation2 = this.animation[1].getClass().newInstance();
				Metro.instance.clientManager.clientAnimationManager.instance.setGunAnimation(this.currentAnimation2);
				ExtendedPlayer.get(p).extData.isAiming = false;
			} catch (Exception e) {
			}
		}
	}

	public int getAmmo(ItemStack stack) {
		return Utils.getNBT(stack).getInteger("ammo");
	}
	
	public void setAmmo(ItemStack stack, int i) {
		Utils.getNBT(stack).setInteger("ammo", i);
	}
	
	public int getMagazin(ItemStack stack) {
		return Utils.getNBT(stack).getInteger("magazin");
	}
	
	public void setMagazin(ItemStack stack, int i) {
		Utils.getNBT(stack).setInteger("magazin", i);
	}
	
	public List<Dual<Integer, ItemStack>> searchMags(EntityPlayer e) {
		if (Metro.isServer) {
			List<Dual<Integer, ItemStack>> stacks = new ArrayList<Dual<Integer, ItemStack>>();
			InventoryPlayer inv = e.inventory;
			for (int i = 0; i != inv.mainInventory.length; i++) {
				ItemStack is = inv.mainInventory[i];
				if (is != null && is.getItem() instanceof ItemMagazin) {
					if (((ItemMagazin)is.getItem()).type == this.magType) {
						stacks.add(new Dual(i, is));
					}
				}
			}
			if (stacks.size() > 1) {
				stacks.sort(Utils.magazinComparator);
			}
			return stacks;
		} else {
			return null;
		}
	}

	public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack) {
		return true;
	}
}
