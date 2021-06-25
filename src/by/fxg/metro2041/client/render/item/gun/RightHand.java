package by.fxg.metro2041.client.render.item.gun;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class RightHand extends ModelBiped {
	public ModelRenderer RightArm;

	public RightHand() {
		this(0.0F);
	}

	public RightHand(float par1) {
		this(par1, 0.0F, 64, 32);
	}

	public RightHand(float par1, float par2, int par3, int par4) {
		this.textureWidth = par3;
		this.textureHeight = par4;
		this.RightArm = new ModelRenderer(this, 40, 16);
		this.RightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, par1);
		this.RightArm.setRotationPoint(-5.0F, 2.0F + par2, 0.0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		super.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().thePlayer.getLocationSkin());
		this.RightArm.render(0.0625f);
	}

	public void render(EntityPlayer p) {
		Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().thePlayer.getLocationSkin());
		this.RightArm.render(0.0625f);
	}

	public void render(float f, EntityPlayer p) {
		Minecraft.getMinecraft().renderEngine.bindTexture(Minecraft.getMinecraft().thePlayer.getLocationSkin());
		this.RightArm.render(f);
	}
}
