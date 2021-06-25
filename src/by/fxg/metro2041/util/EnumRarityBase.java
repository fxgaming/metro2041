package by.fxg.metro2041.util;

import java.util.Collection;
import java.util.HashSet;

public enum EnumRarityBase {
	BROKEN(1, "broken", "Сломанн", 0),
	GLOOMY(2, "gloomy", "Уныл", 0),
	SCORCHED(3, "scorched", "Опаленн", 0),
	CURVED(4, "curved", "Изогнут", 0),
	OLD(5, "old", "Стар", 0),
	BLOOMING(6, "blooming", "Заядл", 0),
	POISON(7, "poison", "Ядовит", 0),
	ANCIENT(8, "ancient", "Древн", 2),
	CURSED(9, "cursed", "Проклят", 0),
	EMERGENCY(10, "emergency", "Аварийн", 0),
	DARK(11, "dark", "Тёмн", 0),
	//
	SMALL(12, "small", "Маленьк", 2),
	FLOWERING(13, "flowering", "Цветущ", 2),
	WEIGHTY(14, "weighty", "Веск", 2),
	FORGED(15, "forged", "Кован", 0),
	GLACIAL(16, "glacial", "Ледян", 1),
	DEFAULT(17, "default", "Прост", 1),
	BUTCHER(18, "butcher", "Мясницк", 2),
	QUARRY(19, "quarry", "Каменоломн", 0),
	BUZZ(20, "buzz", "Жужжащ", 2),
	NEW(21, "new", "Нов", 0),
	LIGHT(22, "light", "Лёгк", 2),
	BIG(23, "big", "Больш", 1),
	BATTLE(24, "battle", "Боев", 1),
	CHIMERICAL(25, "chimerical", "Химерическ", 2),
	VORACIOUS(25, "voracious", "Прожорлив", 0),
	MOLTEN(26, "molten", "Выплавленн", 0),
	STRONG(27, "strong", "Сильн", 0),
	FURIOUS(28, "furious", "Яростн", 0),
	THUNDEROUS(29, "thunderous", "Громов", 1),
	BLOODY(30, "bloody", "Кровав", 0),
	DISASTROUS(31, "disastrous", "Гибельн", 0),
	FLAMING(32, "flaming", "Пылающ", 2),
	BALANCED(33, "balanced", "Сбалансированн", 0),
	HEAVY(34, "heavy", "Тяжел", 0),
	MAGICAL(35, "magical", "Магическ", 2),
	BLESSED(36, "blessed", "Благословенн", 0),
	RUNIC(37, "runic", "Руническ", 2),
	RISING(38, "rising", "Поднимающ", 3),
	POWERFUL(39, "powerful", "Могущественн", 0),
	DEVOURING(40, "devouring", "Пожирающ", 2),
	TUSKARR(41, "tuskarr", "Клыкаррск", 2),
	HEAVENLY(42, "heavenly", "Небесн", 0),
	SPARKING(43, "sparking", "Искрящ", 2),
	CELESTIAL(44, "celestial", "Астрономическ", 2),
	TROPHY(45, "trophy", "Трофейн", 0),
	COSTY(46, "costy", "Ценн", 0);
	
	
	//0 - ый, 1 - ой, 2 - ий, 3 - ийся
	public int ID;
	public String Tag;
	public String Text;
	public int endingType;
	
	private EnumRarityBase(int id, String tag, String text, int endingType) {
		this.ID = id;
		this.Tag = tag;
		this.Text = text;
		this.endingType = endingType;
	}
}
