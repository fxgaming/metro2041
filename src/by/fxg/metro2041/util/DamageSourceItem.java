package by.fxg.metro2041.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.EntityDamageSource;

public class DamageSourceItem extends EntityDamageSource {
	public EntityPlayer source;
	
	public DamageSourceItem(EntityPlayer source) {
		super("item_ext", source);
		this.source = source;
	}
	
	public ChatMessageComponent getDeathMessage(EntityLivingBase ent) {
		ChatMessageComponent chat;
		chat = ChatMessageComponent.createFromText(ent.getEntityName() + " был убит своим оружием");
		return chat;
    }
}
