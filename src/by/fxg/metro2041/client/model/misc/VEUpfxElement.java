package by.fxg.metro2041.client.model.misc;

import by.fxg.metro2041.client.model.VModelExtended;
import net.minecraft.client.model.ModelRenderer;

public class VEUpfxElement extends VModelExtended {
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;

	public VEUpfxElement() {
		textureWidth = 32;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 3, 3, 3);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(32, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0.7853982F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 0);
		Shape2.addBox(0F, 0F, 0F, 3, 3, 3);
		Shape2.setRotationPoint(-0.6F, -1.5F, 2.1F);
		Shape2.setTextureSize(32, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0.7853982F, 0F);
		Shape3 = new ModelRenderer(this, 0, 0);
		Shape3.addBox(0F, 0F, 0F, 3, 3, 3);
		Shape3.setRotationPoint(1.5F, -2.1F, 0.6F);
		Shape3.setTextureSize(32, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0.7853982F);
		Shape4 = new ModelRenderer(this, 12, -5);
		Shape4.addBox(0F, 0F, 0F, 0, 8, 5);
		Shape4.setRotationPoint(1.5F, -9F, -0.5F);
		Shape4.setTextureSize(32, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 12, 0);
		Shape5.addBox(0F, 0F, 0F, 5, 8, 0);
		Shape5.setRotationPoint(-1F, -9F, 2.1F);
		Shape5.setTextureSize(32, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
	}

	@Override
	public void render(int par1) {
		if (par1 == 1) {
			Shape4.render(f5);
			Shape5.render(f5);
		} else {
			Shape1.render(f5);
			Shape2.render(f5);
			Shape3.render(f5);	
		}
	}
}
