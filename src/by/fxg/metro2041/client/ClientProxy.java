package by.fxg.metro2041.client;

import java.util.ArrayList;

import by.fxg.metro2041.Metro;
import by.fxg.metro2041.client.model.block.VModelPortal;
import by.fxg.metro2041.client.render.VRender2DSpecial;
import by.fxg.metro2041.client.render.VRenderBase;
import by.fxg.metro2041.client.render.VRenderCustom;
import by.fxg.metro2041.client.render.VRenderItems;
import by.fxg.metro2041.client.render.entity.RenderGroundItem;
import by.fxg.metro2041.client.render.item.gun.RenderRPK;
import by.fxg.metro2041.common.ServerProxy;
import by.fxg.metro2041.common.block.BlockManager;
import by.fxg.metro2041.common.entity.passive.EntityGroundItem;
import by.fxg.metro2041.common.item.ItemManager;
import by.fxg.metro2041.common.storage.ModelItemCreation;
import by.fxg.metro2041.common.storage.ModelTileCreation;
import by.fxg.metro2041.common.tile.TilePortal;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends ServerProxy {
	public static ArrayList<ModelTileCreation> blockmodels = new ArrayList<ModelTileCreation>();
	public static ArrayList<ModelTileCreation> customblockmodels = new ArrayList<ModelTileCreation>();
	public static ArrayList<ModelItemCreation> itemmodels = new ArrayList<ModelItemCreation>();
	public static ArrayList<Integer> itemSpecial2D = new ArrayList<Integer>();
	
	private int id = -1;
	public void init() {
		for (ModelTileCreation mc : this.blockmodels) {
			ClientRegistry.bindTileEntitySpecialRenderer(mc.tile, new VRenderBase(mc.model, mc.texture, mc.size));
			MinecraftForgeClient.registerItemRenderer(mc.itemID, new VRenderBase(mc.model, mc.texture, mc.size));
		}
		for (ModelTileCreation mc : this.customblockmodels) {
			ClientRegistry.bindTileEntitySpecialRenderer(mc.tile, new VRenderCustom(mc.model, mc.texture, mc.id));
			MinecraftForgeClient.registerItemRenderer(mc.itemID, new VRenderBase(mc.model, mc.texture));
		}
		for (ModelItemCreation mc : this.itemmodels) {
			MinecraftForgeClient.registerItemRenderer(mc.itemID, new VRenderItems(mc.model, mc.texture, getNext()));
		}
		for (Integer id : this.itemSpecial2D) {
			MinecraftForgeClient.registerItemRenderer(id, new VRender2DSpecial());
		}
		
		ClientRegistry.bindTileEntitySpecialRenderer(TilePortal.class, new VRenderCustom(new VModelPortal(), "portal", 3));
		//MinecraftForgeClient.registerItemRenderer(BlockManager.blockPortal.blockID, new VRenderBase(new VModelPortal(), "portal", 1.0F));
		MinecraftForgeClient.registerItemRenderer(ItemManager.testGun.itemID, new RenderRPK());
		
		RenderingRegistry.registerEntityRenderingHandler(EntityGroundItem.class, new RenderGroundItem());
	}
	
	private int getNext() {
		this.id++;
		return this.id;
	}
	
	public void openCustomGui(int var1, EntityPlayer var2) {
		var2.openGui(Metro.instance, var1, var2.worldObj, (int) var2.posX, (int) var2.posY, (int) var2.posZ);
	}
	
	public void openExtGui(int var1, EntityPlayer var2) {
		
	}
}
