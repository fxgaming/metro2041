package by.fxg.metro2041.client.sound;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.audio.SoundPool;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import paulscode.sound.SoundSystem;

public class ClientSoundHandler {
	public static ArrayList soundFiles;
	private ClientAmbienceHandler ambienceHandler;
	public static SoundPool soundPoolMusic;

	public ClientSoundHandler() {
		this.ambienceHandler = new ClientAmbienceHandler();
	}

	public void onIngameUpdate() {
		this.ambienceHandler.onClientTick();
	}

	public ClientAmbienceHandler getAmbienceHandler() {
		return this.ambienceHandler;
	}

	public void addSound(String str) {
		soundFiles.add(str);
	}

	@ForgeSubscribe
	public void onSoundsLoaded(SoundLoadEvent evt) {
		soundFiles = new ArrayList();
		this.addSound("gun_empty");
		this.addSound("alarm0_");
		this.addSound("alarm1_");
		this.addSound("bullet_bounce0");
		this.addSound("bullet_bounce1");
		this.addSound("bullet_bounce2");
		this.addSound("bullet_bounce3");
		this.addSound("bullet_bounce4");
		this.addSound("bullet_bounce5");
		this.addSound("bullet_dirt0");
		this.addSound("bullet_dirt1");
		this.addSound("bullet_dirt2");
		this.addSound("bullet_dirt3");
		this.addSound("bullet_glass0");
		this.addSound("bullet_glass1");
		this.addSound("bullet_glass2");
		this.addSound("bullet_glass3");
		this.addSound("bullet_metal0");
		this.addSound("bullet_metal1");
		this.addSound("bullet_metal2");
		this.addSound("bullet_metal3");
		this.addSound("bullet_stone0");
		this.addSound("bullet_stone1");
		this.addSound("bullet_stone2");
		this.addSound("bullet_stone3");
		this.addSound("bullet_water0");
		this.addSound("bullet_water1");
		this.addSound("bullet_water2");
		this.addSound("bullet_water3");
		this.addSound("bullet_wood0");
		this.addSound("bullet_wood1");
		this.addSound("bullet_wood2");
		this.addSound("bullet_wood3");
		this.addSound("buzz0_");
		this.addSound("buzz1_");
		this.addSound("buzz2_");
		this.addSound("buzz3_");
		this.addSound("buzz4_");
		this.addSound("buzz5_");
		this.addSound("buzz6_");
		this.addSound("buzz7_");
		this.addSound("buzz8_");
		this.addSound("buzz9_");
		this.addSound("door0_");
		this.addSound("explosion0_");
		this.addSound("explosion1_");
		this.addSound("explosion2_");
		this.addSound("explosion3_");
		this.addSound("explosion4_");
		this.addSound("explosion5_");
		this.addSound("explosion6_");
		this.addSound("explosion7_");
		this.addSound("explosion8_");
		this.addSound("explosion9_");
		this.addSound("impact0");
		this.addSound("impact1");
		this.addSound("impact2");
		this.addSound("impact3");
		this.addSound("impact4");
		this.addSound("radiation0");
		this.addSound("radiation1");
		this.addSound("radiation2");
		this.addSound("radiation3");
		this.addSound("scary0_");
		this.addSound("scary1_");
		this.addSound("scary2_");
		this.addSound("scary3_");
		this.addSound("scary4_");
		this.addSound("scary5_");
		this.addSound("scary6_");
		this.addSound("scary7_");
		this.addSound("scary8_");
		this.addSound("scary9_");
		this.addSound("scary10_");
		this.addSound("scary11_");
		this.addSound("scary12_");
		this.addSound("scary13_");
		this.addSound("engine0_");
		this.addSound("engine1_");
		this.addSound("fire0_");
		this.addSound("fire1_");
		this.addSound("bell0_");
		this.addSound("bell1_");
		this.addSound("bell2_");
		this.addSound("lamp0_");
		this.addSound("lamp1_");
		this.addSound("lift0_");
		this.addSound("lift1_");
		this.addSound("tinhit0");
		this.addSound("tinhit1");
		this.addSound("tinhit2");
		this.addSound("tinhit3");
		this.addSound("rpk_fire");
		this.addSound("rpk_fire_distant");
		this.addSound("rpk_fire_silenced");
		this.addSound("rpk_reload");

		soundPoolMusic = new SoundPool(Minecraft.getMinecraft().getResourceManager(), "sound", true);
		Random rand = new Random();
		soundPoolMusic.addSound("metro:menu" + (rand.nextInt(2) + 1) + ".ogg");
		SoundManager manager = evt.manager;
		boolean flag = true;
		Iterator var4 = soundFiles.iterator();

		while (var4.hasNext()) {
			String sound = (String)var4.next();
			try {
				manager.addSound("metro:" + sound + ".ogg");
			} catch (Exception var7) {
				System.out.println("failed to load sound: " + sound);
				flag = false;
			}
		}
		if (!flag) {
			System.out.println("Metro sounds Failed to load!");
		}
	}

	public static void playMenuMusic() {
		Minecraft mc = Minecraft.getMinecraft();
		SoundManager soundManager = mc.sndManager;
		SoundSystem soundHandler = soundManager.sndSystem;
		if (!soundManager.sndSystem.playing("BGMusic") && !soundManager.sndSystem.playing("streaming")) {
			soundManager.stopAllSounds();
			SoundPoolEntry soundpoolentry = soundPoolMusic.getRandomSound();
			if (soundpoolentry != null) {
				soundManager.sndSystem.backgroundMusic("BGMusic", soundpoolentry.getSoundUrl(), soundpoolentry.getSoundName(), false);
				soundManager.sndSystem.setVolume("BGMusic", Minecraft.getMinecraft().gameSettings.musicVolume);
				soundManager.sndSystem.play("BGMusic");
			}
		} else {
			soundManager.sndSystem.setVolume("BGMusic", Minecraft.getMinecraft().gameSettings.musicVolume);
		}
	}

	public static void playSound(String snd, float vol) {
		Minecraft mc = Minecraft.getMinecraft();
		SoundManager soundManager = mc.sndManager;
		SoundSystem soundHandler = soundManager.sndSystem;
		soundManager.sndSystem.play(snd);
		soundManager.sndSystem.setVolume(snd, vol);
	}

	public static void stopSound(String snd) {
		if (Minecraft.getMinecraft().sndManager.sndSystem.playing(snd)) {
			Minecraft.getMinecraft().sndManager.sndSystem.stop(snd);
		}
	}

	public static void stopMenuMusic() {
		try {
			if (Minecraft.getMinecraft().sndManager.sndSystem.playing("BGMusic")) {
				Minecraft.getMinecraft().sndManager.sndSystem.stop("BGMusic");
			}
		} catch (Exception e) {
		}
	}
}
