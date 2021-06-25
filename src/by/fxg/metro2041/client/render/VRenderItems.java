package by.fxg.metro2041.client.render;

import org.lwjgl.opengl.GL11;

import by.fxg.metro2041.client.model.VModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class VRenderItems implements IItemRenderer {
	public VModel model;
	public String texture;
	public Integer id;
	public Float scale = 1.0F;
	
	
	public VRenderItems(VModel model, String texture, Integer id) {
		this.model = model;
		this.texture = texture;
		this.id = id;
	}

	public VRenderItems(VModel model, String texture, Integer id, float scale) {
		this.model = model;
		this.texture = texture;
		this.id = id;
		this.scale = scale;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return type == type.ENTITY || type == type.EQUIPPED || type == type.EQUIPPED_FIRST_PERSON || type == type.INVENTORY;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return type == type.ENTITY;
	}

	@Override
	public void renderItem(ItemRenderType t, ItemStack i, Object... d) {
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("metro:textures/models/" + this.texture + ".png"));
		switch (this.id) {
		case 0:
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			if (t == t.ENTITY) {
			} else if (t == t.EQUIPPED) {
				GL11.glRotatef(0F, 1F, 0F, 0F);
				GL11.glRotatef(10F, 0F, 1F, 0F);
				GL11.glRotatef(0F, 0F, 0F, 1F);
				GL11.glTranslatef(0.35F, 0.1F, 0.0F);
			} else if (t == t.EQUIPPED_FIRST_PERSON) {
				GL11.glScalef(1.5F, 1.5F, 1.5F);
				GL11.glRotatef(0F, 1F, 0F, 0F);
				GL11.glRotatef(10F, 0F, 1F, 0F);
				GL11.glRotatef(0F, 0F, 0F, 1F);
				GL11.glTranslatef(0.25F, 0.2F, 0.0F);
			} else if (t == t.INVENTORY) {
				GL11.glScalef(32F, 32F, 32F);
				GL11.glRotatef(-16F, 0F, 1F, 0F);
				GL11.glRotated(45F, 0F, 0F, 1F);
				GL11.glTranslatef(0.32F, -0.1F, 0.0F);
			}
			((VModel) this.model).render();
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
			break;
		case 1:
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			if (t == t.ENTITY) {
			} else if (t == t.EQUIPPED) {
				GL11.glRotatef(0F, 1F, 0F, 0F);
				GL11.glRotatef(10F, 0F, 1F, 0F);
				GL11.glRotatef(0F, 0F, 0F, 1F);
				GL11.glTranslatef(0.35F, 0.1F, 0.0F);
			} else if (t == t.EQUIPPED_FIRST_PERSON) {
				GL11.glScalef(1.5F, 1.5F, 1.5F);
				GL11.glRotatef(0F, 1F, 0F, 0F);
				GL11.glRotatef(10F, 0F, 1F, 0F);
				GL11.glRotatef(0F, 0F, 0F, 1F);
				GL11.glTranslatef(0.25F, 0.2F, 0.0F);
			} else if (t == t.INVENTORY) {
				GL11.glScalef(32F, 32F, 32F);
				GL11.glRotatef(-16F, 0F, 1F, 0F);
				GL11.glRotated(45F, 0F, 0F, 1F);
				GL11.glTranslatef(0.32F, -0.15F, 0.0F);
			}
			((VModel) this.model).render();
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
			break;
		case 2:
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			if (t == t.ENTITY) {
			} else if (t == t.EQUIPPED) {
				GL11.glRotatef(0F, 1F, 0F, 0F);
				GL11.glRotatef(10F, 0F, 1F, 0F);
				GL11.glRotatef(0F, 0F, 0F, 1F);
				GL11.glTranslatef(0.35F, 0.125F, 0.025F);
			} else if (t == t.EQUIPPED_FIRST_PERSON) {
				GL11.glScalef(1.5F, 1.5F, 1.5F);
				GL11.glRotatef(0F, 1F, 0F, 0F);
				GL11.glRotatef(10F, 0F, 1F, 0F);
				GL11.glRotatef(0F, 0F, 0F, 1F);
				GL11.glTranslatef(0.25F, 0.2F, 0.0F);
			} else if (t == t.INVENTORY) {
				GL11.glScalef(32F, 32F, 32F);
				GL11.glRotatef(-15F, 0F, 1F, 0F);
				GL11.glTranslatef(0.32F, 0.15F, 0.25F);
			}
			((VModel) this.model).render();
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
			break;
		case 3:
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			if (t == t.ENTITY) {
			} else if (t == t.EQUIPPED) {
				GL11.glRotatef(0F, 1F, 0F, 0F);
				GL11.glRotatef(10F, 0F, 1F, 0F);
				GL11.glRotatef(0F, 0F, 0F, 1F);
				GL11.glTranslatef(0.35F, 0.1F, 0.0F);
			} else if (t == t.EQUIPPED_FIRST_PERSON) {
				GL11.glScalef(1.5F, 1.5F, 1.5F);
				GL11.glRotatef(0F, 1F, 0F, 0F);
				GL11.glRotatef(10F, 0F, 1F, 0F);
				GL11.glRotatef(0F, 0F, 0F, 1F);
				GL11.glTranslatef(0.3F, 0.2F, 0.0F);
			} else if (t == t.INVENTORY) {
				GL11.glScalef(32F, 32F, 32F);
				GL11.glRotatef(-16F, 0F, 1F, 0F);
				GL11.glRotated(45F, 0F, 0F, 1F);
				GL11.glTranslatef(0.32F, -0.15F, -0.1F);
			}
			((VModel) this.model).render();
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
			break;
		case 4:
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			if (t == t.ENTITY) {
			} else if (t == t.EQUIPPED) {
				GL11.glRotatef(0F, 1F, 0F, 0F);
				GL11.glRotatef(10F, 0F, 1F, 0F);
				GL11.glRotatef(0F, 0F, 0F, 1F);
				GL11.glTranslatef(0.35F, 0.3F, 0.0F);
			} else if (t == t.EQUIPPED_FIRST_PERSON) {
				GL11.glRotatef(0F, 1F, 0F, 0F);
				GL11.glRotatef(10F, 0F, 1F, 0F);
				GL11.glRotatef(0F, 0F, 0F, 1F);
				GL11.glTranslatef(0.35F, 0.5F, 0.0F);
			} else if (t == t.INVENTORY) {
				GL11.glScalef(32F, 32F, 32F);
				GL11.glRotatef(-16F, 0F, 1F, 0F);
				GL11.glRotated(45F, 0F, 0F, 1F);
				GL11.glTranslatef(0.32F, -0.025F, 0.0F);
			}
			((VModel) this.model).render();
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
			break;
		case 5:
			GL11.glPushMatrix();
			if (t == t.ENTITY) {
				GL11.glScalef(0.35F, 0.35F, 0.35f);
				GL11.glRotatef(180F, 1F, 0F, 0);
				GL11.glTranslatef(0.0F, -0.75F, 0.0F);
			} else if (t == t.EQUIPPED) {
				GL11.glScalef(0.75F, 0.75F, 0.75F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glRotatef(135F, 1F, 0F, 0F);
				GL11.glTranslatef(0.0F, -0.7F, -0.9F);
			} else if (t == t.EQUIPPED_FIRST_PERSON) {
				GL11.glScalef(0.75F, 0.75F, 0.75F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glRotatef(135F, 1F, 0F, 0F);
				GL11.glTranslatef(0.0F, -0.7F, -0.9F);
			} else if (t == t.INVENTORY) {
				GL11.glScalef(-10F, -10F, -10F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glRotatef(135F, 1F, 0F, 0F);
				GL11.glTranslatef(0.0F, -0.55F, 1.15F);
			}
			((VModel) this.model).render();
			GL11.glPopMatrix();
			break;
		case 6:
			GL11.glPushMatrix();
			if (t == t.ENTITY) {
				GL11.glScalef(0.35F, 0.35F, 0.35f);
				GL11.glRotatef(180F, 1F, 0F, 0);
				GL11.glTranslatef(0.0F, -0.75F, 0.0F);
			} else if (t == t.EQUIPPED) {
				GL11.glScalef(0.75F, 0.75F, 0.75F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glRotatef(135F, 1F, 0F, 0F);
				GL11.glTranslatef(0.0F, -0.7F, -0.9F);
			} else if (t == t.EQUIPPED_FIRST_PERSON) {
				GL11.glScalef(0.75F, 0.75F, 0.75F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glRotatef(135F, 1F, 0F, 0F);
				GL11.glTranslatef(0.0F, -0.7F, -0.9F);
			} else if (t == t.INVENTORY) {
				GL11.glScalef(-10F, -10F, -10F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glRotatef(135F, 1F, 0F, 0F);
				GL11.glTranslatef(0.0F, -0.55F, 1.15F);
			}
			((VModel) this.model).render();
			GL11.glPopMatrix();
			break;
		case 7:
			GL11.glPushMatrix();
			if (t == t.ENTITY) {
				GL11.glScalef(0.35F, 0.35F, 0.35f);
				GL11.glRotatef(180F, 1F, 0F, 0);
				GL11.glTranslatef(0.0F, -0.75F, 0.0F);
			} else if (t == t.EQUIPPED) {
				GL11.glScalef(0.75F, 0.75F, 0.75F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glRotatef(135F, 1F, 0F, 0F);
				GL11.glTranslatef(0.0F, -0.7F, -0.9F);
			} else if (t == t.EQUIPPED_FIRST_PERSON) {
				GL11.glScalef(0.75F, 0.75F, 0.75F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glRotatef(135F, 1F, 0F, 0F);
				GL11.glTranslatef(0.0F, -0.7F, -0.9F);
			} else if (t == t.INVENTORY) {
				GL11.glScalef(-10F, -10F, -10F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glRotatef(135F, 1F, 0F, 0F);
				GL11.glTranslatef(0.0F, -0.55F, 1.15F);
			}
			((VModel) this.model).render();
			GL11.glPopMatrix();
			break;
		case 8:
			GL11.glPushMatrix();
			if (t == t.ENTITY) {
				GL11.glScalef(0.35F, 0.35F, 0.35f);
				GL11.glRotatef(180F, 1F, 0F, 0);
				GL11.glTranslatef(0.0F, -0.75F, 0.0F);
			} else if (t == t.EQUIPPED) {
				GL11.glScalef(0.75F, 0.75F, 0.75F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glRotatef(135F, 1F, 0F, 0F);
				GL11.glTranslatef(0.0F, -0.7F, -0.9F);
			} else if (t == t.EQUIPPED_FIRST_PERSON) {
				GL11.glScalef(0.75F, 0.75F, 0.75F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glRotatef(135F, 1F, 0F, 0F);
				GL11.glTranslatef(0.0F, -0.7F, -0.9F);
			} else if (t == t.INVENTORY) {
				GL11.glScalef(-9F, -9F, -9F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glRotatef(135F, 1F, 0F, 0F);
				GL11.glTranslatef(0.0F, -0.5F, 1.4F);
			}
			((VModel) this.model).render();
			GL11.glPopMatrix();
			break;
		case 9:
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			if (t == t.ENTITY) {
				GL11.glScalef(0.35F, 0.35F, 0.35f);
				GL11.glRotatef(180F, 1F, 0F, 0);
				GL11.glTranslatef(0.0F, -0.75F, 0.0F);
			} else if (t == t.EQUIPPED) {
				GL11.glScalef(0.75F, 0.75F, 0.75F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glRotatef(135F, 1F, 0F, 0F);
				GL11.glTranslatef(0.0F, -0.7F, -0.9F);
			} else if (t == t.EQUIPPED_FIRST_PERSON) {
				GL11.glScalef(0.75F, 0.75F, 0.75F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glRotatef(135F, 1F, 0F, 0F);
				GL11.glTranslatef(0.0F, -0.7F, -0.9F);
			} else if (t == t.INVENTORY) {
				GL11.glScalef(-9F, -9F, -9F);
				GL11.glRotatef(90F, 0F, 1F, 0F);
				GL11.glRotatef(135F, 1F, 0F, 0F);
				GL11.glTranslatef(0.0F, -0.5F, 1.4F);
			}
			((VModel) this.model).render();
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
			break;
		}
	}

}
