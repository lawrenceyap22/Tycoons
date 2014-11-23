package com.epicnoobz.tycoons.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
		super(game, Tab.HOME);
	}

	@Override
	protected void initActors() {
		super.initActors();
		addHomeBG();
		addCoin();
		addResearchBar();
		addImageLabels();
		addResourceDrawer();
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
					game.state.calculateMoney(100);
					game.state.subClicksForTech();
					game.state.checkToAddTech();
				}
			}

		});

		stage.addActor(coin);
	}

	private void addResearchBar() {
		researchBar = new GameProgressBar(0, 10, 1, atlas.findRegion("Bar_Research_BG"), new TextureRegion(
				atlas.findRegion("Bar_Research_Full")), true);
		researchBar.setPosition(coin.getRight() - 80, VIEWPORT_HEIGHT / 2 - researchBar.getHeight() / 2 - 50);
		researchBar.setValue(5);
		researchBar.setTouchable(Touchable.disabled);
		researchBar.addHighlight(atlas.findRegion("Bar_Research_Highlight"));
		stage.addActor(researchBar);
	}

	private void addResourceDrawer() {
		resourcesDrawer = new ResourcesDrawer(game.assetManager, 10);
		resourcesDrawer.setPosition(VIEWPORT_WIDTH / 2 - resourcesDrawer.getWidth() / 2, VIEWPORT_HEIGHT
				- resourcesDrawer.getVisibleHeight());
		stage.addActor(resourcesDrawer);
	}

	private void addImageLabels() {
		Color dark_gray = Color.valueOf("696965");
		cash = new ImageLabel(atlas.findRegion("Icon_Money"), game.state.getMoney() + "", game.assetManager.get(
				"font/tycoons.fnt", BitmapFont.class), true, dark_gray, 1.58f);
		cash.setPosition(50, coin.getTop() - 100);
		stage.addActor(cash);

		tech = new ImageLabel(atlas.findRegion("Icon_Research"), game.state.getTechs() + "", game.assetManager.get(
				"font/tycoons.fnt", BitmapFont.class), true, dark_gray, 2.1f);
		tech.setPosition(50, coin.getY() - 50);
		tech.scaleTo(0.75f);
		stage.addActor(tech);

		properties = new ImageLabel(atlas.findRegion("Icon_Properties"), game.state.getPropertiesSize() + "/14",
				game.assetManager.get("font/tycoons.fnt", BitmapFont.class), true, dark_gray, 2.1f);
		properties.setPosition(50, coin.getY() - properties.getHeight() - 30);
		properties.scaleTo(0.75f);
		stage.addActor(properties);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		cash.setLabel(game.state.getMoney() + "");
		tech.setLabel(game.state.getTechs() + "");
		properties.setLabel(game.state.getPropertiesSize() + "/14");
	}

}
