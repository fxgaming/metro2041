package by.fxg.metro2041.client.model.item;

import by.fxg.metro2041.client.model.VModel;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class VModelBook extends VModel {
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;

	public VModelBook() {
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(-6F, 0F, 0F, 6, 1, 8);
		Shape1.setRotationPoint(0F, 0F, -4F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0.6108652F);
		Shape2 = new ModelRenderer(this, 0, 9);
		Shape2.addBox(0F, 0F, 0F, 6, 1, 8);
		Shape2.setRotationPoint(0F, 0F, -4F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, -0.6108652F);
		Shape3 = new ModelRenderer(this, 19, 0);
		Shape3.addBox(-7F, 0F, 0F, 7, 0, 9);
		Shape3.setRotationPoint(0F, 1.3F, -4.5F);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0.6108652F);
		Shape4 = new ModelRenderer(this, 33, 0);
		Shape4.addBox(0F, 0F, 0F, 7, 0, 9);
		Shape4.setRotationPoint(0F, 1.3F, -4.5F);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, -0.6108652F);
		Shape5 = new ModelRenderer(this, 28, 9);
		Shape5.addBox(0F, 0F, 0F, 1, 1, 8);
		Shape5.setRotationPoint(0F, -0.2F, -4F);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0.7853982F);
	}

	@Override
	public void render() {
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
	}
}
