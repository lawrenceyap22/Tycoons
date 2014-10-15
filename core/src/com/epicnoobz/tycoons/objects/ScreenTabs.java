package com.epicnoobz.tycoons.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.epicnoobz.tycoons.screens.GameScreen.Tab;

public class ScreenTabs extends Group {
	Image homeTab;
	Image propertiesTab;
	Image upgradesTab;
	Image marketTab;
	Tab clickedTab;
	boolean hasClicked;

	public ScreenTabs(Texture homeTab, Texture propertiesTab, Texture upgradesTab, Texture marketTab) {
		this(new TextureRegion(homeTab), new TextureRegion(propertiesTab), new TextureRegion(upgradesTab),
				new TextureRegion(marketTab));
	}

	public ScreenTabs(TextureRegion homeTab, TextureRegion propertiesTab, TextureRegion upgradesTab,
			TextureRegion marketTab) {
		this.homeTab = new Image(homeTab);
		this.propertiesTab = new Image(propertiesTab);
		this.upgradesTab = new Image(upgradesTab);
		this.marketTab = new Image(marketTab);
		hasClicked = false;

		initScreenTabs();
		addClickListeners();
	}

	private void initScreenTabs() {
		setBounds(0, 0, homeTab.getWidth() + propertiesTab.getWidth() + upgradesTab.getWidth() + marketTab.getWidth(),
				homeTab.getWidth());
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
				hasClicked = true;
				clickedTab = Tab.HOME;
			}
		});

		propertiesTab.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				hasClicked = true;
				clickedTab = Tab.PROPERTIES;
			}
		});

		upgradesTab.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				hasClicked = true;
				clickedTab = Tab.UPGRADES;
			}
		});

		marketTab.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				hasClicked = true;
				clickedTab = Tab.MARKET;
			}
		});

	}

	public void clearClickedTab() {
		clickedTab = null;
		hasClicked = false;
	}

	public Tab getClickedTab() {
		return clickedTab;
	}
	
	public boolean hasClicked(){
		return hasClicked;
	}
}
