package com.epicnoobz.tycoons.screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
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

	public GameScreen(final Tycoons game) {
		super(game);
	}

	@Override
	protected void loadAssets() {
	}

	protected void addScreenTabs(Tab tab) {
		TextureAtlas atlas = game.assetManager.get("images/images-packed.atlas", TextureAtlas.class);
		addScreenTabs(atlas, tab);
	}

	protected void addScreenTabs(TextureAtlas atlas, Tab tab) {
		TextureRegion home = atlas.findRegion("Button_HomeTab_Inactive");
		TextureRegion properties = atlas.findRegion("Button_PropertiesTab_Inactive");
		TextureRegion upgrades = atlas.findRegion("Button_UpgradesTab_Inactive");
		TextureRegion market = atlas.findRegion("Button_MarketTab_Inactive");

		switch (tab) {
		case HOME:
			home = atlas.findRegion("Button_HomeTab_Active");
			break;
		case PROPERTIES:
			properties = atlas.findRegion("Button_PropertiesTab_Active");
			break;
		case UPGRADES:
			properties = atlas.findRegion("Button_UpgradesTab_Active");
			break;
		case MARKET:
			properties = atlas.findRegion("Button_MarketTab_Active");
			break;
		}

		screenTabs = new ScreenTabs(home, properties, upgrades, market);
		screenTabs.setPosition(VIEWPORT_WIDTH / 2 - screenTabs.getWidth() / 2, 0);
		stage.addActor(screenTabs);
	}

	protected void addSoundButton() {
		TextureAtlas atlas = game.assetManager.get("images/images-packed.atlas", TextureAtlas.class);
		addSoundButton(atlas);
	}

	protected void addSoundButton(TextureAtlas atlas) {
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
				if (!this.getClass().equals(HomeScreen.class))
					game.setScreen(game.home);
				break;
			case PROPERTIES:
				if (!this.getClass().equals(PropertiesScreen.class))
					game.setScreen(game.properties);
				break;
			default:
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
