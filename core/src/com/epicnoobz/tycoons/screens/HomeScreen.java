package com.epicnoobz.tycoons.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.epicnoobz.tycoons.Tycoons;
import com.epicnoobz.tycoons.objects.Coin;
import com.epicnoobz.tycoons.objects.GameProgressBar;

public class HomeScreen extends GameScreen {

	Coin coin;
	GameProgressBar researchBar;

	public HomeScreen(Tycoons game) {
		super(game);
		loadAssets();
		game.manager.finishLoading();
		addActors();
	}

	private void loadAssets() {
		game.manager.load("images/Button_Coin_Clicked.png", Texture.class);
		game.manager.load("images/Button_Coin_Neutral.png", Texture.class);
		game.manager.load("images/images-packed.atlas", TextureAtlas.class);
	}

	private void addActors() {
		TextureAtlas atlas = game.manager.get("images/images-packed.atlas", TextureAtlas.class);

		coin = new Coin(game.manager.get("images/Button_Coin_Neutral.png", Texture.class), game.manager.get(
				"images/Button_Coin_Clicked.png", Texture.class));
		coin.setPosition(0, 0);
		coin.setClickBounds(171, 171, 715, 715);
		stage.addActor(coin);

		researchBar = new GameProgressBar(0, 10, 1, atlas.findRegion("Bar_Research_BG"), new TextureRegion(atlas.findRegion("Bar_Research_Full")), true);
		researchBar.setPosition(0, 200);
		researchBar.setValue(9);
		researchBar.setTouchable(Touchable.disabled);
		researchBar.addHighlight(new TextureRegion(atlas.findRegion("Bar_Research_Highlight")));
		stage.addActor(researchBar);
	}

}
