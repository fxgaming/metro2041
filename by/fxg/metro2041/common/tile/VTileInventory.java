package by.fxg.metro2041.common.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class VTileInventory extends VTileBase implements IInventory {
	public ItemStack[] items;
	private String InvStringName;

	@Override
	public int getSizeInventory() {
		return this.items.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return this.items[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (this.items[i] != null) {
			ItemStack itemstack;
			if (this.items[i].stackSize <= j) {
				itemstack = this.items[i];
				this.items[i] = null;
				return itemstack;
			} else {
				itemstack = this.items[i].splitStack(j);
				if (this.items[i].stackSize == 0) {
					this.items[i] = null;
				}
				return itemstack; 
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if (this.items[i] != null) {
			ItemStack itemstack = this.items[i];
			this.items[i] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		if (i >= 0 && i < this.items.length) {
			this.items[i] = itemstack;
		}
		if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName() {
		return this.isInvNameLocalized() ? this.InvStringName : this.getClass().getSimpleName() + ".container";
	}

	@Override
	public boolean isInvNameLocalized() {
		return this.InvStringName != null && this.InvStringName.length() > 0;
	}

	public void setCustomName(String par1Str) {
		this.InvStringName = par1Str;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items");
		this.items = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");
			if (b0 >= 0 && b0 < this.items.length) {
				this.items[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
		if (par1NBTTagCompound.hasKey("CustomName")) {
			this.InvStringName = par1NBTTagCompound.getString("CustomName");
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.items.length; ++i) {
			if (this.items[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.items[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		par1NBTTagCompound.setTag("Items", nbttaglist);
		if (this.isInvNameLocalized()) {
			par1NBTTagCompound.setString("CustomName", this.InvStringName);
		}

	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return super.worldObj.getBlockTileEntity(super.xCoord, super.yCoord, super.zCoord) != this ? false
				: entityplayer.getDistanceSq((double) super.xCoord + 0.5D, (double) super.yCoord + 0.5D,
						(double) super.zCoord + 0.5D) <= 64.0D;
	}

	public void openChest() {
	}

	public void closeChest() {
	}

	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return false;
	}
}
