package by.fxg.metro2041.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.TextureOffset;

public class ModelRendererEx extends ModelRenderer {
	public int textureOffsetX_double;
	public int textureOffsetY_double;
	public int rotX;
	public int rotY;
	public int rotZ;
	public ModelBase baseModel_double;
	
	public ModelRendererEx(ModelBase par1) {
		super(par1);
		this.baseModel_double = par1;
	}
	
	public ModelRendererEx(ModelBase par1, String par2) {
		super(par1, par2);
		this.baseModel_double = par1;
	}
	
	public ModelRendererEx(ModelBase par1, int par2, int par3) {
		super(par1, par2, par3);
		this.textureOffsetX_double = par2;
		this.textureOffsetY_double = par3;
		this.baseModel_double = par1;
    }
	
	@Override
	public ModelRenderer setTextureOffset(int par1, int par2) {
		this.textureOffsetX_double = par1;
		this.textureOffsetY_double = par2;
        super.setTextureOffset(par1, par2);
        return this;
    }
	
	@Override
    public ModelRenderer addBox(String par1Str, float par2, float par3, float par4, int par5, int par6, int par7) {
        par1Str = this.boxName + "." + par1Str;
        TextureOffset textureoffset = this.baseModel_double.getTextureOffset(par1Str);
        this.setTextureOffset(textureoffset.textureOffsetX, textureoffset.textureOffsetY);
        this.rotX = par5;
		this.rotY = par6;
		this.rotZ = par7;
		this.rotationPointX = (float)par5;
		this.rotationPointY = (float)par6;
		this.rotationPointZ = (float)par7;
        this.cubeList.add((new ModelBox(this, this.textureOffsetX_double, this.textureOffsetY_double, par2, par3, par4, 0, 0, 0, 0.0F)).func_78244_a(par1Str));
        return this;
    }
	
	@Override
	public ModelRenderer addBox(float par1, float par2, float par3, int par4, int par5, int par6) {
		this.rotX = par4;
		this.rotY = par5;
		this.rotZ = par6;
		this.rotationPointX = (float)par4;
		this.rotationPointY = (float)par5;
		this.rotationPointZ = (float)par6;
        this.cubeList.add(new ModelBox(this, this.textureOffsetX_double, this.textureOffsetY_double, par1, par2, par3, 0, 0, 0, 0.0F));
        return this;
	}

	@Override
    public void addBox(float par1, float par2, float par3, int par4, int par5, int par6, float par7) {
		this.rotX = par4;
		this.rotY = par5;
		this.rotZ = par6;
		this.rotationPointX = (float)par4;
		this.rotationPointY = (float)par5;
		this.rotationPointZ = (float)par6;
        this.cubeList.add(new ModelBox(this, this.textureOffsetX_double, this.textureOffsetY_double, par1, par2, par3, 0, 0, 0, par7));
    }
	
	@Override
    public void setRotationPoint(float par1, float par2, float par3) {
        this.rotationPointX += par1;
        this.rotationPointY += par2;
        this.rotationPointZ += par3;
    }
}
