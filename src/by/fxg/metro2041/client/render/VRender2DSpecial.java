package by.fxg.metro2041.client.render;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import by.fxg.metro2041.util.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;

public class VRender2DSpecial implements IItemRenderer {
	public int timer = 0;
	
	public VRender2DSpecial() {
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == type.INVENTORY;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType t, ItemStack i, Object... d) {
		GL11.glPushMatrix();
		if (i != null) {
			if (Utils.getNBT(i).hasKey("rare") && Utils.getNBT(i).getInteger("rare") >= 40) { 
				//TODO: render round
			}
			Icon icon = i.getIconIndex();
			Utils.drawTexturedRectFromIcon(0, 0, icon, 16, 16, 0.0D);
			if (Utils.getNBT(i).getInteger("cooldown") > 0) {
//				int cound = Utils.getPercentOf(16, Utils.getNBT(i).getInteger("cooldown"), Utils.getNBT(i).getInteger("maxcooldown"));
//				System.out.println(cound);
//				GL11.glPushMatrix();
//				GL11.glEnable(GL11.GL_BLEND);
//				//GL11.glDisable(GL11.GL_TEXTURE_2D);
//				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
//				Tessellator tt = Tessellator.instance;
//				tt.startDrawingQuads();
//				tt.setColorRGBA(90, 200, 200, 150);
//				tt.addVertex(0, 0, 0);
//				tt.addVertex(16, 0, 0);
//				tt.addVertex(16, 16, 0);
//				tt.addVertex(0, 16, 0);
//				tt.draw();
//				tt.startDrawingQuads();
//				tt.setColorRGBA(0, 0, 0, 100);
//				tt.addVertex(16, 16 - cound, 0);
//				tt.addVertex(16, 16, 0);
//				tt.addVertex(0, 16, 0);
//				tt.addVertex(0, 16 - cound, 0);
//				tt.draw();
//				GL11.glDisable(GL11.GL_BLEND);
//				GL11.glPopMatrix();
				GL11.glPushMatrix();
				GL11.glScalef(0.75F, 0.75F, 0.75F); 
				Minecraft.getMinecraft().fontRenderer.drawStringWithShadow((double)(Utils.getNBT(i).getInteger("cooldown") / 10.0D) + "", 5, 10, Color.white.getRGB());
				GL11.glPopMatrix();
			}
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
		GL11.glPopMatrix();
	}
}
