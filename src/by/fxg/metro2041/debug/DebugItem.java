package by.fxg.metro2041.debug;

import java.awt.Color;
import java.util.List;

import org.lwjgl.opengl.GL11;

import by.fxg.metro2041.Metro;
import by.fxg.metro2041.common.block.BlockManager;
import by.fxg.metro2041.common.item.VMultiItem;
import by.fxg.metro2041.common.item.gun.EnumAmmoType;
import by.fxg.metro2041.common.player.ExtendedData;
import by.fxg.metro2041.common.player.ExtendedPlayer;
import by.fxg.metro2041.common.tile.TilePortal;
import by.fxg.metro2041.util.IOverlayItem;
import by.fxg.metro2041.util.Utils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class DebugItem extends VMultiItem implements IOverlayItem {
	public DebugItem(int par1, int par2) {
		super(par1, par2, 1, "debug", CreativeTabs.tabMisc);
	}
	
	public void addInformation(ItemStack i, EntityPlayer p, List l, boolean b) {
		if (i.getItemDamage() == 3) {
			if (Utils.getNBT(i).getInteger("x") != 0) l.add("Координаты: " + Utils.getNBT(i).getInteger("x") + " " + Utils.getNBT(i).getInteger("y") + " " + Utils.getNBT(i).getInteger("z"));
			if (Utils.getNBT(i).getFloat("cR") != 0.0F) l.add("Цвет: " + Utils.getNBT(i).getFloat("cR") + " " + Utils.getNBT(i).getFloat("cG") + " " + Utils.getNBT(i).getFloat("cB") + " " + Utils.getNBT(i).getFloat("cF"));
			if (!Utils.getNBT(i).getString("bName").equals("")) l.add("Название: " + Utils.getNBT(i).getString("bName"));
			l.add("SHIFT для передачи доп данных.");
			l.add("nonSHIFT для координат.");
		}
	}

    public ItemStack onItemRightClick(ItemStack i, World w, EntityPlayer p) {
    	switch(i.getItemDamage()) {
    		case 0:
    			if (!w.isRemote) {
    				Metro var100000 = Metro.instance;
    				var100000.proxy.openCustomGui(250, p);
    			}
    			break;
    		case 1:
    			if (!w.isRemote) {
    				ExtendedPlayer epl = ExtendedPlayer.get(p);
    				//epl.extData.sniperAmmo = 0;
    			}
    			break;
    		case 2:
    			if (!w.isRemote) {
    				EntityPlayer aim = null;
    				if (Utils.getAimedEntity(w, p, 15.0D, 1.5F) != null && Utils.getAimedEntity(w, p, 15.0D, 1.5F) instanceof EntityPlayer) {
    					aim = (EntityPlayer)Utils.getAimedEntity(w, p, 15.0D, 1.5F);
    				} else {
    					aim = p;
    				}
    				try {
    					ExtendedPlayer ex = ExtendedPlayer.get(aim);
    					ExtendedData exd = ex.extData;
    					p.addChatMessage(" ");
    					p.addChatMessage("§7Entity: " + ex.player.getEntityName());
    					//p.addChatMessage("§7e:" + ex.virtAmmo.getAmmo(exd, EnumAmmoType.AssaultAmmo));
    				} catch (Exception e) {
    					p.addChatMessage("§cПроизошла ошибка!");
    					e.printStackTrace();
    				}
    			}
    			break;
    		case 3:
    			break;
    		default:	
    	}
        return i;
    }
    
    public boolean onItemUseFirst(ItemStack s, EntityPlayer p, World w, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    	switch(s.getItemDamage()) {
    		case 3:
    			if (!w.isRemote) {
    				NBTTagCompound n = Utils.getNBT(s);
    				if (w.getBlockId(x, y, z) == 0) {
    					p.addChatMessage("§e*Клик по воздуху*");
    				} else if (w.getBlockId(x, y, z) == BlockManager.blockPortal.blockID) {
    					if (p.isSneaking()) {
    						p.addChatMessage("§e*Клик по порталу*");
    						if (!Utils.getNBT(s).getBoolean("remover")) {
	    						TilePortal tile = (TilePortal)w.getBlockTileEntity(x, y, z);
	    						tile.setName(n.getString("bName"));
	        					tile.setColor(n.getFloat("cR"), n.getFloat("cG"), n.getFloat("cB"), n.getFloat("cF"));
	        					p.addChatMessage("§aПортал привязан по данным!");
    						} else {
    							TilePortal tile = (TilePortal)w.getBlockTileEntity(x, y, z);
    							tile.setName("");
    							tile.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    							tile.setXYZ(0, 0, 0);
    							p.addChatMessage("§aРежим ремувер удалил данные блока на стандарт!");
    						}
    					} else {
	    					p.addChatMessage("§e*Клик по порталу*");
	    					if (n.getInteger("x") == 0) {
	    						n.setInteger("x", x);
	    						n.setInteger("y", y + 1);
	    						n.setInteger("z", z);
	    						p.addChatMessage("§aКоординаты привязаны!");
	        				} else {
	        					TilePortal tile = (TilePortal)w.getBlockTileEntity(x, y, z);
	        					tile.setXYZ(n.getInteger("x"), n.getInteger("y"), n.getInteger("z"));
	        					p.addChatMessage("§aПортал привязан по координатам!");
	        				}
    					}
    				} else {
    					p.addChatMessage("§e*Клик по блоку*");
    					if (n.getInteger("x") == 0) {
    						n.setInteger("x", x);
    						n.setInteger("y", y + 1);
    						n.setInteger("z", z);
    						p.addChatMessage("§aКоординаты привязаны!");
    					}
    				}
    			}
    			break;
    		default:	
    	}
        return false;
    }

	@SideOnly(Side.CLIENT)
	public void renderOverlay(net.minecraftforge.client.event.RenderGameOverlayEvent.Pre event, int itemDamage) {
		if (itemDamage == 2) {
			if (Minecraft.getMinecraft().theWorld != null && Minecraft.getMinecraft().thePlayer != null) {
				if (Utils.getAimedEntity(Minecraft.getMinecraft().theWorld, Minecraft.getMinecraft().thePlayer, 15.0D, 0.5F) != null) {
					String name = Utils.getAimedEntity(Minecraft.getMinecraft().theWorld, Minecraft.getMinecraft().thePlayer, 15.0D, 0.5F).getEntityName();
					Minecraft.getMinecraft().fontRenderer.drawString(name, event.resolution.getScaledWidth() / 2 - (Minecraft.getMinecraft().fontRenderer.getStringWidth(name) / 2), event.resolution.getScaledHeight() / 2 - 16, Color.WHITE.getRGB());
				} else {
					String name = Minecraft.getMinecraft().thePlayer.getEntityName();
					Minecraft.getMinecraft().fontRenderer.drawString(name, event.resolution.getScaledWidth() / 2 - (Minecraft.getMinecraft().fontRenderer.getStringWidth(name) / 2), event.resolution.getScaledHeight() / 2 - 16, Color.WHITE.getRGB());
				}
			}
		} else if (itemDamage == 3) {
			if (Minecraft.getMinecraft().theWorld != null && Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.getHeldItem() != null) {
				GL11.glPushMatrix();
				ItemStack is = Minecraft.getMinecraft().thePlayer.getHeldItem();
				GL11.glColor4f(Utils.getNBT(is).getFloat("cR"), Utils.getNBT(is).getFloat("cG"), Utils.getNBT(is).getFloat("cB"), Utils.getNBT(is).getFloat("cF"));
				Minecraft.getMinecraft().fontRenderer.drawString(Utils.getNBT(is).getString("bName").replace("&", "§"), event.resolution.getScaledWidth() / 2 - (Minecraft.getMinecraft().fontRenderer.getStringWidth(Utils.getNBT(is).getString("bName")) / 2), event.resolution.getScaledHeight() / 2 - 16, Color.WHITE.getRGB());
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
				Minecraft.getMinecraft().fontRenderer.drawString(Utils.getNBT(is).getInteger("x") + " " + Utils.getNBT(is).getInteger("y") + " " + Utils.getNBT(is).getInteger("z"), event.resolution.getScaledWidth() / 2 - (Minecraft.getMinecraft().fontRenderer.getStringWidth(Utils.getNBT(is).getInteger("x") + " " + Utils.getNBT(is).getInteger("y") + " " + Utils.getNBT(is).getInteger("z")) / 2), event.resolution.getScaledHeight() / 2 + 16, Color.WHITE.getRGB());
				GL11.glPopMatrix();
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void renderOverlay(net.minecraftforge.client.event.RenderGameOverlayEvent.Post event, int itemDamage) {}
}
