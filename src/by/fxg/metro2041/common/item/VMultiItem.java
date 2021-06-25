package by.fxg.metro2041.common.item;

import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class VMultiItem extends VItem {
	@SideOnly(Side.CLIENT)
	private Icon[] icons;
	public int items;
	public String texture;

	public VMultiItem(int par1, int par2, String par3) {
		super(par1);
		this.items = par2;
		this.texture = par3;
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setMaxStackSize(64);
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	public VMultiItem(int par1, int par2, String par3, CreativeTabs par4) {
		this(par1, par2, par3);
		this.setCreativeTab(par4);
	}

	public VMultiItem(int par1, int par2, int par3, String par4) {
		this(par1, par2, par4);
		this.setMaxStackSize(par3);
	}

	public VMultiItem(int par1, int par2, int par3, String par4, CreativeTabs par5) {
		this(par1, par2, par3, par4);
		this.setCreativeTab(par5);
	}
	
	public VMultiItem setName(String par1, int par2) {
		LanguageRegistry.addName(new ItemStack(this, 1, par2), par1);
		return this;
	}

	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		for (int x = 0; x < this.items; ++x) {
			par3List.add(new ItemStack(this, 1, x));
		}
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1) {
		this.icons = new Icon[this.items];
		for (int i = 0; i != this.items; ++i) {
			if (i >= this.items) {
				this.icons[i] = par1.registerIcon("metro:missingetexture");
			}
			this.icons[i] = par1.registerIcon("metro:" + this.texture + "_" + i);
		}
	}

	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {
		return par1 > this.icons.length ? this.icons[0] : this.icons[par1];
	}

	public String getUnlocalizedName(ItemStack e) {
		if (e.getItemDamage() >= this.items) {
			return "diverse.unknown";
		}
		return "item.metro." + this.texture + "." + e.getItemDamage();
	}
}
