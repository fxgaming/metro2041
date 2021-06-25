package by.fxg.metro2041.client.anim;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class GA_RPK_Fired extends GunAnimation {
	private float rotation1 = 0.0F;
	private float lastRotation1 = 0.0F;
	private float maxRotation1 = 2.0F;
	private float trans1 = 0.0F;
	private float lastTrans1 = 0.0F;
	private float maxTrans1 = 0.3F;
	private float addRangerness = 0.2F;
	private boolean up = true;

	public void onUpdate(Minecraft par1, EntityPlayer par2, ItemStack par3) {
		this.lastRotation1 = this.rotation1;
		this.lastTrans1 = this.trans1;
		float transSpeed = 0.025F; // 0.25f
		if (super.animationTicker == 5) {
			this.up = false;
		}
		if (this.up) {
			this.trans1 += transSpeed * 5;
		} else {
			this.trans1 -= transSpeed;
		}
		if (this.trans1 > this.maxTrans1) {
			this.trans1 = this.maxTrans1;
		}
		if (this.trans1 < 0.0F) {
			this.trans1 = 0.0F;
		}
	}

	public void doRender(ItemStack par1, float par2, boolean isScope) {
		float transprogress = this.lastTrans1 + (this.trans1 - this.lastTrans1) * par2;
		if (isScope) {
			GL11.glTranslatef(-transprogress / 7.5F, -transprogress / 12.5F, -transprogress / 100.0F);
		} else {
			GL11.glTranslatef(-transprogress / 1.5F, -transprogress / 1.5F, 0.0F);
		}
	}

	public void doRenderHand(ItemStack par1, float par2, boolean par3) {
		float transprogress;
		float rotprogress;
		if (par3) {
			transprogress = this.lastTrans1 + (this.trans1 - this.lastTrans1) * par2;
			GL11.glTranslatef(-transprogress, 0.0F, 0.0F);
			rotprogress = this.lastRotation1 + (this.rotation1 - this.lastRotation1) * par2;
			GL11.glRotatef(-rotprogress, 0.0F, 0.0F, 1.0F);
		} else {
			transprogress = this.lastTrans1 + (this.trans1 - this.lastTrans1) * par2;
			GL11.glTranslatef(-transprogress, -transprogress * 0.5F, 0.0F);
			rotprogress = this.lastRotation1 + (this.rotation1 - this.lastRotation1) * par2;
			GL11.glRotatef(-rotprogress, 0.0F, 0.0F, 1.0F);
		}

	}

	public void doRenderAmmo(ItemStack par1, float par2) {
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
