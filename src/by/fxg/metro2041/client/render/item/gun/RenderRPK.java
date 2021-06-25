package by.fxg.metro2041.client.render.item.gun;

import org.lwjgl.opengl.GL11;

import by.fxg.metro2041.client.anim.ClientAnimationManager;
import by.fxg.metro2041.client.anim.GunAnimation;
import by.fxg.metro2041.client.model.ModelManager;
import by.fxg.metro2041.client.model.VModel;
import by.fxg.metro2041.client.model.item.gun.ModelRPK;
import by.fxg.metro2041.common.item.gun.ItemGun;
import by.fxg.metro2041.common.player.ExtendedPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderRPK extends RenderGun {
	private RightHand rh;
	private LeftHand lh;
	private float shoots = 0.0F;
	
	public RenderRPK() {
		lh = new LeftHand();
		rh = new RightHand();
	}

	protected void onTick(ItemStack itemstack) {
		ItemGun gun = (ItemGun) itemstack.getItem();
	}

	protected void renderGunThirdPerson(Entity entityplayer, ItemStack itemstack) {
		this.mc.renderEngine.bindTexture(new ResourceLocation("metro:textures/models/guns/" + this.getTexture() + ".png"));
		GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
		GL11.glScalef(0.3F, 0.3F, 0.3F);
		GL11.glTranslatef(0.0F, 0.4F, 2.0F);
		this.gunModel.render();
	}

	protected void renderHand(EntityPlayer var1, ItemStack var2) {
	}

	protected void renderGunFirstPerson(EntityPlayer entityplayer, ItemStack itemstack) {
		this.mc.renderEngine.bindTexture(new ResourceLocation("metro:textures/models/guns/" + this.getTexture() + ".png"));
		GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(65.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
		GL11.glScalef(2.5F, 2.5F, 2.5F);
		GL11.glTranslatef(0.5F, 0.9F, 1.0F);
		GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
		if (itemstack.hasTagCompound()) GL11.glRotatef(itemstack.getTagCompound().getFloat("recoil") * 1.5F, 1.0F, 0.0F, 0.0F);
		this.gunModel.render();
	}

	protected void renderGunFirstPersonAiming(EntityPlayer entityplayer, ItemStack itemstack) {
		GunAnimation animation = ClientAnimationManager.instance.getCurrentAnimation();
		if (animation != null) {
			animation.doRender(itemstack, this.partialTick, true);
		}
		this.mc.renderEngine.bindTexture(new ResourceLocation("metro:textures/models/guns/" + this.getTexture() + ".png"));
		GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(65.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
		GL11.glScalef(0.3F, 0.3F, 0.3F);
		GL11.glTranslatef(-3.475F, -1.8F, 0.0F);
		GL11.glRotatef(5.25F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(0.0025F, 0.02F, -0.4F);
		if (itemstack.hasTagCompound()) GL11.glRotatef(itemstack.getTagCompound().getFloat("recoil"), 1.0F, 0.0F, 0.0F);
		this.gunModel.render();
	}

	protected void renderGunOnGround(Entity entity, ItemStack itemstack) {
		this.mc.renderEngine.bindTexture(new ResourceLocation("metro:textures/models/guns/" + this.getTexture() + ".png"));
		GL11.glRotatef(270.0F, 0.0F, 0.0F, 1.0F);
		double scale = 0.5D;
		GL11.glScaled(scale, scale, scale);
		GL11.glTranslatef(0.0F, 0.3F, -1.0F);
		this.gunModel.render();
	}
	
	@Override
	protected void renderInventory(ItemStack var1) {
		this.mc.renderEngine.bindTexture(new ResourceLocation("metro:textures/models/guns/" + this.getTexture() + ".png"));
		GL11.glScalef(4.0F, 4.0F, 4.0F);
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(40.0F, 1.0F, 0.0F, 0.0F);
		GL11.glTranslatef(0.0F, 3.0F, -1.0F);
		this.gunModel.render();
	}

	protected VModel getGunModel() {
		return new ModelRPK();
	}

	protected String getTexture() {
		return "rpk";
	}

	@Override
	protected void renderAccessories(ItemStack var1) {
	}
}
