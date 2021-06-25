package by.fxg.metro2041.client;

import java.util.EnumSet;

import by.fxg.metro2041.client.cherry.CherryMainPage;
import by.fxg.metro2041.common.network.VPacketGui;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.settings.KeyBinding;

public class ClientKeyboard extends KeyHandler {
	public static KeyBinding[] keys = {new KeyBinding("Черрипедия", 25)}; //new KeyBinding("Боевые Аксессуары", 24)
	public static boolean[] keysbool = {false};
	int keyUp = 0;
	int keyDown = 0;

	public ClientKeyboard() {
		super(keys, keysbool);
	}

	public String getLabel() {
		return "viod.keyboardclient";
	}

	public void keyDown(EnumSet types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
		Minecraft mc = Minecraft.getMinecraft();
		++this.keyDown;
		if (this.keyDown % 2 == 0) {
			if (kb.keyDescription.equals("Черрипедия") && mc.theWorld != null && mc.currentScreen == null) {
				mc.displayGuiScreen(new CherryMainPage());
			}
			if (kb.keyDescription.equals("Боевые Аксессуары") && mc.theWorld != null && (mc.currentScreen == null || mc.currentScreen instanceof GuiInventory)) {
				mc.displayGuiScreen((GuiScreen)null);
				mc.setIngameFocus();
				EntityClientPlayerMP p = Minecraft.getMinecraft().thePlayer;
				PacketDispatcher.sendPacketToServer((new VPacketGui(250)).makePacket());
			}
		}
	}

	public void keyUp(EnumSet types, KeyBinding kb, boolean tickEnd) {
		++this.keyUp;
	}

	public EnumSet ticks() {
		return EnumSet.of(TickType.CLIENT);
	}
}
