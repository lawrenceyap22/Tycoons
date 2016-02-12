package com.epicnoobz.tycoons.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.epicnoobz.tycoons.Tycoons;
import com.epicnoobz.tycoons.objects.GameProgressBar;
import com.epicnoobz.tycoons.objects.Property;
import com.esotericsoftware.minlog.Log;

public class PropertiesScreen extends GameScreen {

	Table propertiesTable;
	GameProgressBar resourceCollectProgressBar;
	Group requiresGroup;
	Group producesGroup;
	Image sellPropertyImage;
	Array<Property> properties;

	public PropertiesScreen(Tycoons game) {
		super(game, Tab.PROPERTIES);
	}

	@Override
	protected void initActors() {
		super.initActors();
		initImageLabels();
		initProperties();
		initPropertiesTable();
		initDivider();
		initResourceCollect();
		initRequires();
		initProduces();
		initSellProperty();
	}

	private void initImageLabels() {
		showMoneyLabel(50, VIEWPORT_HEIGHT - 150, true);
		showPropertiesLabel(730, VIEWPORT_HEIGHT - 150, false);
	}
	
	private void initProperties(){
		properties = game.state.getProperties();
		properties.add(new Property("Coal Mine",1,100,atlas.findRegion("Building_Coal_Mine")));
		properties.add(new Property("Cattle Farm",1,10,atlas.findRegion("Building_Cattle_Farm")));
		properties.add(new Property("Bakery",1,1000,atlas.findRegion("Building_Bakery")));
		properties.add(new Property("Bar",1,500,atlas.findRegion("Building_Bar")));
		properties.add(new Property("Fruit Orchard",1,300,atlas.findRegion("Building_Fruit_Orchard")));
		properties.add(new Property("Green House",1,400,atlas.findRegion("Building_Green_House")));
		properties.add(new Property("Metal Ore Mine",1,200,atlas.findRegion("Building_Metal_Ore_Mine")));
		for (Property property : properties) {
			property.initProductionProgressBar(new TextureRegion(atlas.findRegion("Bar_TimeLeft_Empty")), new TextureRegion(atlas.findRegion("Bar_TimeLeft_Full")));
			property.registerStartTime();
		}
	}

	private void initPropertiesTable() {
		propertiesTable = new Table();
		ScrollPane scrollPane = new ScrollPane(propertiesTable);
		scrollPane.setBounds(VIEWPORT_WIDTH / 2 - 472,
				VIEWPORT_HEIGHT / 2 - 246, 944, 1036);
		// size based on building images + pads
		propertiesTable.setSize(944, 1554);
		propertiesTable.top();
		
		int property_ctr = 0;
		GameProgressBar leftPropertyProgressBar = new GameProgressBar();
		for (Property property : properties) {
			//add property at the left side
			if(property_ctr % 2 == 0){
				if(property_ctr != 0){
					propertiesTable.row();
				}
				propertiesTable.add(new Image(property.getTexture())).pad(20, 1, 20, 1);
				propertiesTable.add().width(68);
				leftPropertyProgressBar = property.getProductionProgress();
			}
			//add property at the right side and add the progress bars for left and right side
			else{
				propertiesTable.add(new Image(property.getTexture())).pad(20, 1, 20, 1);
				propertiesTable.row();
				propertiesTable.add(leftPropertyProgressBar).pad(0,
						0, 20, 0);
				propertiesTable.add().width(68);
				propertiesTable.add(property.getProductionProgress()).padBottom(20);
			}
			property_ctr++;
		}
		if(property_ctr % 2 == 1){
			propertiesTable.row();
			propertiesTable.add(leftPropertyProgressBar).pad(0,
					0, 20, 0);
		}
		stage.addActor(scrollPane);
	}

	private void initDivider() {
		Image divider = new Image(atlas.findRegion("BG_Property&MarketDivider"));
		divider.setPosition(VIEWPORT_WIDTH / 2 - divider.getWidth() / 2,
				VIEWPORT_HEIGHT / 2 - divider.getHeight() - 266);
		divider.setTouchable(Touchable.disabled);
		stage.addActor(divider);
	}

	private void initResourceCollect() {
		resourceCollectProgressBar = new GameProgressBar(1, 10, 1,
				atlas.findRegion("Bar_ResourceCollected_BG"),
				atlas.findRegion("Bar_ResourceCollected_Full"), false);
		resourceCollectProgressBar.addHighlight(atlas
				.findRegion("Bar_ResourceCollected_Highlight"));
		resourceCollectProgressBar.setPosition(
				VIEWPORT_WIDTH / 2 - resourceCollectProgressBar.getWidth() / 2,
				VIEWPORT_HEIGHT / 2 - resourceCollectProgressBar.getHeight() - 302);
		resourceCollectProgressBar.setTouchable(Touchable.disabled);
		stage.addActor(resourceCollectProgressBar);
	}

	private void initRequires() {
		requiresGroup = new Group();
		Image bg = new Image(atlas.findRegion("BG_RequiredResource"));
		requiresGroup.setBounds(50, resourceCollectProgressBar.getY() - bg.getHeight() - 20,
				bg.getWidth(), bg.getHeight());
		requiresGroup.addActor(bg);
		requiresGroup.setTouchable(Touchable.disabled);
		stage.addActor(requiresGroup);
	}

	private void initProduces() {
		producesGroup = new Group();
		Image bg = new Image(atlas.findRegion("BG_ProducedResource"));
		producesGroup.setBounds(requiresGroup.getRight() + 10, resourceCollectProgressBar.getY()
				- bg.getHeight() - 20, bg.getWidth(), bg.getHeight());
		producesGroup.addActor(bg);
		producesGroup.setTouchable(Touchable.disabled);
		stage.addActor(producesGroup);
	}

	private void initSellProperty() {
		final TextureRegionDrawable sellPropertyImageClicked = new TextureRegionDrawable(
				atlas.findRegion("Button_SellProperty_Clicked"));
		final TextureRegionDrawable sellPropertyImageNeutral = new TextureRegionDrawable(
				atlas.findRegion("Button_SellProperty_Neutral"));
		sellPropertyImage = new Image(sellPropertyImageNeutral);
		sellPropertyImage.addListener(new ClickListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				sellPropertyImage.setDrawable(sellPropertyImageClicked);
				return super.touchDown(event, x, y, pointer, button);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				sellPropertyImage.setDrawable(sellPropertyImageNeutral);
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {
				//TODO sell property
			}

		});
		sellPropertyImage.setPosition(sound.getX() - 10, sound.getTop() + 10);
		stage.addActor(sellPropertyImage);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		for (Property property : properties) {
			GameProgressBar productionProgress = property.getProductionProgress();
			if(property.getShouldStartProduction()){
				property.setTimeElapsed();
				productionProgress.setValue((float)Math.ceil(property.getTimeElapsed()));
				productionProgress.setFont(game.assetManager.get("font/tycoons.fnt", BitmapFont.class));
				productionProgress.setText(property.getTimeRemainingString());
			}
		}
		
	}

}
