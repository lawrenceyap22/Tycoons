package com.epicnoobz.tycoons.screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.epicnoobz.tycoons.Tycoons;

public class UpgradesScreen extends GameScreen {

	private static UpgradesScreen instance = null;

	public UpgradesScreen(Tycoons game) {
		super(game);
	}

	@Override
	protected void loadAssets() {
		// TODO Auto-generated method stub

	}

	public static synchronized UpgradesScreen getScreen(Tycoons game) {
		if (instance == null) {
			instance = new UpgradesScreen(game);
		}
		return instance;
	}

	@Override
	protected void addScreenTabs(TextureAtlas atlas) {
		// TODO Auto-generated method stub
		
	}

}
