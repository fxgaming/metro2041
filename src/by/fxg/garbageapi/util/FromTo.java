package by.fxg.garbageapi.util;

public class FromTo {
	public static boolean Int(int check, int from, int to) {
		if (check >= from && check <= to) return true;
		return false;
	}
	
	public static boolean Double(double check, double from, double to) {
		if (check >= from && check <= to) return true;
		return false;
	}
	
	public static boolean Float(float check, float from, float to) {
		if (check >= from && check <= to) return true;
		return false;
	}
	
	public static boolean Long(long check, long from, long to) {
		if (check >= from && check <= to) return true;
		return false;
	}
	
	public static boolean Byte(byte check, byte from, byte to) {
		if (check >= from && check <= to) return true;
		return false;
	}
	
	public static boolean Short(short check, short from, short to) {
		if (check >= from && check <= to) return true;
		return false;
	}
}
