package by.fxg.metro2041.client.model.block;

import by.fxg.metro2041.client.model.VModel;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRailStraight45Right0 extends VModel {
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape6;
	ModelRenderer Shape5;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;

	public ModelRailStraight45Right0() {
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0).addBox(-0.5F, -2F, 0F, 1, 2, 10);
		Shape1.setTextureSize(64, 32).setRotationPoint(14F, 24F, -7.5F);
		setRotation(Shape1, 0F, 0.7853982F, 0F);
		Shape1.mirror = true;
		Shape2 = new ModelRenderer(this, 0, 0).addBox(0F, -2F, 0F, 1, 2, 30);
		Shape2.setTextureSize(64, 32).setRotationPoint(-15F, 24F, -8F);
		setRotation(Shape2, 0F, 0F, 0F);
		Shape2.mirror = true;
		Shape3 = new ModelRenderer(this, 0, 0).addBox(-1.5F, -1F, 0F, 2, 1, 10);
		Shape3.setTextureSize(64, 32).setRotationPoint(14.5F, 22F, -7F);
		setRotation(Shape3, 0F, 0.7853982F, 0F);
		Shape3.mirror = true;
		Shape4 = new ModelRenderer(this, 0, 0).addBox(0F, 0F, 0F, 2, 1, 30);
		Shape4.setTextureSize(64, 32).setRotationPoint(-15.5F, 21F, -8F);
		setRotation(Shape4, 0F, 0F, 0F);
		Shape4.mirror = true;
		Shape6 = new ModelRenderer(this, 0, 0).addBox(-0.5F, -2F, 4F, 1, 2, 30);
		Shape6.setTextureSize(64, 32).setRotationPoint(-14F, 24F, 7.5F);
		setRotation(Shape6, 0F, 0.7853982F, 0F);
		Shape6.mirror = true;
		Shape5 = new ModelRenderer(this, 0, 0).addBox(-1.5F, 0F, 4F, 2, 1, 31);
		Shape5.setTextureSize(64, 32).setRotationPoint(-14F, 21F, 7F);
		setRotation(Shape5, 0F, 0.7853982F, 0F);
		Shape5.mirror = true;
		Shape7 = new ModelRenderer(this, 0, 0).addBox(0F, 0F, 0F, 2, 3, 2);
		Shape7.setTextureSize(64, 32).setRotationPoint(13F, 21F, -8F);
		setRotation(Shape7, 0F, 0F, 0F);
		Shape7.mirror = true;
		Shape8 = new ModelRenderer(this, 0, 0).addBox(0F, 0F, 0F, 1, 2, 30);
		Shape8.setTextureSize(64, 32).setRotationPoint(14F, 22F, -1F);
		setRotation(Shape8, 0F, 0F, 0F);
		Shape8.mirror = true;
		Shape9 = new ModelRenderer(this, 0, 0).addBox(-1F, 0F, 0F, 2, 1, 30);
		Shape9.setTextureSize(64, 32).setRotationPoint(14.5F, 21F, -1F);
		setRotation(Shape9, 0F, 0F, 0F);
		Shape9.mirror = true;
	}

	public void render() {
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape6.render(f5);
		Shape5.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
	}
}
