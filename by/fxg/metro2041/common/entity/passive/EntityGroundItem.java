package by.fxg.metro2041.common.entity.passive;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import java.util.Iterator;

import by.fxg.garbageapi.util.FromTo;
import by.fxg.metro2041.common.item.gun.EnumAmmoType;
import by.fxg.metro2041.common.player.ExtendedPlayer;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

public class EntityGroundItem extends Entity {
	private int entityAge;
	private int entityAgeMax;
	private final int dataWatcherItemStack;
	private boolean isClicked;
	public float renderDirection;
	private ItemStack stackInstance;

	public EntityGroundItem(World par1World) {
		super(par1World);
		this.entityAge = 0;
		this.entityAgeMax = 2400;
		this.dataWatcherItemStack = 10;
		this.isClicked = false;
		this.renderDirection = 0.0F;
		this.stackInstance = null;
		this.setSize(0.25F, 0.25F);
		this.renderDirection = (float) super.rand.nextInt(360);
	}

	public EntityGroundItem(World par1World, double par2, double par3, double par4) {
		this(par1World);
		this.setPosition(par2, par3, par4);
	}

	protected void entityInit() {
		DataWatcher var10000 = this.getDataWatcher();
		this.getClass();
		var10000.addObjectByDataType(10, 5);
	}

	public void onUpdate() {
		super.onUpdate();
		if (++this.entityAge > this.entityAgeMax) {
			this.setDead();
		}

		if (FMLCommonHandler.instance().getSide() == Side.SERVER) {
			ItemStack stack = this.getEntityItem();
			int var1 = stack.itemID - 256;
			if (stack.getItem() instanceof ItemBlock) {
				this.setDead();
			}
		}

		super.prevPosX = super.posX;
		super.prevPosY = super.posY;
		super.prevPosZ = super.posZ;
		super.motionY -= 0.03999999910593033D;
		super.noClip = this.pushOutOfBlocks(super.posX, (super.boundingBox.minY + super.boundingBox.maxY) / 2.0D, super.posZ);
		this.moveEntity(super.motionX, super.motionY, super.motionZ);
		double f;
		if (this.entityAge % 40 == 0 && !super.worldObj.isRemote) {
			f = 2.0D;
			Iterator iterator = super.worldObj.getEntitiesWithinAABB(EntityGroundItem.class, super.boundingBox.expand(f, 0.0D, f)).iterator();

			while (iterator.hasNext()) {
				EntityGroundItem entityitem = (EntityGroundItem) iterator.next();
				if (entityitem != this && !entityitem.isDead && !super.isDead) {
					ItemStack itemstack = this.getEntityItem();
					ItemStack itemstackOther = entityitem.getEntityItem();
					if (itemstack.getItem().itemID == itemstackOther.getItem().itemID && itemstack.stackSize < itemstack.getMaxStackSize() && itemstack.stackSize + itemstackOther.stackSize <= itemstack.getMaxStackSize()) {
						itemstack.stackSize += itemstackOther.stackSize;
						this.entityAge = 0;
						this.setEntityItemStack(itemstack);
						entityitem.setDead();
					}
				}
			}
		}

		f = 0.9800000190734863D;
		super.motionX *= f;
		super.motionY *= 0.9800000190734863D;
		super.motionZ *= f;
		if (super.onGround) {
			super.motionX = 0.0D;
			super.motionY = 0.0D;
			super.motionZ = 0.0D;
		}

	}

	public boolean canBeCollidedWith() {
		return true;
	}

