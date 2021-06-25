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

public class BlockDecoration extends Block implements ITileEntityProvider {
	private String tilename = "";
	public int maxRotation = 4;
	
	public BlockDecoration(int par1, Material par2Material, String name, String tilename, int maxRotation) {
		super(par1, par2Material);
		this.setUnlocalizedName(name);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.tilename = tilename;
		this.maxRotation = maxRotation;
	}
	
	public BlockDecoration setName(String n) {
		LanguageRegistry.addName(this, n);
		return this;
	}

	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack itemStack) {
		int facing = MathHelper.floor_double((double) (entityliving.rotationYaw * (float)this.maxRotation / 360.0F) + 0.5D) & (this.maxRotation - 1);
		TileEntity te = world.getBlockTileEntity(i, j, k);
		if (te != null && te instanceof VTileBase) {
			((VTileBase)te).setFacing(facing);
			world.markBlockForUpdate(i, j, k);
		}
	}

	public TileEntity createTileEntity(World world, int metadata) {
		if (!this.tilename.equals("")) {
			try {
				Class clazz = Class.forName("by.fxg.metro2041.common.tile." + this.tilename);
				return (TileEntity)clazz.newInstance();
			} catch (Exception e) {
				System.out.println("[ERROR] Skipped creating tileEntity for " + this.getClass().getSimpleName() + ", mapping is missing.");
				return null;
			}
		}
		return null;
	}
	
	public int getRenderType() {
		return -1;
	}
	
	public boolean renderAsNormalBlock() {
        return false;
    }
	
	public boolean isOpaqueCube() {
		return false;
	}
	
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
}
