package by.fxg.metro2041.util;

import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class FancyLightHelper {
	public static float lightJ = 0.0F;
	public static float lightK = 0.0F;
	
    public static void useBrightLight() {
        float fl = 240.0f;
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)fl, (float)fl);
    }

    public static void useWorldLight() {
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, lightJ, lightK);
    }

    public static void useWorldLightExtra(float givenJ, float givenK) {
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)givenJ, (float)givenK);
    }

    public static void usePublicLight(World givenWorld, double givenX, double givenY, double givenZ) {
        int i4 = givenWorld.getLightBrightnessForSkyBlocks(MathHelper.floor_double((double)givenX), MathHelper.floor_double((double)givenY), MathHelper.floor_double((double)givenZ), 0);
        int j4 = i4 % 65536;
        int k4 = i4 / 65536;
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)((float)j4 / 1.0f), (float)((float)k4 / 1.0f));
    }
}

