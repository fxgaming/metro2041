package by.fxg.metro2041.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderLivingEvent.Specials.Post;
import net.minecraftforge.client.event.RenderLivingEvent.Specials.Pre;

public interface IRenderLivingItem {
	@SideOnly(Side.CLIENT)
	void renderLiving(Pre event, ItemStack item);
	@SideOnly(Side.CLIENT)
	void renderLiving(Post event, ItemStack item);
}
