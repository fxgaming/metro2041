package by.fxg.metro2041.common.item.gun;

public enum EnumMagazinType {
	AssaultMag(EnumAmmoType.AssaultAmmo),
	ShotgunMag(EnumAmmoType.ShotgunAmmo),
	PistolMag(EnumAmmoType.PistolAmmo),
	SniperMag(EnumAmmoType.SniperAmmo),
	SteelBallsMag(EnumAmmoType.SteelBalls),
	HeavyCaliberMag(EnumAmmoType.HeavyCaliber);
	
	public EnumAmmoType applicable;
	
	EnumMagazinType(EnumAmmoType applicable) {
		this.applicable = applicable;
	}
	
	public static boolean isApplicable(EnumMagazinType mag, EnumAmmoType ammo) {
		if (mag.applicable == ammo) return true;
		return false;
	}
	
	public boolean isApplicable(EnumAmmoType ammo) {
		if (this.applicable == ammo) return true;
		return false;
	}
	
	public static EnumMagazinType get(int ordinal) {
		for (EnumMagazinType mag : values()) {
			if (mag.ordinal() == ordinal) return mag;
		}
		return null;
	}
}
