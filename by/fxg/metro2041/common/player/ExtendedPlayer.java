package by.fxg.metro2041.common.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties {
	public static final String EXT_PROP_NAME = "epa";
	public final EntityPlayer player;
	//public InventoryAmmo extInv;
	public ExtendedData extData;
	//public VirtualAmmo virtAmmo;

	public ExtendedPlayer(EntityPlayer player) {
		this.player = player;
		//this.extInv = new InventoryAmmo(player);
		this.extData = new ExtendedData();
		//this.virtAmmo = new VirtualAmmo();
	}

	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties("epa", new ExtendedPlayer(player));
	}

	public static final ExtendedPlayer get(EntityPlayer player) {
		return (ExtendedPlayer)player.getExtendedProperties("epa");
	}

	public void saveNBTData(NBTTagCompound tags) {
		NBTTagCompound prop = new NBTTagCompound();
		//this.extInv.saveToNBT(prop);
		this.extData.saveToNBT(prop);
		tags.setCompoundTag("epa", prop);
	}

	public void loadNBTData(NBTTagCompound tags) {
		NBTTagCompound prop = tags.getCompoundTag("epa");
		if (prop != null) {
			//this.extInv.readFromNBT(prop);
			this.extData.readFromNBT(prop);
		}
	}

	public void init(Entity entity, World world) {
		if (entity instanceof EntityPlayer) {
			//if (this.extInv == null) {
			//	this.extInv = new InventoryAmmo((EntityPlayer)entity);
			//}
			if (this.extData == null) {
				this.extData = new ExtendedData();
			}
		}
	}

	public void copy(ExtendedPlayer p) {
		//this.extInv = p.extInv;
		//this.extData = p.extData;
	}
}
