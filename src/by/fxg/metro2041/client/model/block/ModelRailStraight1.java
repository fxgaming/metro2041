package by.fxg.metro2041.client.model.block;

import by.fxg.metro2041.client.model.VModel;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRailStraight1 extends VModel {
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;

	public ModelRailStraight1() {
		textureWidth = 64;
		textureHeight = 32;

		Shape1 = new ModelRenderer(this, 0, 0).addBox(0F, -2F, 0F, 1, 2, 24);
		Shape1.setTextureSize(64, 32).setRotationPoint(-15F, 24F, -12F);
		setRotation(Shape1, 0F, 0F, 0F);
		Shape1.mirror = true;
		Shape2 = new ModelRenderer(this, 0, 0).addBox(0F, -2F, 0F, 1, 2, 24);
		Shape2.setTextureSize(64, 32).setRotationPoint(14F, 24F, -12F);
		setRotation(Shape2, 0F, 0F, 0F);
		Shape2.mirror = true;
		Shape3 = new ModelRenderer(this, 0, 0).addBox(-1F, -1F, 0F, 2, 1, 24);
		Shape3.setTextureSize(64, 32).setRotationPoint(-14.5F, 22F, -12F);
		setRotation(Shape3, 0F, 0F, 0F);
		Shape3.mirror = true;
		Shape4 = new ModelRenderer(this, 0, 0).addBox(0F, 0F, 0F, 2, 1, 24);
		Shape4.setTextureSize(64, 32).setRotationPoint(13.5F, 21F, -12F);
		setRotation(Shape4, 0F, 0F, 0F);
		Shape4.mirror = true;
	}

	public void render() {
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
	}
}
