package by.fxg.metro2041.common.network;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import by.fxg.metro2041.client.storage.ClientData;
import by.fxg.metro2041.common.player.ExtendedPlayer;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;

public class PacketClientData {
	public static class request extends VPacket {
		private int entityID;

		public request(int entityID) {
			this.entityID = entityID;
		}

		public request() {
		}

		public void writeData(ByteArrayDataOutput out) {
			out.writeInt(this.entityID);
		}

		public void readData(ByteArrayDataInput in) throws VPacket.ProtocolException {
			this.entityID = in.readInt();
		}

		public void onPacket(EntityPlayer player, Side side) throws VPacket.ProtocolException {
			if (side.isServer()) {
				if (player.worldObj.getEntityByID(this.entityID) != null && player.worldObj.getEntityByID(this.entityID) instanceof EntityPlayer) PacketDispatcher.sendPacketToPlayer(new PacketClientData.reply((EntityPlayer)player.worldObj.getEntityByID(this.entityID)).makePacket(), (Player)player.worldObj.getEntityByID(this.entityID));
				else PacketDispatcher.sendPacketToPlayer(new PacketClientData.reply(player).makePacket(), (Player)player);
			}
		}
	}
	
	public static class reply extends VPacket {
		private int entityID;
		private int[] intData = new int[8];
		private boolean[] boolData = new boolean[1];
		
		public reply(EntityPlayer p) {
			ExtendedPlayer pl = ExtendedPlayer.get(p);
			this.entityID = p.entityId;
//			this.intData[0] = pl.extData.militaryAmmo;
//			this.intData[1] = pl.extData.assaultAmmo;
//			this.intData[2] = pl.extData.shotgunAmmo;
//			this.intData[3] = pl.extData.pistolAmmo;
//			this.intData[4] = pl.extData.sniperAmmo;
//			this.intData[5] = pl.extData.ballsAmmo;
//			this.intData[6] = pl.extData.arrowsAmmo;
//			this.intData[7] = pl.extData.heavyCaliberAmmo;
			this.boolData[0] = pl.extData.isAiming;
		}

		public reply() {
		}

		public void writeData(ByteArrayDataOutput out) {
			out.writeInt(this.entityID);
//			for (int i = 0; i != 8; i++)
//				out.writeInt(this.intData[i]);
			for (int i = 0; i != 1; i++)
				out.writeBoolean(this.boolData[i]);
		}

		public void readData(ByteArrayDataInput in) throws VPacket.ProtocolException {
			this.entityID = in.readInt();
//			for (int i = 0; i != 8; i++)
//				this.intData[i] = in.readInt();
			for (int i = 0; i != 1; i++)
				this.boolData[i] = in.readBoolean();
		}

		public void onPacket(EntityPlayer player, Side side) throws VPacket.ProtocolException {
			if (side.isClient()) {
//				ClientData.ammo0 = this.intData[0];
//				ClientData.ammo1 = this.intData[1];
//				ClientData.ammo2 = this.intData[2];
//				ClientData.ammo3 = this.intData[3];
//				ClientData.ammo4 = this.intData[4];
//				ClientData.ammo5 = this.intData[5];
//				ClientData.ammo6 = this.intData[6];
//				ClientData.ammo7 = this.intData[7];
				ClientData.isAiming = this.boolData[0];
			}
		}
	}
}
