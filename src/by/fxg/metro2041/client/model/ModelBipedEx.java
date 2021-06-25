package by.fxg.metro2041.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;

public class ModelBipedEx extends ModelBiped {
	public EnumChildType Type;
	public void addChild(ModelRenderer mr, EnumChildType ect, boolean flag) {
		float posX = mr.rotationPointX;
		float posY = mr.rotationPointY;
		float posZ = mr.rotationPointZ;
		switch(ect) {
			case HEAD:
				break;
			case BODY:
				break;
			case L_ARM:
				posX -= 5.0F;
				posY -= 2.0F;
				break;
			case R_ARM:
				posX += 5.0F;
				posY -= 2.0F;
				break;
			case L_LEG:
				posX -= 2.0F;
				posY -= 12.0F;
				break;
			case R_LEG:
				posX += 2.0F;
				posY -= 12.0F;
				break;
			case L_LEG2:
				posX -= 2.0F;
				posY -= 14.0F;
				break;
			case R_LEG2:
				posX += 2.0F;
				posY -= 14.0F;
				break;
			case L_BLEG:
				posX -= 2.0F;
				posY -= 12.0F;
				break;
			case R_BLEG:
				posX += 2.0F;
				posY -= 12.0F;
				break;
		}
		if (flag && mr instanceof ModelRendererEx) {
			ModelRendererEx mre = (ModelRendererEx)mr;
			posX += (float)mre.rotX;
			posY += (float)mre.rotY;
			posZ += (float)mre.rotZ;
		}
		mr.rotationPointX = posX;
		mr.rotationPointY = posY;
		mr.rotationPointZ = posZ;
		switch (ect) {
		case HEAD:
			super.bipedHead.addChild(mr);
			break;
		case BODY:
			super.bipedBody.addChild(mr);
			break;
		case L_ARM:
			super.bipedLeftArm.addChild(mr);
			break;
		case R_ARM:
			super.bipedRightArm.addChild(mr);
			break;
		case L_LEG:
			super.bipedLeftLeg.addChild(mr);
			break;
		case R_LEG:
			super.bipedRightLeg.addChild(mr);
			break;
		case L_BLEG:
			super.bipedLeftLeg.addChild(mr);
			break;
		case R_BLEG:
			super.bipedRightLeg.addChild(mr);
			break;
		}
	}
	
	public void addChild(ModelRenderer mr, EnumChildType ect) {
		float posX = mr.rotationPointX;
		float posY = mr.rotationPointY;
		float posZ = mr.rotationPointZ;
		switch(ect) {
			case HEAD:
				break;
			case BODY:
				break;
			case L_ARM:
				posX -= 5.0F;
				posY -= 2.0F;
				break;
			case R_ARM:
				posX += 5.0F;
				posY -= 2.0F;
				break;
			case L_LEG:
				posX -= 2.0F;
				posY -= 12.0F;
				break;
			case R_LEG:
				posX += 2.0F;
				posY -= 12.0F;
				break;
			case L_LEG2:
				posX -= 2.0F;
				posY -= 14.0F;
				break;
			case R_LEG2:
				posX += 2.0F;
				posY -= 14.0F;
				break;
			case L_BLEG:
				posX -= 2.0F;
				posY -= 12.0F;
				break;
			case R_BLEG:
				posX += 2.0F;
				posY -= 12.0F;
				break;
		}
		mr.rotationPointX = posX;
		mr.rotationPointY = posY;
		mr.rotationPointZ = posZ;
		switch (ect) {
		case HEAD:
			super.bipedHead.addChild(mr);
			break;
		case BODY:
			super.bipedBody.addChild(mr);
			break;
		case L_ARM:
			super.bipedLeftArm.addChild(mr);
			break;
		case R_ARM:
			super.bipedRightArm.addChild(mr);
			break;
		case L_LEG:
			super.bipedLeftLeg.addChild(mr);
			break;
		case R_LEG:
			super.bipedRightLeg.addChild(mr);
			break;
		case L_BLEG:
			super.bipedLeftLeg.addChild(mr);
			break;
		case R_BLEG:
			super.bipedRightLeg.addChild(mr);
			break;
		}
	}
	
	public void translate(ModelRenderer mr, float... f) {
		if (f.length == 3) {
			float fx = mr.rotationPointX + f[0];
			float fy = mr.rotationPointY + f[1];
			float fz = mr.rotationPointZ + f[2];
			mr.rotationPointX = fx;
			mr.rotationPointY = fy;
			mr.rotationPointZ = fz;
		}
	}
}
