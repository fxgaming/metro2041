package by.fxg.metro2041.common.item.gun;

public enum EnumAmmoType {
	AssaultAmmo("Патроны 5.45мм"),
	ShotgunAmmo("Картечь 12мм"),
	PistolAmmo("Патроны 9мм"),
	SniperAmmo("Патроны 7.62мм"),
	SteelBalls("Стальные шарики 9мм"),
	//Arrows,
	HeavyCaliber("Патроны 12.7мм"),
	Grenades("Гранаты"),
	Knifes("Метательные ножи");
	
	public String title;
	EnumAmmoType(String name) {
		this.title = name;
	}
}
