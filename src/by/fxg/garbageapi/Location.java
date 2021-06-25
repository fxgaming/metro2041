package by.fxg.garbageapi;

public class Location {
	public int x1;
	public int y1;
	public int z1;
	
	public Location(int x1, int y1, int z1) {
		this.x1 = x1;
		this.y1 = y1;
		this.z1 = z1;
	}
	
	public int[] getXYZ() {
		return new int[]{this.x1, this.y1, this.z1};
	}
}
