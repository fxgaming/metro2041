package by.fxg.metro2041.common.slot;

import by.fxg.metro2041.util.IExtItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotExtended extends SlotInv {
   public SlotExtended(SlotInv.Access access, IInventory par1iInventory, int SlotIndex, int X, int Y) {
      super(access, par1iInventory, SlotIndex, X, Y);
   }

   public boolean accept(ItemStack stack) {
      return stack != null && stack.getItem() != null && stack.getItem() instanceof IExtItem && ((IExtItem)stack.getItem()).getSlot() == this.getSlotIndex() && this.access != SlotInv.Access.NONE;
   }
}
