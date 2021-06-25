package by.fxg.metro2041.client.model;

import by.fxg.metro2041.client.model.misc.VEUpfxElement;

public class ModelManager {
	public VEUpfxElement e_upfx_model;
	
	public ModelManager init() {
		this.e_upfx_model = new VEUpfxElement();
		return this;
	}
}
