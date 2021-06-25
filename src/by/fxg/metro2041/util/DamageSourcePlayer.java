package by.fxg.metro2041.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.EntityDamageSource;

public class DamageSourcePlayer extends EntityDamageSource {
	public EntityPlayer source;
	
	public DamageSourcePlayer(EntityPlayer source) {
		super("player_ext", source);
		this.source = source;
	}
	
	public ChatMessageComponent getDeathMessage(EntityLivingBase ent) {
		ChatMessageComponent chat;
		if (this.source.getHeldItem() != null) {
			ItemStack is = this.source.getHeldItem();
			String tag = Utils.getNBT(is).hasKey("rare") ? Utils.raritylist.get(Utils.getNBT(is).getInteger("rare")).Text : "err";
			Integer tage = Utils.getNBT(is).hasKey("rare") ? Utils.raritylist.get(Utils.getNBT(is).getInteger("rare")).endingType : -1;
			String end = tage == 0 ? "ый" : (tage == 1 ? "ой" : (tage == 2 ? "ий" : (tage == 3 ? "ийся" : "or")));
			chat = ChatMessageComponent.createFromText(ent.getEntityName() + " был убит " + this.source.getEntityName() + " с помощью " + is.getDisplayName() + "(" + tag + end + ")");
		} else {
			chat = ChatMessageComponent.createFromText(ent.getEntityName() + " был убит " + this.source.getEntityName());
		}
		return chat;
    }
}
