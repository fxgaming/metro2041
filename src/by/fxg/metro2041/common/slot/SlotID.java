package by.fxg.metro2041.common.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotID extends SlotInv {
	Integer id;
	public SlotID(IInventory par1iInventory, int SlotIndex, int X, int Y, int id) {
		super(SlotInv.Access.IO, par1iInventory, SlotIndex, X, Y);
		this.id = id;
	}

	public boolean accept(ItemStack stack) {
		if (id == 0) {
			return false;
		} else if (id == 1) {
			if (stack.itemID >= 5756 && stack.itemID <= 5760) {
				return true;
			}
		} else if (id == 2) {
			if (stack.itemID == 5761) {
				return true;
			}
		} else if (id == 3) {
			if (stack.itemID >= 5762 && stack.itemID <= 5766) {
				return true;
			}
		} else if (id == 4) {
			if (stack.itemID == 263 || stack.itemID == 173) {
				return true;
			}
		}
		return false;
	}
}
