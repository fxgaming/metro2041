package by.fxg.metro2041.client.particle;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.src.ModLoader;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class VParticleWisp extends EntityFX {
	public boolean shrink;
	float moteParticleScale;
	int moteHalfLife;
	public boolean tinkle;
	public int blendmode;
	public int mode = 0;

	public VParticleWisp(World var1, double var2, double var4, double var6, float var8, float var9, float var10) {
		this(var1, var2, var4, var6, 1.0F, var8, var9, var10);
	}

	public VParticleWisp(World var1, double var2, double var4, double var6, float var8, float var9, float var10, float var11) {
		super(var1, var2, var4, var6, 0.0D, 0.0D, 0.0D);
		this.shrink = false;
		this.tinkle = false;
		this.blendmode = 1;
		if (var9 == 0.0F) {
			var9 = 1.0F;
		}

		super.particleRed = var9;
		super.particleGreen = var10;
		super.particleBlue = var11;
		super.particleGravity = 0.0F;
		super.motionZ = 0.0D;
		super.motionY = 0.0D;
		super.motionX = 0.0D;
		super.particleScale *= var8;
		this.moteParticleScale = super.particleScale;
		super.particleMaxAge = (int) (36.0D / (Math.random() * 0.3D + 0.7D));
		this.moteHalfLife = super.particleMaxAge / 2;
		super.noClip = false;
		this.setSize(0.01F, 0.01F);
		EntityLivingBase var12 = ModLoader.getMinecraftInstance().renderViewEntity;
		byte var13 = 50;
		if (!ModLoader.getMinecraftInstance().gameSettings.fancyGraphics) {
			var13 = 25;
		}

		if (var12.getDistance(super.posX, super.posY, super.posZ) > (double) var13) {
			super.particleMaxAge = 0;
		}

		super.prevPosX = super.posX;
		super.prevPosY = super.posY;
		super.prevPosZ = super.posZ;
	}

	public VParticleWisp(World var1, double var2, double var4, double var6, float var8, int var9) {
		this(var1, var2, var4, var6, var8, 0.0F, 0.0F, 0.0F);
		switch (var9) {
		case 0:
			super.particleRed = 0.75F + var1.rand.nextFloat() * 0.25F;
			super.particleGreen = 0.25F + var1.rand.nextFloat() * 0.25F;
			super.particleBlue = 0.75F + var1.rand.nextFloat() * 0.25F;
			break;
		case 1:
			super.particleRed = 0.5F + var1.rand.nextFloat() * 0.3F;
			super.particleGreen = 0.5F + var1.rand.nextFloat() * 0.3F;
			super.particleBlue = 0.2F;
			break;
		case 2:
			super.particleRed = 0.2F;
			super.particleGreen = 0.2F;
			super.particleBlue = 0.7F + var1.rand.nextFloat() * 0.3F;
			break;
		case 3:
			super.particleRed = 0.2F;
			super.particleGreen = 0.7F + var1.rand.nextFloat() * 0.3F;
			super.particleBlue = 0.2F;
			break;
		case 4:
			super.particleRed = 0.7F + var1.rand.nextFloat() * 0.3F;
			super.particleGreen = 0.2F;
			super.particleBlue = 0.2F;
			break;
		case 5:
			this.blendmode = 771;
			super.particleRed = var1.rand.nextFloat() * 0.1F;
			super.particleGreen = var1.rand.nextFloat() * 0.1F;
			super.particleBlue = var1.rand.nextFloat() * 0.1F;
			break;
		case 6:
			super.particleRed = 0.8F + var1.rand.nextFloat() * 0.2F;
			super.particleGreen = 0.8F + var1.rand.nextFloat() * 0.2F;
			super.particleBlue = 0.8F + var1.rand.nextFloat() * 0.2F;
		}

	}
	
	public VParticleWisp setAge(int a) {
		super.particleMaxAge = a;
		return this;
	}

	public VParticleWisp(World var1, double var2, double var4, double var6, double var8, double var10, double var12,
			float var14, int var15) {
		this(var1, var2, var4, var6, var14, var15);
		if (super.particleMaxAge > 0) {
			double var16 = var8 - super.posX;
			double var18 = var10 - super.posY;
			double var20 = var12 - super.posZ;
			super.motionX = var16 / (double) super.particleMaxAge;
			super.motionY = var18 / (double) super.particleMaxAge;
			super.motionZ = var20 / (double) super.particleMaxAge;
		}

	}

	public VParticleWisp(World var1, double var2, double var4, double var6, double var8, double var10, double var12,
			float var14, float var15, float var16, float var17) {
		this(var1, var2, var4, var6, var14, var15, var16, var17);
		if (super.particleMaxAge > 0) {
			double var18 = var8 - super.posX;
			double var20 = var10 - super.posY;
			double var22 = var12 - super.posZ;
			super.motionX = var18 / (double) super.particleMaxAge;
			super.motionY = var20 / (double) super.particleMaxAge;
			super.motionZ = var22 / (double) super.particleMaxAge;
		}

	}

	public void renderParticle(Tessellator var1, float var2, float var3, float var4, float var5, float var6, float var7) {
		this.mode++;
		if (this.mode == 16) this.mode = 0;
		float var8 = 0.0F;
		if (this.shrink) {
			var8 = (float) ((super.particleMaxAge - super.particleAge) / super.particleMaxAge);
		} else {
			var8 = (float) (super.particleAge / this.moteHalfLife);
			if (var8 > 1.0F) {
				var8 = 2.0F - var8;
			}
		}

		super.particleScale = this.moteParticleScale * var8;
		var1.draw();
		GL11.glPushMatrix();
		GL11.glDepthMask(false);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, this.blendmode);
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("metro:textures/fx/wisp" + this.mode + ".png"));
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
		float var9 = 0.5F * super.particleScale;
		float var10 = (float) (super.prevPosX + (super.posX - super.prevPosX) * (double) var2 - EntityFX.interpPosX);
		float var11 = (float) (super.prevPosY + (super.posY - super.prevPosY) * (double) var2 - EntityFX.interpPosY);
		float var12 = (float) (super.prevPosZ + (super.posZ - super.prevPosZ) * (double) var2 - EntityFX.interpPosZ);
		var1.startDrawingQuads();
		var1.setBrightness(240);
		var1.setColorRGBA_F(super.particleRed, super.particleGreen, super.particleBlue, 0.5F);
		var1.addVertexWithUV((double) (var10 - var3 * var9 - var6 * var9), (double) (var11 - var4 * var9),
				(double) (var12 - var5 * var9 - var7 * var9), 0.0D, 1.0D);
		var1.addVertexWithUV((double) (var10 - var3 * var9 + var6 * var9), (double) (var11 + var4 * var9),
				(double) (var12 - var5 * var9 + var7 * var9), 1.0D, 1.0D);
		var1.addVertexWithUV((double) (var10 + var3 * var9 + var6 * var9), (double) (var11 + var4 * var9),
				(double) (var12 + var5 * var9 + var7 * var9), 1.0D, 0.0D);
		var1.addVertexWithUV((double) (var10 + var3 * var9 - var6 * var9), (double) (var11 - var4 * var9),
				(double) (var12 + var5 * var9 - var7 * var9), 0.0D, 0.0D);
		var1.draw();
		GL11.glDisable(3042);
		GL11.glDepthMask(true);
		GL11.glPopMatrix();
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("metro:textures/fx/particles.png"));
		var1.startDrawingQuads();
	}

	public void onUpdate() {
		super.prevPosX = super.posX;
		super.prevPosY = super.posY;
		super.prevPosZ = super.posZ;
		if (super.particleAge == 0 && this.tinkle && super.worldObj.rand.nextInt(3) == 0) {
			super.worldObj.playSoundAtEntity(this, "random.orb", 0.02F,
					0.5F * ((super.worldObj.rand.nextFloat() - super.worldObj.rand.nextFloat()) * 0.6F + 2.0F));
		}

		if (super.particleAge++ >= super.particleMaxAge) {
			this.setDead();
		}

		super.motionY -= 0.04D * (double) super.particleGravity;
		super.posX += super.motionX;
		super.posY += super.motionY;
		super.posZ += super.motionZ;
		super.motionX *= 0.9800000190734863D;
		super.motionY *= 0.9800000190734863D;
		super.motionZ *= 0.9800000190734863D;
		if (super.onGround) {
			super.motionX *= 0.699999988079071D;
			super.motionZ *= 0.699999988079071D;
		}

	}

	public void setGravity(float var1) {
		super.particleGravity = var1;
	}
}
