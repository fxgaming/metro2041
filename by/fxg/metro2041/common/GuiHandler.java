package by.fxg.metro2041.common;

import by.fxg.metro2041.common.block.container.ContainerBlockToolStation;
import by.fxg.metro2041.common.block.gui.GuiBlockToolStation;
import by.fxg.metro2041.common.player.ExtendedPlayer;
import by.fxg.metro2041.common.player.container.ContainerExtended;
import by.fxg.metro2041.common.player.gui.GuiExtended;
import by.fxg.metro2041.common.tile.TileToolStation;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		if (ID == 249) {
			return new ContainerPlayer(player.inventory, false, player);
		}
		if (ID == 250) {
			return new ContainerExtended(player.inventory, ExtendedPlayer.get(player));
		}
		
		if (ID == 1001) {
			if (tile instanceof TileToolStation)
				return new ContainerBlockToolStation(player.inventory, (TileToolStation)tile);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		if (ID == 249) {
			return new GuiInventory(player);
		}
		if (ID == 250) {
			return new GuiExtended(player.inventory, ExtendedPlayer.get(player));
		}
		if (ID == 1001) {
			if (tile instanceof TileToolStation)
				return new GuiBlockToolStation(player.inventory, (TileToolStation)tile);
		}
		return null;
	}
}
