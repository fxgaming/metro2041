package by.fxg.metro2041.client.render.entity;

import org.lwjgl.opengl.GL11;

import by.fxg.metro2041.client.render.item.gun.RenderGun;
import by.fxg.metro2041.common.entity.passive.EntityGroundItem;
import by.fxg.metro2041.common.item.gun.ItemGun;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumMovingObjectType;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.MinecraftForgeClient;

public class RenderGroundItem extends Render {
	private Minecraft mc = FMLClientHandler.instance().getClient();

	public void doRenderGroundItem(EntityGroundItem entity, double d0, double d1, double d2, float f, float f1) {
		ItemStack itemstack = entity.getEntityItem();
		if (itemstack != null) {
			IItemRenderer itemrender;
			if (itemstack.getItem() instanceof ItemGun) {
				GL11.glPushMatrix();
				itemrender = MinecraftForgeClient.getItemRenderer(itemstack, ItemRenderType.ENTITY);
				((RenderGun)itemrender).renderMainGunOnGround(entity, itemstack, d0, d1, d2);
				GL11.glPopMatrix();
			} else {
				itemrender = MinecraftForgeClient.getItemRenderer(itemstack, ItemRenderType.ENTITY);
				if (itemrender != null) {
					GL11.glPushMatrix();
					GL11.glTranslatef((float) d0, (float) (d1 + 0.05000000074505806D), (float) d2);
					itemrender.renderItem(ItemRenderType.ENTITY, itemstack, new Object[0]);
					GL11.glPopMatrix();
					return;
				}

				GL11.glPushMatrix();
				GL11.glTranslatef((float) d0, (float) (d1 + 0.05000000074505806D), (float) d2);
				float scale = 0.75F;
				float scale23 = 0.2F;
				GL11.glScalef(scale - scale23, scale + 0.5F, scale - scale23);
				GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(entity.renderDirection, 0.0F, 0.0F, 1.0F);
				this.renderDroppedItem(entity, itemstack.getIconIndex(), 1, 1.0F, 1.0F, 1.0F, 1.0F);
				GL11.glPopMatrix();
			}

			String par2Str = "" + itemstack.getDisplayName();
			if (itemstack.stackSize > 1) {
				par2Str = par2Str + " (" + itemstack.stackSize + ")";
			}

			if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == EnumMovingObjectType.ENTITY) {
				Entity entity2 = this.mc.objectMouseOver.entityHit;
				if (entity2 == entity) {
					// EntityRendererUtils.renderStringFacingPlayer(par2Str, d0, d1 + 0.25D, d2,
					// super.renderManager);
				}
			}
		}

	}

	public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1) {
		this.doRenderGroundItem((EntityGroundItem) entity, d0, d1, d2, f, f1);
	}

	protected ResourceLocation getEntityTexture(Entity entity) {
		return new ResourceLocation(".png");
	}

	private void renderDroppedItem(EntityGroundItem par1EntityItem, Icon par2Icon, int par3, float par4, float par5, float par6, float par7) {
		Tessellator tessellator = Tessellator.instance;
		if (par2Icon == null) {
			TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
			ResourceLocation resourcelocation = texturemanager.getResourceLocation(par1EntityItem.getEntityItem().getItemSpriteNumber());
			par2Icon = ((TextureMap) texturemanager.getTexture(resourcelocation)).getAtlasSprite("missingno");
		}

		float f4 = ((Icon) par2Icon).getMinU();
		float f5 = ((Icon) par2Icon).getMaxU();
		float f6 = ((Icon) par2Icon).getMinV();
		float f7 = ((Icon) par2Icon).getMaxV();
		float f8 = 1.0F;
		float f9 = 0.5F;
		float f10 = 0.25F;
		GL11.glPushMatrix();
		float f12 = 0.0625F;
		float f11 = 0.021875F;
		ItemStack itemstack = par1EntityItem.getEntityItem();
		int j = itemstack.stackSize;
		byte b0 = 1;
		GL11.glTranslatef(-f9, -f10, -((f12 + f11) * (float) b0 / 2.0F));

		for (int k = 0; k < b0; ++k) {
			GL11.glTranslatef(0.0F, 0.0F, f12 + f11);
			if (itemstack.getItemSpriteNumber() == 0) {
				super.renderManager.renderEngine.bindTexture(TextureMap.locationBlocksTexture);
			} else {
				super.renderManager.renderEngine.bindTexture(TextureMap.locationItemsTexture);
			}

			GL11.glColor4f(par5, par6, par7, 1.0F);
			ItemRenderer.renderItemIn2D(tessellator, f5, f6, f4, f7, ((Icon) par2Icon).getIconWidth(), ((Icon) par2Icon).getIconHeight(), f12);
			if (itemstack != null && itemstack.hasEffect()) {
				GL11.glDepthFunc(514);
				GL11.glDisable(2896);
				ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
				super.renderManager.renderEngine.bindTexture(RES_ITEM_GLINT);
				GL11.glEnable(3042);
				GL11.glBlendFunc(768, 1);
				float f13 = 0.76F;
				GL11.glColor4f(0.5F * f13, 0.25F * f13, 0.8F * f13, 1.0F);
				GL11.glMatrixMode(5890);
				GL11.glPushMatrix();
				float f14 = 0.125F;
				GL11.glScalef(f14, f14, f14);
				float f15 = (float) (Minecraft.getSystemTime() % 3000L) / 3000.0F * 8.0F;
				GL11.glTranslatef(f15, 0.0F, 0.0F);
				GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
				ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, f12);
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				GL11.glScalef(f14, f14, f14);
				f15 = (float) (Minecraft.getSystemTime() % 4873L) / 4873.0F * 8.0F;
				GL11.glTranslatef(-f15, 0.0F, 0.0F);
				GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
				ItemRenderer.renderItemIn2D(tessellator, 0.0F, 0.0F, 1.0F, 1.0F, 255, 255, f12);
				GL11.glPopMatrix();
				GL11.glMatrixMode(5888);
				GL11.glDisable(3042);
				GL11.glEnable(2896);
				GL11.glDepthFunc(515);
			}
		}

		GL11.glPopMatrix();
	}
}
