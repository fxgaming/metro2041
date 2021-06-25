package by.fxg.metro2041.client.particle;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.src.ModLoader;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class VParticlePortal extends EntityFX {
	public boolean shrink;
	float moteParticleScale;
	int moteHalfLife;
	public boolean tinkle;
	public int blendmode;

	public VParticlePortal(World world, double d, double d1, double d2, float f, float red, float green, float blue) {
		super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);
		this.shrink = false;
		this.tinkle = false;
		this.blendmode = 1;
		if (red == 0.0F) {
			red = 1.0F;
		}

		super.particleRed = red;
		super.particleGreen = green;
		super.particleBlue = blue;
		super.particleGravity = 0.0F;
		super.motionX = super.motionY = super.motionZ = 0.0D;
		super.particleScale *= f;
		this.moteParticleScale = super.particleScale;
		super.particleMaxAge = (int) (36.0D / (Math.random() * 1.3D + 0.7D));
		this.moteHalfLife = super.particleMaxAge / 2;
		super.noClip = false;
		this.setSize(0.01F, 0.01F);
		EntityLivingBase renderentity = ModLoader.getMinecraftInstance().renderViewEntity;
		int visibleDistance = 50;
		if (!ModLoader.getMinecraftInstance().gameSettings.fancyGraphics) {
			visibleDistance = 25;
		}

		if (renderentity.getDistance(super.posX, super.posY, super.posZ) > (double) visibleDistance) {
			super.particleMaxAge = 0;
		}

		super.prevPosX = super.posX;
		super.prevPosY = super.posY;
		super.prevPosZ = super.posZ;
	}

	public VParticlePortal(World world, double d, double d1, double d2, float f, int type) {
		this(world, d, d1, d2, f, 0.0F, 0.0F, 0.0F);
		switch (type) {
		case 0:
			super.particleRed = 0.75F + world.rand.nextFloat() * 0.25F;
			super.particleGreen = 0.25F + world.rand.nextFloat() * 0.25F;
			super.particleBlue = 0.75F + world.rand.nextFloat() * 0.25F;
			break;
		case 1:
			super.particleRed = 0.5F + world.rand.nextFloat() * 0.3F;
			super.particleGreen = 0.5F + world.rand.nextFloat() * 0.3F;
			super.particleBlue = 0.2F;
			break;
		case 2:
			super.particleRed = 0.2F;
			super.particleGreen = 0.2F;
			super.particleBlue = 0.7F + world.rand.nextFloat() * 0.3F;
			break;
		case 3:
			super.particleRed = 0.2F;
			super.particleGreen = 0.7F + world.rand.nextFloat() * 0.3F;
			super.particleBlue = 0.2F;
			break;
		case 4:
			super.particleRed = 0.7F + world.rand.nextFloat() * 0.3F;
			super.particleGreen = 0.2F;
			super.particleBlue = 0.2F;
			break;
		case 5:
			this.blendmode = 771;
			super.particleRed = world.rand.nextFloat() * 0.1F;
			super.particleGreen = world.rand.nextFloat() * 0.1F;
			super.particleBlue = world.rand.nextFloat() * 0.1F;
			break;
		case 6:
			super.particleRed = 0.8F + world.rand.nextFloat() * 0.2F;
			super.particleGreen = 0.8F + world.rand.nextFloat() * 0.2F;
			super.particleBlue = 0.8F + world.rand.nextFloat() * 0.2F;
		}

	}

	public VParticlePortal(World world, double d, double d1, double d2, double x, double y, double z, float f, int type) {
		this(world, d, d1, d2, f, type);
		if (super.particleMaxAge > 0) {
			double dx = x - super.posX;
			double dy = y - super.posY;
			double dz = z - super.posZ;
			super.motionX = dx / (double) super.particleMaxAge;
			super.motionY = dy / (double) super.particleMaxAge;
			super.motionZ = dz / (double) super.particleMaxAge;
		}

	}

	public void setGravity(float value) {
		super.particleGravity = value;
	}

	public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
		float agescale = 2.0F;
		if (super.particleMaxAge > 0) {
			super.particleScale = this.moteParticleScale * agescale;
			tessellator.draw();
			GL11.glPushMatrix();
			GL11.glDepthMask(false);
			GL11.glEnable(3042);
			GL11.glBlendFunc(770, this.blendmode);
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("metro:textures/fx/portal.png"));
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
			float f10 = 0.5F * super.particleScale;
			float f11 = (float) (super.prevPosX + (super.posX - super.prevPosX) * (double) f - EntityFX.interpPosX);
			float f12 = (float) (super.prevPosY + (super.posY - super.prevPosY) * (double) f - EntityFX.interpPosY);
			float f13 = (float) (super.prevPosZ + (super.posZ - super.prevPosZ) * (double) f - EntityFX.interpPosZ);
			tessellator.startDrawingQuads();
			tessellator.setBrightness(240);
			tessellator.setColorRGBA_F(super.particleRed, super.particleGreen, super.particleBlue, 0.2F);
			tessellator.addVertexWithUV((double) (f11 - f1 * f10 - f4 * f10), (double) (f12 - f2 * f10),(double) (f13 - f3 * f10 - f5 * f10), 0.0D, 1.0D);
			tessellator.addVertexWithUV((double) (f11 - f1 * f10 + f4 * f10), (double) (f12 + f2 * f10),(double) (f13 - f3 * f10 + f5 * f10), 1.0D, 1.0D);
			tessellator.addVertexWithUV((double) (f11 + f1 * f10 + f4 * f10), (double) (f12 + f2 * f10),(double) (f13 + f3 * f10 + f5 * f10), 1.0D, 0.0D);
			tessellator.addVertexWithUV((double) (f11 + f1 * f10 - f4 * f10), (double) (f12 - f2 * f10),(double) (f13 + f3 * f10 - f5 * f10), 0.0D, 0.0D);
			tessellator.draw();
			GL11.glDisable(3042);
			GL11.glDepthMask(true);
			GL11.glPopMatrix();
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("metro:textures/fx/particles.png"));
			tessellator.startDrawingQuads();
		}

	}

	public void onUpdate() {
		super.prevPosX = super.posX;
		super.prevPosY = super.posY;
		super.prevPosZ = super.posZ;
		if (super.particleAge == 0 && this.tinkle && super.worldObj.rand.nextInt(3) == 0) {
			;
		}

		if (super.particleAge++ >= super.particleMaxAge) {
			this.setDead();
		}

		super.motionY -= 0.04D * (double) super.particleGravity;
		super.posX += super.motionX;
		super.posY += super.motionY * 6.0D;
		super.posZ += super.motionZ;
		super.motionX *= 0.9800000190734863D;
		super.motionY *= 0.8800000190734864D;
		super.motionZ *= 0.9800000190734863D;
		if (super.onGround) {
			super.motionX *= 0.699999988079071D;
			super.motionZ *= 0.699999988079071D;
		}

	}
}
