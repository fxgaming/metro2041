package by.fxg.metro2041.common.item.gun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.fxg.metro2041.Metro;
import by.fxg.metro2041.common.item.VItem;
import by.fxg.metro2041.util.Dual;
import by.fxg.metro2041.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMagazin extends VItem {
	public static Map<Integer, ItemMagazin> mags = new HashMap<Integer, ItemMagazin>();
	public EnumMagazinType type;
	public int maxAmmo;
	
	public ItemMagazin(int id, String uname, EnumMagazinType type, int maxAmmo) {
		super(id, 1, uname);
		this.type = type;
		this.maxAmmo = maxAmmo;
		mags.put(this.type.ordinal(), this);
	}
	
	public void addInformation(ItemStack i, EntityPlayer p, List l, boolean par4) {
		l.add("Вмещает " + this.maxAmmo + " ед. аммуниции");
		l.add("В магазине: " + Utils.getNBT(i).getInteger("ammo") + " ед. аммуниции");
		l.add("Тип аммуниции: " + this.type.applicable.title);
		l.add("ПКМ - Вставить патрон в магазин");
		l.add("SHIFT+ПКМ - Вытащить патрон с магазина");
	}

	
	public boolean isApplicableForMag(ItemStack ammo) {
		if (ammo.getItem() instanceof ItemAmmo) {
			return this.type.isApplicable(((ItemAmmo)ammo.getItem()).type);
		}
		return false;
	}
	
	public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p) {
		if (Metro.isServer) {
			if (p.isSneaking()) {
				if (Utils.getNBT(i).getInteger("ammo") > 0) {
					int slot = -1;
					for (int i$ = 0; i$ != p.inventory.mainInventory.length && slot == -1; i$++) {
						if (p.inventory.mainInventory[i$] != null && p.inventory.mainInventory[i$].stackSize < p.inventory.mainInventory[i$].getMaxStackSize()) {
							if (p.inventory.mainInventory[i$].getItem() instanceof ItemAmmo && ((ItemAmmo)p.inventory.mainInventory[i$].getItem()).type == this.type.applicable) {
								slot = i$;
							}
						}
					}
					for (int i$ = 0; i$ != p.inventory.mainInventory.length && slot == -1; i$++) {
						if (p.inventory.mainInventory[i$] == null) slot = i$;
					}
					if (slot != -1) {
						ItemStack is = new ItemStack(ItemAmmo.ammos.get(this.type.applicable.ordinal()));
						Utils.getNBT(i).setInteger("ammo", Utils.getNBT(i).getInteger("ammo") - 1);
						if (p.inventory.mainInventory[slot] == null) {
							p.inventory.mainInventory[slot] = is;
						} else {
							p.inventory.mainInventory[slot].stackSize++;
						}
						w.playSoundAtEntity(p, "metro:" + this.type.toString() + "_extract", 0.25F, 0.75F);
						return i;
					}
				}
			} else {
				if (Utils.getNBT(i).getInteger("ammo") < this.maxAmmo) {
					Dual<Integer, ItemStack> dual = this.searchAmmo(p);
					if (dual.right != null) {
						Utils.getNBT(i).setInteger("ammo", Utils.getNBT(i).getInteger("ammo") + 1);
						if (p.inventory.mainInventory[dual.left].stackSize == 1) {
							p.inventory.mainInventory[dual.left] = null;
						} else {
							p.inventory.mainInventory[dual.left].stackSize--;
						}
						w.playSoundAtEntity(p, "metro:" + this.type.toString() + "_insert", 0.25F, 0.75F);
						return i;
					}
				}
			}
		}
		return i;
	}
	
	public Dual<Integer, ItemStack> searchAmmo(EntityPlayer e) {
		if (Metro.isServer) {
			Dual<Integer, ItemStack> dual = new Dual<Integer, ItemStack>(0, null);
			InventoryPlayer inv = e.inventory;
			for (int i = 0; i != inv.mainInventory.length; i++) {
				ItemStack is = inv.mainInventory[i];
				if (is != null && is.getItem() instanceof ItemAmmo) {
					if (this.type.isApplicable(((ItemAmmo)is.getItem()).type)) {
						dual.left = i;
						dual.right = is;
						return dual;
					}
				}
			}
			return dual;
		} else {
			return null;
		}
	}
}
