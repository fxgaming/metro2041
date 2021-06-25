package by.fxg.metro2041.common.item;

import by.fxg.metro2041.client.ClientProxy;
import by.fxg.metro2041.client.anim.GA_RPK_Fired;
import by.fxg.metro2041.client.anim.GA_RPK_Reload;
import by.fxg.metro2041.common.item.gun.EnumAmmoType;
import by.fxg.metro2041.common.item.gun.EnumGunType;
import by.fxg.metro2041.common.item.gun.EnumMagazinType;
import by.fxg.metro2041.common.item.gun.EnumShotParts;
import by.fxg.metro2041.common.item.gun.EnumShotType;
import by.fxg.metro2041.common.item.gun.ItemAmmo;
import by.fxg.metro2041.common.item.gun.ItemGun;
import by.fxg.metro2041.common.item.gun.ItemMagazin;
import by.fxg.metro2041.common.storage.ModelItemCreation;
import by.fxg.metro2041.debug.DebugItem;
import by.fxg.metro2041.util.Utils;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.model.ModelBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ItemManager {
	public static ItemGun testGun;
	
	public static VItem assaultAmmo;
	public static VItem shotgunAmmo;
	public static VItem pistolAmmo;
	public static VItem sniperAmmo;
	public static VItem ballsAmmo;
	//public static VItem arrowsAmmo;
	public static VItem heavyCaliberAmmo;
	
	public static VItem assaultMag;
	public static VItem shotgunMag;
	public static VItem pistolMag;
	public static VItem sniperMag;
	public static VItem ballsMag;
	public static VItem heavyCaliberMag;
	
	public static VMultiItem rpgFood;
	public static VMultiItem rpgItem;
	public static VMultiItem rpgItems;
	public static DebugItem itemDebug = (DebugItem)new DebugItem(9743, 4).setName("Debug[боевой инвентарь]", 0).setName("Debug[поиск руд]", 1).setName("Debug[перс данные ext]", 2).setName("Tool[Портальный настройщик]", 3);
	
	public ItemManager init() {
		testGun = new ItemGun(5000, "rpk", EnumGunType.Assault, EnumShotType.AUTO, EnumShotParts.ALL).setGun(5, 30, 15.0F, 1.5F, 85L).setMag(EnumMagazinType.AssaultMag).setAnimation(new GA_RPK_Reload(), new GA_RPK_Fired());
		
		//militaryAmmo = new VItem(5800, 10000, "militaryAmmo", CreativeTabs.tabMaterials).setName("Армейские патроны 5.45мм");
		//arrowsAmmo = new VItem(5806, 160, "arrowsAmmo", CreativeTabs.tabMaterials).setName("Стальные стрелы");
		assaultAmmo = new ItemAmmo(5801, "assaultAmmo", EnumAmmoType.AssaultAmmo).setName("Патроны 5.45мм");
		shotgunAmmo = new ItemAmmo(5802, "shotgunAmmo", EnumAmmoType.ShotgunAmmo).setName("Картечь 12мм");
		pistolAmmo = new ItemAmmo(5803, "pistolAmmo", EnumAmmoType.PistolAmmo).setName("Патроны 9мм");
		sniperAmmo = new ItemAmmo(5804, "sniperAmmo", EnumAmmoType.SniperAmmo).setName("Патроны 7.62мм");
		ballsAmmo = new ItemAmmo(5805, "ballsAmmo", EnumAmmoType.SteelBalls).setName("Стальные шарики 9мм");
		heavyCaliberAmmo = new ItemAmmo(5807, "heavyCaliberAmmo", EnumAmmoType.HeavyCaliber).setName("Патроны 12.7мм");
		
		assaultMag = new ItemMagazin(5821, "assaultMag", EnumMagazinType.AssaultMag, 30).setName("Винтовочный магазин");
		shotgunMag = new ItemMagazin(5822, "shotgunMag", EnumMagazinType.ShotgunMag, 6).setName("Холдер картечи"); //Холдер дробных патронов
		pistolMag = new ItemMagazin(5823, "pistolMag", EnumMagazinType.PistolMag, 15).setName("Пистолетный магазин");
		sniperMag = new ItemMagazin(5824, "sniperMag", EnumMagazinType.SniperMag, 5).setName("СВ магазин"); //магазин Снайперской Винтовки
		ballsMag = new ItemMagazin(5825, "ballsMag", EnumMagazinType.SteelBallsMag, 15).setName("КСШ"); //Коробка для стальных шаров
		heavyCaliberMag = new ItemMagazin(5826, "heavyCaliberMag", EnumMagazinType.HeavyCaliberMag, 5).setName("СВ-ВК магазин"); //Снайпер-Винтовочный ВысокоКалиберный магазин
		
		rpgFood = new VMultiItem(5900, 26, 64, "rpgFood").setName("Клубника", 0).setName("Кусок арбуза", 1).setName("Вишня", 2).setName("Светлый виноград", 3).setName("Тёмный виноград", 4).setName("Ежевика", 5).setName("Зеленый перец", 6).setName("Желтый перец", 7).setName("Красный перец", 8).setName("Красная редька", 9).setName("Морковь", 10).setName("Груша", 11).setName("Ананас", 12).setName("Апельсин", 13).setName("Лимон", 14).setName("Банан", 15).setName("Гриб", 16).setName("Рыбье тело", 17).setName("Сырая рыба", 18).setName("Жаренная рыба", 19).setName("Сырое мясо", 20).setName("Жаренное мясо", 21).setName("Желудь", 22).setName("Темный хлеб", 23).setName("Сыр", 24).setName("Киви", 25);
		rpgItem = new VMultiItem(5901, 3, 1, "rpgItem").setName("Карта", 0).setName("Записная книжка", 1).setName("Часы", 2);
		rpgItems = new VMultiItem(5902, 24, 64, "rpgItems").setName("Трехлистный клевер", 0).setName("Кристалл", 1).setName("Ткань", 2).setName("Перо", 3).setName("Лист", 4).setName("Кость", 5).setName("Книга морей", 6).setName("Книга космоса", 7).setName("Книга расписаний", 8).setName("Книга исскуств", 9).setName("Некромоникон", 10).setName("Книга крови", 11).setName("Книга секретов", 12).setName("Книга силуэтов", 13).setName("Книга", 14).setName("Книга путеводов", 15).setName("Книга карт", 16).setName("Книга X", 17).setName("Книга древностей", 18).setName("Книга воспоминаний", 19).setName("Книга боевых исскуств", 20).setName("Песчаник с гравировкой", 21).setName("Книга шифров", 22).setName("Каменная плита с гравировкой", 23);
		return this;
	}
	
	public ItemManager post() {
		
		return this;
	}
	
	public void registerModel(ModelBase par1, String par2, int par3) {
		if (Utils.isClient()) {
			ClientProxy.itemmodels.add(new ModelItemCreation(par3, par1, par2));
		}
	}
	
	public void add(String obj, ItemStack i, char[] j, ItemStack[] k) {
		if (k.length == 1)
			GameRegistry.addRecipe(i, new Object[]{obj.substring(0, 3), obj.substring(3, 6), obj.substring(6, 9), j[0], k[0]});
		else if (k.length == 2)
			GameRegistry.addRecipe(i, new Object[]{obj.substring(0, 3), obj.substring(3, 6), obj.substring(6, 9), j[0], k[0], j[1], k[1]});
		else if (k.length == 3)
			GameRegistry.addRecipe(i, new Object[]{obj.substring(0, 3), obj.substring(3, 6), obj.substring(6, 9), j[0], k[0], j[1], k[1], j[2], k[2]});
		else if (k.length == 4)
			GameRegistry.addRecipe(i, new Object[]{obj.substring(0, 3), obj.substring(3, 6), obj.substring(6, 9), j[0], k[0], j[1], k[1], j[2], k[2], j[3], k[3]});
		else if (k.length == 5)
			GameRegistry.addRecipe(i, new Object[]{obj.substring(0, 3), obj.substring(3, 6), obj.substring(6, 9), j[0], k[0], j[1], k[1], j[2], k[2], j[3], k[3], j[4], k[4]});
		else if (k.length == 6)
			GameRegistry.addRecipe(i, new Object[]{obj.substring(0, 3), obj.substring(3, 6), obj.substring(6, 9), j[0], k[0], j[1], k[1], j[2], k[2], j[3], k[3], j[4], k[4], j[5], k[5]});
		else if (k.length == 7)
			GameRegistry.addRecipe(i, new Object[]{obj.substring(0, 3), obj.substring(3, 6), obj.substring(6, 9), j[0], k[0], j[1], k[1], j[2], k[2], j[3], k[3], j[4], k[4], j[5], k[5], j[6], k[6]});
		else if (k.length == 8) 
			GameRegistry.addRecipe(i, new Object[]{obj.substring(0, 3), obj.substring(3, 6), obj.substring(6, 9), j[0], k[0], j[1], k[1], j[2], k[2], j[3], k[3], j[4], k[4], j[5], k[5], j[6], k[6], j[7], k[7]});
		else if (k.length == 9)
			GameRegistry.addRecipe(i, new Object[]{obj.substring(0, 3), obj.substring(3, 6), obj.substring(6, 9), j[0], k[0], j[1], k[1], j[2], k[2], j[3], k[3], j[4], k[4], j[5], k[5], j[6], k[6], j[7], k[7], j[8], k[8]});
	}
}
