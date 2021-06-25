package by.fxg.metro2041.client.storage;

import java.util.HashMap;

import by.fxg.metro2041.common.storage.ServerKit;

public class ClientData {
	public static int entityid = 0;
	public static boolean isAiming = false;
	
	public static int ammo0 = 0;
	public static int ammo1 = 0;
	public static int ammo2 = 0;
	public static int ammo3 = 0;
	public static int ammo4 = 0;
	public static int ammo5 = 0;
	public static int ammo6 = 0;
	public static int ammo7 = 0;
	
	public static HashMap<Integer, ServerKit> serverKits = new HashMap<Integer, ServerKit>();
	
	//Weather Properties\\
	public static float f1Rain = 0.0F;
	public static float f2Thunder = 0.0F;
	public static float f3Thunderbolt = 0.0F;
}
