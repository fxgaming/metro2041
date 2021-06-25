package by.fxg.metro2041.client.cherry;

import java.util.Random;

import by.fxg.metro2041.Metro;
import by.fxg.metro2041.client.render.VRenderHelper;
import by.fxg.metro2041.common.item.ItemManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;

@SideOnly(Side.CLIENT)
public class CherryMainPage extends GuiScreen {
	private static final Random rand = new Random();

	public CherryMainPage() {
	}

	public void updateScreen() {
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	public void initGui() {
		ScaledResolution sr = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
		this.buttonList.add(new CherryButton(0, sr.getScaledWidth() - 23, 11, 12, 12).setText(" X"));
		this.buttonList.add(new CherryButton(1, 11, 28, 60, 10).setText("Главная"));
		this.buttonList.add(new CherryButton(2, 72, 28, 60, 10).setText("Предметы"));
		this.buttonList.add(new CherryButton(3, 133, 28, 60, 10).setText("Блоки"));
		this.buttonList.add(new CherryButton(4, 194, 28, 60, 10).setText("Строения"));
		this.buttonList.add(new CherryButton(5, 255, 28, 60, 10).setText("Инфо"));
	}

	protected void actionPerformed(GuiButton b) {
		if (b.id == 0) {
			this.mc.displayGuiScreen((GuiScreen)null);
			this.mc.setIngameFocus();
		} else if (b.id == 1) {
			this.mc.displayGuiScreen(new CherryMainPage());
		} else if (b.id == 2) {
			this.mc.displayGuiScreen(new CherryItemPage(this, Metro.clientManager.clientWikiDatabase.itemWiki));
		} else if (b.id == 3) {
			this.mc.displayGuiScreen(new CherryBlockPage(this, Metro.clientManager.clientWikiDatabase.blockWiki));
		}
	}

	public void drawScreen(int par1, int par2, float par3) {
		String text = "TODO лист:<SPLIT>Нет крафта у уловителя, нету синтезатора предметов.<SPLIT>Не заполнены разделы в вики СТРОЕНИЯ и ИНФО.<SPLIT>Сервер DiverseMine Panemo находится в БЕТА-тестировании.<SPLIT>О всех найденных проблемах просьба связатся с администрацией сервера.<SPLIT>vk.com/diversemine";
		ScaledResolution sr = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
		VRenderHelper.drawRect(10, 10, sr.getScaledWidth() - 10, sr.getScaledHeight() - 10, 0.0F, 0.0F, 0.0F, 0.4F);
		VRenderHelper.drawRect(10, 10, sr.getScaledWidth() - 10, 27, 0.0F, 0.0F, 0.0F, 0.5F);
		VRenderHelper.drawRect(10, 27, sr.getScaledWidth() - 10, 39, 0.0F, 0.0F, 0.0F, 0.25F);
		VRenderHelper.renderItemStackIntoGUI(new ItemStack(ItemManager.rpgFood, 1, 2), 10, 10);
		VRenderHelper.renderText("Черрипедия. Вишневая книга всепознания.", 26, 15);
		if (!(this.mc.currentScreen instanceof CherryItemPage) && !(this.mc.currentScreen instanceof CherryBlockPage) && !(this.mc.currentScreen instanceof CherryItemInfo)) {
			String[] textspl = text.split("<SPLIT>");
			for (int i = 0; i != textspl.length; i++) {
				VRenderHelper.renderText(textspl[i], 12, 40 + (10 * i));
			}
		}
		super.drawScreen(par1, par2, par3);
	}
}
