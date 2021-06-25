package by.fxg.metro2041.common.storage;

import java.io.Serializable;

import by.fxg.metro2041.client.model.VModel;
import net.minecraft.client.model.ModelBase;

public class ModelItemCreation implements Serializable {
	public int itemID;
	public VModel model;
	public String texture;
	
	public ModelItemCreation(int id, ModelBase model, String texture) {
		if (model instanceof VModel) {
			this.itemID = id;
			this.model = (VModel)model;
			this.texture = texture;
		} else {
			this.itemID = 0;
			this.model = new VModel(){
				public void render() {
				}};
			this.texture = "";
		}
	}
}
