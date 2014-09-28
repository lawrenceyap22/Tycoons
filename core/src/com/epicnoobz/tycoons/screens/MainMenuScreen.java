package com.epicnoobz.tycoons.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.epicnoobz.tycoons.Tycoons;

public class MainMenuScreen extends GameScreen {

	private static MainMenuScreen instance = null;

	SpriteBatch batch;

	public MainMenuScreen(Tycoons game) {
		super(game);
		batch = game.getBatch();
	}

	public static synchronized MainMenuScreen getScreen(Tycoons game) {
		if (instance == null) {
			instance = new MainMenuScreen(game);
		}
		return instance;
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		super.render(delta);
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
