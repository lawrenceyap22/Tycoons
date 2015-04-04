package com.epicnoobz.tycoons.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MarketSlider extends Group {

	TextureRegion background;
	Image knob;
	final TextureRegionDrawable knobBuyDrawable;
	final TextureRegionDrawable knobSellDrawable;
	int maxBuy;
	int maxSell;

	public MarketSlider(int maxBuy, int maxSell, TextureRegion background,
			TextureRegion knobBuyTextureRegion,
			TextureRegion knobSellTextureRegion) {
		this.maxBuy = maxBuy;
		this.maxSell = maxSell;
		this.background = background;
		knobBuyDrawable = new TextureRegionDrawable(knobBuyTextureRegion);
		knobSellDrawable = new TextureRegionDrawable(knobSellTextureRegion);
		setSize(background.getRegionWidth(), background.getRegionHeight());
		addKnob();
	}

	private void addKnob() {
		knob = new Image(knobBuyDrawable);
		knob.addListener(new ClickListener() {
			float diffX;
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				diffX = x;
				return super.touchDown(event, x, y, pointer, button);
			}

			@Override
			public void touchDragged(InputEvent event, float x, float y,
					int pointer) {
				super.touchDragged(event, x, y, pointer);
				knob.setX(MathUtils.clamp(knob.getX() + x - diffX, 0,
						getWidth() - knob.getWidth()));
				if (knob.getX() < getWidth() / 2 - knob.getWidth() / 2) {
					knob.setDrawable(knobSellDrawable);
				} else {
					knob.setDrawable(knobBuyDrawable);
				}
				System.out.println((int) (knob.getX()
						/ (getRight() - getCenterX()) * 10));
			}

		});
		knob.setX(getCenterX() - knob.getWidth() / 2);
		addActor(knob);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(background, getX(), getY());
		super.draw(batch, parentAlpha);
	}

}
