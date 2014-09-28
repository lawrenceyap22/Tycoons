package com.epicnoobz.tycoons.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.epicnoobz.tycoons.Tycoons;

public class HomeScreen extends GameScreen {

	private static HomeScreen instance = null;

	Texture screenBackgroundTexture;
	Texture tabBackgroundTexture;

	public HomeScreen(Tycoons game) {
		super(game);

		initBackground();
		initCoin();
	}

	public static synchronized HomeScreen getScreen(Tycoons game) {
		if (instance == null) {
			instance = new HomeScreen(game);
		}
		return instance;
	}

	private void initBackground() {
		screenBackgroundTexture = new Texture("images/background/screen_bg.png");
		Image screenBackgroundImage = new Image(new TextureRegion(screenBackgroundTexture));
		screenBackgroundImage.setPosition(VIEWPORT_WIDTH / 2 - screenBackgroundImage.getWidth() / 2, VIEWPORT_HEIGHT
				- screenBackgroundImage.getHeight());
		screenBackgroundImage.setTouchable(Touchable.disabled);

		tabBackgroundTexture = new Texture("images/background/tab_bg.png");
		Image tabBackgroundImage = new Image(new TextureRegion(tabBackgroundTexture));
		tabBackgroundImage.setPosition(VIEWPORT_WIDTH / 2 - tabBackgroundImage.getWidth() / 2, VIEWPORT_HEIGHT
				- tabBackgroundImage.getHeight());
		tabBackgroundImage.setTouchable(Touchable.disabled);

		stage.addActor(screenBackgroundImage);
		stage.addActor(tabBackgroundImage);
	}

	private void initCoin() {
		TextureAtlas atlas = game.getAtlas();
		final TextureRegionDrawable coinUnpressed = new TextureRegionDrawable(atlas.findRegion("coin"));
		final TextureRegionDrawable coinPressed = new TextureRegionDrawable(atlas.findRegion("coin_pressed"));
		final Image coin = new Image(coinUnpressed);

		coin.setPosition(VIEWPORT_WIDTH / 2 - coin.getWidth() / 2, VIEWPORT_HEIGHT / 3 - coin.getHeight() / 4);
		coin.addListener(new ClickListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				coin.setDrawable(coinPressed);
				coin.setSize(coinPressed.getRegion().getRegionWidth(), coinPressed.getRegion().getRegionHeight());
				coin.setPosition(VIEWPORT_WIDTH / 2 - coin.getWidth() / 2 - 12, VIEWPORT_HEIGHT / 3 - coin.getHeight() / 4 - 50);
				return super.touchDown(event, x, y, pointer, button);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				coin.setDrawable(coinUnpressed);
				coin.setSize(coinUnpressed.getRegion().getRegionWidth(), coinUnpressed.getRegion().getRegionHeight());
				coin.setPosition(VIEWPORT_WIDTH / 2 - coin.getWidth() / 2, VIEWPORT_HEIGHT / 3 - coin.getHeight() / 4);
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
			}

		});

		stage.addActor(coin);
	}

	@Override
	public void show() {

	}

	@Override
	public void dispose() {
		super.dispose();

		if (screenBackgroundTexture != null)
			screenBackgroundTexture.dispose();

		if (tabBackgroundTexture != null)
			tabBackgroundTexture.dispose();
	}

}
