package by.fxg.metro2041.client.anim;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class GA_RPK_Reload extends GunAnimation {
	private float rotation1 = 0.0F;
	private float lastRotation1 = 0.0F;
	private float maxRotation1 = 55.0F;
	private float trans1 = -1.0F;
	private float lastTrans1 = 0.0F;
	private float maxTrans1 = 0.3F;
	private boolean up = true;
	public boolean ejectingClip = false;

	public GA_RPK_Reload() {
		this.ejectingClip = true;
		this.trans1 = 0.0F;
	}

	public GA_RPK_Reload(boolean par1) {
		this.ejectingClip = par1;
		this.trans1 = this.ejectingClip ? 0.0F : -1.0F;
	}

	public void setEjectingClip(boolean par1) {
		this.ejectingClip = par1;
		this.trans1 = this.ejectingClip ? 0.0F : -1.0F;
	}

	public void onUpdate(Minecraft par1, EntityPlayer par2, ItemStack par3) {
		if (super.animationTicker == 15) {
			this.up = false;
		}

		this.lastRotation1 = this.rotation1;
		this.lastTrans1 = this.trans1;
		float roation1Speed = 20.0F;
		float transSpeed = 0.2F;
		if (this.ejectingClip) {
			this.trans1 -= transSpeed;
		} else {
			this.trans1 += transSpeed;
			if (this.trans1 > 0.0F) {
				this.trans1 = 0.0F;
			}
		}

		if (this.up) {
			this.rotation1 += roation1Speed;
		} else {
			this.rotation1 -= roation1Speed;
		}

		if (this.rotation1 > this.maxRotation1) {
			this.rotation1 = this.maxRotation1;
		}

		if (this.rotation1 < 0.0F) {
			this.rotation1 = 0.0F;
		}

	}

	public void doRender(ItemStack par1, float par2, boolean par3) {
		float progress = this.lastRotation1 + (this.rotation1 - this.lastRotation1) * par2;
		GL11.glRotatef(-progress / 2, 0.0F, 0.0F, -1.0F);
		GL11.glRotatef(-progress / 2, -1.0F, 0.0F, 0.0F);
	}

	public void doRenderAmmo(ItemStack par1, float par2) {
		float transprogress;
		if (!this.ejectingClip) {
			transprogress = this.lastTrans1 + (this.trans1 - this.lastTrans1) * par2;
			GL11.glTranslatef(0.0F, -transprogress, 0.0F);
		} else {
			transprogress = this.lastTrans1 + (this.trans1 - this.lastTrans1) * par2;
			GL11.glTranslatef(0.0F, -transprogress, 0.0F);
		}
	}

	public void onAnimationStopped(ItemStack par1) {
	}

	public float getMaxAnimationTick() {
		return 20.0F;
	}

	@Override
	public void onRun(Object... par1) {
	}
}
