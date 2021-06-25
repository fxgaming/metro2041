package by.fxg.metro2041.common.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class VOreBase extends VBlockRotationBase {
	private String tilename = "";
	private int itemid;
	private float hardness;

	public VOreBase(int id, String tile, int itemid, float hard) {
		super(id, Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.tilename = tile;
		this.itemid = itemid;
		this.hardness = hard;
		this.setResistance(hard);
		this.setHardness(hard);
	}

	public VOreBase install(String unlocalizedName) {
		this.setUnlocalizedName("more." + unlocalizedName);
		return this;
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
	
    public int quantityDropped(Random par1Random) {
        return 1;
    }

    public int idDropped(int par1, Random par2Random, int par3) {
        return this.itemid;
    }

	public boolean isOpaqueCube() {
		return false;
	}

	public void registerIcons(IconRegister par1IconRegister) {
		super.blockIcon = par1IconRegister.registerIcon("metro:orebase");
	}
}
