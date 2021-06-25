package by.fxg.metro2041.client.model.item.gun;

import by.fxg.metro2041.client.model.VModel;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFX extends VModel {
	ModelRenderer Shape1;

	public ModelFX() {
		textureWidth = 32;
		textureHeight = 16;
		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(0F, 0F, 0F, 16, 16, 0);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(32, 16);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
	}

	@Override
	public void render() {
		Shape1.render(f5);
	}
}
