package by.fxg.metro2041.client.gui;

import org.lwjgl.opengl.GL11;

import by.fxg.metro2041.Metro;
import by.fxg.metro2041.common.network.VPacketGui;
import by.fxg.metro2041.util.Utils;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiButtonExtended extends GuiButton {
	public int guiId;
    public GuiButtonExtended(int par1, int par2, int par3, int par4) {
        super(par1, par2, par3, 22, 22, "");
        this.guiId = par4;
    }

    public void drawButton(Minecraft par1Minecraft, int par2, int par3) {
        if (this.drawButton) {
            par1Minecraft.getTextureManager().bindTexture(new ResourceLocation("metro:textures/overlay/overlay.png"));
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            boolean flag = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
            int k = 128;

            if (flag) {
                k += 44;
            }

            GL11.glPushMatrix();
            GL11.glTranslatef((float)(this.xPosition / 2), (float)(this.yPosition / 2), 0.0F);
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            
            this.drawTexturedModalRect(this.xPosition, this.yPosition, k, 90, 44, 44);
            GL11.glPopMatrix();
        }
    }
    

	public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
		boolean inWindow = super.enabled && super.drawButton && mouseX >= super.xPosition && mouseY >= super.yPosition && mouseX < super.xPosition + super.width && mouseY < super.yPosition + super.height;
		if (inWindow) {
			if (mc.thePlayer.inventory.getItemStack() != null) {
				return false;
			}
			EntityClientPlayerMP p = Minecraft.getMinecraft().thePlayer;
			PacketDispatcher.sendPacketToServer((new VPacketGui(this.guiId)).makePacket());
			//if (this.guiId == 250 && !p.worldObj.isRemote) Viod.instance.proxy.openCustomGui(250, p);
		}

      return inWindow;
   }
}
