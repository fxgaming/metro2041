package by.fxg.metro2041.common.item;

import java.util.List;

import by.fxg.metro2041.util.IArmor;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;

public class VItemArmor extends ItemArmor implements ISpecialArmor, IArmor {
	public int maxCharge = 0;
	public Long tick = 0L;
	public String texture;
	public int toCharge = 2;
	public boolean isSpeedCharge = false;
	public boolean isEffect = false;
	
	public VItemArmor(int par1, int par2, int par3, String par4, String par5) {
		super(par1, EnumArmorMaterial.DIAMOND, par2, par2);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabCombat);
		this.iconString = "metro:" + par5;
		this.setUnlocalizedName("metro.item:" + par5);
		this.maxCharge = par3;
		this.texture = par4;
		this.setNoRepair();
	}
	
	public VItemArmor(int par1, int par2, int par3, String par4, String par5, CreativeTabs par6) {
		this(par1, par2, par3, par4, par5);
		this.setCreativeTab(par6);
	}
	
	public VItemArmor setChargeSpeed(int par1) {
		this.toCharge = par1;
		return this;
	}
	
	public VItemArmor setSpeedyCharge() {
		this.isSpeedCharge = true;
		return this;
	}
	
	public VItemArmor setEffect() {
		this.isEffect = true;
		return this;
	}
	
	public VItemArmor setName(String par1) {
		LanguageRegistry.addName(this, par1);
		return this;
	}
	
	public void addInformation(ItemStack i, EntityPlayer e, List l, boolean par4) {
		l.add("§a " + this.getCharge(i) + "/" + this.maxCharge + " Элов.");
	}
	
    public void onArmorTickUpdate(World w, EntityPlayer p, ItemStack i) {
    	if (!w.isRemote) {
    		this.tick++;
    		if (this.tick % 40L == 0L) {
    			this.tick = 0L;
    			this.charge(i, this.isSpeedCharge ? (p.isPotionActive(Potion.regeneration) ? this.toCharge * 2 : this.toCharge) : this.toCharge);
    		}
    	}
    }
    
    public void charge(ItemStack i, int value) {
    	NBTTagCompound n = this.getNBT(i);
    	int c = n.getInteger("charge");
    	if (c + value > this.maxCharge) {
    		n.setInteger("charge", this.maxCharge);
    	} else { 
    		n.setInteger("charge", (c + value));
    	}
    }
    
    public void discharge(ItemStack i, int value) {
    	NBTTagCompound n = this.getNBT(i);
    	int c = n.getInteger("charge");
    	if (c - value < 0) {
    		n.setInteger("charge", 0);
    	} else {
    		n.setInteger("charge", (c - value));
    	}
    }
    
    public int getCharge(ItemStack i) {
    	return this.getNBT(i).getInteger("charge");
    }
	
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack) {
		return this.isEffect;
	}

	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack paramItemStack, Entity paramEntity, int paramInt1, int paramInt2) {
		return "metro:textures/models/armors/" + this.texture + ".png";
	}
	
	@Override
	public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
		return new ArmorProperties(0, 0.0D, 0);
	}
	
	@Override
	public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
		return 0;
	}

	@Override
	public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
	}
	
    public NBTTagCompound getNBT(ItemStack i) {
    	NBTTagCompound n = i.stackTagCompound;
    	if (n == null) {
    		n = new NBTTagCompound("tag");
    		i.setTagCompound(n);
    		i.stackTagCompound = n;
    	}
    	return n;
    }
}
