package by.fxg.metro2041.client.cherry;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;

import by.fxg.metro2041.client.render.VRenderHelper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;

public class CherryBlockPage extends CherryMainPage {
	private ArrayList<ItemWiki> currentList = new ArrayList<ItemWiki>();
	private CherryMainPage screen;
	private int iid = 0;
	private int lastScroll = 0;
	public int startScroll = 0;
	
	private CherryButtonItem[] slots = new CherryButtonItem[7];
	private CherryButton open;
	
	public CherryBlockPage(CherryMainPage scr, ArrayList<ItemWiki> arr) {
		this.screen = scr;
		this.currentList = arr;
	}
	
	public void initGui() {
		for (int i = 0; i != this.slots.length; i++) {
			super.buttonList.add(this.slots[i] = new CherryButtonItem(10 + i, 15, 52 + (i * 25), 200, 22));
		}
		super.buttonList.add(this.open = new CherryButton(8, 225, 90, 150, 15).setText("Просмотреть информацию").setEnabled(false));
		this.lastScroll = -1;
		this.iid = -1;
		super.initGui();
	}
	
	public void updateScreen() {
		if (this.iid != -1) {
			this.open.setEnabled(true);
		} else {
			this.open.setEnabled(false);
		}
		if (this.lastScroll != this.startScroll) {
			for (int i = 0; i != this.slots.length; i++) {
				try {
					ItemWiki j = (ItemWiki)this.currentList.get(this.startScroll + i);
					this.slots[i].reinstall(j.itemStack, this.startScroll + i);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	protected void actionPerformed(GuiButton b) {
		if (b.id >= 10) {
			this.iid = ((CherryButtonItem)b).slotid;
		} else if (b.id == 8) {
			this.mc.displayGuiScreen(new CherryItemInfo(this, this.currentList.get(this.iid), this.startScroll));
		}
		super.actionPerformed(b);
	}
	
	public void drawScreen(int par1, int par2, float par3) {
		ScaledResolution sr = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
		if (this.iid != -1) {
			VRenderHelper.drawRect(220, 52, sr.getScaledWidth() - 15, 109, 0.0F, 0.0F, 0.0F, 0.5F);
		}
		super.drawScreen(par1, par2, par3);
		VRenderHelper.renderText("Блоки можно прокрутить колёсиком мыши!", 15, 41);
		if (this.iid != -1) {
			VRenderHelper.renderText("Выбранный блок:", 225, 57);
			ItemStack is = this.currentList.get(this.iid).itemStack;
			VRenderHelper.renderText(is.getDisplayName(), 250, 67);
			VRenderHelper.renderText(is.itemID + ":" + is.getItemDamage(), 250, 77);
			VRenderHelper.renderItemStackIntoGUI(is, 228, 68);
		}
	}
	
	public void handleMouseInput() {
	    super.handleMouseInput();
	    int dw = Mouse.getEventDWheel();
	    if(dw != 0) {
	        if (dw > 0) {
	            dw = -1;
	        } else {
	            dw = 1;
	        }
	        float amountScrolled = (float)(dw * 1);
	        if (GuiScreen.isShiftKeyDown()) amountScrolled = (float)(dw * 5);
	        if (GuiScreen.isCtrlKeyDown()) amountScrolled = (float)(dw * 10);
	        if (this.startScroll + amountScrolled >= 0 && this.startScroll + amountScrolled < this.currentList.size() - 7) {
	        	this.startScroll += amountScrolled;
	        } else if (this.startScroll + amountScrolled > 0 && this.startScroll + amountScrolled >= this.currentList.size() - 7) {
	        	this.startScroll = this.currentList.size() - 7;
	        } else if (this.startScroll + amountScrolled <= 0 && this.startScroll + amountScrolled < this.currentList.size() - 7) {
	        	this.startScroll = 0;
	        }
	    }
	}
}
