package com.epicnoobz.tycoons.screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.epicnoobz.tycoons.Tycoons;

public class PropertiesScreen extends GameScreen {

	public PropertiesScreen(Tycoons game) {
		super(game);
	}

	@Override
	protected void initActors() {
		TextureAtlas atlas = game.assetManager.get("images/images-packed.atlas", TextureAtlas.class);
		addScreenTabs(atlas, Tab.PROPERTIES);
		addSoundButton(atlas);
	}

}
