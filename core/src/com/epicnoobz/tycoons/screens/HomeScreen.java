package com.epicnoobz.tycoons.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.epicnoobz.tycoons.Tycoons;
import com.epicnoobz.tycoons.objects.GameProgressBar;
import com.epicnoobz.tycoons.objects.ResourcesDrawer;

public class HomeScreen extends GameScreen {

	Image coin;
	ResourcesDrawer resourcesDrawer;
	GameProgressBar researchBar;
	int multiplier;
	int nClicks;
	float comboTimeExpire;

	public HomeScreen(Tycoons game) {
		super(game, Tab.HOME);
		multiplier = 1;
		comboTimeExpire = Tycoons.TIME_COMBO_EXPIRE;
	}

	@Override
	protected void initActors() {
		super.initActors();
		initBG();
		initCoin();
		initResearchBar();
		initImageLabels();
		initResourceDrawer();
	}

	private void initBG() {
		Image homeBG = new Image(game.assetManager.get(
				"images/BG_HomeTab_Town.png", Texture.class));
		homeBG.setPosition(VIEWPORT_WIDTH / 2 - homeBG.getWidth() / 2,
				VIEWPORT_HEIGHT / 2 - homeBG.getHeight() / 2);
		homeBG.setTouchable(Touchable.disabled);
		stage.addActor(homeBG);
	}

	private void initCoin() {
		final TextureRegionDrawable unPressedCoinDrawable = new TextureRegionDrawable(
				new TextureRegion(game.assetManager.get(
						"images/Button_Coin_Neutral.png", Texture.class)));
		final TextureRegionDrawable pressedCoinDrawable = new TextureRegionDrawable(
				new TextureRegion(game.assetManager.get(
						"images/Button_Coin_Clicked.png", Texture.class)));
		coin = new Image(unPressedCoinDrawable);
		coin.setPosition(-100, VIEWPORT_HEIGHT / 2 - coin.getHeight() * 2 / 3
				+ 100);
		final Rectangle clickBounds = new Rectangle(171, 171, 715, 715);

		coin.addListener(new ClickListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (clickBounds.contains(x, y)) {
					coin.setDrawable(pressedCoinDrawable);
					return super.touchDown(event, x, y, pointer, button);
				}
				return false;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				coin.setDrawable(unPressedCoinDrawable);
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (clickBounds.contains(x, y)) {
					Gdx.app.log(Tycoons.TAG, "Clicked!");
					coinActionEvent();
				}
			}

		});

		stage.addActor(coin);
	}

	private void coinActionEvent() {
		game.state.calculateMoney(multiplier * game.state.getMoneyPerClick());
		game.state.subClicksForTech();
		game.state.checkToAddTech();

		comboTimeExpire = Tycoons.TIME_COMBO_EXPIRE;
		nClicks++;

		if (nClicks == Tycoons.NUMBER_OF_CLICKS_FOR_COMBO) {
			nClicks = 0;
			multiplier += multiplier < Tycoons.MAX_MULTIPLIER ? 1 : 0;
		}

		Gdx.app.log(Tycoons.TAG, "Multiplier: " + multiplier);
	}

	private void initResearchBar() {
		researchBar = new GameProgressBar(0, 200, 1,
				atlas.findRegion("Bar_Research_BG"), new TextureRegion(
						atlas.findRegion("Bar_Research_Full")), true);
		researchBar.setPosition(coin.getRight() - 80, VIEWPORT_HEIGHT / 2
				- researchBar.getHeight() / 2 - 50);
		researchBar.setTouchable(Touchable.disabled);
		researchBar.addHighlight(atlas.findRegion("Bar_Research_Highlight"));
		stage.addActor(researchBar);
	}

	private void initResourceDrawer() {
		resourcesDrawer = new ResourcesDrawer(game.assetManager, 10);
		resourcesDrawer.setPosition(
				VIEWPORT_WIDTH / 2 - resourcesDrawer.getWidth() / 2,
				VIEWPORT_HEIGHT - resourcesDrawer.getVisibleHeight());
		stage.addActor(resourcesDrawer);
	}

	private void initImageLabels() {
		showMoneyLabel(50, coin.getTop() - 100, true);
		showTechLabel(50, coin.getY() - 50, true);
		showPropertiesLabel(50, coin.getY() - 140, true);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		researchBar.setValue(200 - game.state.getNClicksForTech());

		comboTimeExpire -= delta;
		if (comboTimeExpire <= 0)
			multiplier = 1;
	}

}
