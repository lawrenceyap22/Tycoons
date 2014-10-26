package com.epicnoobz.tycoons.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.epicnoobz.tycoons.Tycoons;
import com.epicnoobz.tycoons.objects.GameProgressBar;

public class LoadingScreen extends AbstractScreen {

	GameProgressBar loadingBar;
	Label label;

	public LoadingScreen(final Tycoons game) {
		super(game);
	}

	@Override
	protected void loadAssets() {
		game.assetManager.load("images/images-packed.atlas", TextureAtlas.class);
		game.assetManager.load("images/BG_Overall.png", Texture.class);
		game.assetManager.load("images/BG_Tab.png", Texture.class);
		game.assetManager.load("images/BG_HomeTab_Town.png", Texture.class);
		game.assetManager.load("images/Button_Coin_Clicked.png", Texture.class);
		game.assetManager.load("images/Button_Coin_Neutral.png", Texture.class);
		game.assetManager.load("images/Drawer_HomeTab_BG.png", Texture.class);
		game.assetManager.load("images/Drawer_HomeTab_ResourceList.png", Texture.class);
	}

	@Override
	protected void initActors() {
		// TODO initialize actors of loading screen, i.e. progress bar
		LabelStyle style = new LabelStyle(game.assetManager.get("font/tycoons.fnt", BitmapFont.class), Color.WHITE);
		label = new Label("Loading...", style);
		label.setPosition(VIEWPORT_WIDTH / 2 - label.getWidth() / 2, VIEWPORT_HEIGHT / 2 + label.getHeight());
		stage.addActor(label);
		addLoadingBar();
	}

	private void addLoadingBar() {
		TextureAtlas atlas = game.assetManager.get("images/images-packed.atlas", TextureAtlas.class);
		loadingBar = new GameProgressBar(0, 1000, 1, atlas.findRegion("Bar_ResourceCollected_BG"), new TextureRegion(
				atlas.findRegion("Bar_ResourceCollected_Full")), false);
		loadingBar.setPosition(VIEWPORT_WIDTH / 2 - loadingBar.getWidth() / 2,
				VIEWPORT_HEIGHT / 2 - loadingBar.getHeight());
		loadingBar.addHighlight(atlas.findRegion("Bar_ResourceCollected_Highlight"));
		stage.addActor(loadingBar);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		if (game.assetManager.update()) {
			game.home = new HomeScreen(game);
			game.properties = new PropertiesScreen(game);
			game.setScreen(game.home);
		}

		// TODO update progress bar
		loadingBar.setValue(game.assetManager.getProgress() * 1000);
	}

	@Override
	public void hide() {
		super.hide();
		super.dispose();
	}

}
