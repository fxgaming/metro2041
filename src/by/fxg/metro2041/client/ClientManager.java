package by.fxg.metro2041.client;

import api.player.model.ModelPlayerAPI;
import by.fxg.metro2041.client.anim.ClientAnimationManager;
import by.fxg.metro2041.client.sound.ClientSoundHandler;
import by.fxg.metro2041.client.storage.ClientWikiDatabase;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraftforge.common.MinecraftForge;

public class ClientManager {
	public static boolean gameStarted = false;
	public ClientHelper clientHelper;
	public ClientWikiDatabase clientWikiDatabase;
	public ClientKeyboard clientKeyboard;
	public ClientEventHandler clientEventHandler;
	public ClientTickHandler clientTickHandler;
	public ClientAnimationManager clientAnimationManager;
	public ClientSoundHandler clientSoundHandler;
	
	public ClientManager init() {
		this.clientWikiDatabase = new ClientWikiDatabase().init();
		this.clientHelper = new ClientHelper().init();
		this.clientAnimationManager = new ClientAnimationManager();
		KeyBindingRegistry.registerKeyBinding(this.clientKeyboard = new ClientKeyboard());
		MinecraftForge.EVENT_BUS.register(this.clientEventHandler = new ClientEventHandler());
		MinecraftForge.EVENT_BUS.register(this.clientSoundHandler = new ClientSoundHandler());
		ModelPlayerAPI.register("MetroModelManager", RenderPlayerModel.class);
		TickRegistry.registerTickHandler(this.clientTickHandler = new ClientTickHandler(), Side.CLIENT);
		return this;
	}
}