	public boolean interactFirst(EntityPlayer par1EntityPlayer) {
		if (!super.worldObj.isRemote && !this.isClicked && this.entityAge > 10 && this.canPickupItem(par1EntityPlayer)) {
			this.isClicked = true;
			EntityItemPickupEvent event = new EntityItemPickupEvent(par1EntityPlayer, new EntityItem(super.worldObj, super.posX, super.posY, super.posZ, this.getEntityItem()));
			MinecraftForge.EVENT_BUS.post(event);
			if (event.isCanceled()) {
				return false;
			} else {
				if (event.getResult() != Result.ALLOW) {
					if (FromTo.Int(this.getEntityItem().itemID, 6056, 6063)) {
						EnumAmmoType ammo = EnumAmmoType.values()[this.getEntityItem().itemID - 6056];
						ExtendedPlayer ext = ExtendedPlayer.get(par1EntityPlayer);
						if (false) {//ext.virtAmmo.getAmmo(ext.extData, ammo) < ext.virtAmmo.getMaxAmmo(ext.extData, ammo)) {
//							if (ext.virtAmmo.setAmmo(ext.extData, ammo, this.getEntityItem().stackSize) > 0) {
//								int toFill = this.getEntityItem().stackSize;
//								int toReject = 0;
//								toReject = ext.virtAmmo.setAmmo(ext.extData, ammo, toFill);
//
//								ItemStack copyof = this.getEntityItem().copy();
//								copyof.stackSize = toReject;
//								EntityGroundItem egi = new EntityGroundItem(this.worldObj, this.posX, this.posY, this.posZ);
//								egi.setEntityItemStack(copyof);
//								this.worldObj.spawnEntityInWorld(egi);
//								ext.virtAmmo.setAmmo(ext.extData, ammo, this.getEntityItem().stackSize - toReject);
//								System.out.println("гандона" + toReject + " " + this.getEntityItem().stackSize);
//								this.playSound("random.pop", 0.2F, ((super.rand.nextFloat() - super.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
//								this.setDead();
//								return true;
//							} else {
//								this.playSound("random.pop", 0.2F, ((super.rand.nextFloat() - super.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
//								this.setDead();
//								return true;
//							}
						} else {
							ItemStack copyof = this.getEntityItem().copy();
							EntityGroundItem egi = new EntityGroundItem(this.worldObj, this.posX, this.posY, this.posZ);
							egi.setEntityItemStack(copyof);
							this.worldObj.spawnEntityInWorld(egi);
							this.setDead();
							return false;
						}
					} else {
						this.playSound("random.pop", 0.2F, ((super.rand.nextFloat() - super.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
						par1EntityPlayer.inventory.addItemStackToInventory(this.getEntityItem());
					}
				}
				this.setDead();
				return true;
			}
		} else {
			return true;
		}
	}

	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		this.entityAge = nbttagcompound.getInteger("age");
		NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("itemstack");
		this.setEntityItemStack(ItemStack.loadItemStackFromNBT(nbttagcompound1));
		DataWatcher var10000 = this.getDataWatcher();
		this.getClass();
		ItemStack item = var10000.getWatchableObjectItemStack(10);
		if (item == null || item.stackSize <= 0) {
			this.setDead();
		}

	}

	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		nbttagcompound.setInteger("age", this.entityAge);
		if (this.getEntityItem() != null) {
			nbttagcompound.setCompoundTag("itemstack", this.getEntityItem().writeToNBT(new NBTTagCompound()));
		}

	}

	public boolean canPickupItem(EntityPlayer par1EntityPlayer) {
		InventoryPlayer inv = par1EntityPlayer.inventory;
		ItemStack stack = this.getEntityItem();

		for (int i = 0; i < inv.mainInventory.length - 1; ++i) {
			if (inv.mainInventory[i] == null || inv.mainInventory[i].itemID == stack.itemID && inv.mainInventory[i].stackSize < inv.mainInventory[i].getMaxStackSize()) {
				return true;
			}
		}

		return false;
	}

	public ItemStack getEntityItem() {
		DataWatcher var10000 = this.getDataWatcher();
		this.getClass();
		ItemStack itemstack = var10000.getWatchableObjectItemStack(10);
		if (itemstack == null) {
			if (super.worldObj != null) {
				super.worldObj.getWorldLogAgent().logSevere("Item entity " + super.entityId + " has no item?!");
			}
			return new ItemStack(Item.brick);
		} else {
			return itemstack;
		}
	}

	public void setEntityItemStack(ItemStack par1ItemStack) {
		DataWatcher var10000 = this.getDataWatcher();
		this.getClass();
		var10000.updateObject(10, par1ItemStack);
		var10000 = this.getDataWatcher();
		this.getClass();
		var10000.setObjectWatched(10);
	}
}
