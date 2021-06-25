package by.fxg.metro2041.common.network.gun;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import by.fxg.metro2041.Metro;
import by.fxg.metro2041.common.item.gun.ItemGun;
import by.fxg.metro2041.common.network.VPacket;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PacketGunTrigger extends VPacket {
	public PacketGunTrigger() {
	}

	public void writeData(ByteArrayDataOutput w) {
	}

	public void readData(ByteArrayDataInput r) throws VPacket.ProtocolException {
	}

	public void onPacket(EntityPlayer player, Side side) throws VPacket.ProtocolException {
		if (Metro.isServer) {
			if (player instanceof EntityPlayerMP) {
				World world = player.worldObj;
				ItemStack itemstack = player.inventory.getCurrentItem();
				if (itemstack != null && itemstack.getItem() != null && itemstack.getItem() instanceof ItemGun) {
					((ItemGun) itemstack.getItem()).fireServer(itemstack, player);
				}
			}
		}
	}
}
