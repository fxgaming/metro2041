package by.fxg.metro2041.client.render;

import org.lwjgl.opengl.GL11;

import by.fxg.metro2041.client.model.VModel;
import by.fxg.metro2041.common.tile.TilePortal;
import by.fxg.metro2041.common.tile.VTileBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class VRenderCustom extends TileEntitySpecialRenderer {
	private VModel model;
	private String texture;
	private int id;
	
	public VRenderCustom(ModelBase Model, String texture, int id) {
		this.model = (VModel)Model;
		this.texture = texture;
		this.id = id;
	}

	public void renderTileEntityAt(TileEntity tileentity, double d1, double d2, double d3, float f) {
		this.renderTileEntity((VTileBase)tileentity, d1, d2, d3, f);
	}

	public void renderTileEntity(VTileBase tile, double x, double y, double z, float f) {
		if (this.id == 0) {
			int ii = tile.getFacing();
			GL11.glPushMatrix();
			GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
			//GL11.glRotatef(180F, 0F, 0F, 1F);
			//GL11.glRotatef((float) (ii * 90) + 180F, 0.0F, 1.0F, 0.0F);
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("metro:textures/models/" + texture + ".png"));
			this.model.render();
			GL11.glPopMatrix();
		} else if (this.id == 1) {
			int ii = tile.getFacing();
			GL11.glPushMatrix();
			GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
			GL11.glRotatef(180F, 0F, 0F, 1F);
			GL11.glRotatef((float) (ii * 45), 0.0F, 1.0F, 0.0F);
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("metro:textures/models/" + texture + ".png"));
			this.model.render();
			GL11.glPopMatrix();
		} else if (this.id == 2) {
			int ii = tile.getFacing();
			GL11.glPushMatrix();
			GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
			GL11.glRotatef(180F, 0F, 0F, 1F);
			GL11.glRotatef((float) (ii * 90), 0.0F, 1.0F, 0.0F);
			GL11.glScalef(0.5F, 0.5F, 0.5F);
			GL11.glTranslatef(-0.5F, 1.5F, -0.5F);
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("metro:textures/models/" + texture + ".png"));
			this.model.render();
			GL11.glPopMatrix();
		} else if (this.id == 3) {
			String par2Str = ((TilePortal)tile).name.replace("&", "§");
			EntityPlayer p = Minecraft.getMinecraft().thePlayer;
			double d5 = tile.getDistanceFrom(p.posX, p.posY, p.posZ);
			if (d5 < 100.0D && !par2Str.equals("")) {
				FontRenderer fontrenderer = RenderManager.instance.getFontRenderer();
				f = 1.6F;
				float f1 = 0.016666668F * f;
				GL11.glPushMatrix();
				GL11.glTranslatef((float) x + 0.5F, (float) y + 2.2F, (float) z + 0.5F);
				GL11.glNormal3f(0.0F, 1.0F, 0.0F);
				GL11.glRotatef(-RenderManager.instance.playerViewY, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(RenderManager.instance.playerViewX, 1.0F, 0.0F, 0.0F);
				GL11.glScalef(-f1, -f1, f1);
				GL11.glDisable(2896);
				GL11.glDepthMask(false);
				GL11.glDisable(2929);
				GL11.glEnable(3042);
				GL11.glBlendFunc(770, 771);
				Tessellator tessellator = Tessellator.instance;
				GL11.glDisable(3553);
				tessellator.startDrawingQuads();
				int j = fontrenderer.getStringWidth(par2Str) / 2;
				tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
				tessellator.addVertex((double) (-j - 1), -1.0D, 0.0D);
				tessellator.addVertex((double) (-j - 1), 8.0D, 0.0D);
				tessellator.addVertex((double) (j + 1), 8.0D, 0.0D);
				tessellator.addVertex((double) (j + 1), -1.0D, 0.0D);
				tessellator.draw();
				GL11.glEnable(3553);
				fontrenderer.drawString(par2Str, -fontrenderer.getStringWidth(par2Str) / 2, 0, 553648127);
				GL11.glEnable(2929);
				GL11.glDepthMask(true);
				fontrenderer.drawString(par2Str, -fontrenderer.getStringWidth(par2Str) / 2, 0, -1);
				GL11.glEnable(2896);
				GL11.glDisable(3042);
				GL11.glPopMatrix();
			}
			int ii = tile.getFacing();
			GL11.glPushMatrix();
			GL11.glTranslatef((float)x + 0.5F, (float)y + 1.5F, (float)z + 0.5F);
			GL11.glRotatef(180F, 0F, 0F, 1F);
			GL11.glRotatef((float) (ii * 90), 0.0F, 1.0F, 0.0F);
			GL11.glColor4f(((TilePortal)tile).color[0], ((TilePortal)tile).color[1], ((TilePortal)tile).color[2], ((TilePortal)tile).color[3]);
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("metro:textures/models/" + texture + ".png"));
			this.model.render();
			GL11.glPopMatrix();
		}
	}
}