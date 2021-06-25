package by.fxg.metro2041.util;

public class TextUtils {
	public static String getEndingFromNumber(int par1) {
		if (("" + par1).length() == 1) {
			switch(par1) {
				case 1:
					return "";
				case 2:
				case 3:
				case 4:
					return "а";
				case 5:
				case 6:
				case 7:
				case 8:
				case 9:
				case 0:
					return "ов";
			}
			return "err#0 " + par1;
		} else if (("" + par1).length() > 1) {
			int var1 = Integer.valueOf(("" + par1).substring(("" + par1).length() - 1, ("" + par1).length()));
			switch(var1) {
				case 1:
					return "";
				case 2:
				case 3:
				case 4:
					return "а";
				case 5:
				case 6:
				case 7:
				case 8:
				case 9:
				case 0:
					return "ов";
			}
			return "err#1 " + var1;
		} else {
			return "err#2";
		}
	}
}
