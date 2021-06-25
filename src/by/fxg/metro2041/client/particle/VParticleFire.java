package by.fxg.metro2041.client.particle;

import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class VParticleFire extends EntityFX {

	protected VParticleFire(World w, double x, double y, double z) {
		super(w, x, y, z);
	}
	
	public VParticleFire setNoclip(boolean par1) {
		this.noClip = par1;
		return this;
	}
	
	public VParticleFire setAirBorne(boolean par1) {
		this.isAirBorne = par1;
		return this;
	}
	
	public VParticleFire setImmuneToFire(boolean par1) {
		this.isImmuneToFire = par1;
		return this;
	}
	
	public VParticleFire setIsInWeb(boolean par1) {
		this.isInWeb = par1;
		return this;
	}
	
	public void renderParticle(Tessellator t, float x, float y, float z, float xx, float yy, float zz) {
		
    }

	public int getBrightnessForRender(float par1) {
		return 15728880;
	}

	public float getBrightness(float par1) {
		return 1.0F;
	}
}
