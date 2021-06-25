package by.fxg.metro2041.common.network;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableBiMap.Builder;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import by.fxg.metro2041.common.network.gun.PacketGunReload;
import by.fxg.metro2041.common.network.gun.PacketGunTrigger;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;

public abstract class VPacket {
	public static final String CHANNEL = "metropak";
	private static final BiMap idMap;
	public static int cout = 0;

	public static VPacket constructPacket(int packetId) throws VPacket.ProtocolException, IllegalAccessException, InstantiationException {
		Class clazz = (Class) idMap.get(Integer.valueOf(packetId));
		if (clazz == null) {
			throw new VPacket.ProtocolException("Unknown Packet Id!");
		} else {
			return (VPacket) clazz.newInstance();
		}
	}

	public final int getPacketId() {
		if (idMap.inverse().containsKey(this.getClass())) {
			return ((Integer)idMap.inverse().get(this.getClass())).intValue();
		} else {
			throw new RuntimeException("Packet " + this.getClass().getSimpleName() + " is missing a mapping!");
		}
	}

	public final Packet makePacket() {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeByte(this.getPacketId());
		this.writeData(out);
		return PacketDispatcher.getPacket(this.CHANNEL, out.toByteArray());
	}

	public abstract void writeData(ByteArrayDataOutput out);

	public abstract void readData(ByteArrayDataInput in) throws VPacket.ProtocolException;

	public abstract void onPacket(EntityPlayer var1, Side var2) throws VPacket.ProtocolException;

	static {
		Builder builder = ImmutableBiMap.builder();
		builder.put(nextId(), VPacketGui.class);
		builder.put(nextId(), PacketClientData.request.class);
		builder.put(nextId(), PacketClientData.reply.class);
		builder.put(nextId(), PacketParticle.class);
		builder.put(nextId(), PacketGunTrigger.class);
		builder.put(nextId(), PacketGunReload.class);
		
		idMap = builder.build();
	}

	public static class ProtocolException extends Exception {
		public ProtocolException() {
		}

		public ProtocolException(String message, Throwable cause) {
			super(message, cause);
		}

		public ProtocolException(String message) {
			super(message);
		}

		public ProtocolException(Throwable cause) {
			super(cause);
		}
	}
	
	public static int nextId() {
		return cout++;
	}
}
