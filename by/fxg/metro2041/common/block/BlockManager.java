package by.fxg.metro2041.common.block;

import java.lang.reflect.Field;

import by.fxg.metro2041.client.ClientProxy;
import by.fxg.metro2041.client.model.VModel;
import by.fxg.metro2041.client.model.block.ModelRail45Left0;
import by.fxg.metro2041.client.model.block.ModelRail45Left1;
import by.fxg.metro2041.client.model.block.ModelRail45Right0;
import by.fxg.metro2041.client.model.block.ModelRail45Right1;
import by.fxg.metro2041.client.model.block.ModelRailQuad0;
import by.fxg.metro2041.client.model.block.ModelRailStraight0;
import by.fxg.metro2041.client.model.block.ModelRailStraight1;
import by.fxg.metro2041.client.model.block.ModelRailStraight45Left0;
import by.fxg.metro2041.client.model.block.ModelRailStraight45Right0;
import by.fxg.metro2041.client.model.block.VModelToolStation;
import by.fxg.metro2041.common.storage.ModelTileCreation;
import by.fxg.metro2041.common.tile.Rail45Left0;
import by.fxg.metro2041.common.tile.Rail45Left1;
import by.fxg.metro2041.common.tile.Rail45Right0;
import by.fxg.metro2041.common.tile.Rail45Right1;
import by.fxg.metro2041.common.tile.RailQuad0;
import by.fxg.metro2041.common.tile.RailStraight0;
import by.fxg.metro2041.common.tile.RailStraight1;
import by.fxg.metro2041.common.tile.RailStraight45Left0;
import by.fxg.metro2041.common.tile.RailStraight45Right0;
import by.fxg.metro2041.common.tile.TilePortal;
import by.fxg.metro2041.common.tile.TileToolStation;
import by.fxg.metro2041.util.Utils;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockManager {

	public int id = 3999;
	public static VBlockGuiBase blockToolStation;
	public static VBlockBase blockPortal;
	public static BlockDecoration RailStraight0;
	public static BlockDecoration RailStraight1;
	public static BlockDecoration RailStraight45Left0;
	public static BlockDecoration RailStraight45Right0;
	public static BlockDecoration Rail45Left0;
	public static BlockDecoration Rail45Left1;
	public static BlockDecoration Rail45Right0;
	public static BlockDecoration Rail45Right1;
	public static BlockDecoration RailQuad0;
	
	public static BlockMulti metal;
	public static BlockMulti ground;
	public static BlockMulti stone;
	public static BlockMulti concrete;
	
	public BlockManager init() {
		//registerGuiBlock(TileToolStation.class, "TileToolStation", "blockToolStation", "Инструментальный стол", 2200, 1001, new VModelToolStation(), "blocktoolstation", 1);
		//registerBlockModel(tf0.class, "tf0", "Блок тоннеля 1", 3001, Material.iron, new mtf0(), "tf0", 1.0F);
		GameRegistry.registerBlock(metal = new BlockMulti(3001, Material.iron, "metal", 1).setName("Iron block"), "metal");
		GameRegistry.registerBlock(ground = new BlockMulti(3002, Material.ground, "ground", 5).setName("Ground block"), "ground");
		GameRegistry.registerBlock(stone = new BlockMulti(3003, Material.rock, "stone", 15).setName("Stone block"), "stone");
		GameRegistry.registerBlock(concrete = new BlockMulti(3004, Material.rock, "concrete", 3).setName("Concrete block"), "concrete");
		registerRail(RailStraight0.class, "RailStraight0", "Рельсы прямые короткие", 3501, Material.iron, new ModelRailStraight0(), "rail", 1.0F, 1, 8);
		registerRail(RailStraight1.class, "RailStraight1", "Рельсы прямые длинные", 3502, Material.iron, new ModelRailStraight1(), "rail", 1.0F, 1, 8);
		registerRail(RailStraight45Left0.class, "RailStraight45Left0", "Рельсы прямые-левые", 3503, Material.iron, new ModelRailStraight45Left0(), "rail", 1.0F, 1, 8);
		registerRail(RailStraight45Right0.class, "RailStraight45Right0", "Рельсы прямые-правые", 3504, Material.iron, new ModelRailStraight45Right0(), "rail", 1.0F, 1, 8);
		registerRail(Rail45Left0.class, "Rail45Left0", "Рельсы левые короткие", 3505, Material.iron, new ModelRail45Left0(), "rail", 1.0F, 1, 8);
		registerRail(Rail45Left1.class, "Rail45Left1", "Рельсы левые длинные", 3506, Material.iron, new ModelRail45Left1(), "rail", 1.0F, 1, 8);
		registerRail(Rail45Right0.class, "Rail45Right0", "Рельсы правые короткие", 3507, Material.iron, new ModelRail45Right0(), "rail", 1.0F, 1, 8);
		registerRail(Rail45Right1.class, "Rail45Right1", "Рельсы правые длинные", 3508, Material.iron, new ModelRail45Right1(), "rail", 1.0F, 1, 8);
		registerRail(RailQuad0.class, "RailQuad0", "Рельсы перекрестье", 3509, Material.iron, new ModelRailQuad0(), "rail", 1.0F, 1, 8);
//		GameRegistry.registerTileEntity(TilePortal.class, "TilePortal");
//		GameRegistry.registerBlock((Block)(this.blockPortal = new BlockPortal(2201)), "blockPortal.item");
//		LanguageRegistry.addName(this.blockPortal, "Портал"); 
		
		return this;
	}
	
	private void registerGuiBlock(Class tile, String tileobj, String field, String name, int id, int guid, VModel model, String texture, int rendid) {
		try {
			GameRegistry.registerTileEntity(tile, tile.getSimpleName());
			Field f = this.getClass().getField(field);
			Object fo = f.get(this.getClass());
			GameRegistry.registerBlock((Block)(fo = new VBlockGuiBase(id, tileobj, guid).install(tile.getSimpleName() + ".block")), tile.getSimpleName() + ".item");
			LanguageRegistry.addName(fo, name); 
			f.set(this.getClass(), fo);
			if (Utils.isClient()) {
				ClientProxy.customblockmodels.add(new ModelTileCreation(id, rendid, model, tile, texture));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void registerDecoration(Class tile, String field, String name, int id, Material mat, VModel model, String texture, float size, int rendid, int maxRotation) {
		try {
			GameRegistry.registerTileEntity(tile, tile.getSimpleName());
			Field f = this.getClass().getField(field);
			Object fo = f.get(this.getClass());
			GameRegistry.registerBlock((Block)(fo = new BlockDecoration(id, mat, field, field, maxRotation).setName(name)), field);
			LanguageRegistry.addName(fo, name); 
			f.set(this.getClass(), fo);
			if (Utils.isClient()) {
				ClientProxy.customblockmodels.add(new ModelTileCreation(id, rendid, model, tile, texture));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void registerRail(Class tile, String field, String name, int id, Material mat, VModel model, String texture, float size, int rendid, int maxRotation) {
		try {
			GameRegistry.registerTileEntity(tile, tile.getSimpleName());
			Field f = this.getClass().getField(field);
			Object fo = f.get(this.getClass());
			GameRegistry.registerBlock((Block)(fo = new BlockRail(id, mat, field, field, maxRotation).setName(name)), field);
			LanguageRegistry.addName(fo, name); 
			f.set(this.getClass(), fo);
			if (Utils.isClient()) {
				ClientProxy.customblockmodels.add(new ModelTileCreation(id, rendid, model, tile, texture));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int nextInt() {
		this.id++;
		return this.id;
	}
}
