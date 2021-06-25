package by.fxg.metro2041.client.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class VModelArmor extends ModelBipedEx {
	public void render(Entity e, float f1, float f2, float f3, float f4, float f5, float f6) {
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		super.render(e, f1, f2, f3, f4, f5, f6);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glPopMatrix();
	}

	public void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
