package by.fxg.metro2041.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;

public class VBlockTextured extends Block {
	private String texture = "";
	
	public VBlockTextured(int id, String tile) {
		super(id, Material.rock);
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setResistance(25.0F);
		this.setHardness(2.0F);
		this.texture = tile;
	}

	public VBlockTextured install(String unlocalizedName) {
		this.setUnlocalizedName("mblock." + unlocalizedName);
		return this;
	}

	public void registerIcons(IconRegister par1IconRegister) {
		super.blockIcon = par1IconRegister.registerIcon("metro:" + texture);
	}
}
