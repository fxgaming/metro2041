package by.fxg.metro2041.common.player.container;

import by.fxg.metro2041.common.container.BaseContainer;
import by.fxg.metro2041.common.player.ExtendedPlayer;
import by.fxg.metro2041.common.slot.SlotExtended;
import by.fxg.metro2041.common.slot.SlotInv.Access;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerExtended extends BaseContainer {
	public InventoryPlayer invPlayer;

	public ContainerExtended(InventoryPlayer var1, ExtendedPlayer var2) {
		this.invPlayer = var1;
//		this.addSlotToContainer(new SlotExtended(Access.NONE, var2.extInv, 0, 70, 4));
//		this.addSlotToContainer(new SlotExtended(Access.NONE, var2.extInv, 1, 70, 22));
//		this.addSlotToContainer(new SlotExtended(Access.NONE, var2.extInv, 2, 70, 40));
//		this.addSlotToContainer(new SlotExtended(Access.NONE, var2.extInv, 3, 70, 58));
//		this.addSlotToContainer(new SlotExtended(Access.NONE, var2.extInv, 4, 156, 4));
//		this.addSlotToContainer(new SlotExtended(Access.NONE, var2.extInv, 5, 156, 22));
//		this.addSlotToContainer(new SlotExtended(Access.NONE, var2.extInv, 6, 156, 40));
//		this.addSlotToContainer(new SlotExtended(Access.NONE, var2.extInv, 7, 156, 58));

		int var3;
		for (var3 = 0; var3 < 3; ++var3) {
			for (int var4 = 0; var4 < 9; ++var4) {
				this.addSlotToContainer(new Slot(var1, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
			}
		}

		for (var3 = 0; var3 < 9; ++var3) {
			this.addSlotToContainer(new Slot(var1, var3, 8 + var3 * 18, 142));
		}
	}

	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}

	@Override
	public int getMergeMaxSlotIndex(int var1) {
		return 0;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) {
		return null;
	}
}
