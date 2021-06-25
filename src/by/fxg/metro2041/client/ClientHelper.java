package by.fxg.metro2041.client;

import java.util.ArrayList;

import by.fxg.metro2041.util.IExtItemRender;

public class ClientHelper {
	public int[] rgb = new int[]{255, 0, 0};
	public int rgbSpeed = 1;
	
	public ClientHelper() {
		
	}
	
	public ClientHelper init() {
		
		return this;
	}
	
	public void updateHelper() {
		if (this.rgb[0] > 255) this.rgb[0] = 255;
		else if (this.rgb[0] < 0) this.rgb[0] = 0;
		if (this.rgb[2] > 255) this.rgb[2] = 255;
		else if (this.rgb[2] < 0) this.rgb[2] = 0;
		if (this.rgb[1] > 255) this.rgb[1] = 255;
		else if (this.rgb[1] < 0) this.rgb[1] = 0;
		if(this.rgb[0] > 0 && this.rgb[2] == 0){
			this.rgb[0] -= this.rgbSpeed;
			this.rgb[1] += this.rgbSpeed;
		}
		if(this.rgb[1] > 0 && this.rgb[0] == 0) {
			this.rgb[1] -= this.rgbSpeed;
			this.rgb[2] += this.rgbSpeed;
		}
		if(this.rgb[2] > 0 && this.rgb[1] == 0) {
			this.rgb[0] += this.rgbSpeed;
			this.rgb[2] -= this.rgbSpeed;
		} 
	}
}
