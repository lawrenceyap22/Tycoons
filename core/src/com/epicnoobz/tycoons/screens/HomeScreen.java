package com.epicnoobz.tycoons.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.epicnoobz.tycoons.Tycoons;
import com.epicnoobz.tycoons.objects.GameProgressBar;
import com.epicnoobz.tycoons.objects.ImageLabel;
import com.epicnoobz.tycoons.objects.ResourcesDrawer;

public class HomeScreen extends GameScreen {

	Image coin;
	ResourcesDrawer resourcesDrawer;
	GameProgressBar researchBar;
	ImageLabel cash;
	ImageLabel tech;
	ImageLabel properties;

	public HomeScreen(Tycoons game) {
		super(game);
	}

	@Override
	protected void loadAssets() {
		super.loadAssets();
	}

	@Override
	protected void initActors() {
		TextureAtlas atlas = game.assetManager.get("images/images-packed.atlas", TextureAtlas.class);
		addBG();
		addScreenTabs(atlas, Tab.HOME);
		addTabBG();
		addHomeBG();
		addCoin();
		addResearchBar(atlas);
		addImageLabels(atlas);
		addResourceDrawer(atlas);
		addSoundButton(atlas);
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

	private void addHomeBG() {
		Image homeBG = new Image(game.assetManager.get("images/BG_HomeTab_Town.png", Texture.class));
		homeBG.setPosition(VIEWPORT_WIDTH / 2 - homeBG.getWidth() / 2, VIEWPORT_HEIGHT / 2 - homeBG.getHeight() / 2);
		homeBG.setTouchable(Touchable.disabled);
		stage.addActor(homeBG);
	}

	private void addCoin() {
		final TextureRegionDrawable unPressedCoinDrawable = new TextureRegionDrawable(new TextureRegion(
				game.assetManager.get("images/Button_Coin_Neutral.png", Texture.class)));
		final TextureRegionDrawable pressedCoinDrawable = new TextureRegionDrawable(new TextureRegion(
				game.assetManager.get("images/Button_Coin_Clicked.png", Texture.class)));
		coin = new Image(unPressedCoinDrawable);
		coin.setPosition(-100, VIEWPORT_HEIGHT / 2 - coin.getHeight() * 2 / 3 + 100);
		final Rectangle clickBounds = new Rectangle(171, 171, 715, 715);

		coin.addListener(new ClickListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (clickBounds.contains(x, y)) {
					coin.setDrawable(pressedCoinDrawable);
					return super.touchDown(event, x, y, pointer, button);
				}
				return false;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				coin.setDrawable(unPressedCoinDrawable);
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (clickBounds.contains(x, y)) {
					Gdx.app.log(Tycoons.TAG, "Clicked!");
					// TODO do something upon clicked!
				}
			}

		});

		stage.addActor(coin);
	}

	private void addResearchBar(TextureAtlas atlas) {
		researchBar = new GameProgressBar(0, 10, 1, atlas.findRegion("Bar_Research_BG"), new TextureRegion(
				atlas.findRegion("Bar_Research_Full")), true);
		researchBar.setPosition(coin.getRight() - 80, VIEWPORT_HEIGHT / 2 - researchBar.getHeight() / 2 - 50);
		researchBar.setValue(5);
		researchBar.setTouchable(Touchable.disabled);
		researchBar.addHighlight(atlas.findRegion("Bar_Research_Highlight"));
		stage.addActor(researchBar);
	}

	private void addResourceDrawer(TextureAtlas atlas) {
		resourcesDrawer = new ResourcesDrawer(game.assetManager.get("images/Drawer_HomeTab_BG.png", Texture.class),
				game.assetManager.get("images/Drawer_HomeTab_ResourceList.png", Texture.class),
				atlas.findRegion("Button_HomeTabDrawer_Pullup"), atlas.findRegion("Button_HomeTabDrawer_Pulldown"),
				game.assetManager.get("font/tycoons.fnt", BitmapFont.class), 10);
		resourcesDrawer.setPosition(VIEWPORT_WIDTH / 2 - resourcesDrawer.getWidth() / 2, VIEWPORT_HEIGHT
				- resourcesDrawer.getVisibleHeight());
		stage.addActor(resourcesDrawer);
	}

	private void addImageLabels(TextureAtlas atlas) {
		Color dark_gray = Color.valueOf("696965");
		cash = new ImageLabel(atlas.findRegion("Icon_Money"), "1234567890", game.assetManager.get("font/tycoons.fnt",
				BitmapFont.class), true, dark_gray, 1.58f);
		cash.setPosition(50, coin.getTop() - 100);
		stage.addActor(cash);

		tech = new ImageLabel(atlas.findRegion("Icon_Research"), "12", game.assetManager.get("font/tycoons.fnt",
				BitmapFont.class), true, dark_gray, 1.58f);
		tech.setPosition(50, coin.getY());
		stage.addActor(tech);

		properties = new ImageLabel(atlas.findRegion("Icon_Properties"), "6/14", game.assetManager.get(
				"font/tycoons.fnt", BitmapFont.class), true, dark_gray, 1.58f);
		properties.setPosition(50, tech.getY() - properties.getHeight());
		stage.addActor(properties);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		if (resourcesDrawer.isClicked()) {
			resourcesDrawer.wasClicked();
			if (resourcesDrawer.isOpen()) {
				// TODO change layout
			} else {

			}
		}
	}

}
