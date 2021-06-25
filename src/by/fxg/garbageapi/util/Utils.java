package by.fxg.garbageapi.util;

public class Utils {
	public static void removeArrayNulls(Object[] par1) {
		int realSize = 0;
		for (Object o : par1) {
			if (o != null) realSize++;
		}
		Object[] par1Copy = par1.clone();
		par1 = new Object[realSize];
		int nextSize = 0;
		for (Object o : par1Copy) {
			if (o != null) {
				par1[nextSize] = o;
				continue;
			}
		}
	}
}
