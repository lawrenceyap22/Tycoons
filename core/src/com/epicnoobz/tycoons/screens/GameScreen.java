package com.epicnoobz.tycoons.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.epicnoobz.tycoons.Tycoons;
import com.epicnoobz.tycoons.objects.ScreenTabs;

public abstract class GameScreen implements Screen {

	public enum Tab {
		HOME, PROPERTIES, UPGRADES, MARKET
	}

	public static final int VIEWPORT_WIDTH = 1080;
	public static final int VIEWPORT_HEIGHT = 1920;

	protected final Tycoons game;
	protected static Button volume = null;
	protected OrthographicCamera camera;
	protected Stage stage;
	protected ScreenTabs screenTabs;

	public GameScreen(final Tycoons game) {
		this.game = game;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);

		stage = new Stage(new StretchViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, camera), game.batch);
		Gdx.input.setInputProcessor(stage);

		stage.setDebugAll(Tycoons.DEV_MODE);

		loadAssets();
		game.assetManager.finishLoading();
	}

	protected void loadAssets() {
		game.assetManager.load("images/images-packed.atlas", TextureAtlas.class);
		game.assetManager.load("images/BG_Overall.png", Texture.class);
		game.assetManager.load("images/BG_Tab.png", Texture.class);
		game.assetManager.load("font/tycoons.fnt", BitmapFont.class);
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

	protected void initVolumeButton() {
		TextureAtlas atlas = game.assetManager.get("images/images-packed.atlas", TextureAtlas.class);
		initVolumeButton(atlas);
	}

	protected void initVolumeButton(TextureAtlas atlas) {
		if(volume != null) return;
		final TextureRegionDrawable volumeToMute = new TextureRegionDrawable(
				atlas.findRegion("Button_VolumeMute_Neutral"));
		final TextureRegionDrawable volumeToMuteClicked = new TextureRegionDrawable(
				atlas.findRegion("Button_VolumeMute_Clicked"));
		final TextureRegionDrawable volumeToUnmute = new TextureRegionDrawable(
				atlas.findRegion("Button_VolumeUnmute_Neutral"));
		final TextureRegionDrawable volumeToUnmuteClicked = new TextureRegionDrawable(
				atlas.findRegion("Button_VolumeUnmute_Clicked"));

		volume = new Button(volumeToMute, volumeToMuteClicked, volumeToUnmute);
		volume.addListener(new ChangeListener() {

			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Button button = (Button) actor;
				ButtonStyle style = button.getStyle();
				if (button.isChecked()) {
					game.soundManager.mute();
					style.down = volumeToUnmuteClicked;
				} else {
					game.soundManager.unmute();
					style.down = volumeToMuteClicked;
				}
				button.setStyle(style);
			}

		});
		volume.setPosition(876, screenTabs.getTop());
	}

	@Override
	public void resize(int width, int height) {
		Gdx.app.log(Tycoons.TAG, "Resize screen " + getName());
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

		camera.update();

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void show() {
		Gdx.app.log(Tycoons.TAG, "Show screen " + getName());
	}

	@Override
	public void hide() {
		Gdx.app.log(Tycoons.TAG, "Hide screen " + getName());
	}

	@Override
	public void pause() {
		Gdx.app.log(Tycoons.TAG, "Pause screen " + getName());
	}

	@Override
	public void resume() {
		Gdx.app.log(Tycoons.TAG, "Resume screen " + getName());
	}

	@Override
	public void dispose() {
		Gdx.app.log(Tycoons.TAG, "Dispose screen " + getName());
		stage.dispose();
	}

	private String getName() {
		return getClass().getSimpleName();
	}
}
