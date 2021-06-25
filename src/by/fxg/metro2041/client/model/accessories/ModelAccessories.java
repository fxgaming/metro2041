package by.fxg.metro2041.client.model.accessories;

import java.lang.reflect.Field;
import java.util.LinkedList;

import org.lwjgl.opengl.GL11;

import api.player.model.ModelPlayer;
import by.fxg.metro2041.client.model.ModelBipedEx;
import by.fxg.metro2041.client.model.VModelArmor;
import by.fxg.metro2041.common.player.ExtendedPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class ModelAccessories extends VModelArmor {
	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		super.render(par1Entity, par2, par3, par4, par5, par6, par7);
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
	} 
}
