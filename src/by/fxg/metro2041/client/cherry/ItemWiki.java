package by.fxg.metro2041.client.cherry;

import net.minecraft.item.ItemStack;

public class ItemWiki {
	public ItemStack itemStack; //1
	public ItemStack[] crafting; //9
	public int idcraft;
	public String infoText;
	public String whereFindText;
	public String whereUseText;
	public String rareness;
	
	public ItemWiki(ItemStack par1, ItemStack[] par2, int par3, String par4, String par5, String par6, String par7) {
		this.itemStack = par1;
		this.crafting = par2;
		this.idcraft = par3;
		this.infoText = par4;
		this.whereFindText = par5;
		this.whereUseText = par6;
		this.rareness = par7;
	}
}
