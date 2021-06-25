package by.fxg.metro2041.common.damage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;

public class DamageSourceGun extends DamageSource {
	protected Entity damageSourceEntity;
	protected EnumDSGType dsg;

	public DamageSourceGun(Entity e, EnumDSGType et) {
		super("gunMetro");
		this.damageSourceEntity = e;
		this.dsg = et;
	}

	public static DamageSource causeGunDamage(Entity entity, EnumDSGType dsg) {
		return new DamageSourceGun(entity, dsg).setDamageBypassesArmor().setProjectile();
	}

	@Override
	public Entity getEntity() {
		return this.damageSourceEntity;
	}

	@Override
	public ChatMessageComponent getDeathMessage(EntityLivingBase e) {
		String dsgmsg = this.dsg == dsg.GUNSHOT ? " был застрелен " : " был убит ";
        return ChatMessageComponent.createFromText("" + e.getEntityName() + dsgmsg + "игроком " + this.damageSourceEntity.getEntityName());
    }

	public boolean isDifficultyScaled() {
		return false;
	}

	public DamageSource setDamageBypassesArmor() {
		super.setDamageBypassesArmor();
		return this;
	}
}
