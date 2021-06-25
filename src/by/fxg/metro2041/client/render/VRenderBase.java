package by.fxg.metro2041.client.render;

import org.lwjgl.opengl.GL11;

import by.fxg.metro2041.client.model.VModel;
import by.fxg.metro2041.common.tile.VTileBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class VRenderBase extends TileEntitySpecialRenderer implements IItemRenderer {
	private ModelBase model;
	private String texture;
	private float scale = 1.0F;

	public VRenderBase(ModelBase Model, String texture) {
		this.model = Model;
		this.texture = texture;
	}
	
	public VRenderBase(ModelBase Model, String texture, float scale) {
		this.model = Model;
		this.texture = texture;
		this.scale = scale;
	}

	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch (type) {
		case EQUIPPED:
			return true;
		case INVENTORY:
			return true;
		case ENTITY:
			return true;
		case EQUIPPED_FIRST_PERSON:
			return true;
		default:
			return false;
		}
	}

	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		switch (helper) {
		case ENTITY_ROTATION:
		case ENTITY_BOBBING:
		case EQUIPPED_BLOCK:
		case BLOCK_3D:
		case INVENTORY_BLOCK:
			return true;
		default:
			return false;
		}
	}

	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch (type) {
		case EQUIPPED:
			GL11.glPushMatrix();
			GL11.glRotatef(170.0F, 1.0F, 0.0F, 0.0F);
			boolean isFirstPerson = false;
			if (data[1] != null && data[1] instanceof EntityPlayer) {
				if ((EntityPlayer) data[1] == Minecraft.getMinecraft().renderViewEntity && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0 && (!(Minecraft.getMinecraft().currentScreen instanceof GuiInventory) && !(Minecraft.getMinecraft().currentScreen instanceof GuiContainerCreative) || RenderManager.instance.playerViewY != 180.0F)) {
					isFirstPerson = true;
					GL11.glTranslatef(4F, -2.5F, -0.2F);
					GL11.glRotatef(90f, 0f, 1f, 0f);
					GL11.glScalef(2.5F, 2.5F, 2.5F);
				} else {
					GL11.glTranslatef(0.6F, -1.5F, -0.6F);
				}
			} else {
				GL11.glTranslatef(0.65F, -1.2F, 0.0F);
			}
			GL11.glScalef(this.scale, this.scale, this.scale);
			if (this.scale == 0.5F) {
				GL11.glTranslatef(-0.6F, 1.2F, -0.6F);
			}
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("metro:textures/models/" + this.texture + ".png"));
			((VModel)this.model).render();
			GL11.glPopMatrix();
			break;
		case INVENTORY:
			GL11.glDisable(2884);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glPushMatrix();
			GL11.glTranslatef(0.0F, 1.0F, 0.0F);
			GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
			GL11.glScalef(this.scale, this.scale, this.scale);
			if (this.scale == 0.5F) {
				GL11.glTranslatef(-0.5F, 1.5F, -0.4F);
			}
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("metro:textures/models/" + this.texture + ".png"));
			((VModel)this.model).render();
			GL11.glDisable(3042);
			GL11.glPopMatrix();
			break;
		case ENTITY:
			GL11.glDisable(2884);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glPushMatrix();
			GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(135.0F, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(205.0F, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(0.0F, -1.0F, 0.0F);
			GL11.glScalef(this.scale, this.scale, this.scale);
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("metro:textures/models/" + this.texture + ".png"));
			((VModel)this.model).render();
			GL11.glDisable(3042);
			GL11.glPopMatrix();
			break;
		case EQUIPPED_FIRST_PERSON:
			GL11.glPushMatrix();
			GL11.glRotatef(170.0F, 1.0F, 0.0F, 0.0F);
			GL11.glTranslatef(4F, -2.5F, -0.2F);
			GL11.glRotatef(90f, 0f, 1f, 0f);
			GL11.glScalef(2.5f, 2.5f, 2.5f);
			GL11.glScalef(this.scale, this.scale, this.scale);
			if (this.scale == 0.5F) {
				GL11.glTranslatef(-0.4F, 1.2F, -0.8F);
			}
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("metro:textures/models/" + this.texture + ".png"));
			((VModel)this.model).render();
			GL11.glPopMatrix();
			break;
		default:
			break;
		}
	}

	public void renderTileEntityAt(TileEntity tileentity, double d1, double d2, double d3, float f) {
		this.renderTileEntity((VTileBase)tileentity, d1, d2, d3, f);
	}

	public void renderTileEntity(VTileBase tile, double dl, double d2, double d3, float f) {
		int ii = tile.getFacing();
		GL11.glPushMatrix();
		GL11.glTranslatef((float) dl + 0.5F, (float) d2 + 1.5F, (float) d3 + 0.5F);
		GL11.glRotatef(180F, 0F, 0F, 1F);
		GL11.glRotatef((float) (ii * 90), 0.0F, 1.0F, 0.0F);
		GL11.glScalef(this.scale, this.scale, this.scale);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("metro:textures/models/" + texture + ".png"));
		((VModel)this.model).render();
		GL11.glPopMatrix();
	}
}
