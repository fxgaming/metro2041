package by.fxg.metro2041.common.item;

import by.fxg.metro2041.client.ClientProxy;
import by.fxg.metro2041.util.Utils;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;

public class VItem extends Item {
	public float partColor[];
	public boolean isMagical;
	
	public VItem(int par1) {
		super(par1);
	}
	
	public VItem(int par1, int par2, String par3) {
		super(par1);
		this.setMaxStackSize(par2);
		this.setCreativeTab(CreativeTabs.tabMaterials);
		this.iconString = "metro:" + par3;
		this.setUnlocalizedName("metro.item:" + par3);
	}
	
	public VItem(int par1, int par2, String par3, CreativeTabs par4) {
		super(par1);
		this.setMaxStackSize(par2);
		this.setCreativeTab(par4);
		this.iconString = "metro:" + par3;
		this.setUnlocalizedName("metro.item:" + par3);
	}
	
	public VItem setMagical(float[] par1) {
		this.partColor = par1;
		this.isMagical = true;
		return this;
	}
	
	public VItem setName(String par1) {
		LanguageRegistry.addName(this, par1);
		return this;
	}
	
	@SideOnly(Side.CLIENT)
    public boolean onEntityItemUpdate(EntityItem par1) {
    	if (this.isMagical) {
    		if (par1.worldObj.isRemote) {
    			EntityFX entityfx = new EntitySpellParticleFX(par1.worldObj, par1.posX, par1.posY, par1.posZ, 0.0D, 0.1D, 0.0D);
                ((EntityFX)entityfx).setRBGColorF((float)this.partColor[0], (float)this.partColor[1], (float)this.partColor[2]);
                Minecraft.getMinecraft().effectRenderer.addEffect(entityfx);
    		}
    		return false;
    	}
        return false;
    } 
	
	public VItem set2DSpecial() {
		if (Utils.isClient()) {
			ClientProxy.itemSpecial2D.add(this.itemID);
		}
		return this;
	}
}
