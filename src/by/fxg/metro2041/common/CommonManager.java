package by.fxg.metro2041.common;

import by.fxg.metro2041.Metro;
import by.fxg.metro2041.common.command.CommandManager;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraftforge.common.MinecraftForge;

public class CommonManager {
	public GuiHandler guiHandler;
	public CommonEventHandler commonEventHandler;
	public CommandManager commandManager;
	
	public CommonManager init() {
		this.commandManager = new CommandManager();
		NetworkRegistry.instance().registerGuiHandler(Metro.instance, this.guiHandler = new GuiHandler());
		MinecraftForge.EVENT_BUS.register(this.commonEventHandler = new CommonEventHandler());
		return this;
	}
}
