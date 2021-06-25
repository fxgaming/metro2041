package by.fxg.metro2041.common;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;

import by.fxg.metro2041.util.EnumRarityBase;
import by.fxg.metro2041.util.Utils;

public class UtilLoader {
	public UtilLoader init() {
		return this.loadEnums();
	}
	
	public UtilLoader loadEnums() {
		Object[] rarity = EnumSet.allOf(EnumRarityBase.class).toArray();
		for (Object emum : rarity) {
			if (emum instanceof EnumRarityBase) {
				Utils.raritylist.put(((EnumRarityBase)emum).ID, (EnumRarityBase)emum);
			}
		}
		return this;
	}
}
