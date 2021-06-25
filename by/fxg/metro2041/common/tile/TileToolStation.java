package by.fxg.metro2041.common.tile;

import java.util.HashMap;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TileToolStation extends VTileInventory {
	public int tick = 0;
	public boolean isWorking = false;
	public int time = 0;
	public int item1 = 0;
	public int item2 = 0;
	public int type = 0;
	public HashMap<Integer, AdvancedRecipe> recipe_pickaxe = new HashMap<Integer, AdvancedRecipe>();
	public HashMap<Integer, AdvancedRecipe> recipe_sword = new HashMap<Integer, AdvancedRecipe>();
	public HashMap<Integer, AdvancedRecipe> recipe_ingot = new HashMap<Integer, AdvancedRecipe>();
	
	public TileToolStation() {
		this.items = new ItemStack[5];
		AdvancedRecipe[] adv_pick = {
				new AdvancedRecipe(new ItemStack(5762, 16, 0), new ItemStack(173, 16, 0), new ItemStack(5767, 1, 0)),
				new AdvancedRecipe(new ItemStack(5763, 24, 0), new ItemStack(173, 24, 0), new ItemStack(5768, 1, 0)),
				new AdvancedRecipe(new ItemStack(5764, 32, 0), new ItemStack(173, 32, 0), new ItemStack(5769, 1, 0)),
				new AdvancedRecipe(new ItemStack(5765, 48, 0), new ItemStack(173, 48, 0), new ItemStack(5770, 1, 0)),
				new AdvancedRecipe(new ItemStack(5766, 64, 0), new ItemStack(173, 64, 0), new ItemStack(5771, 1, 0))
		};
		AdvancedRecipe[] adv_sword = {
				new AdvancedRecipe(new ItemStack(5762, 48, 0), new ItemStack(173, 64, 0), new ItemStack(5772, 1, 0)),
				new AdvancedRecipe(new ItemStack(5763, 48, 0), new ItemStack(173, 64, 0), new ItemStack(5773, 1, 0)),
				new AdvancedRecipe(new ItemStack(5764, 64, 0), new ItemStack(173, 64, 0), new ItemStack(5774, 1, 0)),
				new AdvancedRecipe(new ItemStack(5765, 64, 0), new ItemStack(173, 64, 0), new ItemStack(5775, 1, 0)),
				new AdvancedRecipe(new ItemStack(5766, 64, 0), new ItemStack(173, 64, 0), new ItemStack(5776, 1, 0))
		};
		AdvancedRecipe[] adv_ingot = {
				new AdvancedRecipe(new ItemStack(5762, 64, 0), new ItemStack(173, 16, 0), new ItemStack(5777, 1, 0)),
				new AdvancedRecipe(new ItemStack(5763, 64, 0), new ItemStack(173, 24, 0), new ItemStack(5778, 1, 0)),
				new AdvancedRecipe(new ItemStack(5764, 64, 0), new ItemStack(173, 32, 0), new ItemStack(5779, 1, 0)),
				new AdvancedRecipe(new ItemStack(5765, 64, 0), new ItemStack(173, 48, 0), new ItemStack(5780, 1, 0)),
				new AdvancedRecipe(new ItemStack(5766, 64, 0), new ItemStack(173, 64, 0), new ItemStack(5781, 1, 0))
		};
		for (AdvancedRecipe adv : adv_pick) {
			this.recipe_pickaxe.put(adv.aim.itemID, adv);
		}
		for (AdvancedRecipe adv : adv_sword) {
			this.recipe_sword.put(adv.aim.itemID, adv);
		}
		for (AdvancedRecipe adv : adv_ingot) {
			this.recipe_ingot.put(adv.aim.itemID, adv);
		}
	}
	
	public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readFromNBT(par1NBTTagCompound);
		this.isWorking = par1NBTTagCompound.getBoolean("work");
		this.time = par1NBTTagCompound.getInteger("time");
		this.item1 = par1NBTTagCompound.getInteger("item1");
		this.item2 = par1NBTTagCompound.getInteger("item2");
		this.type = par1NBTTagCompound.getInteger("type");
	}

	public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setBoolean("work", this.isWorking);
		par1NBTTagCompound.setInteger("time", this.time);
		par1NBTTagCompound.setInteger("item1", this.item1);
		par1NBTTagCompound.setInteger("item2", this.item2);
		par1NBTTagCompound.setInteger("type", this.type);
	}
		
	public void updateEntity() {
		super.updateEntity();
		try {
			this.tick++;
			if (this.tick >= 20) {
				this.tick = 0;
				PacketDispatcher.sendPacketToAllAround((double) super.xCoord, (double) super.yCoord, (double) super.zCoord, 50.0D, super.worldObj.provider.dimensionId, this.getDescriptionPacket());
				ItemStack plateIn = super.items[0];
				ItemStack platePi = super.items[1];
				ItemStack plateSw = super.items[2];
				ItemStack items = super.items[3];
				
				if (plateIn != null && platePi != null || plateIn != null && plateSw != null || platePi != null && plateSw != null || plateIn != null && platePi != null && plateSw != null) {
					this.close();
					return;
				} else {
					int type = plateIn != null ? 1 : (platePi != null ? 2 : (plateSw != null ? 3 : 4));
					if (type == 4) {
						this.close();
						return;
					} else {
						if (type == 1) {
							this.type1_ingot(plateIn, items);
							return;
						} else if (type == 2) {
							this.type2_pickaxe(platePi, items);
							return;
						} else if (type == 3) {
							this.type3_sword(plateSw, items);
							return;
						}
					}
				}
				this.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void type1_ingot(ItemStack plates, ItemStack items) {
		if (!this.isWorking) {
			if (plates != null && items != null) {
				if (this.recipe_ingot.containsKey(plates.itemID)) {
					AdvancedRecipe adv = this.recipe_ingot.get(plates.itemID);
					if (plates.stackSize >= adv.aim.stackSize && items.stackSize >= adv.coalNeeded.stackSize && items.itemID == adv.coalNeeded.itemID) {
						this.isWorking = true;
						this.time++;
						this.item1 = plates.itemID;
						this.item2 = items.itemID;
						this.type = 1;
						return;
					}
				}
			}
		} else {
			if (plates != null && items != null) {
				if (this.type == 1) {
					if (this.recipe_ingot.containsKey(plates.itemID)) {
						AdvancedRecipe adv = this.recipe_ingot.get(plates.itemID);
						if (plates.stackSize >= adv.aim.stackSize && items.stackSize >= adv.coalNeeded.stackSize && items.itemID == adv.coalNeeded.itemID) {
							if (this.item1 == plates.itemID && this.item2 == items.itemID) {
								if (this.items[4] != null) {
									if (this.items[4].stackSize >= 64) {
										this.close();
										return;
									}
									if ((this.items[4].itemID - 15) != this.item1 || (this.items[4].itemID - 15) != adv.aim.itemID) {
										this.close();
										return;
									}
								}
								this.time++;
								if (this.time >= 22) {
									if (this.items[4] == null) {
										this.items[4] = new ItemStack(adv.doneItem.itemID, 1, 0);
									} else {
										this.items[4].stackSize = this.items[4].stackSize + 1;
									}
									if (this.items[0].stackSize == adv.aim.stackSize) {
										this.items[0] = null;
									} else {
										this.items[0].stackSize = this.items[0].stackSize - adv.aim.stackSize;
									}
									if (this.items[3].stackSize == adv.coalNeeded.stackSize) {
										this.items[3] = null;
									} else {
										this.items[3].stackSize = this.items[3].stackSize - adv.coalNeeded.stackSize;
									}
									this.close();
									return;
								} else {
									return;
								}
							}
						}
					}
				}
			}
		}
		this.close();
	}
	
	public void type2_pickaxe(ItemStack plates, ItemStack items) {
		if (!this.isWorking) {
			if (plates != null && items != null) {
				if (this.recipe_pickaxe.containsKey(plates.itemID)) {
					AdvancedRecipe adv = this.recipe_pickaxe.get(plates.itemID);
					if (plates.stackSize >= adv.aim.stackSize && items.stackSize >= adv.coalNeeded.stackSize && items.itemID == adv.coalNeeded.itemID) {
						this.isWorking = true;
						this.time++;
						this.item1 = plates.itemID;
						this.item2 = items.itemID;
						this.type = 2;
						return;
					}
				}
			}
		} else {
			if (plates != null && items != null) {
				if (this.type == 2) {
					if (this.recipe_pickaxe.containsKey(plates.itemID)) {
						AdvancedRecipe adv = this.recipe_pickaxe.get(plates.itemID);
						if (plates.stackSize >= adv.aim.stackSize && items.stackSize >= adv.coalNeeded.stackSize && items.itemID == adv.coalNeeded.itemID) {
							if (this.item1 == plates.itemID && this.item2 == items.itemID) {
								if (this.items[4] != null) {
									if (this.items[4].stackSize >= 64) {
										this.close();
										return;
									}
									if ((this.items[4].itemID - 5) != this.item1 || (this.items[4].itemID - 5) != adv.aim.itemID) {
										this.close();
										return;
									}
								}
								this.time++;
								if (this.time >= 22) {
									if (this.items[4] == null) {
										this.items[4] = new ItemStack(adv.doneItem.itemID, 1, 0);
									} else {
										this.items[4].stackSize = this.items[4].stackSize + 1;
									}
									if (this.items[1].stackSize == adv.aim.stackSize) {
										this.items[1] = null;
									} else {
										this.items[1].stackSize = this.items[1].stackSize - adv.aim.stackSize;
									}
									if (this.items[3].stackSize == adv.coalNeeded.stackSize) {
										this.items[3] = null;
									} else {
										this.items[3].stackSize = this.items[3].stackSize - adv.coalNeeded.stackSize;
									}
									this.close();
									return;
								} else {
									return;
								}
							}
						}
					}
				}
			}
		}
		this.close();
	}
	
	public void type3_sword(ItemStack plates, ItemStack items) {
		if (!this.isWorking) {
			if (plates != null && items != null) {
				if (this.recipe_sword.containsKey(plates.itemID)) {
					AdvancedRecipe adv = this.recipe_sword.get(plates.itemID);
					if (plates.stackSize >= adv.aim.stackSize && items.stackSize >= adv.coalNeeded.stackSize && items.itemID == adv.coalNeeded.itemID) {
						this.isWorking = true;
						this.time++;
						this.item1 = plates.itemID;
						this.item2 = items.itemID;
						this.type = 3;
						return;
					}
				}
			}
		} else {
			if (plates != null && items != null) {
				if (this.type == 3) {
					if (this.recipe_sword.containsKey(plates.itemID)) {
						AdvancedRecipe adv = this.recipe_sword.get(plates.itemID);
						if (plates.stackSize >= adv.aim.stackSize && items.stackSize >= adv.coalNeeded.stackSize && items.itemID == adv.coalNeeded.itemID) {
							if (this.item1 == plates.itemID && this.item2 == items.itemID) {
								if (this.items[4] != null) {
									if (this.items[4].stackSize >= 64) {
										this.close();
										return;
									}
									if ((this.items[4].itemID - 10) != this.item1 || (this.items[4].itemID - 10) != adv.aim.itemID) {
										this.close();
										return;
									}
								}
								this.time++;
								if (this.time >= 22) {
									if (this.items[4] == null) {
										this.items[4] = new ItemStack(adv.doneItem.itemID, 1, 0);
									} else {
										this.items[4].stackSize = this.items[4].stackSize + 1;
									}
									if (this.items[2].stackSize == adv.aim.stackSize) {
										this.items[2] = null;
									} else {
										this.items[2].stackSize = this.items[2].stackSize - adv.aim.stackSize;
									}
									if (this.items[3].stackSize == adv.coalNeeded.stackSize) {
										this.items[3] = null;
									} else {
										this.items[3].stackSize = this.items[3].stackSize - adv.coalNeeded.stackSize;
									}
									this.close();
									return;
								} else {
									return;
								}
							}
						}
					}
				}
			}
		}
		this.close();
	}

	public void close() {
		this.isWorking = false;
		this.time = 0;
		this.item1 = 0;
		this.item2 = 0;
		this.type = 0;
	}
	
	public void openChest() {
	}

	public void closeChest() {
	}

	public boolean isStackValidForSlot(int i, ItemStack itemstack) {
		return false;
	}

	public String getInventoryName() {
		return "container.PressSw";
	}
	
	public class AdvancedRecipe {
		public ItemStack aim;
		public ItemStack coalNeeded;
		public ItemStack doneItem;
		
		public AdvancedRecipe(ItemStack aim, ItemStack coal, ItemStack done) {
			this.aim = aim;
			this.coalNeeded = coal;
			this.doneItem = done;
		}
	}
}
