package by.fxg.metro2041.common.player;

import by.fxg.metro2041.common.item.gun.EnumAmmoType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class VirtualAmmo {
//	public static ExtendedData getData(EntityPlayer p) {
//		return ExtendedPlayer.get(p).extData;
//	}
//	
//	public int getAmmo(ExtendedData ed, EnumAmmoType enu) {
//		switch(enu) {
//			case MilitaryAmmo:
//				return ed.militaryAmmo;
//			case AssaultAmmo:
//				return ed.assaultAmmo;
//			case ShotgunAmmo:
//				return ed.shotgunAmmo;
//			case PistolAmmo:
//				return ed.pistolAmmo;
//			case SniperAmmo:
//				return ed.sniperAmmo;
//			case SteelBalls:
//				return ed.ballsAmmo;
//			case Arrows:
//				return ed.arrowsAmmo;
//			case HeavyCaliber:
//				return ed.heavyCaliberAmmo;
//			case Grenades:
//				return ed.grenadesAmmo;
//			case Knifes:
//				return ed.knifesAmmo;
//			default:
//				return 0;
//		}
//	}
//	
//	public int getMaxAmmo(ExtendedData ed, EnumAmmoType enu) {
//		switch(enu) {
//			case MilitaryAmmo:
//				return ed.maxMilitaryAmmo;
//			case AssaultAmmo:
//				return ed.maxAssaultAmmo;
//			case ShotgunAmmo:
//				return ed.maxShotgunAmmo;
//			case PistolAmmo:
//				return ed.maxPistolAmmo;
//			case SniperAmmo:
//				return ed.maxSniperAmmo;
//			case SteelBalls:
//				return ed.maxBallsAmmo;
//			case Arrows:
//				return ed.maxArrowsAmmo;
//			case HeavyCaliber:
//				return ed.maxHeavyCaliberAmmo;
//			case Grenades:
//				return ed.maxGrenadesAmmo;
//			case Knifes:
//				return ed.maxKnifesAmmo;
//			default:
//				return 0;
//		}
//	}
//	
//	public int setAmmo(ExtendedData ed, EnumAmmoType enu, int cout) {
//		int ammo;
//		int maxammo;
//		int reject;
//		switch(enu) {
//			case MilitaryAmmo:
//				ammo = ed.militaryAmmo;
//				maxammo = ed.maxMilitaryAmmo;
//				reject = 0;
//				if ((reject = ammo + cout) > maxammo) {
//					return reject - maxammo;
//				} else {
//					ed.militaryAmmo = ed.militaryAmmo + cout;
//					return 0;
//				}
//			case AssaultAmmo:
//				ammo = ed.assaultAmmo;
//				maxammo = ed.maxAssaultAmmo;
//				reject = 0;
//				if ((reject = ammo + cout) > maxammo) {
//					return reject - maxammo;
//				} else {
//					ed.assaultAmmo = ed.assaultAmmo + cout;
//					return 0;
//				}
//			case ShotgunAmmo:
//				ammo = ed.shotgunAmmo;
//				maxammo = ed.maxShotgunAmmo;
//				reject = 0;
//				if ((reject = ammo + cout) > maxammo) {
//					return reject - maxammo;
//				} else {
//					ed.shotgunAmmo = ed.shotgunAmmo + cout;
//					return 0;
//				}
//			case PistolAmmo:
//				ammo = ed.pistolAmmo;
//				maxammo = ed.maxPistolAmmo;
//				reject = 0;
//				if ((reject = ammo + cout) > maxammo) {
//					return reject - maxammo;
//				} else {
//					ed.pistolAmmo = ed.pistolAmmo + cout;
//					return 0;
//				}
//			case SniperAmmo:
//				ammo = ed.sniperAmmo;
//				maxammo = ed.maxSniperAmmo;
//				reject = 0;
//				if ((reject = ammo + cout) > maxammo) {
//					return reject - maxammo;
//				} else {
//					ed.sniperAmmo = ed.sniperAmmo + cout;
//					return 0;
//				}
//			case SteelBalls:
//				ammo = ed.ballsAmmo;
//				maxammo = ed.maxBallsAmmo;
//				reject = 0;
//				if ((reject = ammo + cout) > maxammo) {
//					return reject - maxammo;
//				} else {
//					ed.ballsAmmo = ed.ballsAmmo + cout;
//					return 0;
//				}
//			case Arrows:
//				ammo = ed.arrowsAmmo;
//				maxammo = ed.maxArrowsAmmo;
//				reject = 0;
//				if ((reject = ammo + cout) > maxammo) {
//					return reject - maxammo;
//				} else {
//					ed.arrowsAmmo = ed.arrowsAmmo + cout;
//					return 0;
//				}
//			case HeavyCaliber:
//				ammo = ed.heavyCaliberAmmo;
//				maxammo = ed.maxHeavyCaliberAmmo;
//				reject = 0;
//				if ((reject = ammo + cout) > maxammo) {
//					return reject - maxammo;
//				} else {
//					ed.heavyCaliberAmmo = ed.heavyCaliberAmmo + cout;
//					return 0;
//				}
//			case Grenades:
//				ammo = ed.grenadesAmmo;
//				maxammo = ed.maxGrenadesAmmo;
//				reject = 0;
//				if ((reject = ammo + cout) > maxammo) {
//					return reject - maxammo;
//				} else {
//					ed.grenadesAmmo = ed.grenadesAmmo + cout;
//					return 0;
//				}
//			case Knifes:
//				ammo = ed.knifesAmmo;
//				maxammo = ed.maxKnifesAmmo;
//				reject = 0;
//				if ((reject = ammo + cout) > maxammo) {
//					return reject - maxammo;
//				} else {
//					ed.knifesAmmo = ed.knifesAmmo + cout;
//					return 0;
//				}
//			default:
//				return -1;
//		}
//	}
//	
//	public int decAmmo(ExtendedData ed, EnumAmmoType enu, int cout) {
//		int ammo;
//		int maxammo;
//		int reject;
//		switch(enu) {
//			case MilitaryAmmo:
//				ammo = ed.militaryAmmo;
//				maxammo = ed.maxMilitaryAmmo;
//				reject = 0;
//				if ((reject = ammo + cout) < 0) {
//					return reject - maxammo;
//				} else {
//					ed.militaryAmmo = ed.militaryAmmo - cout;
//					return 0;
//				}
//			case AssaultAmmo:
//				ammo = ed.assaultAmmo;
//				maxammo = ed.maxAssaultAmmo;
//				reject = 0;
//				if ((reject = ammo + cout) < 0) {
//					return reject - maxammo;
//				} else {
//					ed.assaultAmmo = ed.assaultAmmo - cout;
//					return 0;
//				}
//			case ShotgunAmmo:
//				ammo = ed.shotgunAmmo;
//				maxammo = ed.maxShotgunAmmo;
//				reject = 0;
//				if ((reject = ammo + cout) < 0) {
//					return reject - maxammo;
//				} else {
//					ed.shotgunAmmo = ed.shotgunAmmo - cout;
//					return 0;
//				}
//			case PistolAmmo:
//				ammo = ed.pistolAmmo;
//				maxammo = ed.maxPistolAmmo;
//				reject = 0;
//				if ((reject = ammo + cout) < 0) {
//					return reject - maxammo;
//				} else {
//					ed.pistolAmmo = ed.pistolAmmo - cout;
//					return 0;
//				}
//			case SniperAmmo:
//				ammo = ed.sniperAmmo;
//				maxammo = ed.maxSniperAmmo;
//				reject = 0;
//				if ((reject = ammo + cout) < 0) {
//					return reject - maxammo;
//				} else {
//					ed.sniperAmmo = ed.sniperAmmo - cout;
//					return 0;
//				}
//			case SteelBalls:
//				ammo = ed.ballsAmmo;
//				maxammo = ed.maxBallsAmmo;
//				reject = 0;
//				if ((reject = ammo + cout) < 0) {
//					return reject - maxammo;
//				} else {
//					ed.ballsAmmo = ed.ballsAmmo - cout;
//					return 0;
//				}
//			case Arrows:
//				ammo = ed.arrowsAmmo;
//				maxammo = ed.maxArrowsAmmo;
//				reject = 0;
//				if ((reject = ammo + cout) < 0) {
//					return reject - maxammo;
//				} else {
//					ed.arrowsAmmo = ed.arrowsAmmo - cout;
//					return 0;
//				}
//			case HeavyCaliber:
//				ammo = ed.heavyCaliberAmmo;
//				maxammo = ed.maxHeavyCaliberAmmo;
//				reject = 0;
//				if ((reject = ammo + cout) < 0) {
//					return reject - maxammo;
//				} else {
//					ed.heavyCaliberAmmo = ed.heavyCaliberAmmo - cout;
//					return 0;
//				}
//			case Grenades:
//				ammo = ed.grenadesAmmo;
//				maxammo = ed.maxGrenadesAmmo;
//				reject = 0;
//				if ((reject = ammo + cout) < 0) {
//					return reject - maxammo;
//				} else {
//					ed.grenadesAmmo = ed.grenadesAmmo - cout;
//					return 0;
//				}
//			case Knifes:
//				ammo = ed.knifesAmmo;
//				maxammo = ed.maxKnifesAmmo;
//				reject = 0;
//				if ((reject = ammo + cout) < 0) {
//					return reject - maxammo;
//				} else {
//					ed.knifesAmmo = ed.knifesAmmo - cout;
//					return 0;
//				}
//			default:
//				return -1;
//		}
//	}
}
