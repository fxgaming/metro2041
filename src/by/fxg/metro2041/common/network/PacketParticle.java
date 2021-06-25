package by.fxg.metro2041.common.network;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class PacketParticle extends VPacket {
    private String name;
    private double x = 0.0;
    private double y = 0.0;
    private double z = 0.0;
    private double velX;
    private double velY;
    private double velZ;
    
    public PacketParticle(String givenParticleName, double d1, double d2, double d3) {
    	this.name = givenParticleName;
        this.x = d1;
        this.y = d2;
        this.z = d3;
	}
    
	public PacketParticle() {
	}

	public void writeData(ByteArrayDataOutput w) {
		w.writeUTF(this.name);
		w.writeDouble(this.x);
        w.writeDouble(this.y);
        w.writeDouble(this.z);
	}

	public void readData(ByteArrayDataInput r) throws VPacket.ProtocolException {
		this.name = r.readUTF();
        this.x = r.readDouble();
        this.y = r.readDouble();
        this.z = r.readDouble();
	}

	public void onPacket(EntityPlayer player, Side side) throws VPacket.ProtocolException {
		 Minecraft mc = Minecraft.getMinecraft();
         mc.theWorld.spawnParticle(this.name, this.x, this.y, this.z, 0.0F, 0.0F, 0.0F);
	}
}
