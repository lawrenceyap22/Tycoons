package com.epicnoobz.tycoons.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.epicnoobz.tycoons.Tycoons;
import com.epicnoobz.tycoons.screens.GameScreen.Tab;

public class ScreenTabs extends Group {
	TextureAtlas atlas;
	Image homeTab;
	Image propertiesTab;
	Image upgradesTab;
	Image marketTab;
	Tab clickedTab;
	Tab activeTab;
	boolean isClicked;

	public ScreenTabs(AssetManager assetManager) {
		atlas = assetManager.get("images/images-packed.atlas", TextureAtlas.class);
		homeTab = new Image();
		propertiesTab = new Image();
		upgradesTab = new Image();
		marketTab = new Image();
		isClicked = false;

		setActiveTab(null);
		initScreenTabs();
		addClickListeners();
	}

	public void wasClicked() {
		clickedTab = null;
		isClicked = false;
	}

	public Tab getClickedTab() {
		return clickedTab;
	}

	public boolean isClicked() {
		if (activeTab == clickedTab)
			wasClicked();
		return isClicked;
	}

	public void setActiveTab(Tab tab) {
		activeTab = tab;
		Drawable home = new TextureRegionDrawable(atlas.findRegion("Button_HomeTab_Inactive"));
		Drawable properties = new TextureRegionDrawable(atlas.findRegion("Button_PropertiesTab_Inactive"));
		Drawable upgrades = new TextureRegionDrawable(atlas.findRegion("Button_UpgradesTab_Inactive"));
		Drawable market = new TextureRegionDrawable(atlas.findRegion("Button_MarketTab_Inactive"));

		if (tab != null) {
			switch (tab) {
			case HOME:
				home = new TextureRegionDrawable(atlas.findRegion("Button_HomeTab_Active"));
				break;
			case PROPERTIES:
				properties = new TextureRegionDrawable(atlas.findRegion("Button_PropertiesTab_Active"));
				break;
			case UPGRADES:
				upgrades = new TextureRegionDrawable(atlas.findRegion("Button_UpgradesTab_Active"));
				break;
			case MARKET:
				market = new TextureRegionDrawable(atlas.findRegion("Button_MarketTab_Active"));
				break;
			}
		}

		homeTab.setDrawable(home);
		homeTab.setSize(homeTab.getPrefWidth(), homeTab.getPrefHeight());
		
		propertiesTab.setDrawable(properties);
		propertiesTab.setSize(propertiesTab.getPrefWidth(), propertiesTab.getPrefHeight());
		
		upgradesTab.setDrawable(upgrades);
		upgradesTab.setSize(upgradesTab.getPrefWidth(), upgradesTab.getPrefHeight());
		
		marketTab.setDrawable(market);
		marketTab.setSize(marketTab.getPrefWidth(), marketTab.getPrefHeight());
	}

	public Tab getActiveTab() {
		return activeTab;
	}

	private void initScreenTabs() {
		Gdx.app.log(Tycoons.TAG,
				homeTab.getWidth() + propertiesTab.getWidth() + upgradesTab.getWidth() + marketTab.getWidth() + " "
						+ homeTab.getHeight());
		setBounds(0, 0, homeTab.getWidth() + propertiesTab.getWidth() + upgradesTab.getWidth() + marketTab.getWidth(),
				homeTab.getHeight());
		setTabPosition();
		addToGroup();
	}

	private void addToGroup() {
		addActor(homeTab);
		addActor(propertiesTab);
		addActor(upgradesTab);
		addActor(marketTab);
	}

	private void setTabPosition() {
		homeTab.setPosition(0, 0);
		propertiesTab.setPosition(homeTab.getRight(), 0);
		upgradesTab.setPosition(propertiesTab.getRight(), 0);
		marketTab.setPosition(upgradesTab.getRight(), 0);
	}

	private void addClickListeners() {
		homeTab.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				isClicked = true;
				clickedTab = Tab.HOME;
			}
		});

		propertiesTab.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				isClicked = true;
				clickedTab = Tab.PROPERTIES;
			}
		});

		upgradesTab.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				isClicked = true;
				clickedTab = Tab.UPGRADES;
			}
		});

		marketTab.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				isClicked = true;
				clickedTab = Tab.MARKET;
			}
		});

	}
}
