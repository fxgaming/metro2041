package by.fxg.metro2041.client;

import by.fxg.metro2041.Metro;
import by.fxg.metro2041.client.gui.GuiLoading;
import by.fxg.metro2041.util.FancyLightHelper;
import by.fxg.metro2041.util.IOverlayItem;
import by.fxg.metro2041.util.IRenderLivingItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class ClientEventHandler {
	public RenderItem rend = new RenderItem();
	public Minecraft mc = Minecraft.getMinecraft();
	public FontRenderer font = Minecraft.getMinecraft().fontRenderer;
	
	private int currentHealth = 0;
	private int currentFood = 0;
	private int currentCharge = 0;
	private int armorPercentage = 0;
	private int maxCharge = 0;
	private int charge = 0;
	private int tick = 0;
	
	@ForgeSubscribe
    public void renderEntity(RenderLivingEvent.Specials.Pre e) {
		if (this.mc.thePlayer != null) {
			if (this.mc.thePlayer.getHeldItem() != null) {
				if (this.mc.thePlayer.getHeldItem().getItem() instanceof IRenderLivingItem) {
					((IRenderLivingItem)this.mc.thePlayer.getHeldItem().getItem()).renderLiving(e, this.mc.thePlayer.getHeldItem());
				}
			}
		}
    }
    
    @ForgeSubscribe
    public void renderEntity(RenderLivingEvent.Specials.Post e) {
    	if (this.mc.thePlayer != null) {
			if (this.mc.thePlayer.getHeldItem() != null) {
				if (this.mc.thePlayer.getHeldItem().getItem() instanceof IRenderLivingItem) {
					((IRenderLivingItem)this.mc.thePlayer.getHeldItem().getItem()).renderLiving(e, this.mc.thePlayer.getHeldItem());
				}
			}
		}
    }

	@ForgeSubscribe
	public void drawHud(RenderGameOverlayEvent.Pre e) {
//		if (this.mc.thePlayer != null) {
//			if (this.mc.thePlayer.getHeldItem() != null) {
//				if (this.mc.thePlayer.getHeldItem().getItem() instanceof IOverlayItem) {
//					((IOverlayItem)this.mc.thePlayer.getHeldItem().getItem()).renderOverlay(e, this.mc.thePlayer.getHeldItem().getItemDamage());
//				}
//			}
//		}
//		if (e.type == e.type.CROSSHAIRS || e.type == e.type.FOOD || e.type == e.type.HEALTH || e.type == e.type.HEALTHMOUNT || e.type == e.type.ARMOR || e.type == e.type.EXPERIENCE || e.type == e.type.HOTBAR || e.type == e.type.TEXT) {
//			if (e.type == e.type.CROSSHAIRS || e.type == e.type.HOTBAR || e.type == e.type.TEXT) {
//				if (!this.mc.thePlayer.capabilities.isCreativeMode) {
//					e.setCanceled(true);
//				}
//			} else {
//				e.setCanceled(true);
//			}
//		}
	}
	
	@ForgeSubscribe
	public void drawHud(RenderGameOverlayEvent.Post e) {
		if (this.mc.thePlayer != null) {
			if (this.mc.thePlayer.getHeldItem() != null) {
				if (this.mc.thePlayer.getHeldItem().getItem() instanceof IOverlayItem) {
					((IOverlayItem)this.mc.thePlayer.getHeldItem().getItem()).renderOverlay(e, this.mc.thePlayer.getHeldItem().getItemDamage());
				}
			}
		}
	}
	
	@ForgeSubscribe
	public void onGui(GuiOpenEvent e) {
		if (e.gui instanceof GuiMainMenu && Metro.clientManager.gameStarted) {
			e.setCanceled(true);
			this.mc.displayGuiScreen(new GuiLoading());
		} else if (e.gui instanceof GuiMainMenu && !Metro.clientManager.gameStarted && !Metro.isServer) {
			//this.mc.shutdownMinecraftApplet();
		}
	}
	
	@ForgeSubscribe
	public void renderWorldLast(RenderWorldLastEvent e) {
		if (this.mc.thePlayer != null) {
	        int i = this.mc.theWorld.getLightBrightnessForSkyBlocks(MathHelper.floor_double(this.mc.thePlayer.posX), MathHelper.floor_double(this.mc.thePlayer.posY), MathHelper.floor_double(this.mc.thePlayer.posZ), 0);
	        int j = i % 65536;
	        int k = i / 65536;
	        FancyLightHelper.lightJ = (float)j / 1.0F;
	        FancyLightHelper.lightK = (float)k / 1.0F;
		}
	}
	
    public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), 0, (double)((float)(par3 + 0) * f), (double)((float)(par4 + par6) * f1));
        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), 0, (double)((float)(par3 + par5) * f), (double)((float)(par4 + par6) * f1));
        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), 0, (double)((float)(par3 + par5) * f), (double)((float)(par4 + 0) * f1));
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), 0, (double)((float)(par3 + 0) * f), (double)((float)(par4 + 0) * f1));
        tessellator.draw();
    }
}
