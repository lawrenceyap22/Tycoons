package com.epicnoobz.tycoons.screens;

import com.epicnoobz.tycoons.Tycoons;
import com.epicnoobz.tycoons.objects.MarketSlider;

public class MarketScreen extends GameScreen {

	MarketSlider slider;
	
	public MarketScreen(Tycoons game) {
		super(game, Tab.MARKET);
	}

	@Override
	protected void initActors() {
		super.initActors();
		initImageLabels();
		initMarketSlider();
	}
	
	private void initImageLabels(){
		showMoneyLabel(50, VIEWPORT_HEIGHT - 150, true);
	}
	
	private void initMarketSlider() {
		slider = new MarketSlider(10, 10, atlas.findRegion("Bar_MarketSlider"),
				atlas.findRegion("Button_MarketSlider_BuySide"),
				atlas.findRegion("Button_MarketSlider_SellSide"));
		slider.setPosition(VIEWPORT_WIDTH / 2 - slider.getWidth() / 2,
				VIEWPORT_HEIGHT / 2 - slider.getHeight() / 2);
		stage.addActor(slider);
	}

}
