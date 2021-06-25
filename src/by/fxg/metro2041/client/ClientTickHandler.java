package by.fxg.metro2041.client;

import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import by.fxg.metro2041.Metro;
import by.fxg.metro2041.client.gui.GuiButtonExtended;
import by.fxg.metro2041.common.item.gun.ItemGun;
import by.fxg.metro2041.common.network.PacketClientData;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;

public class ClientTickHandler implements ITickHandler {
	public int tick = 0;
	public final String[] getGuiLeft = new String[] {"field_74198_m", "guiLeft"};
	public final String[] getGuiTop = new String[] {"field_74197_n", "guiTop"};
	public final String[] mapFieldsGuiScreen = ObfuscationReflectionHelper.remapFieldNames(GuiScreen.class.getSimpleName(), new String[] {"buttonList", "i"});

	public void tickPlayer(EntityPlayer p) {
		if (p.getHeldItem() != null && p.getHeldItem().getItem() instanceof ItemGun) {
			((ItemGun)p.getHeldItem().getItem()).onClientTick(p.worldObj, p, p.getHeldItem());
		}
	}
	
	public void tickClient() {
		Metro.instance.clientManager.clientAnimationManager.onClientUpdate(Minecraft.getMinecraft());
		Metro.instance.clientManager.clientSoundHandler.onIngameUpdate();
	}
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		try {
			Metro.clientManager.clientHelper.updateHelper();
			if (tickData != null && tickData.length > 0 && tickData[0] != null && tickData[0] instanceof EntityPlayer) {
				EntityPlayer p = (EntityPlayer)tickData[0];
				this.tickPlayer(p);
			} else {
				this.tickClient();
			}
			GuiScreen screen = Minecraft.getMinecraft().currentScreen;
			if (screen != null && screen instanceof GuiInventory) {
				Field field = ReflectionHelper.findField(GuiScreen.class, (String[]) ObfuscationReflectionHelper.remapFieldNames(GuiScreen.class.getName(), (String[]) (new String[] {"buttonList", "i", "field_73887_h"})));
				field.setAccessible(true);
				List buttonList = (List)field.get(screen);
				boolean bool = true;
				Iterator iterator = buttonList.iterator();
				while (iterator.hasNext()) {
					Object buttonIterator = iterator.next();
					if (buttonIterator instanceof GuiButtonExtended) {
						bool = false;
						break;
					}
				}
				if (bool) {
					int guiLeft = this.getGuiLeft((GuiInventory) screen);
					int guiTop = this.getGuiTop((GuiInventory) screen);
					GuiButtonExtended button = new GuiButtonExtended(100, guiLeft, guiTop - 22, 250);
					buttonList.add(button);
				}
			}
		} catch (Exception var1) {
			var1.printStackTrace();
		}
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		this.tick++;
		if (this.tick % 100 == 0) {
			this.tick = 0;
			if (Minecraft.getMinecraft().thePlayer != null) {
				PacketDispatcher.sendPacketToServer(new PacketClientData.request(Minecraft.getMinecraft().thePlayer.entityId).makePacket());
			}
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT, TickType.PLAYER, TickType.RENDER);
	}

	@Override
	public String getLabel() {
		return "ClientTickHandlerViod";
	}
	
	private int getGuiLeft(GuiInventory clazz) {
		return (Integer)ObfuscationReflectionHelper.getPrivateValue(GuiContainer.class, clazz, this.getGuiLeft);
	}

	private int getGuiTop(GuiInventory clazz) {
		return (Integer)ObfuscationReflectionHelper.getPrivateValue(GuiContainer.class, clazz, this.getGuiTop);
	}
}
