package by.fxg.metro2041.common.block;

import by.fxg.metro2041.Metro;
import by.fxg.metro2041.common.tile.VTileInventory;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class VBlockGuiBase extends VBlockBase {
	private String tilename = "";
	private int guid;

	public VBlockGuiBase(int id, String tile, int guid) {
		super(id);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.tilename = tile;
		this.guid = guid;
		this.setResistance(5.0F);
		this.setHardness(2.0F);
	}

	public VBlockGuiBase install(String unlocalizedName) {
		this.setUnlocalizedName("mgui." + unlocalizedName);
		return this;
	}
	
	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
		if (par1World.isRemote) {
			return true;
		} else if (!player.isSneaking()) {
			VTileInventory var10 = (VTileInventory)par1World.getBlockTileEntity(x, y, z);
			if (var10 != null) {
				player.openGui(Metro.instance, this.guid, par1World, x, y, z);
			}
			return true;
		} else {
			return false;
		}
	}

	public TileEntity createNewTileEntity(World world) {
		return null;
	}

	public TileEntity createTileEntity(World world, int metadata) {
		if (!this.tilename.equals("")) {
			try {
				Class clazz = Class.forName("by.fxg.metro2041.common.tile." + this.tilename);
				return (TileEntity)clazz.newInstance();
			} catch (Exception e) {
				System.out.println("[ERROR] Skipped creating tileEntity for " + this.getClass().getSimpleName() + ", because mapping is missing.");
				return null;
			}
		}
		return null;
	}

	public int getRenderType() {
		return -1;
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public void registerIcons(IconRegister par1IconRegister) {
		super.blockIcon = par1IconRegister.registerIcon("metro:machine");
	}
}
