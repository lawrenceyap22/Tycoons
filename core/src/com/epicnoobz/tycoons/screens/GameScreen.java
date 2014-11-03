package com.epicnoobz.tycoons.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.epicnoobz.tycoons.Tycoons;
import com.epicnoobz.tycoons.objects.ScreenTabs;

public abstract class GameScreen extends AbstractScreen {

	public enum Tab {
		HOME, PROPERTIES, UPGRADES, MARKET
	}

	protected static Button sound = null;
	protected ScreenTabs screenTabs;
	protected Tab tab;
	protected TextureAtlas atlas;

	public GameScreen(final Tycoons game, Tab tab) {
		super(game);
		screenTabs.setActiveTab(tab);
	}

	@Override
	protected void initActors() {
		atlas = game.assetManager.get("images/images-packed.atlas", TextureAtlas.class);
		addBG();
		addScreenTabs();
		addTabBG();
		addSoundButton();
	}

	@Override
	protected void loadAssets() {
	}

	private void addBG() {
		Image mainBG = new Image(game.assetManager.get("images/BG_Overall.png", Texture.class));
		mainBG.setPosition(VIEWPORT_WIDTH / 2 - mainBG.getWidth() / 2, VIEWPORT_HEIGHT - mainBG.getHeight());
		mainBG.setTouchable(Touchable.disabled);
		stage.addActor(mainBG);
	}

	private void addTabBG() {
		Image tabBG = new Image(game.assetManager.get("images/BG_Tab.png", Texture.class));
		tabBG.setPosition(VIEWPORT_WIDTH / 2 - tabBG.getWidth() / 2, VIEWPORT_HEIGHT - tabBG.getHeight());
		tabBG.setTouchable(Touchable.disabled);
		stage.addActor(tabBG);
	}

	private void addScreenTabs() {
		screenTabs = new ScreenTabs(game.assetManager);
		screenTabs.setPosition(VIEWPORT_WIDTH / 2 - screenTabs.getWidth() / 2, 0);
		stage.addActor(screenTabs);
	}

	protected void addSoundButton() {
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
				sound.getStyle().down = game.soundManager.isMute() ? volumeToUnmuteClicked : volumeToMuteClicked;
			}

		});
		sound.setPosition(876, screenTabs.getTop());
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
	}

	@Override
	public void dispose() {
		super.dispose();
		sound = null;
	}
}
