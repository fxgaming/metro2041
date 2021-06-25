package by.fxg.metro2041.common.command;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;

public class CommandNBT implements ICommand {
	private List aliases = new ArrayList();
	private String[] addTypes = {"String", "Integer", "Byte", "Short", "Long", "Float", "Double", "Boolean"};

	public CommandNBT() {
		this.aliases.add("nbt");
		this.aliases.add("nbts");
	}
	
	public String getCommandName() {
		return "nbtsher";
	}

	public String getCommandUsage(ICommandSender icommandsender) {
		return "/nbtsher [add/edit/remove/help] <all/single>(remove) <String/Integer/Byte/Short/Long/Float/Double/Boolean>(add/edit) [tag] <value(if adding or editing)>";
	}

	public List getCommandAliases() {
		return this.aliases;
	}

	public void processCommand(ICommandSender icommandsender, String[] val) {
		EntityPlayer p = (EntityPlayer) icommandsender;
		Boolean addTypeEquals = false;
		try {
			if (p.getHeldItem() == null) p.addChatMessage("§7NBTSHER: §dВозьмите редактируемый предмет в руку");
			else if (p.getHeldItem() != null) {
				ItemStack held = p.getHeldItem();
				if (val[0] != null) {
					if ("add".equals(val[0])) {
						if (held.hasTagCompound()) {
							if (held.stackTagCompound.hasKey(val[2])) p.addChatMessage("§7NBTSHER: §dДанный тег уже существует");
							else {
								for (String value : this.addTypes) {
									if (value.equals(val[1])) {
										addTypeEquals = Boolean.valueOf(true);
										break;
									}
								}
								if (addTypeEquals.booleanValue()) {
									if (val[3] != null)
										try {
											if ("String".equals(val[1])) {
												held.stackTagCompound.setString(val[2], val[3]);
												success(p, val[2], val[3], held);
											} else if ("Integer".equals(val[1])) {
												held.stackTagCompound.setInteger(val[2], Integer.valueOf(val[3]).intValue());
												success(p, val[2], val[3], held);
											} else if ("Byte".equals(val[1])) {
												held.stackTagCompound.setByte(val[2], Byte.valueOf(val[3]).byteValue());
												success(p, val[2], val[3], held);
											} else if ("Short".equals(val[1])) {
												held.stackTagCompound.setShort(val[2], Short.valueOf(val[3]).shortValue());
												success(p, val[2], val[3], held);
											} else if ("Long".equals(val[1])) {
												held.stackTagCompound.setLong(val[2], Long.valueOf(val[3]).longValue());
												success(p, val[2], val[3], held);
											} else if ("Float".equals(val[1])) {
												held.stackTagCompound.setFloat(val[2], Float.valueOf(val[3]).floatValue());
												success(p, val[2], val[3], held);
											} else if ("Double".equals(val[1])) {
												held.stackTagCompound.setDouble(val[2], Double.valueOf(val[3]).doubleValue());
												success(p, val[2], val[3], held);
											} else if ("Boolean".equals(val[1])) {
												held.stackTagCompound.setBoolean(val[2], Boolean.valueOf(val[3]).booleanValue());
												success(p, val[2], val[3], held);
											} else p.addChatMessage("§7NBTSHER: §dНеккоректный 2-й аргумент! ошибка #001");
										} catch (Exception e) {
											p.addChatMessage("§7NBTSHER: §dУстановите верный параметр для тега");
										}
									else p.addChatMessage("§7NBTSHER: §dУстановите устанавливаемый значение для тега");
								} else p.addChatMessage("§7NBTSHER: §dУстановите верный 2-й аргумент - <String/Integer/Byte/Short/Long/Float/Double/Boolean>");
							}
						} else {
							held.stackTagCompound = new NBTTagCompound();
							if (held.stackTagCompound.hasKey(val[2])) p.addChatMessage("§7NBTSHER: §dДанный тег уже существует");
							else {
								for (String value : this.addTypes) {
									if (value.equals(val[1])) {
										addTypeEquals = Boolean.valueOf(true);
										break;
									}
								}
								if (addTypeEquals.booleanValue()) {
									if (val[3] != null)
										try {
											if ("String".equals(val[1])) {
												held.stackTagCompound.setString(val[2], val[3]);
												success(p, val[2], val[3], held);
											} else if ("Integer".equals(val[1])) {
												held.stackTagCompound.setInteger(val[2], Integer.valueOf(val[3]).intValue());
												success(p, val[2], val[3], held);
											} else if ("Byte".equals(val[1])) {
												held.stackTagCompound.setByte(val[2], Byte.valueOf(val[3]).byteValue());
												success(p, val[2], val[3], held);
											} else if ("Short".equals(val[1])) {
												held.stackTagCompound.setShort(val[2], Short.valueOf(val[3]).shortValue());
												success(p, val[2], val[3], held);
											} else if ("Long".equals(val[1])) {
												held.stackTagCompound.setLong(val[2], Long.valueOf(val[3]).longValue());
												success(p, val[2], val[3], held);
											} else if ("Float".equals(val[1])) {
												held.stackTagCompound.setFloat(val[2], Float.valueOf(val[3]).floatValue());
												success(p, val[2], val[3], held);
											} else if ("Double".equals(val[1])) {
												held.stackTagCompound.setDouble(val[2], Double.valueOf(val[3]).doubleValue());
												success(p, val[2], val[3], held);
											} else if ("Boolean".equals(val[1])) {
												held.stackTagCompound.setBoolean(val[2], Boolean.valueOf(val[3]).booleanValue());
												success(p, val[2], val[3], held);
											} else p.addChatMessage("§7NBTSHER: §dНеккоректный 2-й аргумент! ошибка #001");
										} catch (Exception e) {
											p.addChatMessage("§7NBTSHER: §dУстановите верный параметр для тега");
										}
									else p.addChatMessage("§7NBTSHER: §dУстановите устанавливаемое значение для тега");
								} else p.addChatMessage("§7NBTSHER: §dУстановите верный 2-й аргумент - <String/Integer/Byte/Short/Long/Float/Double/Boolean>");
							}
						}
					} else if ("edit".equals(val[0])) {
						if (!held.hasTagCompound()) p.addChatMessage("§7NBTSHER: §dУ предмета нет NBT компаунда, 1-й аргумент должен быть 'add'");
						else {
							for (String value : this.addTypes) {
								if (value.equals(val[1])) {
									addTypeEquals = Boolean.valueOf(true);
									break;
								}
							}
							if (addTypeEquals.booleanValue()) {
								if (val[3] != null)
									try {
										if ("String".equals(val[1])) {
											held.stackTagCompound.setString(val[2], val[3]);
											success(p, val[2], val[3], held);
										} else if ("Integer".equals(val[1])) {
											held.stackTagCompound.setInteger(val[2], Integer.valueOf(val[3]).intValue());
											success(p, val[2], val[3], held);
										} else if ("Byte".equals(val[1])) {
											held.stackTagCompound.setByte(val[2], Byte.valueOf(val[3]).byteValue());
											success(p, val[2], val[3], held);
										} else if ("Short".equals(val[1])) {
											held.stackTagCompound.setShort(val[2], Short.valueOf(val[3]).shortValue());
											success(p, val[2], val[3], held);
										} else if ("Long".equals(val[1])) {
											held.stackTagCompound.setLong(val[2], Long.valueOf(val[3]).longValue());
											success(p, val[2], val[3], held);
										} else if ("Float".equals(val[1])) {
											held.stackTagCompound.setFloat(val[2], Float.valueOf(val[3]).floatValue());
											success(p, val[2], val[3], held);
										} else if ("Double".equals(val[1])) {
											held.stackTagCompound.setDouble(val[2], Double.valueOf(val[3]).doubleValue());
											success(p, val[2], val[3], held);
										} else if ("Boolean".equals(val[1])) {
											held.stackTagCompound.setBoolean(val[2], Boolean.valueOf(val[3]).booleanValue());
											success(p, val[2], val[3], held);
										} else p.addChatMessage("§7NBTSHER: §dНеккоректный 2-й аргумент! ошибка #101");
									} catch (Exception e) {
										p.addChatMessage("§7NBTSHER: §dУстановите верный параметр для тега");
									}
								else p.addChatMessage("§7NBTSHER: §dУстановите изменяемое значение для тега");
							} else p.addChatMessage("§7NBTSHER: §dУстановите верный 2-й аргумент - <String/Integer/Byte/Short/Long/Float/Double/Boolean>");
						}
					} else if ("remove".equals(val[0])) {
						if ("all".equals(val[1])) {
							if (!held.hasTagCompound()) p.addChatMessage("§7NBTSHER: §dУ предмета уже нет NBT компаунда!");
							else {
								held.stackTagCompound = null;
								p.addChatMessage("§7NBTSHER: §dУспешно убраны все NBT теги с предмета " + held.getDisplayName());
							}
						} else if ("single".equals(val[1])) {
							if (!held.stackTagCompound.hasKey(val[2])) p.addChatMessage("§7NBTSHER: §dДанный тег уже отсутствует");
							else {
								held.stackTagCompound.removeTag(val[2]);
								p.addChatMessage("§7NBTSHER: §dУспешно убран тег " + val[2] + " с предмета " + held.getDisplayName());
							}
						} else p.addChatMessage("§7NBTSHER: §dУстановите верный 2-й аргумент - <all/single>");
					} else if ("help".equals(val[0])) {
						p.addChatMessage("§7=====>NBTSHER Help<=====");
						p.addChatMessage("§d1-й аргумент add/edit/remove/help");
						p.addChatMessage("§d2-й аргумент all/single при remove");
						p.addChatMessage("§d2-й аргумент String/Integer/Byte/Short/Long/Float/Double/Boolean при add/edit");
						p.addChatMessage("§d3-й аргумент 'Название тега' при single и remove или add/edit");
						p.addChatMessage("§d4-й аргумент 'Значение' при add/edit");
					} else p.addChatMessage("§7NBTSHER: §dУстановите верный 1-й аргумент - [add/edit/remove]");
				} else p.addChatMessage("§7NBTSHER: §dУстановите 1-й аргумент - [add/edit/remove]");
			}
		} catch (Exception e) {
			p.addChatMessage("§7NBTSHER: §dПроизошла ошибка, попробуйте еще раз.");
		}
	}

	public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
		return true;
	}

	public List addTabCompletionOptions(ICommandSender icommandsender, String[] astring) {
		return null;
	}

	public boolean isUsernameIndex(String[] astring, int i) {
		return false;
	}

	public int compareTo(Object o) {
		return 0;
	}

	private void success(EntityPlayer p, String val0, String val1, ItemStack held) {
		p.addChatMessage("§7NBTSHER: §dУспешно установлен " + val0 + " тег с параметром " + val1 + " на предмет " + held.getDisplayName());
	}
}