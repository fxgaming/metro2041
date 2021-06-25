package by.fxg.metro2041;

import by.fxg.metro2041.client.ClientManager;
import by.fxg.metro2041.common.CommonManager;
import by.fxg.metro2041.common.ServerProxy;
import by.fxg.metro2041.common.UtilLoader;
import by.fxg.metro2041.common.block.BlockManager;
import by.fxg.metro2041.common.entity.EntityManager;
import by.fxg.metro2041.common.item.ItemManager;
import by.fxg.metro2041.common.network.PacketHandler;
import by.fxg.metro2041.util.FakePlayerFactory;
import by.fxg.metro2041.util.Utils;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "Metro", name = "Metro2041", version = "0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, channels = {"metropak", "metrospec"}, packetHandler = PacketHandler.class)
public class Metro {
	@Instance("Metro")
	public static Metro instance;
	public static final boolean isServer = true;
	
	@SidedProxy(clientSide = "by.fxg.metro2041.client.ClientProxy", serverSide = "by.fxg.metro2041.common.ServerProxy")
	public static ServerProxy proxy;
	
	public static BlockManager blocks;
	public static ItemManager items;
	public static EntityManager entityes;
	public static UtilLoader utilLoader;
	public static CommonManager commonManager;
	public static ClientManager clientManager;
	public static FakePlayerFactory fakePlayerFactory;

	/**
	 * TODO:
	 *  Звуки:
	 *   - звуки дропа разных магазинов на пол если игрок не сидит
	 *   - звук разрядки оружия
	 *   - звук зарядки патрона в магазин
	 *   - звук вытаскивания патрона с магазина
	 */
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		this.items = new ItemManager().init();
		this.blocks = new BlockManager().init();
		this.entityes = new EntityManager().init();
		this.utilLoader = new UtilLoader().init();
		this.commonManager = new CommonManager().init();
		
		if (Utils.isClient()) {
			this.clientManager = new ClientManager().init();
			this.fakePlayerFactory = new FakePlayerFactory();
		}
		proxy.init();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		this.items.post();
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent e) {
		this.commonManager.commandManager.init(e);
	}
}
