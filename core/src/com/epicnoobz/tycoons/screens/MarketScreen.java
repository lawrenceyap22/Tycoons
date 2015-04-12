package com.epicnoobz.tycoons.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.epicnoobz.tycoons.Tycoons;
import com.epicnoobz.tycoons.objects.MarketSlider;

public class MarketScreen extends GameScreen {

	MarketSlider slider;
	Table resources;
	Image buySellResource;
	TextureRegionDrawable buyResourceNeutral;
	TextureRegionDrawable buyResourceClicked;
	TextureRegionDrawable sellResourceNeutral;
	TextureRegionDrawable sellResourceClicked;
	boolean isBuySellResourceTouchedDown;

	public MarketScreen(Tycoons game) {
		super(game, Tab.MARKET);
	}

	@Override
	protected void initActors() {
		super.initActors();
		initImageLabels();
		initResources();
		initDivider();
		initBuySellResource();
		initMarketSlider();
	}

	private void initImageLabels() {
		showMoneyLabel(50, VIEWPORT_HEIGHT - 150, true);
		showTechLabel(860, VIEWPORT_HEIGHT - 150, false);
	}

	private void initResources() {
		TextureRegionDrawable bg = new TextureRegionDrawable(
				new TextureRegion(game.assetManager.get(
						"images/ResourceList.png", Texture.class)));
		resources = new Table();
		resources.setBackground(bg);
		ScrollPane scrollPane = new ScrollPane(resources);
		scrollPane.setBounds(VIEWPORT_WIDTH / 2
				- bg.getRegion().getRegionWidth() / 2,
				VIEWPORT_HEIGHT / 2 - 246, bg.getRegion().getRegionWidth(),
				1036);
		stage.addActor(scrollPane);
	}

	private void initDivider() {
		Image divider = new Image(atlas.findRegion("BG_Property&MarketDivider"));
		divider.setPosition(VIEWPORT_WIDTH / 2 - divider.getWidth() / 2,
				VIEWPORT_HEIGHT / 2 - divider.getHeight() - 266);
		divider.setTouchable(Touchable.disabled);
		stage.addActor(divider);
	}

	private void initMarketSlider() {
		slider = new MarketSlider(10, 10, atlas.findRegion("Bar_MarketSlider"),
				atlas.findRegion("Button_MarketSlider_BuySide"),
				atlas.findRegion("Button_MarketSlider_SellSide"));
		slider.setPosition(VIEWPORT_WIDTH / 2 - slider.getWidth() / 2,
				sound.getTop() + 30);
		stage.addActor(slider);
	}

	private void initBuySellResource() {
		isBuySellResourceTouchedDown = false;
		buyResourceNeutral = new TextureRegionDrawable(
				atlas.findRegion("Button_BuyResource_Neutral"));
		buyResourceClicked = new TextureRegionDrawable(
				atlas.findRegion("Button_BuyResource_Clicked"));
		sellResourceNeutral = new TextureRegionDrawable(
				atlas.findRegion("Button_SellResource_Neutral"));
		sellResourceClicked = new TextureRegionDrawable(
				atlas.findRegion("Button_SellResource_Clicked"));

		buySellResource = new Image(buyResourceNeutral);
		buySellResource.addListener(new ClickListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (slider.isBuy()) {
					buySellResource.setDrawable(buyResourceClicked);
				} else {
					buySellResource.setDrawable(sellResourceClicked);
				}
				isBuySellResourceTouchedDown = true;
				return super.touchDown(event, x, y, pointer, button);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				isBuySellResourceTouchedDown = false;
				if (slider.isBuy()) {
					buySellResource.setDrawable(buyResourceNeutral);
				} else {
					buySellResource.setDrawable(sellResourceNeutral);
				}
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO clicked buysellresource
			}

		});
		buySellResource.setPosition(45, sound.getY());
		stage.addActor(buySellResource);
	}

	private void changeDrawable() {
		if (isBuySellResourceTouchedDown)
			return;
		if (slider.isBuy()) {
			buySellResource.setDrawable(buyResourceNeutral);
		} else {
			buySellResource.setDrawable(sellResourceNeutral);
		}
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		changeDrawable();
	}

}
