package by.fxg.metro2041.common.block;

import by.fxg.metro2041.common.tile.TilePortal;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPortal extends VBlockBase {
	public BlockPortal(int id) {
		super(id);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setResistance(25.0F);
		this.setHardness(5.0F);
		this.setUnlocalizedName("mblock.portal");
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.15F, 1.0F);
	}
	
	public void onEntityCollidedWithBlock(World w, int x, int y, int z, Entity e) {
		if (!w.isRemote) {
			System.out.println("" + e.getClass().getName());
			if (e != null && !(e instanceof EntityFX) && e instanceof EntityLiving || e instanceof EntityPlayerMP) {
				TilePortal tp = (TilePortal)w.getBlockTileEntity(x, y, z);
				if (tp.isWorking) {
					if (e instanceof EntityPlayerMP) ((EntityPlayerMP)e).setPositionAndUpdate(tp.xyzTeleport[0], tp.xyzTeleport[1], tp.xyzTeleport[2]);
					else ((EntityLiving)e).setPositionAndUpdate(tp.xyzTeleport[0], tp.xyzTeleport[1], tp.xyzTeleport[2]);
					tp.isWorking = false;
				}
			}
		}
	}

	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
		return false;
	}

	public TileEntity createNewTileEntity(World world) {
		return null;
	}

	public TileEntity createTileEntity(World world, int metadata) {
		return new TilePortal();
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
