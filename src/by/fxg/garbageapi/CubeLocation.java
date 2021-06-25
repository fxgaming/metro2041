package by.fxg.garbageapi;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CubeLocation {
	public int x1;
	public int y1;
	public int z1;
	public int x2;
	public int y2;
	public int z2;
	
	public CubeLocation(int x1, int y1, int z1, int x2, int y2, int z2) {
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;
		this.x2 = x2;
		this.y2 = y2;
		this.z2 = z2;
	}
	
	@SideOnly(Side.SERVER)
	public int[] getXYZ() {
		return new int[]{this.x1, this.y1, this.z1};
	}
	
	public int[] getEndXYZ() {
		return new int[]{this.x2, this.y2, this.z2};
	}
}
