package com.epicnoobz.tycoons.screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.epicnoobz.tycoons.Tycoons;

public class MarketScreen extends GameScreen {

	private static MarketScreen instance = null;

	public MarketScreen(Tycoons game) {
		super(game);
	}

	@Override
	protected void loadAssets() {
	}

	public static synchronized MarketScreen getScreen(Tycoons game) {
		if (instance == null) {
			instance = new MarketScreen(game);
		}
		return instance;
	}

	@Override
	protected void addScreenTabs(TextureAtlas atlas) {
		// TODO Auto-generated method stub
		
	}

}
