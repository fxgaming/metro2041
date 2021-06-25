package by.fxg.metro2041.common.entity;

import by.fxg.metro2041.Metro;
import by.fxg.metro2041.common.entity.passive.EntityGroundItem;
import cpw.mods.fml.common.registry.EntityRegistry;

public class EntityManager {
	public EntityManager init() {
		EntityRegistry.registerModEntity(EntityGroundItem.class, "grounditem", 80, Metro.instance, 48, 20, true);
		return this;
	}
}
