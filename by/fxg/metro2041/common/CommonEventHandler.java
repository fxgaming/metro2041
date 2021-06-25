package by.fxg.metro2041.common;

import java.util.ArrayList;
import java.util.Iterator;

import by.fxg.garbageapi.util.FromTo;
import by.fxg.metro2041.common.entity.passive.EntityGroundItem;
import by.fxg.metro2041.common.item.gun.EnumAmmoType;
import by.fxg.metro2041.common.player.ExtendedPlayer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.world.BlockEvent;

public class CommonEventHandler {
	@ForgeSubscribe
	public void onEntityConstructing(EntityConstructing event) {
		if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer) event.entity) == null) {
			ExtendedPlayer.register((EntityPlayer) event.entity);
		}
		if (event.entity instanceof EntityPlayer && event.entity.getExtendedProperties("epa") == null) {
			event.entity.registerExtendedProperties("epa", new ExtendedPlayer((EntityPlayer) event.entity));
		}
	}

	@ForgeSubscribe
	public void onBlockBreak(BlockEvent.BreakEvent e) {
		if (!e.world.isRemote) {
		}
	}

	@ForgeSubscribe
	public void onLivingDrop(LivingDropsEvent e) {
		if (!e.entityLiving.worldObj.isRemote) {
			if (e.drops.size() > 0) {
				ArrayList items = e.drops;
				Iterator var3 = items.iterator();
				while (var3.hasNext()) {
					EntityItem item = (EntityItem) var3.next();
					ItemStack itemstack = item.getEntityItem();
					EntityGroundItem groundItem = new EntityGroundItem(e.entityLiving.worldObj, item.posX, item.posY, item.posZ);
					groundItem.setEntityItemStack(itemstack);
					e.entityLiving.worldObj.spawnEntityInWorld(groundItem);
				}
				e.setCanceled(true);
			}
		}
	}

	@ForgeSubscribe
	public void onItemToss(ItemTossEvent e) {
		World world = e.player.worldObj;
		EntityItem entityItem = e.entityItem;
		ItemStack stack = entityItem.getEntityItem();
		if (!world.isRemote) {
			if (!(stack.getItem() instanceof ItemBlock)) {
				EntityGroundItem groundItem = new EntityGroundItem(world, entityItem.posX, entityItem.posY, entityItem.posZ);
				groundItem.setEntityItemStack(stack);
				world.spawnEntityInWorld(groundItem);
				e.setCanceled(true);
			}
		}
	}

	@ForgeSubscribe
	public void onPlayerDrop(PlayerDropsEvent event) {
		EntityPlayer player = event.entityPlayer;
		if (!player.worldObj.isRemote) {
//			int i;
//			for (i = 0; i < cdinv.length; ++i) {
//				if (cdinv[i] != null) {
//					event.drops.add(new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, cdinv[i]));
//				}
//			}
		}
	}
	
	@ForgeSubscribe
	public void onItemPickup(EntityItemPickupEvent e) {
		if (!e.entityPlayer.worldObj.isRemote) {

		}
	}
}
