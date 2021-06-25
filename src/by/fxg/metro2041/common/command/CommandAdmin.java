package by.fxg.metro2041.common.command;

import by.fxg.metro2041.common.player.ExtendedPlayer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class CommandAdmin extends CommandBase {
    public String getCommandName() {
        return "admin";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender) {
        return "admin";
    }

    public void processCommand(ICommandSender icmd, String[] args) {
    	if (icmd instanceof EntityPlayer) {
    		EntityPlayer p = (EntityPlayer)icmd;
    		if (args.length == 0) {
    			p.addChatMessage("§7/admin recharge - перезаряжает предмет в руке.");
    			p.addChatMessage("§7/admin type INT - устанавливает тип INT на предмет.");
    			p.addChatMessage("пока-что команда пустышка. енто плохо! :(");
    			p.addChatMessage("§7/admin clearchat - Очистить чат(cc/ccs - silence).");
    		} else {
    			if (args.length == 1) {
    				if (args[0].toLowerCase().equals("cc") || args[0].toLowerCase().equals("clearchat")) {
    					for (String str : MinecraftServer.getServer().getAllUsernames()) {
    						for (int i = 0; i != 127; i++) p.worldObj.getPlayerEntityByName(str).addChatMessage(" ");
    						p.worldObj.getPlayerEntityByName(str).addChatMessage("§2Чат был очищен администрацией.");
    					}
    				} else if (args[0].toLowerCase().equals("ccs") || args[0].toLowerCase().equals("clearchatsilence")) {
    					for (String str : MinecraftServer.getServer().getAllUsernames()) {
    						for (int i = 0; i != 128; i++) p.worldObj.getPlayerEntityByName(str).addChatMessage(" ");
    					}
    				}
    			}
    		}
    	}
    }
}
