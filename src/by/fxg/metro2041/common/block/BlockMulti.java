package by.fxg.metro2041.common.block;

import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMulti extends Block {
	@SideOnly(Side.CLIENT)
	private Icon[] iconArray;
	public int count;
	
	public BlockMulti(int par1, Material par2Material, String name, int count) {
		super(par1, par2Material);
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.count = count;
	}
	
	public BlockMulti setName(String n) {
		LanguageRegistry.addName(this, n);
		return this;
	}
	
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {
		if (par2 >= 0 && par2 < this.iconArray.length) {
			return this.iconArray[par2];
		}
		return null;
	}
	
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		par1World.setBlockMetadataWithNotify(par2, par3, par4, par6ItemStack.getItemDamage(), 2);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		this.iconArray = new Icon[this.count];
		for (int i = 0; i < this.iconArray.length; ++i) {
			this.iconArray[i] = par1IconRegister.registerIcon("metro:" + this.getUnlocalizedName().substring(5) + i);
		}
	}
	
	public int damageDropped(int par1) {
		return par1;
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int i = 0; i != this.count; i++) {
			par3List.add(new ItemStack(par1, 1, i));
		}
	}
}


	