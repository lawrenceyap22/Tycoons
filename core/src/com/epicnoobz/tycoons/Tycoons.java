package com.epicnoobz.tycoons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicnoobz.tycoons.screens.HomeScreen;
import com.epicnoobz.tycoons.screens.MainMenuScreen;
import com.epicnoobz.tycoons.screens.PropertiesScreen;

public class Tycoons extends Game {
	public static final boolean DEV_MODE = false;
	public static final String TAG = Tycoons.class.getSimpleName();

	public SpriteBatch batch;
	public AssetManager assetManager;
	public SoundManager soundManager;
	public MainMenuScreen mainMenu;
	public HomeScreen home;
	public PropertiesScreen properties;

	@Override
	public void create() {
		batch = new SpriteBatch();
		assetManager = new AssetManager();
		soundManager = new SoundManager();
		setScreen(mainMenu = new MainMenuScreen(this));
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose() {
		Gdx.app.log(Tycoons.TAG, "Disposing game");
		soundManager.dispose();
		assetManager.dispose();
		mainMenu.dispose();
		home.dispose();
		properties.dispose();
		batch.dispose();
	}

}
