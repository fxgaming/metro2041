package by.fxg.metro2041.client.cherry;

import org.lwjgl.opengl.GL11;

import by.fxg.metro2041.client.render.VRenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.item.ItemStack;

public class CherryButtonItem extends GuiButton {
	private int[] btnColor = {-6250336, 14737632, 16777120};
	private float[] offColor = {0.0F, 0.0F, 0.0F, 0.5F};
	private float[] onColor = {1.0F, 1.0F, 1.0F, 0.5F};
	
	private ItemStack item = null;
	public int slotid = 0;
	
	public CherryButtonItem(int id, int x, int y, int width, int height) {
		super(id, x, y, width, height, "");
	}
	
	public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
        FontRenderer fontrenderer = par1Minecraft.fontRenderer;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.field_82253_i = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
        int k = this.getHoverState(this.field_82253_i);
        if (k == 1) {
        	VRenderHelper.drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, this.offColor[0], this.offColor[1], this.offColor[2], this.offColor[3]);
        } else if (k == 2) {
        	VRenderHelper.drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, this.onColor[0], this.onColor[1], this.onColor[2], this.onColor[3]);
        }
        this.mouseDragged(par1Minecraft, par2, par3);

        if (this.item != null) {
        	GL11.glTranslatef(0.0F, 0.0F, 16.0F);
        	VRenderHelper.renderItemStackIntoGUI(this.item, this.xPosition + 2, this.yPosition + 3);
        	this.drawString(fontrenderer, this.item.getDisplayName(), this.xPosition + 22, this.yPosition + 2, this.btnColor[k]);
        	this.drawString(fontrenderer, this.item.itemID + ":" + this.item.getItemDamage(), this.xPosition + 22, this.yPosition + 12, this.btnColor[k]);
        }
    }
	
	public CherryButtonItem setButtonTextColors(int... par1) {
		this.btnColor = par1;
		return this;
	}
	
	public CherryButtonItem setItemstack(ItemStack par1) {
		this.item = par1;
		return this;
	}
	
	public CherryButtonItem setButtonOffColors(float... par1) {
		this.offColor = par1;
		return this;
	}
	
	public CherryButtonItem setButtonOnColors(float... par1) {
		this.onColor = par1;
		return this;
	}
	
	public CherryButtonItem reinstall(ItemStack par1, int id) {
		this.item = par1;
		this.slotid = id;
		return this;
	}
}
