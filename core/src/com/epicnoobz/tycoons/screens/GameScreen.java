package com.epicnoobz.tycoons.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.epicnoobz.tycoons.Tycoons;
import com.epicnoobz.tycoons.objects.ImageLabel;
import com.epicnoobz.tycoons.objects.ScreenTabs;

public abstract class GameScreen extends AbstractScreen {

	public enum Tab {
		HOME, PROPERTIES, UPGRADES, MARKET
	}

	private static float timeToSave;
	protected static Button sound = null;
	protected ScreenTabs screenTabs;
	protected Tab tab;
	protected TextureAtlas atlas;
	protected ImageLabel money;
	protected ImageLabel tech;
	protected ImageLabel properties;

	public GameScreen(final Tycoons game, Tab tab) {
		super(game);
		screenTabs.setActiveTab(tab);
		timeToSave = 0;
	}

	@Override
	protected void initActors() {
		atlas = game.assetManager.get("images/images-packed.atlas",
				TextureAtlas.class);
		addBG();
		addScreenTabs();
		addTabBG();
		addSoundButton();
	}

	@Override
	protected void loadAssets() {
	}

	private void addBG() {
		Image mainBG = new Image(game.assetManager.get("images/BG_Overall.png",
				Texture.class));
		mainBG.setPosition(VIEWPORT_WIDTH / 2 - mainBG.getWidth() / 2,
				VIEWPORT_HEIGHT - mainBG.getHeight());
		mainBG.setTouchable(Touchable.disabled);
		stage.addActor(mainBG);
	}

	private void addTabBG() {
		Image tabBG = new Image(game.assetManager.get("images/BG_Tab.png",
				Texture.class));
		tabBG.setPosition(VIEWPORT_WIDTH / 2 - tabBG.getWidth() / 2,
				VIEWPORT_HEIGHT - tabBG.getHeight());
		tabBG.setTouchable(Touchable.disabled);
		stage.addActor(tabBG);
	}

	private void addScreenTabs() {
		screenTabs = new ScreenTabs(game.assetManager);
		screenTabs.setPosition(VIEWPORT_WIDTH / 2 - screenTabs.getWidth() / 2,
				0);
		stage.addActor(screenTabs);
	}

	private void addSoundButton() {
		if (sound != null)
			return;
		final TextureRegionDrawable volumeToMute = new TextureRegionDrawable(
				atlas.findRegion("Button_VolumeMute_Neutral"));
		final TextureRegionDrawable volumeToUnmute = new TextureRegionDrawable(
				atlas.findRegion("Button_VolumeUnmute_Neutral"));
		final TextureRegionDrawable volumeToMuteClicked = new TextureRegionDrawable(
				atlas.findRegion("Button_VolumeMute_Clicked"));
		final TextureRegionDrawable volumeToUnmuteClicked = new TextureRegionDrawable(
				atlas.findRegion("Button_VolumeUnmute_Clicked"));

		sound = new Button(volumeToMute, volumeToMuteClicked, volumeToUnmute);
		sound.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				game.soundManager.setMute(((Button) actor).isChecked());
				sound.getStyle().down = game.soundManager.isMute() ? volumeToUnmuteClicked
						: volumeToMuteClicked;
			}

		});
		sound.setPosition(890, screenTabs.getTop());
	}

	protected void showMoneyLabel(float x, float y, boolean isImageFirst) {
		money = new ImageLabel(atlas.findRegion("Icon_Money"),
				game.state.getMoney() + "", game.assetManager.get(
						"font/tycoons.fnt", BitmapFont.class), isImageFirst,
				Color.valueOf("696965"), 1.58f);
		money.setPosition(x, y);
		money.setTouchable(Touchable.disabled);
		stage.addActor(money);

	}

	protected void showTechLabel(float x, float y, boolean isImageFirst) {
		tech = new ImageLabel(atlas.findRegion("Icon_Research"),
				game.state.getTechs() + "", game.assetManager.get(
						"font/tycoons.fnt", BitmapFont.class), isImageFirst,
				Color.valueOf("696965"), 2.1f);
		tech.setPosition(x, y);
		tech.scaleTo(0.75f);
		tech.setTouchable(Touchable.disabled);
		stage.addActor(tech);
	}

	protected void showPropertiesLabel(float x, float y, boolean isImageFirst) {
		properties = new ImageLabel(atlas.findRegion("Icon_Properties"),
				game.state.getPropertiesSize() + "/14", game.assetManager.get(
						"font/tycoons.fnt", BitmapFont.class), isImageFirst,
				Color.valueOf("696965"), 2.1f);
		properties.setPosition(x, y);
		properties.scaleTo(0.75f);
		properties.setTouchable(Touchable.disabled);
		stage.addActor(properties);
	}

	@Override
	public void show() {
		super.show();
		stage.addActor(sound);
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		if (screenTabs.isClicked()) {
			switch (screenTabs.getClickedTab()) {
			case HOME:
				game.setScreen(game.home);
				break;
			case PROPERTIES:
				game.setScreen(game.properties);
				break;
			case UPGRADES:
				game.setScreen(game.upgrades);
				break;
			case MARKET:
				game.setScreen(game.market);
				break;
			}
			screenTabs.wasClicked();
		}

		timeToSave += delta;
		if (timeToSave >= 60) {
			game.state.save();
			timeToSave = 0;
		}

		if (money != null)
			money.setLabel(game.state.getMoney() + "");
		if (tech != null)
			tech.setLabel(game.state.getTechs() + "");
		if (properties != null)
			properties.setLabel(game.state.getPropertiesSize() + "/14");
	}

	@Override
	public void dispose() {
		super.dispose();
		sound = null;
	}
}
