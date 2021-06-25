package by.fxg.metro2041.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IExtItemUpdate {
	void onUpdateExt(ItemStack i, EntityPlayer p);
}
