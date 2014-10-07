package com.epicnoobz.tycoons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicnoobz.tycoons.screens.HomeScreen;

public class Tycoons extends Game {
	public static final boolean DEV_MODE = true;
	public static final String TAG = Tycoons.class.getSimpleName();

	public SpriteBatch batch;
	public AssetManager manager;
	public HomeScreen home;

	@Override
	public void create() {
		batch = new SpriteBatch();
		manager = new AssetManager();

		setScreen(home = new HomeScreen(this));
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
		home.dispose();
		batch.dispose();
		manager.dispose();
	}

}
