package by.fxg.metro2041.common.network;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;

public class PacketTest extends VPacket {
	public PacketTest() {
	}

	public void writeData(ByteArrayDataOutput w) {
	}

	public void readData(ByteArrayDataInput r) throws VPacket.ProtocolException {
	}

	public void onPacket(EntityPlayer player, Side side) throws VPacket.ProtocolException {
	}
}
