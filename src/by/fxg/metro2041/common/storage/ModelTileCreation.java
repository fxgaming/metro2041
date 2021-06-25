package by.fxg.metro2041.common.storage;

import java.io.Serializable;

import by.fxg.metro2041.client.model.VModel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.tileentity.TileEntity;

public class ModelTileCreation implements Serializable {
	public int itemID;
	public int id;
	public VModel model;
	public Class<? extends TileEntity> tile;
	public String texture;
	public float size = 1.0F;
	
	public ModelTileCreation(int id, int id2, ModelBase model, Class<? extends TileEntity> tile, String texture) {
		if (model instanceof VModel) {
			this.itemID = id;
			this.id = id2;
			this.model = (VModel)model;
			this.tile = tile;
			this.texture = texture;
		} else {
			this.itemID = 0;
			this.id = id2;
			this.tile = tile;
			this.model = new VModel(){
				public void render() {
				}};
			this.texture = "";
		}
	}
	
	public ModelTileCreation(int id, int id2, ModelBase model, Class<? extends TileEntity> tile, String texture, float size) {
		if (model instanceof VModel) {
			this.itemID = id;
			this.id = id2;
			this.model = (VModel)model;
			this.tile = tile;
			this.texture = texture;
			this.size = size;
		} else {
			this.itemID = 0;
			this.id = id2;
			this.tile = tile;
			this.model = new VModel(){
				public void render() {
				}};
			this.texture = "";
			this.size = size;
		}
	}
}
