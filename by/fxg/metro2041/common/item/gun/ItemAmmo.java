package by.fxg.metro2041.common.item.gun;

import java.util.HashMap;
import java.util.Map;

import by.fxg.metro2041.common.item.VItem;
import net.minecraft.item.ItemStack;

public class ItemAmmo extends VItem {
	public static Map<Integer, ItemAmmo> ammos = new HashMap<Integer, ItemAmmo>();
	public EnumAmmoType type;
	
	public ItemAmmo(int id, String uname, EnumAmmoType type) {
		super(id, 30, uname);
		this.type = type;
		ammos.put(this.type.ordinal(), this);
	}
}
