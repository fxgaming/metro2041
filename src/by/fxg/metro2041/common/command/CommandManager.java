package by.fxg.metro2041.common.command;

import by.fxg.metro2041.Metro;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommandManager {
	public void init(FMLServerStartingEvent e) {
		if (Metro.isServer) e.registerServerCommand(new CommandDebug());
		e.registerServerCommand(new CommandAdmin());
		e.registerServerCommand(new CommandPortalEditor());
		e.registerServerCommand(new CommandNBT());
	}
}
