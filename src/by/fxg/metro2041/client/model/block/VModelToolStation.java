package by.fxg.metro2041.client.model.block;

import by.fxg.metro2041.client.model.VModel;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class VModelToolStation extends VModel {
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer Shape12;
	ModelRenderer Shape13;

	public VModelToolStation() {
		textureWidth = 64;
		textureHeight = 64;

		Shape1 = new ModelRenderer(this, 0, 47);
		Shape1.addBox(0F, 0F, 0F, 16, 1, 16);
		Shape1.setRotationPoint(-8F, 23F, -8F);
		Shape1.setTextureSize(64, 64);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 47);
		Shape2.addBox(0F, 0F, 0F, 16, 8, 1);
		Shape2.setRotationPoint(-8F, 15F, 7F);
		Shape2.setTextureSize(64, 64);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 32, 39);
		Shape3.addBox(0F, 0F, 0F, 1, 8, 15);
		Shape3.setRotationPoint(7F, 15F, -8F);
		Shape3.setTextureSize(64, 64);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 32, 39);
		Shape4.addBox(0F, 0F, 0F, 1, 8, 15);
		Shape4.setRotationPoint(-8F, 15F, -8F);
		Shape4.setTextureSize(64, 64);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		Shape5 = new ModelRenderer(this, 0, 47);
		Shape5.addBox(0F, 0F, 0F, 16, 1, 16);
		Shape5.setRotationPoint(-8F, 14F, -8F);
		Shape5.setTextureSize(64, 64);
		Shape5.mirror = true;
		setRotation(Shape5, 0F, 0F, 0F);
		Shape6 = new ModelRenderer(this, 30, 22);
		Shape6.addBox(0F, 0F, 0F, 16, 8, 1);
		Shape6.setRotationPoint(-8F, 6F, 7F);
		Shape6.setTextureSize(64, 64);
		Shape6.mirror = true;
		setRotation(Shape6, 0F, 0F, 0F);
		Shape7 = new ModelRenderer(this, 0, 29);
		Shape7.addBox(0F, 0F, 0F, 1, 17, 1);
		Shape7.setRotationPoint(-8F, 6F, 7F);
		Shape7.setTextureSize(64, 64);
		Shape7.mirror = true;
		setRotation(Shape7, -1.082104F, 0F, 0F);
		Shape8 = new ModelRenderer(this, 0, 29);
		Shape8.addBox(0F, 0F, 0F, 1, 17, 1);
		Shape8.setRotationPoint(7F, 6F, 7F);
		Shape8.setTextureSize(64, 64);
		Shape8.mirror = true;
		setRotation(Shape8, -1.082104F, 0F, 0F);
		Shape9 = new ModelRenderer(this, 4, 39);
		Shape9.addBox(0F, 0F, 0F, 3, 3, 5);
		Shape9.setRotationPoint(5F, 11F, 1F);
		Shape9.setTextureSize(64, 64);
		Shape9.mirror = true;
		setRotation(Shape9, 0F, 0F, 0F);
		Shape10 = new ModelRenderer(this, 20, 38);
		Shape10.addBox(0F, 0F, 0F, 7, 4, 5);
		Shape10.setRotationPoint(-8F, 10F, 2F);
		Shape10.setTextureSize(64, 64);
		Shape10.mirror = true;
		setRotation(Shape10, 0F, 0F, 0F);
		Shape11 = new ModelRenderer(this, 24, 13);
		Shape11.addBox(0F, 0F, 0F, 12, 1, 8);
		Shape11.setRotationPoint(-6F, 13.5F, -7F);
		Shape11.setTextureSize(64, 64);
		Shape11.mirror = true;
		setRotation(Shape11, 0F, 0F, 0F);
		Shape12 = new ModelRenderer(this, 34, 4);
		Shape12.addBox(0F, 0F, 0F, 14, 8, 1);
		Shape12.setRotationPoint(-7F, 15F, -8F);
		Shape12.setTextureSize(64, 64);
		Shape12.mirror = true;
		setRotation(Shape12, -0.0349066F, 0.0174533F, 0.0523599F);
		Shape13 = new ModelRenderer(this, 58, 2);
		Shape13.addBox(0F, 0F, 0F, 2, 1, 1);
		Shape13.setRotationPoint(3F, 19F, -9F);
		Shape13.setTextureSize(64, 64);
		Shape13.mirror = true;
		setRotation(Shape13, 0F, 0F, 0F);
	}

	@Override
	public void render() {
		float f5 = 0.0625F;
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
		Shape9.render(f5);
		Shape10.render(f5);
		Shape11.render(f5);
		Shape12.render(f5);
		Shape13.render(f5);
	}
}
