package by.fxg.metro2041.common.player;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ExtendedData {
//	public static int maxMilitaryAmmo = 10000;
//	public static int maxAssaultAmmo = 1000;
//	public static int maxShotgunAmmo = 400;
//	public static int maxPistolAmmo = 500;
//	public static int maxSniperAmmo = 100;
//	public static int maxBallsAmmo = 300;
//	public static int maxArrowsAmmo = 160;
//	public static int maxHeavyCaliberAmmo = 80;
//	public static int maxGrenadesAmmo = 5;
//	public static int maxKnifesAmmo = 10;
	
	public int stance;
	public int fire;
	public Boolean isAiming;
//	public int militaryAmmo;
//	public int assaultAmmo;
//	public int shotgunAmmo;
//	public int pistolAmmo;
//	public int sniperAmmo;
//	public int ballsAmmo;
//	public int arrowsAmmo;
//	public int heavyCaliberAmmo;
//	public int grenadesAmmo;
//	public int knifesAmmo;
//	
	public ExtendedData() {
		this.stance = 0;
		this.fire = 0;
		this.isAiming = false;
//		this.militaryAmmo = 0;
//		this.assaultAmmo = 0;
//		this.shotgunAmmo = 0;
//		this.pistolAmmo = 0;
//		this.sniperAmmo = 0;
//		this.ballsAmmo = 0;
//		this.arrowsAmmo = 0;
//		this.heavyCaliberAmmo = 0;
//		this.grenadesAmmo = 0;
//		this.knifesAmmo = 0;
	}
	
	public void saveToNBT(NBTTagCompound tags) {
		NBTTagCompound nbt = tags.getCompoundTag("extendedData");
		nbt.setInteger("stance", this.stance);
		nbt.setInteger("fire", this.fire);
		nbt.setBoolean("isAiming", this.isAiming);
//		nbt.setInteger("militaryAmmo", this.militaryAmmo);
//		nbt.setInteger("assaultAmmo", this.assaultAmmo);
//		nbt.setInteger("shotgunAmmo", this.shotgunAmmo);
//		nbt.setInteger("pistolAmmo", this.pistolAmmo);
//		nbt.setInteger("sniperAmmo", this.sniperAmmo);
//		nbt.setInteger("ballsAmmo", this.ballsAmmo);
//		nbt.setInteger("arrowsAmmo", this.arrowsAmmo);
//		nbt.setInteger("heavyCaliberAmmo", this.heavyCaliberAmmo);
//		nbt.setInteger("grenadesAmmo", this.grenadesAmmo);
//		nbt.setInteger("knifesAmmo", this.knifesAmmo);
		tags.setCompoundTag("extendedData", nbt);
	}

	public void readFromNBT(NBTTagCompound tags) {
		NBTTagCompound nbt = tags.getCompoundTag("extendedData");
		this.stance = nbt.getInteger("stance");
		this.fire = nbt.getInteger("fire");
		this.isAiming = nbt.getBoolean("isAiming");
//		this.militaryAmmo = nbt.getInteger("militaryAmmo");
//		this.assaultAmmo = nbt.getInteger("assaultAmmo");
//		this.shotgunAmmo = nbt.getInteger("shotgunAmmo");
//		this.pistolAmmo = nbt.getInteger("pistolAmmo");
//		this.sniperAmmo = nbt.getInteger("sniperAmmo");
//		this.ballsAmmo = nbt.getInteger("ballsAmmo");
//		this.arrowsAmmo = nbt.getInteger("arrowsAmmo");
//		this.heavyCaliberAmmo = nbt.getInteger("heavyCaliberAmmo");
//		this.grenadesAmmo = nbt.getInteger("grenadesAmmo");
//		this.knifesAmmo = nbt.getInteger("knifesAmmo");
	}
}
