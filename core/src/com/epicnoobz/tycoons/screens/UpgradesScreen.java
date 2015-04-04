package com.epicnoobz.tycoons.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.epicnoobz.tycoons.Tycoons;

public class UpgradesScreen extends GameScreen {

	Table upgrades;
	Group upgradeLabel;
	Image buyUpgrade;

	public UpgradesScreen(Tycoons game) {
		super(game, Tab.UPGRADES);
	}

	@Override
	protected void initActors() {
		super.initActors();
		initImageLabels();
		initUpgrades();
		initUpgradeLabel();
		initBuyUpgrade();
	}

	private void initImageLabels() {
		showMoneyLabel(50, VIEWPORT_HEIGHT - 150, true);
		showTechLabel(860, VIEWPORT_HEIGHT - 150, false);
	}

	private void initUpgrades() {
		Image bg = new Image(game.assetManager.get(
				"images/BG_UpgradeList_Tiles.png", Texture.class));
		Image bg2 = new Image(game.assetManager.get(
				"images/BG_UpgradeList_AdditionalTiles.png", Texture.class));
		bg.setTouchable(Touchable.disabled);
		bg2.setTouchable(Touchable.disabled);
		Group group = new Group();
		upgrades = new Table();
		group.setSize(bg.getWidth(), bg.getHeight() + bg2.getHeight());
		group.addActor(bg);
		group.addActor(bg2);
		group.addActor(upgrades);
		bg.setY(bg2.getTop());
		ScrollPane scrollPane = new ScrollPane(group);
		scrollPane.setBounds(VIEWPORT_WIDTH / 2 - bg.getWidth() / 2,
				VIEWPORT_HEIGHT / 2 - bg.getHeight() / 4, bg.getWidth(),
				bg.getHeight());
		upgrades.setSize(bg.getWidth(), 3 * bg.getHeight() / 2);
		upgrades.add().expand();
		upgrades.add().expand();
		upgrades.add().expand();
		upgrades.add().expand();
		upgrades.row();
		upgrades.add().expand();
		upgrades.add().expand();
		upgrades.add().expand();
		upgrades.add().expand();
		upgrades.row();
		upgrades.add().expand();
		upgrades.add().expand();
		upgrades.add().expand();
		upgrades.add().expand();
		upgrades.row();
		upgrades.add().expand();
		upgrades.add().expand();
		upgrades.add().expand();
		upgrades.add().expand();
		upgrades.row();
		upgrades.add().expand();
		upgrades.add().expand();
		upgrades.add().expand();
		upgrades.add().expand();
		upgrades.row();
		upgrades.add().expand();
		upgrades.add().expand();
		upgrades.add().expand();
		upgrades.add().expand();

		stage.addActor(scrollPane);
	}

	private void initUpgradeLabel() {
		upgradeLabel = new Group();
		Image bg = new Image(game.assetManager.get(
				"images/BG_UpgradeLabel.png", Texture.class));
		upgradeLabel.setBounds(VIEWPORT_WIDTH / 2 - bg.getWidth() / 2,
				VIEWPORT_HEIGHT / 2 - bg.getHeight() - 252.75f, bg.getWidth(),
				bg.getHeight());
		upgradeLabel.addActor(bg);
		upgradeLabel.setTouchable(Touchable.disabled);
		stage.addActor(upgradeLabel);
	}

	private void initBuyUpgrade() {
		final TextureRegionDrawable buyUpgradeNeutral = new TextureRegionDrawable(
				atlas.findRegion("Button_BuyUpgrade_Neutral"));
		final TextureRegionDrawable buyUpgradeClicked = new TextureRegionDrawable(
				atlas.findRegion("Button_BuyUpgrade_Clicked"));
		buyUpgrade = new Image(buyUpgradeNeutral);
		buyUpgrade.addListener(new ClickListener(){

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				buyUpgrade.setDrawable(buyUpgradeClicked);
				return super.touchDown(event, x, y, pointer, button);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				buyUpgrade.setDrawable(buyUpgradeNeutral);
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {
				//TODO click buy upgrade
			}
			
		});	
		buyUpgrade.setPosition(sound.getX() - 10, sound.getTop() + 45);
		stage.addActor(buyUpgrade);
	}

}
