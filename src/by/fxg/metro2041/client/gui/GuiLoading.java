package by.fxg.metro2041.client.gui;

import java.awt.Color;
import java.util.Random;

import by.fxg.metro2041.Metro;
import by.fxg.metro2041.client.render.VRenderHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiLoading extends GuiScreen {
	private static final Random rand = new Random();
	private ResourceLocation black = new ResourceLocation("metro:start/black.png");
	
	private int timer1 = 0;
	private float timer2 = -0.0F;
	private int timer3 = 0;
	public GuiLoading() {
	}

	public void updateScreen() {
		if (this.timer1 >= 10) {
			if (this.timer2 <= 0.99F && this.timer3 == 0)
			this.timer2 += 0.02F;
			else {
				this.timer3++;
				if (this.timer3 >= 40) {
					if (this.timer2 >= 0.001F) {
						this.timer2 -= 0.02F;
					} else if (this.timer2 < 0.0F) {
						this.timer2 = 0.0F;
					}
				}
				if (this.timer3 >= 110) {
					this.timer1 = 0;
					this.timer2 = 0.0F;
					this.timer3 = 0;
					Metro.clientManager.gameStarted = true;
					if (!Metro.isServer) this.mc.displayGuiScreen(new GuiConnecting(new GuiMainMenu(), this.mc, "193.70.81.74", 25132));
					else this.mc.displayGuiScreen(new GuiMainMenu());
				}
			}
		} else {
			this.timer1++;
		}
	}

	public void initGui() {
	}

	protected void actionPerformed(GuiButton b) {
	}

	public void drawScreen(int par1, int par2, float par3) {
		ScaledResolution sr = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
		this.mc.renderEngine.bindTexture(this.black);
		VRenderHelper.drawRect(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), 0.0F, 0.0F, 0.0F, 1.0F);
		VRenderHelper.renderText("Diverse Mine", sr.getScaledWidth() / 2 - this.fontRenderer.getStringWidth("Divers"), sr.getScaledHeight() / 2 - 5, Color.getHSBColor(1.0F, 0.0F, this.timer2).getRGB());
		VRenderHelper.renderText("Panemo", sr.getScaledWidth() / 2 - this.fontRenderer.getStringWidth("Pan"), sr.getScaledHeight() / 2 + 5, Color.getHSBColor(1.0F, 0.0F, this.timer2).getRGB());
	}
	
    public boolean doesGuiPauseGame() {
        return true;
    }
    
    protected void keyTyped(char par1, int par2) {
    }
}
