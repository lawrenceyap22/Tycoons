package com.epicnoobz.tycoons.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.epicnoobz.tycoons.Tycoons;

public class MainMenuScreen extends AbstractScreen {
	
	Image title;

	public MainMenuScreen(Tycoons game) {
		super(game);
	}
	
	@Override
	protected void loadAssets(){
		game.assetManager.load("font/tycoons.fnt", BitmapFont.class);
		game.assetManager.load("images/TitleScreen.png", Texture.class);
		//TODO load assets for loading screen
		game.assetManager.load("images/images-packed.atlas", TextureAtlas.class);
		game.assetManager.finishLoading();
	}
	
	@Override
	protected void initActors() {
		title = new Image(game.assetManager.get("images/TitleScreen.png", Texture.class));
		title.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log(Tycoons.TAG, "Going to Loading Screen");
				game.setScreen(new LoadingScreen(game));
			}
			
		});
		stage.addActor(title);
	}

}
