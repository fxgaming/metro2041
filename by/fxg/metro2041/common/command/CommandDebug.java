package by.fxg.metro2041.common.command;

import by.fxg.metro2041.Metro;
import by.fxg.metro2041.common.player.ExtendedPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;

public class CommandDebug extends CommandBase {
    public String getCommandName() {
        return "debag";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender) {
        return "debag";
    }

    public void processCommand(ICommandSender icmd, String[] args) {
    	if (args.length == 2) {
    		if (args[0].equals("exp")) {
    			if (icmd instanceof EntityPlayer) {
    				try {
    					ExtendedPlayer ext = ExtendedPlayer.get((EntityPlayer)icmd);
//    					ext.extData.playerExperience = Integer.valueOf(args[1]);
//    					ext.player.addChatMessage(ext.extData.playerExperience + "");
    				} catch (Exception e) {
    					 ((EntityPlayer)icmd).addChatMessage("Soseh");
    				}
    			}
    		} else if (args[0].equals("inv")) {
    			if (icmd instanceof EntityPlayer) {
    				Metro.proxy.openCustomGui(250, (EntityPlayer)icmd);
    			}
    		} else if (args[0].equals("heal")) {
    			try {
	    			if (icmd instanceof EntityPlayer) {
	    				//((EntityPlayer)icmd).setHealth(Float.valueOf((float)Integer.valueOf(args[1])));
	    				((EntityPlayer)icmd).getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth).applyModifier(new AttributeModifier("generic.maxHealth", (double)Integer.valueOf(args[1]), 0).setSaved(true));;
	    			}
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }
}
