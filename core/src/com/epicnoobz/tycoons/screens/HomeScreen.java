package com.epicnoobz.tycoons.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.epicnoobz.tycoons.Tycoons;
import com.epicnoobz.tycoons.objects.Coin;
import com.epicnoobz.tycoons.objects.GameProgressBar;
import com.epicnoobz.tycoons.objects.ResourcesDrawer;
import com.epicnoobz.tycoons.objects.ScreenTabs;

public class HomeScreen extends GameScreen {

	Coin coin;
	ResourcesDrawer resourcesDrawer;
	GameProgressBar researchBar;

	public HomeScreen(Tycoons game) {
		super(game);
		init();
	}

	@Override
	protected void loadAssets() {
		game.manager.load("images/BG_Overall.png", Texture.class);
		game.manager.load("images/BG_Tab.png", Texture.class);
		game.manager.load("images/BG_HomeTab_Town.png", Texture.class);
		game.manager.load("images/Button_Coin_Clicked.png", Texture.class);
		game.manager.load("images/Button_Coin_Neutral.png", Texture.class);
		game.manager.load("images/images-packed.atlas", TextureAtlas.class);
		game.manager.load("images/Drawer_HomeTab_BG.png", Texture.class);
		game.manager.load("images/Drawer_HomeTab_ResourceList.png", Texture.class);
		game.manager.load("font/tycoons.fnt", BitmapFont.class);
	}

	private void addBG() {
		Image mainBG = new Image(game.manager.get("images/BG_Overall.png", Texture.class));
		mainBG.setPosition(VIEWPORT_WIDTH / 2 - mainBG.getWidth() / 2, VIEWPORT_HEIGHT - mainBG.getHeight());
		mainBG.setTouchable(Touchable.disabled);
		stage.addActor(mainBG);
	}

	private void addTabBG() {
		Image tabBG = new Image(game.manager.get("images/BG_Tab.png", Texture.class));
		tabBG.setPosition(VIEWPORT_WIDTH / 2 - tabBG.getWidth() / 2, VIEWPORT_HEIGHT - tabBG.getHeight());
		tabBG.setTouchable(Touchable.disabled);
		stage.addActor(tabBG);
	}

	private void addHomeBG() {
		Image homeBG = new Image(game.manager.get("images/BG_HomeTab_Town.png", Texture.class));
		homeBG.setPosition(VIEWPORT_WIDTH / 2 - homeBG.getWidth() / 2, VIEWPORT_HEIGHT / 2 - homeBG.getHeight() / 2);
		homeBG.setTouchable(Touchable.disabled);
		stage.addActor(homeBG);
	}

	private void init() {
		TextureAtlas atlas = game.manager.get("images/images-packed.atlas", TextureAtlas.class);
		addBG();
		addScreenTabs(atlas);
		addTabBG();
		addHomeBG();
		addCoin();
		addResearchBar(atlas);
		addResourceDrawer(atlas);
	}

	private void addCoin() {
		coin = new Coin(game.manager.get("images/Button_Coin_Neutral.png", Texture.class), game.manager.get(
				"images/Button_Coin_Clicked.png", Texture.class));
		coin.setPosition(-100, VIEWPORT_HEIGHT / 2 - coin.getHeight() * 2 / 3 + 100);
		coin.setClickBounds(171, 171, 715, 715);
		stage.addActor(coin);
	}

	private void addResearchBar(TextureAtlas atlas) {
		researchBar = new GameProgressBar(0, 10, 1, atlas.findRegion("Bar_Research_BG"), new TextureRegion(
				atlas.findRegion("Bar_Research_Full")), true);
		researchBar.setPosition(coin.getRight() - 80, VIEWPORT_HEIGHT / 2 - researchBar.getHeight() / 2 - 50);
		researchBar.setValue(9);
		researchBar.setTouchable(Touchable.disabled);
		researchBar.addHighlight(atlas.findRegion("Bar_Research_Highlight"));
		stage.addActor(researchBar);
	}

	private void addResourceDrawer(TextureAtlas atlas) {
		resourcesDrawer = new ResourcesDrawer(game.manager.get("images/Drawer_HomeTab_BG.png", Texture.class),
				game.manager.get("images/Drawer_HomeTab_ResourceList.png", Texture.class),
				atlas.findRegion("Button_HomeTabDrawer_Pullup"), atlas.findRegion("Button_HomeTabDrawer_Pulldown"),
				game.manager.get("font/tycoons.fnt", BitmapFont.class), 10);
		resourcesDrawer.setPosition(VIEWPORT_WIDTH / 2 - resourcesDrawer.getWidth() / 2, VIEWPORT_HEIGHT
				- resourcesDrawer.getVisibleHeight());
		stage.addActor(resourcesDrawer);
	}

	@Override
	protected void addScreenTabs(TextureAtlas atlas) {
		screenTabs = new ScreenTabs(atlas.findRegion("Button_HomeTab_Active"),
				atlas.findRegion("Button_PropertiesTab_Inactive"), atlas.findRegion("Button_UpgradesTab_Inactive"),
				atlas.findRegion("Button_MarketTab_Inactive"));
		screenTabs.setPosition(VIEWPORT_WIDTH / 2 - screenTabs.getWidth() / 2, 0);
		stage.addActor(screenTabs);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		if (screenTabs.hasClicked()) {
			Gdx.app.log(Tycoons.TAG, screenTabs.getClickedTab().toString());
			screenTabs.clearClickedTab();
		}
	}

}
