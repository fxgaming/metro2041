package by.fxg.metro2041.common.player.gui;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import by.fxg.metro2041.client.storage.ClientData;
import by.fxg.metro2041.common.item.gun.EnumAmmoType;
import by.fxg.metro2041.common.player.ExtendedPlayer;
import by.fxg.metro2041.common.player.container.ContainerExtended;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiExtended extends InventoryEffectRenderer {
	public InventoryPlayer inv;
	public ExtendedPlayer epl;
	private float xSize_lo;
	private float ySize_lo;

	public GuiExtended(InventoryPlayer inventoryplayer, ExtendedPlayer epl) {
		super(new ContainerExtended(inventoryplayer, epl));
		this.inv = inventoryplayer;
		this.epl = epl;
		this.xSize = 176;
		this.ySize = 166;
	}

	public void initGui() {
		super.initGui();
	}

	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		int x = par1;
		int y = par2;
		super.fontRenderer.drawString(("" + ClientData.ammo0), 88, 8, Color.WHITE.getRGB());
		super.fontRenderer.drawString(("" + ClientData.ammo1), 88, 26, Color.WHITE.getRGB());
		super.fontRenderer.drawString(("" + ClientData.ammo2), 88, 44, Color.WHITE.getRGB());
		super.fontRenderer.drawString(("" + ClientData.ammo3), 88, 62, Color.WHITE.getRGB());
		super.fontRenderer.drawString(("" + ClientData.ammo4), 149 - super.fontRenderer.getStringWidth(("" + ClientData.ammo4).substring(1)), 8, Color.WHITE.getRGB());
		super.fontRenderer.drawString(("" + ClientData.ammo5), 149 - super.fontRenderer.getStringWidth(("" + ClientData.ammo5).substring(1)), 26, Color.WHITE.getRGB());
		super.fontRenderer.drawString(("" + ClientData.ammo6), 149 - super.fontRenderer.getStringWidth(("" + ClientData.ammo6).substring(1)), 44, Color.WHITE.getRGB());
		super.fontRenderer.drawString(("" + ClientData.ammo7), 149 - super.fontRenderer.getStringWidth(("" + ClientData.ammo7).substring(1)), 62, Color.WHITE.getRGB());
	}

	public void drawScreen(int par1, int par2, float par3) {
		super.drawScreen(par1, par2, par3);
		this.xSize_lo = (float) par1;
		this.ySize_lo = (float) par2;
	}

	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		super.mc.renderEngine.bindTexture(new ResourceLocation("metro:textures/gui/inventoryext.png"));
		int cornerX = super.guiLeft;
		int cornerY = super.guiTop;
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		this.drawTexturedModalRect(cornerX, cornerY, 0, 0, super.xSize, super.ySize);
		GL11.glDisable(GL11.GL_BLEND);
		this.drawPlayerOnGui(super.mc, cornerX + 33, cornerY + 72, 30, (float)(cornerX + 51) - this.xSize_lo, (float)(cornerY + 75 - 50) - this.ySize_lo);
	}

	public static void drawPlayerOnGui(Minecraft par0Minecraft, int par1, int par2, int par3, float par4, float par5) {
		GL11.glEnable(2903);
		GL11.glPushMatrix();
		GL11.glTranslatef((float) par1, (float) par2, 50.0F);
		GL11.glScalef((float) (-par3), (float) par3, (float) par3);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		float f2 = par0Minecraft.thePlayer.renderYawOffset;
		float f3 = par0Minecraft.thePlayer.rotationYaw;
		float f4 = par0Minecraft.thePlayer.rotationPitch;
		par4 -= 19.0F;
		GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-((float) Math.atan((double) (par5 / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
		par0Minecraft.thePlayer.renderYawOffset = (float) Math.atan((double) (par4 / 40.0F)) * 20.0F;
		par0Minecraft.thePlayer.rotationYaw = (float) Math.atan((double) (par4 / 40.0F)) * 40.0F;
		par0Minecraft.thePlayer.rotationPitch = -((float) Math.atan((double) (par5 / 40.0F))) * 20.0F;
		par0Minecraft.thePlayer.rotationYawHead = par0Minecraft.thePlayer.rotationYaw;
		GL11.glTranslatef(0.0F, par0Minecraft.thePlayer.yOffset, 0.0F);
		RenderManager.instance.playerViewY = 180.0F;
		RenderManager.instance.renderEntityWithPosYaw(par0Minecraft.thePlayer, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
		par0Minecraft.thePlayer.renderYawOffset = f2;
		par0Minecraft.thePlayer.rotationYaw = f3;
		par0Minecraft.thePlayer.rotationPitch = f4;
		GL11.glPopMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable('耺');
		OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GL11.glDisable(3553);
		OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}
}
