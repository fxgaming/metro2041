package by.fxg.metro2041.common.tile;

import by.fxg.metro2041.client.particle.VParticleWisp;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TilePortal extends VTileInventory {
	public int tick = 0;
	public String name = "";
	public int[] xyzTeleport = {0, 0, 0};
	public float[] color = {1.0F, 1.0F, 1.0F, 1.0F};
	public boolean isWorking = false;

	public TilePortal() {
		this.items = new ItemStack[3];
	}

	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.isWorking = par1NBTTagCompound.getBoolean("work");
		this.xyzTeleport = par1NBTTagCompound.getIntArray("xyzTeleport");
		this.color[0] = par1NBTTagCompound.getFloat("cR");
		this.color[1] = par1NBTTagCompound.getFloat("cG");
		this.color[2] = par1NBTTagCompound.getFloat("cB");
		this.color[3] = par1NBTTagCompound.getFloat("cF");
		this.name = par1NBTTagCompound.getString("bName");
	}

	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setBoolean("work", this.isWorking);
		par1NBTTagCompound.setIntArray("xyzTeleport", this.xyzTeleport);
		par1NBTTagCompound.setFloat("cR", this.color[0]);
		par1NBTTagCompound.setFloat("cG", this.color[1]);
		par1NBTTagCompound.setFloat("cB", this.color[2]);
		par1NBTTagCompound.setFloat("cF", this.color[3]);
		par1NBTTagCompound.setString("bName", this.name);
	}

	public void setColor(float... par1) {
		this.color = par1;
	}
	
	public void setXYZ(int... par1) {
		this.xyzTeleport = par1;
	}
	
	public void setName(String par1) {
		this.name = par1;
	}
	
	public void updateEntity() {
		super.updateEntity();
		this.tick++;
		if (super.worldObj.isRemote) {
			if (this.isWorking) {
				EntityFX entityfx = new EntitySpellParticleFX(super.worldObj, super.xCoord, super.yCoord + 1.0D, super.zCoord, 0.0D, 0.0D, 0.0D);
                ((EntityFX)entityfx).setRBGColorF(this.color[0], this.color[1], this.color[2]);
                Minecraft.getMinecraft().effectRenderer.addEffect(entityfx);
                entityfx = new EntitySpellParticleFX(super.worldObj, super.xCoord + 1.0D, super.yCoord + 1.0D, super.zCoord, 0.0D, 0.0D, 0.0D);
                ((EntityFX)entityfx).setRBGColorF(this.color[0], this.color[1], this.color[2]);
                Minecraft.getMinecraft().effectRenderer.addEffect(entityfx);
                entityfx = new EntitySpellParticleFX(super.worldObj, super.xCoord + 1.0D, super.yCoord + 1.0D, super.zCoord + 1.0D, 0.0D, 0.0D, 0.0D);
                ((EntityFX)entityfx).setRBGColorF(this.color[0], this.color[1], this.color[2]);
                Minecraft.getMinecraft().effectRenderer.addEffect(entityfx);
                entityfx = new EntitySpellParticleFX(super.worldObj, super.xCoord, super.yCoord + 1.0D, super.zCoord + 1.0D, 0.0D, 0.0D, 0.0D);
                ((EntityFX)entityfx).setRBGColorF(this.color[0], this.color[1], this.color[2]);
                Minecraft.getMinecraft().effectRenderer.addEffect(entityfx);
                
                VParticleWisp wisp = new VParticleWisp(super.worldObj, super.xCoord + 0.5D, super.yCoord + 0.5D, super.zCoord + 0.5D, 1.5F, this.color[0], this.color[1], this.color[2]);
                if (this.tick % 5 == 0) Minecraft.getMinecraft().effectRenderer.addEffect(wisp);
			}
		} else {
			if (this.tick >= 400) {
				this.tick = 0;
				if (!this.isWorking) this.isWorking = true;
			}
			if (this.tick % 10 == 0) {
				PacketDispatcher.sendPacketToAllAround((double) super.xCoord, (double) super.yCoord, (double) super.zCoord, 100.0D, super.worldObj.provider.dimensionId, this.getDescriptionPacket());
			}
		}
	}

	public void openChest() {
	}

	public void closeChest() {
	}

	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return false;
	}

	public String getInventoryName() {
		return "container.Portal";
	}
}
