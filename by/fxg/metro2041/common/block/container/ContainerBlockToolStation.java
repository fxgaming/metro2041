package by.fxg.metro2041.common.block.container;

import by.fxg.metro2041.common.slot.SlotID;
import by.fxg.metro2041.common.tile.TileToolStation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;

public class ContainerBlockToolStation extends ContainerBase {
   private TileToolStation tileentity;
   private boolean work = false;
   private int time = 0;
   private int item1 = 0;
   private int item2 = 0;

   public ContainerBlockToolStation(InventoryPlayer inventoryplayer, TileToolStation tileentity) {
      super(inventoryplayer);
      this.tileentity = tileentity;
      SlotID slot = new SlotID(tileentity, 0, 22, 23, 3);
      this.addSlotToContainer(slot);
      slot = new SlotID(tileentity, 1, 40, 23, 3);
      this.addSlotToContainer(slot);
      slot = new SlotID(tileentity, 2, 58, 23, 3);
      this.addSlotToContainer(slot);
      
      slot = new SlotID(tileentity, 3, 53, 44, 4);
      this.addSlotToContainer(slot);
      slot = new SlotID(tileentity, 4, 129, 34, 0);
      this.addSlotToContainer(slot);
      this.callPlayerInventory(84);
   }

   public void addCraftingToCrafters(ICrafting par1ICrafting) {
      super.addCraftingToCrafters(par1ICrafting);
      par1ICrafting.sendProgressBarUpdate(this, 0, this.tileentity.isWorking ? 1 : 0);
      par1ICrafting.sendProgressBarUpdate(this, 1, this.tileentity.time);
      par1ICrafting.sendProgressBarUpdate(this, 2, this.tileentity.item1);
      par1ICrafting.sendProgressBarUpdate(this, 3, this.tileentity.item2);
   }

   public void detectAndSendChanges() {
      super.detectAndSendChanges();
      for(int i = 0; i < super.crafters.size(); ++i) {
         ICrafting icrafting = (ICrafting)super.crafters.get(i);
         if(this.work != this.tileentity.isWorking) {
            icrafting.sendProgressBarUpdate(this, 0, this.tileentity.isWorking ? 1 : 0);
         }
         if(this.time != this.tileentity.time) {
             icrafting.sendProgressBarUpdate(this, 1, this.tileentity.time);
          }
         
         if(this.item1 != this.tileentity.item1) {
            icrafting.sendProgressBarUpdate(this, 2, this.tileentity.item1);
         }
         
         if(this.item2 != this.tileentity.item2) {
             icrafting.sendProgressBarUpdate(this, 3, this.tileentity.item2);
          }
      }

      this.work = this.tileentity.isWorking;
      this.time = this.tileentity.time;
      this.item1 = this.tileentity.item1;
      this.item2 = this.tileentity.item2;
   }

	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		if (par1 == 0) {
			this.tileentity.isWorking = par2 == 1;
		}
		if (par1 == 1) {
			this.tileentity.time = par2;
		}
		if (par1 == 2) {
			this.tileentity.item1 = par2;
		}
		if (par1 == 3) {
			this.tileentity.item2 = par2;
		}
	}

   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
      return this.tileentity.isUseableByPlayer(par1EntityPlayer);
   }

   public int getMergeMaxSlotIndex(int SlotIndex) {
      return SlotIndex + 1;
   }
}
