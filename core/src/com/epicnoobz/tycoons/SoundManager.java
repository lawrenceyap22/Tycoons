package com.epicnoobz.tycoons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;

public class SoundManager implements Disposable {

	public enum TycoonsSound {
		COIN_TAP("");

		public final String filename;

		private TycoonsSound(String filename) {
			this.filename = filename;
		}
	}

	private ObjectMap<TycoonsSound, Sound> soundCache;
	private boolean mute;

	public SoundManager() {
		soundCache = new ObjectMap<SoundManager.TycoonsSound, Sound>();
		mute = false;
	}

	public void play(TycoonsSound sound) {
		if (!mute) {
			if (!soundCache.containsKey(sound)) {
				Sound soundToPlay = Gdx.audio.newSound(Gdx.files.internal(sound.filename));
				soundCache.put(sound, soundToPlay);
				soundToPlay.play();
			} else {
				soundCache.get(sound).play();
			}
		}
	}

	public void setMute(boolean mute) {
		this.mute = mute;
	}

	public boolean isMute() {
		return mute;
	}

	@Override
	public void dispose() {
		for (Sound sound : soundCache.values()) {
			sound.stop();
			sound.dispose();
		}
		soundCache.clear();
	}

}
