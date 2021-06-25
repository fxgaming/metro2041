package by.fxg.metro2041.client.sound;

import java.lang.reflect.Field;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.audio.SoundPoolEntry;
import net.minecraft.client.settings.GameSettings;
import paulscode.sound.SoundSystem;

public class ClientAmbienceHandler {
	private SoundManager sndMng;
	private GameSettings games;
	private float lastMusicVolume;
	private int musicInterval = 4800;
	private int maxMusicInterval = 6000;
	public float volumeModifier = 0.45F;
	private SoundSystem sndSystem;

	public ClientAmbienceHandler() {
		Minecraft mc = Minecraft.getMinecraft();
		this.games = mc.gameSettings;
		this.sndMng = mc.sndManager;
		this.lastMusicVolume = mc.gameSettings.musicVolume;
		this.refreshSoundManager();
	}

	public void shutdownAmbience() {
		try {
			this.sndMng.stopAllSounds();
			this.sndSystem = null;
		} catch (Exception var2) {
			;
		}
	}

	public void onClientTick() {
		if (this.sndSystem != null && this.sndMng != null) {
			if (this.sndSystem.playing("BgMusic")) {
				this.sndSystem.stop("BgMusic");
			}
			if (this.musicInterval++ >= this.maxMusicInterval) {
				this.refreshSoundManager();
				if (!this.sndSystem.playing("BgMusic2")) {
					try {
						Random rand = new Random();
						String sound = "ambient_" + (rand.nextInt(6) + 1);
						SoundPoolEntry soundpoolentry = new SoundPoolEntry(sound + ".ogg", this.getClass().getResource("/assets/metro/sound/" + sound + ".ogg"));
						this.sndSystem.backgroundMusic("BgMusic2", soundpoolentry.getSoundUrl(), soundpoolentry.getSoundName(), false);
						this.sndSystem.setVolume("BgMusic2", this.games.musicVolume * this.volumeModifier);
						this.sndSystem.play("BgMusic2");
					} catch (Exception var4) {
						System.out.println("Ambient Sound Handler has CRASHED! Please contact with Administrator.");
						var4.printStackTrace();
					}
				}
				this.musicInterval = 0;
			}
			if (this.lastMusicVolume != this.games.musicVolume && this.sndSystem.playing("BgMusic2")) {
				if (this.games.musicVolume <= 0.0F) {
					this.sndSystem.stop("BgMusic2");
				} else {
					this.sndSystem.setVolume("BgMusic2", this.games.musicVolume * this.volumeModifier);
				}
			}
			this.lastMusicVolume = this.games.musicVolume;
		}
	}

	private void refreshSoundManager() {
		try {
			Field[] fs = this.sndMng.getClass().getDeclaredFields();
			fs[1].setAccessible(true);
			this.sndSystem = (SoundSystem) fs[1].get(this.sndMng);
		} catch (Exception var2) {
		}
	}
}
