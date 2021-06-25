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

public class BlockRail extends BlockDecoration {
	public BlockRail(int par1, Material par2Material, String name, String tilename, int maxRotation) {
		super(par1, par2Material, name, tilename, maxRotation);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.05F, 1.0F);
	}
}
