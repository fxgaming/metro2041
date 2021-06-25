package by.fxg.metro2041.client.gui;

import java.awt.Color;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import by.fxg.metro2041.Metro;
import by.fxg.metro2041.client.render.VRenderHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiMain extends GuiScreen {
	private static final Random rand = new Random();
	private int timer1 = 0;
	private float timer2 = 0.0F;
	public GuiMain() {
	}

	public void updateScreen() {
		if (this.timer1 >= 10) {
			if (this.timer2 <= 0.99F)
			this.timer2 += 0.02F;
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
		VRenderHelper.drawRect(0, 0, sr.getScaledWidth(), sr.getScaledHeight(), 0.0F, 0.0F, 0.0F, this.timer2);
	}
}
