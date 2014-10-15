package com.epicnoobz.tycoons.screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.epicnoobz.tycoons.Tycoons;

public class MainMenuScreen extends GameScreen {

	private static MainMenuScreen instance = null;


	public MainMenuScreen(Tycoons game) {
		super(game);
	}
	
	@Override
	protected void loadAssets() {
		
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

	@Override
	protected void addScreenTabs(TextureAtlas atlas) {
		// TODO Auto-generated method stub
		
	}
}
