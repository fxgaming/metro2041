package by.fxg.garbageapi.util;

import by.fxg.garbageapi.CubeLocation;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerUtil {
	public static boolean isPlayerIn(CubeLocation par1, EntityPlayer par2) {
		boolean pass1 = false;
		boolean pass2 = false;
		boolean pass3 = false;
		if (par1.x1 < 0) {
			pass1 = passNegative((int)par2.posX) >= passNegative(par1.x1) && passNegative((int)par2.posX) <= passNegative(par1.x2);
		} else {
			pass1 = (int)par2.posX >= par1.x1 && (int)par2.posX <= par1.x2;
		}
		if (par1.y1 < 0) {
			pass2 = passNegative((int)par2.posY) >= passNegative(par1.y1) && passNegative((int)par2.posY) <= passNegative(par1.y2);
		} else {
			pass2 = (int)par2.posY >= par1.y1 && (int)par2.posY <= par1.y2;
		}
		if (par1.z1 < 0) {
			pass3 = passNegative((int)par2.posZ) >= passNegative(par1.z1) && passNegative((int)par2.posZ) <= passNegative(par1.z2);
		} else {
			pass3 = (int)par2.posZ >= par1.z1 && (int)par2.posZ <= par1.z2;
		}
		return pass1 && pass2 && pass3;
	}
	
	public static int passNegative(int par1) {
		return -par1;
	}
}
