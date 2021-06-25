package by.fxg.metro2041.common.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotDisabled extends SlotInv {
   public SlotDisabled(IInventory par1iInventory, int SlotIndex, int X, int Y) {
      super(SlotInv.Access.NONE, par1iInventory, SlotIndex, X, Y);
   }

   public boolean accept(ItemStack stack) {
      return false;
   }
}
