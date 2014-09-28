package com.epicnoobz.tycoons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.epicnoobz.tycoons.screens.HomeScreen;

public class Tycoons extends Game {
	public static final boolean DEV_MODE = true;
	public static final String TAG = Tycoons.class.getSimpleName();

	private SpriteBatch batch;
	private BitmapFont font;
	private TextureAtlas atlas;
	private Skin skin;

	@Override
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		atlas = new TextureAtlas("images/game-ui.atlas");
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));

		setScreen(HomeScreen.getScreen(this));
	}

	@Override
	public void dispose() {
		Gdx.app.log(Tycoons.TAG, "Disposing Tycoons");

		HomeScreen.getScreen(this).dispose();
		// MainMenuScreen.getScreen(this).dispose();
		// PropertiesScreen.getScreen(this).dispose();
		// UpgradesScreen.getScreen(this).dispose();
		// MarketScreen.getScreen(this).dispose();

		if (batch != null)
			batch.dispose();
		if (font != null)
			font.dispose();
		if (atlas != null)
			atlas.dispose();
		if (skin != null)
			skin.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public BitmapFont getFont() {
		return font;
	}

	public TextureAtlas getAtlas() {
		return atlas;
	}

	public Skin getSkin() {
		return skin;
	}

}
