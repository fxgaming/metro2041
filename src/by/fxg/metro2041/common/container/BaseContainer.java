package by.fxg.metro2041.common.container;

import java.util.Iterator;
import by.fxg.metro2041.common.slot.SlotInv;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class BaseContainer extends Container {
	private int CountCustomSlots = 0;
	protected int CountSlots = 0;
	public final IInventory base;

	public BaseContainer(IInventory base) {
		this.base = base;
	}

	public BaseContainer() {
		this.base = null;
	}
	
	public void callPlayerInventory(int y_) {
		int i$;
		for (i$ = 0; i$ < 3; ++i$) {
			for (int var51 = 0; var51 < 9; ++var51) {
				this.addSlotToContainer(new Slot(this.base, var51 + i$ * 9 + 9, 8 + var51 * 18, y_ + i$ * 18));
			}
		}
		for (i$ = 0; i$ < 9; ++i$) {
			this.addSlotToContainer(new Slot(this.base, i$, 8 + i$ * 18, y_ + 58));
		}
		Iterator var51 = super.inventorySlots.iterator();
		while (var51.hasNext()) {
			Object var5 = var51.next();
			if (var5 != null && var5 instanceof SlotInv) {
				++this.CountCustomSlots;
			}
		}
		this.CountSlots = super.inventorySlots.size();
	}
	
	public void init() {
		Iterator var51 = super.inventorySlots.iterator();
		while (var51.hasNext()) {
			Object var5 = var51.next();
			if (var5 != null && var5 instanceof SlotInv) {
				++this.CountCustomSlots;
			}
		}
		this.CountSlots = super.inventorySlots.size();
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		ItemStack itemstack = null;
		Slot slot = (Slot) super.inventorySlots.get(par2);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack2 = slot.getStack();
			itemstack = itemstack2.copy();
			if (par2 < this.CountCustomSlots) {
				if (!this.mergeItemStack(itemstack2, this.CountCustomSlots, this.CountSlots, false)) {
					return null;
				}
			} else {
				SlotInv s;
				int i;
				if (par2 >= this.CountCustomSlots && par2 < this.CountSlots - 9) {
					s = null;
					for (i = 0; this.CountCustomSlots > i; ++i) {
						s = (SlotInv) super.inventorySlots.get(i);
						if (s.canInput() && s.accept(itemstack2)) {
							if (!s.getHasStack() || s.getStack().isItemEqual(itemstack2) && ItemStack.areItemStackTagsEqual(s.getStack(), itemstack2)) {
								break;
							}
							s = null;
						} else {
							s = null;
						}
					}
					if (!this.mergeItemStack(itemstack2, s == null ? this.CountSlots - 9 : s.getSlotIndex(), s == null ? this.CountSlots : this.getMergeMaxSlotIndex(s.getSlotIndex()), false)) {
						return null;
					}
				} else if (par2 >= this.CountSlots - 9 && par2 < this.CountSlots) {
					s = null;
					for (i = 0; this.CountCustomSlots > i; ++i) {
						s = (SlotInv) super.inventorySlots.get(i);
						if (s.canInput() && s.accept(itemstack2)) {
							if (!s.getHasStack() || s.getStack().isItemEqual(itemstack2) && ItemStack.areItemStackTagsEqual(s.getStack(), itemstack2)) {
								break;
							}
							s = null;
						} else {
							s = null;
						}
					}
					if (!this.mergeItemStack(itemstack2, s == null ? this.CountCustomSlots : s.getSlotIndex(), s == null ? this.CountSlots - 9 : this.getMergeMaxSlotIndex(s.getSlotIndex()), false)) {
						return null;
					}
				}
			}
			if (itemstack2.stackSize <= 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
			if (itemstack2.stackSize == itemstack.stackSize) {
				return null;
			}
			slot.onPickupFromSlot(par1EntityPlayer, itemstack2);
		}
		return itemstack;
	}

	public abstract int getMergeMaxSlotIndex(int var1);

	public void updateProgressBar(int index, int value) {
	}

	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.base.isUseableByPlayer(entityplayer);
	}
}
