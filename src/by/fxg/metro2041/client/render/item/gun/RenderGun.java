package by.fxg.metro2041.client.render.item.gun;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import by.fxg.metro2041.client.anim.ClientAnimationManager;
import by.fxg.metro2041.client.anim.GA_RPK_Reload;
import by.fxg.metro2041.client.anim.GunAnimation;
import by.fxg.metro2041.client.model.VModel;
import by.fxg.metro2041.client.model.item.gun.ModelFX;
import by.fxg.metro2041.common.entity.passive.EntityGroundItem;
import by.fxg.metro2041.common.item.gun.EnumShotParts;
import by.fxg.metro2041.common.item.gun.ItemGun;
import by.fxg.metro2041.common.player.ExtendedPlayer;
import by.fxg.metro2041.util.IRenderPartialTick;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public abstract class RenderGun implements IItemRenderer, IRenderPartialTick {
	public Minecraft mc = Minecraft.getMinecraft();
	public VModel gunModel = null;
	public ModelFX fx = new ModelFX();
	public Random rand = new Random();
	public float partialTick;

	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == ItemRenderType.EQUIPPED || type == ItemRenderType.ENTITY || type == ItemRenderType.EQUIPPED_FIRST_PERSON || type == ItemRenderType.INVENTORY;
	}

	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	public void setPartialTick(float par1) {
		this.partialTick = par1;
	}
	
	public void bindFX(ItemStack i) {
		if (i != null && i.getItem() instanceof ItemGun) {
			if (((ItemGun)i.getItem()).shotParts == EnumShotParts.FIRE) {
				String tx = "metro:textures/particles/wideflame" + rand.nextInt(4) + ".png";
				this.mc.renderEngine.bindTexture(new ResourceLocation(tx));
			} else if (((ItemGun)i.getItem()).shotParts == EnumShotParts.SPARK) {
				String tx = "metro:textures/particles/spark" + rand.nextInt(4) + ".png";
				this.mc.renderEngine.bindTexture(new ResourceLocation(tx));
			} else if (((ItemGun)i.getItem()).shotParts == EnumShotParts.ALL) {
				String tx = "metro:textures/particles/" + (rand.nextBoolean() ? "spark" : "wideflame") + rand.nextInt(4) + ".png";
				this.mc.renderEngine.bindTexture(new ResourceLocation(tx));
			}
		}
	}

	public void renderItem(ItemRenderType type, ItemStack itemstack, Object... data) {
		this.applyGunModel();
		if (itemstack != null) {
			Entity entity = null;
			try {
				entity = (Entity) data[1];
			} catch (Exception e) {
			}
			GL11.glPushMatrix();
			this.onTick(itemstack);
			String guntexture = this.getTexture(itemstack);
			if (type == ItemRenderType.EQUIPPED_FIRST_PERSON) {
				if (!ExtendedPlayer.get(((EntityPlayer) entity)).extData.isAiming) {
					GunAnimation animation = ClientAnimationManager.instance.getCurrentAnimation();
					if (animation != null) {
						animation.doRender(itemstack, this.partialTick, false);
					}

					this.renderHand((EntityPlayer)entity, itemstack);
					this.renderGunFirstPerson((EntityPlayer) entity, itemstack);
					this.mc.getTextureManager().bindTexture(new ResourceLocation("metro:textures/models/guns/" + guntexture + ".png"));
					this.gunModel.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
					this.renderAccessories(itemstack);
					GL11.glPushMatrix();
					if (animation != null && animation instanceof GA_RPK_Reload) {
						((GA_RPK_Reload) animation).doRenderAmmo(itemstack, this.partialTick);
					}
					GL11.glPopMatrix();
					GL11.glPopMatrix();
					if (!Minecraft.getMinecraft().gameSettings.viewBobbing) {
						Minecraft.getMinecraft().gameSettings.viewBobbing = true;
					}
					return;
				} else if (ExtendedPlayer.get(((EntityPlayer) entity)).extData.isAiming) {
					if (Minecraft.getMinecraft().gameSettings.viewBobbing) {
						Minecraft.getMinecraft().gameSettings.viewBobbing = false;
					}
					if (Minecraft.getMinecraft().thePlayer.isSprinting()) {
						Minecraft.getMinecraft().thePlayer.setSprinting(false);
					}
					this.renderGunFirstPersonAiming((EntityPlayer) entity, itemstack);
					GunAnimation animation = ClientAnimationManager.instance.getCurrentAnimation();
					if (animation != null) {
						animation.doRender(itemstack, this.partialTick, true);
					}
				}
			} else if (type == ItemRenderType.EQUIPPED) {
				this.renderGunThirdPerson(entity, itemstack);
			} else if (type == ItemRenderType.ENTITY) {
				this.renderGunOnGround(entity, itemstack);
			} else if (type == ItemRenderType.INVENTORY) {
				this.renderInventory(itemstack);
			}

			this.mc.getTextureManager().bindTexture(new ResourceLocation("metro:textures/models/guns/" + guntexture + ".png"));
			this.gunModel.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			this.renderAccessories(itemstack);
			GL11.glPopMatrix();
		}
	}

	public void renderMainGunOnGround(Entity entity, ItemStack itemstack, double par1, double par2, double par3) {
		this.applyGunModel();
		GL11.glPushMatrix();
		String guntexture = this.getTexture(itemstack);
		this.mc.getTextureManager().bindTexture(new ResourceLocation("metro:textures/models/guns/" + guntexture + ".png"));
		GL11.glTranslated(par1, par2 + 0.1D, par3);
		double scale = 0.4D;
		GL11.glScaled(scale, scale, scale);
		GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(((EntityGroundItem) entity).renderDirection, 0.0F, 1.0F, 0.0F);
		this.renderGunOnGround(entity, itemstack);
		this.gunModel.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		this.renderAccessories(itemstack);
		GL11.glDisable(2884);
		GL11.glPopMatrix();
	}

	private void applyGunModel() {
		if (this.gunModel == null) {
			this.gunModel = this.getGunModel();
		}
	}

	protected abstract void renderGunThirdPerson(Entity var1, ItemStack var2);

	protected abstract void renderGunFirstPerson(EntityPlayer var1, ItemStack var2);

	protected abstract void renderGunFirstPersonAiming(EntityPlayer var1, ItemStack var2);

	protected abstract void renderAccessories(ItemStack var1);

	protected abstract void renderGunOnGround(Entity var1, ItemStack var2);
	
	protected abstract void renderInventory(ItemStack var1);
	
	protected abstract void onTick(ItemStack var1);

	// protected abstract void renderGunOnPlayerBack(EntityPlayer var1, ItemStack
	// var2);

	// protected abstract void renderGunAmmo(Entity var1, ItemStack var2);

	// protected abstract void renderGunAttachment(Entity var1, ItemStack var2,
	// GunAttachment var3);

	protected abstract void renderHand(EntityPlayer var1, ItemStack var2);

	protected abstract VModel getGunModel();

	protected abstract String getTexture();

	public String getTexture(ItemStack i) {
		return this.getTexture();
	}
}
