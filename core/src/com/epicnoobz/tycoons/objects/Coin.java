package com.epicnoobz.tycoons.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.epicnoobz.tycoons.Tycoons;

public class Coin extends Image {

	Drawable unPressedCoinDrawable;
	Drawable pressedCoinDrawable;
	Rectangle clickBounds;

	public Coin(Texture unPressedCoin, Texture pressedCoin) {
		this(new TextureRegion(unPressedCoin), new TextureRegion(pressedCoin));
	}

	public Coin(TextureRegion unPressedCoin, TextureRegion pressedCoin) {
		this(new TextureRegionDrawable(unPressedCoin), new TextureRegionDrawable(pressedCoin));
	}

	public Coin(Drawable unPressedCoin, Drawable pressedCoin) {
		super(unPressedCoin);
		unPressedCoinDrawable = unPressedCoin;
		pressedCoinDrawable = pressedCoin;
		clickBounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
		addListener(new ClickListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (clickBounds.contains(x, y)) {
					setDrawable(pressedCoinDrawable);
					return super.touchDown(event, x, y, pointer, button);
				}
				return false;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				super.touchUp(event, x, y, pointer, button);
				setDrawable(unPressedCoinDrawable);
			}

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (clickBounds.contains(x, y)) {
					Gdx.app.log(Tycoons.TAG, "Clicked!");
					// TODO do something upon clicked!
				}
			}

		});
	}

	public void setClickBounds(float x, float y, float width, float height) {
		clickBounds = clickBounds.set(x, y, width, height);
	}

	public void setClickBounds(Rectangle rect) {
		clickBounds.set(rect);
	}
}
