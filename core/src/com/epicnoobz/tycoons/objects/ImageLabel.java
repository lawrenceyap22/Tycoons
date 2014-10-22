package com.epicnoobz.tycoons.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class ImageLabel extends Actor {

	TextureRegion image;
	BitmapFont font;
	String label;
	TextBounds bounds;
	Color color;
	float scale;
	boolean isImageFirst;
	boolean isImageLarger;
	int gap;

	public ImageLabel(Texture image, String label, BitmapFont font, boolean isImageFirst, Color color, float scale) {
		this(new TextureRegion(image), label, font, isImageFirst, color, scale);
	}

	public ImageLabel(TextureRegion image, String label, BitmapFont font, boolean isImageFirst, Color color, float scale) {
		this.image = image;
		this.font = font;
		this.label = label;
		this.isImageFirst = isImageFirst;
		this.color = color;
		this.scale = scale;
		font.setScale(scale);
		bounds = font.getBounds(label);
		isImageLarger = image.getRegionHeight() > bounds.height ? true : false;
		gap = 20;
		setBounds(0, 0, image.getRegionWidth() + bounds.width + gap, isImageLarger ? image.getRegionHeight()
				: bounds.height);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setGap(int gap) {
		this.gap = gap;
	}

	public void setFontColor(Color color) {
		this.color = color;
	}
	
	public void setFontSize(float scale){
		this.scale = scale;
	}

	public void moveTo(float x, float y) {
		this.addAction(Actions.moveTo(x, y, 0.5f));
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		font.setColor(color);
		font.setScale(scale);
		if (isImageFirst) {
			batch.draw(image, getX(), getY() + getHeight() / 2 - image.getRegionHeight() / 2);
			font.draw(batch, label, getX() + image.getRegionWidth() + gap, getY() + getHeight() / 2 + bounds.height / 2);
		} else {
			font.draw(batch, label, getX(), getY() + getHeight() / 2 + bounds.height / 2);
			batch.draw(image, getX() + bounds.width + gap, getY() + getHeight() / 2 - image.getRegionHeight() / 2);
		}
		batch.flush();
	}
}
