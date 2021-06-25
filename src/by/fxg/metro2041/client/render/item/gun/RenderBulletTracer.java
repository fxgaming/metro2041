package by.fxg.metro2041.client.render.item.gun;

import java.awt.Color;
import java.util.ArrayList;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import by.fxg.metro2041.common.item.gun.ItemGun;
import by.fxg.metro2041.util.FancyLightHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderBulletTracer {
    private static final ArrayList<BulletTracer> bulletTracerTrails = new ArrayList();
    private static TextureManager textureManager;

    public static void addTracer(BulletTracer trail) {
        bulletTracerTrails.add(trail);
    }

    public static void renderTracers(float partialTicks) {
        try {
            for (BulletTracer trail : bulletTracerTrails) {
                trail.render(partialTicks);
            }
        }
        catch (IllegalStateException e) {
        }
    }

    public static void updateTracers() {
        for (int i = RenderBulletTracer.bulletTracerTrails.size() - 1; i >= 0; --i) {
            if (!bulletTracerTrails.get(i).update()) continue;
            bulletTracerTrails.remove(i);
        }
    }

    public static class BulletTracer {
        private final Vector3f origin;
        private final Vector3f hitPos;
        private final float width;
        private final float length;
        private final float bulletSpeed;
        private final Color tracerColor;
        private float distanceToTarget;
        private int ticksExisted = 0;

        public BulletTracer(Vector3f origin, Vector3f hitPos, Color givenColor) {
            this.bulletSpeed = 10.0f;
            this.origin = origin;
            this.hitPos = hitPos;
            this.width = 0.05f;
            this.length = 10.0f;
            this.tracerColor = givenColor;
            Vector3f dPos = Vector3f.sub((Vector3f)hitPos, (Vector3f)origin, null);
            this.distanceToTarget = dPos.length();
            if (Math.abs(this.distanceToTarget) > 300.0f) {
                this.distanceToTarget = 300.0f;
            }
        }

        public static Color hex2Rgb(String colorStr) {
            return Color.ORANGE;
        }

        boolean update() {
            ++this.ticksExisted;
            return (float)this.ticksExisted * this.bulletSpeed >= this.distanceToTarget - this.length;
        }

        void render(float partialTicks) {
            GL11.glPushMatrix();
            if (textureManager == null) {
                textureManager = Minecraft.getMinecraft().renderEngine;
            }
            textureManager.bindTexture(new ResourceLocation("metro:textures/particles/tracer.png"));
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            FancyLightHelper.useBrightLight();
            boolean wasLightEnabled = false;
            if (GL11.glIsEnabled((int)2896)) {
                GL11.glDisable((int)2896);
                wasLightEnabled = true;
            }
            EntityLivingBase camera = Minecraft.getMinecraft().renderViewEntity;
            double x = camera.lastTickPosX + (camera.posX - camera.lastTickPosX) * (double)partialTicks;
            double y = camera.lastTickPosY + (camera.posY - camera.lastTickPosY) * (double)partialTicks;
            double z = camera.lastTickPosZ + (camera.posZ - camera.lastTickPosZ) * (double)partialTicks;
            GL11.glTranslatef((float)(-((float)x)), (float)(-((float)y)), (float)(-((float)z)));
            float parametric = ((float)this.ticksExisted + partialTicks) * this.bulletSpeed;
            Vector3f dPos = Vector3f.sub((Vector3f)this.hitPos, (Vector3f)this.origin, null);
            dPos.normalise();
            float startParametric = parametric - this.length * 0.5f;
            Vector3f startPos = new Vector3f(this.origin.x + dPos.x * startParametric, this.origin.y + dPos.y * startParametric, this.origin.z + dPos.z * startParametric);
            float endParametric = parametric + this.length * 0.5f;
            Vector3f endPos = new Vector3f(this.origin.x + dPos.x * endParametric, this.origin.y + dPos.y * endParametric, this.origin.z + dPos.z * endParametric);
            Tessellator var2 = Tessellator.instance;
            dPos.normalise();
            EntityClientPlayerMP thePlayer = Minecraft.getMinecraft().thePlayer;
            Vector3f vectorToPlayer = new Vector3f((float)thePlayer.posX - this.hitPos.x, (float)thePlayer.posY - this.hitPos.y, (float)thePlayer.posZ - this.hitPos.z);
            vectorToPlayer.normalise();
            Vector3f trailTangent = Vector3f.cross((Vector3f)dPos, (Vector3f)vectorToPlayer, null);
            trailTangent.scale(-this.width * 0.5f);
            Vector3f normal = Vector3f.cross((Vector3f)trailTangent, (Vector3f)dPos, null);
            GL11.glNormal3f((float)normal.x, (float)normal.y, (float)normal.z);
            var2.startDrawing(3);
            var2.addVertexWithUV((double)(startPos.x + trailTangent.x), (double)(startPos.y + trailTangent.y), (double)(startPos.z + trailTangent.z), 0.0, 0.0);
            var2.addVertexWithUV((double)(startPos.x - trailTangent.x), (double)(startPos.y - trailTangent.y), (double)(startPos.z - trailTangent.z), 0.0, 1.0);
            var2.addVertexWithUV((double)(endPos.x - trailTangent.x), (double)(endPos.y - trailTangent.y), (double)(endPos.z - trailTangent.z), 1.0, 1.0);
            var2.addVertexWithUV((double)(endPos.x + trailTangent.x), (double)(endPos.y + trailTangent.y), (double)(endPos.z + trailTangent.z), 1.0, 0.0);
            var2.draw();
            FancyLightHelper.useWorldLight();
            if (wasLightEnabled) {
                GL11.glEnable((int)2896);
            }
            GL11.glPopMatrix();
        }
    }

}

