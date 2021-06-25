package by.fxg.metro2041.common.block.gui;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import by.fxg.metro2041.common.block.container.ContainerBlockToolStation;
import by.fxg.metro2041.common.tile.TileToolStation;
import by.fxg.metro2041.util.TextUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiBlockToolStation extends GuiContainer {
	private TileToolStation tile;

	public GuiBlockToolStation(InventoryPlayer inv, TileToolStation tile) {
		super(new ContainerBlockToolStation(inv, tile));
		this.tile = tile;
	}
	
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		if (this.tile.items[0] != null) {
			String count = this.tile.recipe_ingot.containsKey(this.tile.items[0].itemID) ? "" + this.tile.recipe_ingot.get(this.tile.items[0].itemID).coalNeeded.stackSize : "NaN";
			String type = this.tile.recipe_ingot.containsKey(this.tile.items[0].itemID) ? "" + (this.tile.recipe_ingot.get(this.tile.items[0].itemID).coalNeeded.itemID == 173 ? "блок" + TextUtils.getEndingFromNumber(Integer.valueOf(count)) + " угля" : "угля") : "Null";
			GL11.glPushMatrix();
			super.fontRenderer.drawString("" + count + " " + type, 9, 70, Color.BLACK.getRGB());
			GL11.glPopMatrix();
		} else if (this.tile.items[1] != null) {
			String count = this.tile.recipe_pickaxe.containsKey(this.tile.items[1].itemID) ? "" + this.tile.recipe_pickaxe.get(this.tile.items[1].itemID).coalNeeded.stackSize : "NaN";
			String type = this.tile.recipe_pickaxe.containsKey(this.tile.items[1].itemID) ? "" + (this.tile.recipe_pickaxe.get(this.tile.items[1].itemID).coalNeeded.itemID == 173 ? "блок" + TextUtils.getEndingFromNumber(Integer.valueOf(count)) + " угля" : "угля") : "Null";
			GL11.glPushMatrix();
			super.fontRenderer.drawString("" + count + " " + type, 9, 70, Color.BLACK.getRGB());
			GL11.glPopMatrix();
		} else if (this.tile.items[2] != null) {
			String count = this.tile.recipe_sword.containsKey(this.tile.items[2].itemID) ? "" + this.tile.recipe_sword.get(this.tile.items[2].itemID).coalNeeded.stackSize : "NaN";
			String type = this.tile.recipe_sword.containsKey(this.tile.items[2].itemID) ? "" + (this.tile.recipe_sword.get(this.tile.items[2].itemID).coalNeeded.itemID == 173 ? "блок" + TextUtils.getEndingFromNumber(Integer.valueOf(count)) + " угля" : "угля") : "Null";
			GL11.glPushMatrix();
			super.fontRenderer.drawString("" + count + " " + type, 9, 70, Color.BLACK.getRGB());
			GL11.glPopMatrix();
		}
	}

	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		super.mc.renderEngine.bindTexture(new ResourceLocation("metro:textures/gui/blocktoolstation.png"));
		int j = (super.width - super.xSize) / 2;
		int k = (super.height - super.ySize) / 2;
		this.drawTexturedModalRect(j, k, 0, 0, super.xSize, super.ySize);
		this.drawTexturedModalRect(j + 92, k + 34, 176, 0, (int)tile.time, 15);
		if (this.tile.items[0] != null) {
			this.drawTexturedModalRect(j + 22, k + 23, 208, 15, 16, 16);
		}
		if (this.tile.items[1] != null) {
			this.drawTexturedModalRect(j + 40, k + 23, 208, 15, 16, 16);
		}
		if (this.tile.items[2] != null) {
			this.drawTexturedModalRect(j + 58, k + 23, 208, 15, 16, 16);
		}
	}
}
