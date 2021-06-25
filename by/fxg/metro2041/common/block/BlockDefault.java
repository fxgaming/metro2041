package by.fxg.metro2041.common.block;

import by.fxg.metro2041.common.tile.VTileBase;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockDefault extends Block {
	public BlockDefault(int par1, Material par2Material, String name, String texture) {
		super(par1, par2Material);
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setTextureName(texture);
	}
	
	public BlockDefault setName(String n) {
		LanguageRegistry.addName(this, n);
		return this;
	}
}
