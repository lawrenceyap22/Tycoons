package com.epicnoobz.tycoons.screens;

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

public class PropertiesScreen extends GameScreen {

	Table properties;
	GameProgressBar resourceCollect;
	Group requires;
	Group produces;
	Array<GameProgressBar> timesToCollect;
	Image sellProperty;

	public PropertiesScreen(Tycoons game) {
		super(game, Tab.PROPERTIES);
	}

	@Override
	protected void initActors() {
		super.initActors();
		initImageLabels();
		initProperties();
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

	private void initProperties() {
		properties = new Table();
		properties.setSize(944, 1554);
		properties.top();
		ScrollPane scrollPane = new ScrollPane(properties);
		scrollPane.setBounds(VIEWPORT_WIDTH / 2 - 472,
				VIEWPORT_HEIGHT / 2 - 246, 944, 1036);
		Array<Property> propertyList = game.state.getProperties();
		Array<GameProgressBar> propertyProgressBars = new Array<GameProgressBar>(
				propertyList.size);
		for (Property property : propertyList) {
			propertyProgressBars.add(new GameProgressBar(0, 60, property
					.getTimeToCollect(),
					atlas.findRegion("Bar_TimeLeft_Empty"), atlas
							.findRegion("Bar_TimeLeft_Full"), false));
		}

		for (int i = 0; i < propertyList.size; i++) {

		}

		Property property = new Property("Coal Mine", 1, 10,
				atlas.findRegion("Building_temp"));
		GameProgressBar testBar = new GameProgressBar(0, 60, 1,
				atlas.findRegion("Bar_TimeLeft_Empty"),
				atlas.findRegion("Bar_TimeLeft_Full"), false);
		testBar.setValue(30);
		scrollPane.setBounds(VIEWPORT_WIDTH / 2 - 472,
				VIEWPORT_HEIGHT / 2 - 246, 944, 1036);
		// size based on building images + pads
		properties.setSize(944, 1554);
		properties.top();
		// Array<Property> list = game.state.getProperties();
		properties.add(new Image(property.getTexture())).pad(20, 1, 20, 1);
		properties.add().width(68);
		properties.add(new Image(property.getTexture())).pad(20, 1, 20, 1);
		properties.row();
		properties.add(testBar).pad(0, 0, 20, 0);
		testBar.setValue(50);
		// properties.add(new
		// Image(atlas.findRegion("Bar_TimeLeft_Full"))).pad(0,
		// 0, 20, 0);
		properties.add().width(68);
		properties.add(new Image(atlas.findRegion("Bar_TimeLeft_Full")))
				.padBottom(20);
		properties.row();
		properties.add(new Image(property.getTexture())).pad(20, 1, 20, 1);
		properties.add().width(68);
		properties.add(new Image(property.getTexture())).pad(20, 1, 20, 1);
		properties.row();
		properties.add(new Image(atlas.findRegion("Bar_TimeLeft_Full"))).pad(0,
				0, 20, 0);
		properties.add().width(68);
		properties.add(new Image(atlas.findRegion("Bar_TimeLeft_Full")))
				.padBottom(20);
		properties.row();
		properties.add(new Image(property.getTexture())).pad(20, 1, 20, 1);
		properties.add().width(68);
		properties.add(new Image(property.getTexture())).pad(20, 1, 20, 1);
		properties.row();
		properties.add(new Image(atlas.findRegion("Bar_TimeLeft_Full"))).pad(0,
				0, 20, 0);
		properties.add().width(68);
		properties.add(new Image(atlas.findRegion("Bar_TimeLeft_Full")))
				.padBottom(20);
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
		resourceCollect = new GameProgressBar(1, 10, 1,
				atlas.findRegion("Bar_ResourceCollected_BG"),
				atlas.findRegion("Bar_ResourceCollected_Full"), false);
		resourceCollect.addHighlight(atlas
				.findRegion("Bar_ResourceCollected_Highlight"));
		resourceCollect.setPosition(
				VIEWPORT_WIDTH / 2 - resourceCollect.getWidth() / 2,
				VIEWPORT_HEIGHT / 2 - resourceCollect.getHeight() - 302);
		resourceCollect.setTouchable(Touchable.disabled);
		stage.addActor(resourceCollect);
	}

	private void initRequires() {
		requires = new Group();
		Image bg = new Image(atlas.findRegion("BG_RequiredResource"));
		requires.setBounds(50, resourceCollect.getY() - bg.getHeight() - 20,
				bg.getWidth(), bg.getHeight());
		requires.addActor(bg);
		requires.setTouchable(Touchable.disabled);
		stage.addActor(requires);
	}

	private void initProduces() {
		produces = new Group();
		Image bg = new Image(atlas.findRegion("BG_ProducedResource"));
		produces.setBounds(requires.getRight() + 10, resourceCollect.getY()
				- bg.getHeight() - 20, bg.getWidth(), bg.getHeight());
		produces.addActor(bg);
		produces.setTouchable(Touchable.disabled);
		stage.addActor(produces);
	}

	private void initSellProperty() {
		final TextureRegionDrawable sellPropertyClicked = new TextureRegionDrawable(
				atlas.findRegion("Button_SellProperty_Clicked"));
		final TextureRegionDrawable sellPropertyNeutral = new TextureRegionDrawable(
				atlas.findRegion("Button_SellProperty_Neutral"));
		sellProperty = new Image(sellPropertyNeutral);
		sellProperty.addListener(new ClickListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				sellProperty.setDrawable(sellPropertyClicked);
				return super.touchDown(event, x, y, pointer, button);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				sellProperty.setDrawable(sellPropertyNeutral);
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO sell property
			}

		});
		sellProperty.setPosition(sound.getX() - 10, sound.getTop() + 10);
		stage.addActor(sellProperty);
	}

	@Override
	public void render(float delta) {
		super.render(delta);
	}

}
