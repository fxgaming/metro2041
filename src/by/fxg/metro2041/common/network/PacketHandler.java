package by.fxg.metro2041.common.network;

import java.util.logging.Logger;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import by.fxg.metro2041.common.network.server.VSPacket;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

public class PacketHandler implements IPacketHandler {
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		if (packet != null && packet.channel.equals("metrospec")) {
			try {
				EntityPlayer ePlayer = (EntityPlayer)player;
				ByteArrayDataInput bytein = ByteStreams.newDataInput(packet.data);
				int pakId = bytein.readUnsignedByte();
				VSPacket paket = VSPacket.constructPacket(pakId);
				paket.readData(bytein);
				paket.onPacket(ePlayer, ePlayer.worldObj.isRemote ? Side.CLIENT : Side.SERVER);
			} catch (VSPacket.ProtocolException var8) {
				if (player instanceof EntityPlayerMP) {
					((EntityPlayerMP) player).playerNetServerHandler.kickPlayerFromServer("Protocol Exception!");
					Logger.getLogger("viodspec").warning("Player " + ((EntityPlayer) player).username + " caused a Protocol Exception!");
				}
			} catch (InstantiationException var9) {
				throw new RuntimeException("Unexpected Reflection exception during Packet construction!", var9);
			} catch (IllegalAccessException var10) {
				throw new RuntimeException("Unexpected Reflection exception during Packet construction!", var10);
			}
		} else if (packet != null && packet.channel.equals("metropak")) {
			try {
				EntityPlayer ePlayer = (EntityPlayer) player;
				ByteArrayDataInput bytein = ByteStreams.newDataInput(packet.data);
				int pakId = bytein.readUnsignedByte();
				VPacket paket = VPacket.constructPacket(pakId);
				paket.readData(bytein);
				paket.onPacket(ePlayer, ePlayer.worldObj.isRemote ? Side.CLIENT : Side.SERVER);
			} catch (VPacket.ProtocolException var8) {
				if (player instanceof EntityPlayerMP) {
					((EntityPlayerMP) player).playerNetServerHandler.kickPlayerFromServer("Protocol Exception!");
					Logger.getLogger("viod").warning("Player " + ((EntityPlayer) player).username + " caused a Protocol Exception!");
				}
			} catch (InstantiationException var9) {
				throw new RuntimeException("Unexpected Reflection exception during Packet construction!", var9);
			} catch (IllegalAccessException var10) {
				throw new RuntimeException("Unexpected Reflection exception during Packet construction!", var10);
			}
		}
	}
}
