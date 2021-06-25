package by.fxg.metro2041.common.command;

import java.util.ArrayList;
import java.util.List;

import by.fxg.metro2041.debug.DebugItem;
import by.fxg.metro2041.util.Utils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class CommandPortalEditor extends CommandBase {
    public String getCommandName() {
        return "portaleditor";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender) {
        return "Portal Editor";
    }
    
    public List getCommandAliases() {
    	List l = new ArrayList();
    	l.add("pe");
        return l;
    }

    public void processCommand(ICommandSender icmd, String[] args) {
    	if (icmd instanceof EntityPlayer) {
    		EntityPlayer p = (EntityPlayer)icmd;
    		if (args.length == 0) {
    			p.addChatMessage("§7/PE clear - очистить данные");
    			p.addChatMessage("§7/PE color clear - убрать цвет");
    			p.addChatMessage("§7/PE color set R G B F - дать цвет");
    			p.addChatMessage("§7/PE coord remove - убрать координаты");
    			p.addChatMessage("§7/PE remover - Режим очистки");
				p.addChatMessage("§7/PE name set - Добавить имя");
				p.addChatMessage("§7/PE name clear - Очистить имя");
        	} else {
        		if (p.getHeldItem() != null && p.getHeldItem().getItem() instanceof DebugItem && p.getHeldItem().getItemDamage() == 3) {
        			ItemStack i = p.getHeldItem();
        			if (args[0].equals("name")) {
        				if (args[1].equals("set")) {
        					String pattern = "";
        					for (int j = 3; j != args.length + 1; j++) {
        						pattern += args[j - 1] + " ";
        					}
        					pattern = pattern.substring(0, pattern.length() - 1);
        					Utils.getNBT(i).setString("bName", pattern);
        					p.addChatMessage("§aНазвание установлено: " + pattern);
        				} else if (args[1].equals("clear")) {
        					Utils.getNBT(i).removeTag("bName");
        					p.addChatMessage("§aНазвание очищено.");
        				}
        			} else if (args.length == 1) {
            			if (args[0].equals("clear")) {
            				Utils.getNBT(i).removeTag("bName");
            				Utils.getNBT(i).removeTag("cR");
            				Utils.getNBT(i).removeTag("cG");
            				Utils.getNBT(i).removeTag("cB");
            				Utils.getNBT(i).removeTag("cA");
            				Utils.getNBT(i).removeTag("x");
            				Utils.getNBT(i).removeTag("y");
            				Utils.getNBT(i).removeTag("z");
            				Utils.getNBT(i).removeTag("remover");
            				p.addChatMessage("§aДанные очищены.");
            			} else if (args[0].equals("remover")) {
            				if (Utils.getNBT(i).getBoolean("remover")) Utils.getNBT(i).setBoolean("remover", false);
            				else Utils.getNBT(i).setBoolean("remover", true);
            				p.addChatMessage("§aРежим очистки: " + Utils.getNBT(i).getBoolean("remover") + ".");
            			}
            		} else if (args.length == 2) {
            			if (args[0].equals("color") && args[1].equals("clear")) {
            				Utils.getNBT(i).removeTag("cR");
            				Utils.getNBT(i).removeTag("cG");
            				Utils.getNBT(i).removeTag("cB");
            				Utils.getNBT(i).removeTag("cF");
            				p.addChatMessage("§aЦвет удален.");
            			} else if (args[0].equals("coord") && args[1].equals("remove")) {
            				Utils.getNBT(i).removeTag("x");
            				Utils.getNBT(i).removeTag("y");
            				Utils.getNBT(i).removeTag("z");
            				p.addChatMessage("§aКоординаты удалены.");
            			}
            		} else if (args.length == 6 && args[0].equals("color") && args[1].equals("set")) {
        				Utils.getNBT(i).setFloat("cR", Float.valueOf(args[2]));
        				Utils.getNBT(i).setFloat("cG", Float.valueOf(args[3]));
        				Utils.getNBT(i).setFloat("cB", Float.valueOf(args[4]));
        				Utils.getNBT(i).setFloat("cF", Float.valueOf(args[5]));
        				p.addChatMessage("§aЦвет установлен.");
            		}
        		} else {
        			p.addChatMessage("§7Возьмите в руку портальный настройщик");
        		}
        	}
    	}
    }
}
