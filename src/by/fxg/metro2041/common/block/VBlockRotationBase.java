package by.fxg.metro2041.common.block;

import java.util.Random;

import by.fxg.metro2041.common.tile.VTileBase;
import by.fxg.metro2041.common.tile.VTileInventory;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class VBlockRotationBase extends BlockContainer {
	public VBlockRotationBase(int id, Material mat) {
		super(id, mat);
	}

	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving, ItemStack itemStack) {
		int facing = MathHelper.floor_double((double) (entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
		TileEntity te = world.getBlockTileEntity(i, j, k);
		if (te != null && te instanceof VTileBase) {
			((VTileBase)te).setFacing(facing);
			world.markBlockForUpdate(i, j, k);
		}
	}
}
