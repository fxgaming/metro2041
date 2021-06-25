package by.fxg.metro2041.client.model.block;

import by.fxg.metro2041.client.model.VModel;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRailQuad0 extends VModel {
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;

	public ModelRailQuad0() {
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0).addBox(0F, 0F, 0F, 1, 2, 24);
		Shape1.setTextureSize(64, 32).setRotationPoint(-15F, 22F, -12F);
		setRotation(Shape1, 0F, 0F, 0F);
		Shape1.mirror = true;
		Shape2 = new ModelRenderer(this, 0, 0).addBox(0F, 0F, 0F, 1, 2, 24);
		Shape2.setTextureSize(64, 32).setRotationPoint(14F, 22F, -12F);
		setRotation(Shape2, 0F, 0F, 0F);
		Shape2.mirror = true;
		Shape3 = new ModelRenderer(this, 0, 0).addBox(0F, 0F, 0F, 24, 2, 1);
		Shape3.setTextureSize(64, 32).setRotationPoint(-12F, 22F, 14F);
		setRotation(Shape3, 0F, 0F, 0F);
		Shape3.mirror = true;
		Shape4 = new ModelRenderer(this, 0, 0).addBox(0F, 0F, 0F, 24, 2, 1);
		Shape4.setTextureSize(64, 32).setRotationPoint(-12F, 22F, -15F);
		setRotation(Shape4, 0F, 0F, 0F);
		Shape4.mirror = true;
		Shape5 = new ModelRenderer(this, 0, 0).addBox(0F, 0F, 0F, 24, 1, 2);
		Shape5.setTextureSize(64, 32).setRotationPoint(-12F, 21F, 13.5F);
		setRotation(Shape5, 0F, 0F, 0F);
		Shape5.mirror = true;
		Shape6 = new ModelRenderer(this, 0, 0).addBox(0F, 0F, 0F, 24, 1, 2);
		Shape6.setTextureSize(64, 32).setRotationPoint(-12F, 21F, -15.5F);
		setRotation(Shape6, 0F, 0F, 0F);
		Shape6.mirror = true;
		Shape7 = new ModelRenderer(this, 0, 0).addBox(0F, 0F, 0F, 2, 1, 24);
		Shape7.setTextureSize(64, 32).setRotationPoint(-15.5F, 21F, -12F);
		setRotation(Shape7, 0F, 0F, 0F);
		Shape7.mirror = true;
		Shape8 = new ModelRenderer(this, 0, 0).addBox(0F, 0F, 0F, 2, 1, 24);
		Shape8.setTextureSize(64, 32).setRotationPoint(13.5F, 21F, -12F);
		setRotation(Shape8, 0F, 0F, 0F);
		Shape8.mirror = true;
	}

	public void render() {
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
	}
}
