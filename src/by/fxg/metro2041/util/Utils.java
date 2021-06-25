package by.fxg.metro2041.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class Utils {
	public static HashMap<Integer, EnumRarityBase> raritylist = new HashMap<Integer, EnumRarityBase>();
	public static  AmmoComparator magazinComparator = new AmmoComparator();
	public static boolean isClient() {
		return FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT;
	}
	
	public static NBTTagCompound getNBT(ItemStack i) {
		if (i.getTagCompound() == null) {
			i.setTagCompound(new NBTTagCompound("tag"));
			return i.getTagCompound();
		} else {
			return i.getTagCompound();
		}
	}
	
	public static Entity getAimedEntity(World par1World, Entity par2Entity, double par3Range, float par4Padding) {
		return getAimedEntity(par1World, par2Entity, par3Range, par4Padding, false);
	}

	public static Entity getAimedEntity(World par1World, Entity par2Entity, double par3Range, float par4Padding, boolean par5NonCollide) {
		return getAimedEntity(par1World, par2Entity, par3Range, par4Padding, par5NonCollide, (IEntitySelector)null);
	}

	public static Entity getAimedEntity(World par1World, Entity par2Entity, double par3Range, float par4Padding, boolean par5NonCollide, IEntitySelector par6Selector) {
		Entity var1 = null;
		Vec3 var2 = Vec3.fakePool.getVecFromPool(par2Entity.posX, par2Entity.posY + (double) par2Entity.getEyeHeight(), par2Entity.posZ);
		Vec3 var3 = par2Entity.getLookVec();
		List var4 = par1World.getEntitiesWithinAABBExcludingEntity(par2Entity, par2Entity.boundingBox.addCoord(var3.xCoord * par3Range, var3.yCoord * par3Range, var3.zCoord * par3Range).expand((double) par4Padding, (double) par4Padding, (double) par4Padding), par6Selector);
		double var5 = 0.0D;
		Vec3 var6 = null;
		List var7 = new ArrayList();
		if (var4.size() > 0) {
			var6 = var2.addVector(var3.xCoord * par3Range, var3.yCoord * par3Range, var3.zCoord * par3Range);
		}
		float var8;
		for (int i$ = 0; i$ < var4.size(); ++i$) {
			Entity var9 = (Entity) var4.get(i$);
			if ((var9.canBeCollidedWith() || par5NonCollide) && par1World.rayTraceBlocks_do_do(par1World.getWorldVec3Pool().getVecFromPool(par2Entity.posX, par2Entity.posY + (double) par2Entity.getEyeHeight(), par2Entity.posZ), par1World.getWorldVec3Pool().getVecFromPool(var9.posX, var9.posY + (double) var9.getEyeHeight(), var9.posZ), false, true) == null) {
				var8 = par4Padding > 0.0F ? par4Padding - 0.3F : 0.8F;
				var8 = Math.max(var8, var9.getCollisionBorderSize());
				AxisAlignedBB var10 = var9.boundingBox.expand((double) var8, (double) var8, (double) var8);
				MovingObjectPosition var11 = null;
				if (var10.isVecInside(var2)) {
					if (0.0D < var5 || var5 == 0.0D) {
						var1 = var9;
						var5 = 0.0D;
					}
				} else if ((var11 = var10.calculateIntercept(var2, var6)) != null) {
					double var12 = var2.distanceTo(var11.hitVec);
					if (var12 < var5 || var5 == 0.0D) {
						var1 = var9;
						var5 = var12;
					}
				}
			}
		}
		if (var1 != null) {
			Vec3 var9 = Vec3.fakePool.getVecFromPool(var1.posX, var1.posY + (double) var1.getEyeHeight(), var1.posZ);
			for (int i$ = 0; i$ < var7.size(); ++i$) {
				Entity var10 = (Entity) var7.get(i$);
				var8 = par4Padding > 0.0F ? par4Padding - 0.3F : 0.8F;
				float var11 = Math.max(var8, var10.getCollisionBorderSize());
				AxisAlignedBB var12 = var10.boundingBox.expand((double) var11, (double) var11, (double) var11);
				if (var12.isVecInside(var9)) {
					var1 = var10;
					break;
				}
			}
		}
		return var1;
	}
	
	public static int getPercentOf(int par1Percent, int par2Low, int par3High) {
		return (int)((double)par2Low / ((double)par3High / par1Percent));
	}
	
	public static double getPercentOf(double par1Percent, double par2Low, double par3High) {
		return ((double)par2Low / ((double)par3High / par1Percent));
	}
	
    public static void drawTexturedRect(int par1, int par2, int par3, int par4, int par5, int par6, double zLevel) {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + par6), zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + par6) * f1));
        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + par6), zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + par6) * f1));
        tessellator.addVertexWithUV((double)(par1 + par5), (double)(par2 + 0), zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + 0) * f1));
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + 0) * f1));
        tessellator.draw();
    }

    public static void drawTexturedRectFromIcon(int par1, int par2, Icon par3Icon, int par4, int par5, double zLevel) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + par5), zLevel, (double)par3Icon.getMinU(), (double)par3Icon.getMaxV());
        tessellator.addVertexWithUV((double)(par1 + par4), (double)(par2 + par5), zLevel, (double)par3Icon.getMaxU(), (double)par3Icon.getMaxV());
        tessellator.addVertexWithUV((double)(par1 + par4), (double)(par2 + 0), zLevel, (double)par3Icon.getMaxU(), (double)par3Icon.getMinV());
        tessellator.addVertexWithUV((double)(par1 + 0), (double)(par2 + 0), zLevel, (double)par3Icon.getMinU(), (double)par3Icon.getMinV());
        tessellator.draw();
    }
    
    private static class AmmoComparator implements Comparator<Dual<Integer, ItemStack>> {
		public int compare(Dual<Integer, ItemStack> arg0, Dual<Integer, ItemStack> arg1) {
			return Utils.getNBT(arg0.right).getInteger("ammo") > Utils.getNBT(arg1.right).getInteger("ammo") ? -1 : Utils.getNBT(arg0.right).getInteger("ammo") == Utils.getNBT(arg1.right).getInteger("ammo") ? 0 : 1;
		}
	}
}
