package by.fxg.metro2041.client.cherry;

import org.lwjgl.opengl.GL11;

import by.fxg.metro2041.client.render.VRenderHelper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class CherryItemInfo extends CherryMainPage {
	private ItemWiki item;
	private CherryMainPage screen;
	private int offset;
	
	public CherryItemInfo(CherryMainPage scr, ItemWiki item, int offset) {
		this.screen = scr;
		this.item = item;
		this.offset = offset;
	}
	
	public void initGui() {
		ScaledResolution sr = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
		super.buttonList.add(new CherryButton(9, sr.getScaledWidth() - 95, 40, 80, 15).setText("Вернутся"));
		super.initGui();
	}
	
	public void updateScreen() {
	}
	
	protected void actionPerformed(GuiButton b) {
		if (b.id == 9) {
			if (this.screen instanceof CherryBlockPage) ((CherryBlockPage)this.screen).startScroll = this.offset;
			else if (this.screen instanceof CherryItemPage) ((CherryItemPage)this.screen).startScroll = this.offset;
			this.mc.displayGuiScreen(this.screen);
		}
		super.actionPerformed(b);
	}
	
	public void drawScreen(int par1, int par2, float par3) {
		ScaledResolution sr = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
		VRenderHelper.drawRect(15, 60, sr.getScaledWidth() - 15, sr.getScaledHeight() - 15, 0.0F, 0.0F, 0.0F, 0.5F);
		if (this.item.idcraft >= 0) {
			VRenderHelper.drawRect(sr.getScaledWidth() - 150, 65, sr.getScaledWidth() - 20, 130, 0.0F, 0.0F, 0.0F, 0.3F);
			VRenderHelper.drawImage(sr.getScaledWidth() - 145, 70, new ResourceLocation("diverse:textures/wiki/" + this.item.idcraft + ".png"), 120, 55);
			GL11.glTranslatef(0.0F, 0.0F, 16.0F);
			if (this.item.idcraft == 0) {
				if (this.item.crafting[0] != null) {
					VRenderHelper.renderItemStackIntoGUI(this.item.crafting[0], sr.getScaledWidth() - 142, 70);
					GL11.glTranslatef(0.0F, 0.0F, 24.0F);
					VRenderHelper.renderTextScaled((this.item.crafting[0].stackSize + "").equals("1") ? "" : this.item.crafting[0].stackSize + "", sr.getScaledWidth() - 144, 81, 0.75D);
					GL11.glTranslatef(0.0F, 0.0F, -24.0F);
				}
				if (this.item.crafting[1] != null) {
					VRenderHelper.renderItemStackIntoGUI(this.item.crafting[1], sr.getScaledWidth() - 122, 70);
					GL11.glTranslatef(0.0F, 0.0F, 24.0F);
					VRenderHelper.renderTextScaled((this.item.crafting[1].stackSize + "").equals("1") ? "" : this.item.crafting[1].stackSize + "", sr.getScaledWidth() - 124, 81, 0.75D);
					GL11.glTranslatef(0.0F, 0.0F, -24.0F);
				}
				if (this.item.crafting[2] != null) {
					VRenderHelper.renderItemStackIntoGUI(this.item.crafting[2], sr.getScaledWidth() - 101, 70);
					GL11.glTranslatef(0.0F, 0.0F, 24.0F);
					VRenderHelper.renderTextScaled((this.item.crafting[2].stackSize + "").equals("1") ? "" : this.item.crafting[2].stackSize + "", sr.getScaledWidth() - 103, 81, 0.75D);
					GL11.glTranslatef(0.0F, 0.0F, -24.0F);
				}
				if (this.item.crafting[3] != null) {
					VRenderHelper.renderItemStackIntoGUI(this.item.crafting[3], sr.getScaledWidth() - 142, 89);
					GL11.glTranslatef(0.0F, 0.0F, 24.0F);
					VRenderHelper.renderTextScaled((this.item.crafting[3].stackSize + "").equals("1") ? "" : this.item.crafting[3].stackSize + "", sr.getScaledWidth() - 144, 99, 0.75D);
					GL11.glTranslatef(0.0F, 0.0F, -24.0F);
				}
				if (this.item.crafting[4] != null) {
					VRenderHelper.renderItemStackIntoGUI(this.item.crafting[4], sr.getScaledWidth() - 122, 89);
					GL11.glTranslatef(0.0F, 0.0F, 24.0F);
					VRenderHelper.renderTextScaled((this.item.crafting[4].stackSize + "").equals("1") ? "" : this.item.crafting[4].stackSize + "", sr.getScaledWidth() - 124, 99, 0.75D);
					GL11.glTranslatef(0.0F, 0.0F, -24.0F);
				}
				if (this.item.crafting[5] != null) {
					VRenderHelper.renderItemStackIntoGUI(this.item.crafting[5], sr.getScaledWidth() - 101, 89);
					GL11.glTranslatef(0.0F, 0.0F, 24.0F);
					VRenderHelper.renderTextScaled((this.item.crafting[5].stackSize + "").equals("1") ? "" : this.item.crafting[5].stackSize + "", sr.getScaledWidth() - 103, 99, 0.75D);
					GL11.glTranslatef(0.0F, 0.0F, -24.0F);
				}
				if (this.item.crafting[6] != null) {
					VRenderHelper.renderItemStackIntoGUI(this.item.crafting[6], sr.getScaledWidth() - 142, 107);
					GL11.glTranslatef(0.0F, 0.0F, 24.0F);
					VRenderHelper.renderTextScaled((this.item.crafting[6].stackSize + "").equals("1") ? "" : this.item.crafting[6].stackSize + "", sr.getScaledWidth() - 144, 117, 0.75D);
					GL11.glTranslatef(0.0F, 0.0F, -24.0F);
				}
				if (this.item.crafting[7] != null) {
					VRenderHelper.renderItemStackIntoGUI(this.item.crafting[7], sr.getScaledWidth() - 122, 107);
					GL11.glTranslatef(0.0F, 0.0F, 24.0F);
					VRenderHelper.renderTextScaled((this.item.crafting[7].stackSize + "").equals("1") ? "" : this.item.crafting[7].stackSize + "", sr.getScaledWidth() - 124, 117, 0.75D);
					GL11.glTranslatef(0.0F, 0.0F, -24.0F);
				}
				if (this.item.crafting[8] != null) {
					VRenderHelper.renderItemStackIntoGUI(this.item.crafting[8], sr.getScaledWidth() - 101, 107);
					GL11.glTranslatef(0.0F, 0.0F, 24.0F);
					VRenderHelper.renderTextScaled((this.item.crafting[8].stackSize + "").equals("1") ? "" : this.item.crafting[8].stackSize + "", sr.getScaledWidth() - 103, 117, 0.75D);
					GL11.glTranslatef(0.0F, 0.0F, -24.0F);
				}
				if (this.item.crafting[9] != null) {
					VRenderHelper.renderItemStackIntoGUI(this.item.crafting[9], sr.getScaledWidth() - 48, 89);
					GL11.glTranslatef(0.0F, 0.0F, 24.0F);
					VRenderHelper.renderTextScaled((this.item.crafting[9].stackSize + "").equals("1") ? "" : this.item.crafting[9].stackSize + "", sr.getScaledWidth() - 50, 100, 0.75D);
					GL11.glTranslatef(0.0F, 0.0F, -24.0F);
				}
			} else if (this.item.idcraft == 1) {
				
			} else if (this.item.idcraft == 2) {
				
			} else if (this.item.idcraft == 3) {
				
			} else if (this.item.idcraft == 4) {
				
			} else if (this.item.idcraft == 5) {
				if (this.item.crafting[0] != null) {
					VRenderHelper.renderItemStackIntoGUI(this.item.crafting[0], sr.getScaledWidth() - 127, 77);
					GL11.glTranslatef(0.0F, 0.0F, 24.0F);
					VRenderHelper.renderTextScaled((this.item.crafting[0].stackSize + "").equals("1") ? "" : this.item.crafting[0].stackSize + "", sr.getScaledWidth() - 131, 88, 0.75D);
					GL11.glTranslatef(0.0F, 0.0F, -24.0F);
				}
				if (this.item.crafting[1] != null) {
					VRenderHelper.renderItemStackIntoGUI(this.item.crafting[1], sr.getScaledWidth() - 127, 102);
					GL11.glTranslatef(0.0F, 0.0F, 24.0F);
					VRenderHelper.renderTextScaled((this.item.crafting[1].stackSize + "").equals("1") ? "" : this.item.crafting[1].stackSize + "", sr.getScaledWidth() - 131, 114, 0.75D);
					GL11.glTranslatef(0.0F, 0.0F, -24.0F);
				}
				if (this.item.crafting[2] != null) {
					VRenderHelper.renderItemStackIntoGUI(this.item.crafting[2], sr.getScaledWidth() - 46, 90);
					GL11.glTranslatef(0.0F, 0.0F, 24.0F);
					VRenderHelper.renderTextScaled((this.item.crafting[2].stackSize + "").equals("1") ? "" : this.item.crafting[2].stackSize + "", sr.getScaledWidth() - 51, 101, 0.75D);
					GL11.glTranslatef(0.0F, 0.0F, -24.0F);
				}
			} else if (this.item.idcraft == 6) {
				if (this.item.crafting[0] != null) {
					VRenderHelper.renderItemStackIntoGUI(this.item.crafting[0], sr.getScaledWidth() - 139, 78);
					GL11.glTranslatef(0.0F, 0.0F, 24.0F);
					VRenderHelper.renderTextScaled((this.item.crafting[0].stackSize + "").equals("1") ? "" : this.item.crafting[0].stackSize + "", sr.getScaledWidth() - 140, 89, 0.75D);
					GL11.glTranslatef(0.0F, 0.0F, -24.0F);
				}
				if (this.item.crafting[1] != null) {
					VRenderHelper.renderItemStackIntoGUI(this.item.crafting[1], sr.getScaledWidth() - 117, 78);
					GL11.glTranslatef(0.0F, 0.0F, 24.0F);
					VRenderHelper.renderTextScaled((this.item.crafting[1].stackSize + "").equals("1") ? "" : this.item.crafting[1].stackSize + "", sr.getScaledWidth() - 119, 89, 0.75D);
					GL11.glTranslatef(0.0F, 0.0F, -24.0F);
				}
				if (this.item.crafting[2] != null) {
					VRenderHelper.renderItemStackIntoGUI(this.item.crafting[2], sr.getScaledWidth() - 96, 78);
					GL11.glTranslatef(0.0F, 0.0F, 24.0F);
					VRenderHelper.renderTextScaled((this.item.crafting[2].stackSize + "").equals("1") ? "" : this.item.crafting[2].stackSize + "", sr.getScaledWidth() - 98, 89, 0.75D);
					GL11.glTranslatef(0.0F, 0.0F, -24.0F);
				}
				if (this.item.crafting[3] != null) {
					VRenderHelper.renderItemStackIntoGUI(this.item.crafting[3], sr.getScaledWidth() - 102, 102);
					GL11.glTranslatef(0.0F, 0.0F, 24.0F);
					VRenderHelper.renderTextScaled((this.item.crafting[3].stackSize + "").equals("1") ? "" : this.item.crafting[3].stackSize + "", sr.getScaledWidth() - 104, 114, 0.75D);
					GL11.glTranslatef(0.0F, 0.0F, -24.0F);
				}
				if (this.item.crafting[4] != null) {
					VRenderHelper.renderItemStackIntoGUI(this.item.crafting[4], sr.getScaledWidth() - 43, 92);
					GL11.glTranslatef(0.0F, 0.0F, 24.0F);
					VRenderHelper.renderTextScaled((this.item.crafting[4].stackSize + "").equals("1") ? "" : this.item.crafting[4].stackSize + "", sr.getScaledWidth() - 45, 103, 0.75D);
					GL11.glTranslatef(0.0F, 0.0F, -24.0F);
				}
			}
			GL11.glTranslatef(0.0F, 0.0F, -16.0F);
		}
		VRenderHelper.renderItemStackIntoGUI(this.item.itemStack, 15, 43);
		super.drawScreen(par1, par2, par3);
		VRenderHelper.renderText(this.item.itemStack.getDisplayName() + " " + this.item.itemStack.itemID + ":" + this.item.itemStack.getItemDamage(), 35, 47);
		VRenderHelper.renderText("Насколько сложно добыть: " + this.item.rareness, 20, 63);
		if (this.item.infoText.contains("<SPLIT>")) {
			String[] spl = this.item.infoText.split("<SPLIT>");
			for (int i = 0; i != spl.length; i++) {
				VRenderHelper.renderText(spl[i], 20,73 + (10 * i));
			}
		} else {
			VRenderHelper.renderText(this.item.infoText, 20, 73);
		}
		VRenderHelper.renderText("Где использовать?", 20, 150);
		if (this.item.whereUseText.contains("<SPLIT>")) {
			String[] spl = this.item.whereUseText.split("<SPLIT>");
			for (int i = 0; i != spl.length; i++) {
				VRenderHelper.renderText(spl[i], 20, 160 + (10 * i));
			}
		} else {
			VRenderHelper.renderText(this.item.whereUseText, 20, 160);
		}
		VRenderHelper.renderText("Где найти?", 20, 190);
		if (this.item.whereFindText.contains("<SPLIT>")) {
			String[] spl = this.item.whereFindText.split("<SPLIT>");
			for (int i = 0; i != spl.length; i++) {
				VRenderHelper.renderText(spl[i], 20, 200 + (10 * i));
			}
		} else {
			VRenderHelper.renderText(this.item.whereFindText, 20, 200);
		}
	}
}
