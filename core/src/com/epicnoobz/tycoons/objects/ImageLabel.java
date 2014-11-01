package com.epicnoobz.tycoons.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ImageLabel extends Actor {

	Image image;
	BitmapFont font;
	String label;
	TextBounds bounds;
	Color color;
	float fontSize;
	float scale;
	boolean isImageFirst;
	boolean isImageLarger;
	int gap;

	public ImageLabel(Texture image, String label, BitmapFont font, boolean isImageFirst, Color color, float fontSize) {
		this(new TextureRegion(image), label, font, isImageFirst, color, fontSize);
	}

	public ImageLabel(TextureRegion image, String label, BitmapFont font, boolean isImageFirst, Color color,
			float fontSize) {
		this.image = new Image(image);
		this.font = font;
		this.label = label;
		this.isImageFirst = isImageFirst;
		this.color = color;
		this.fontSize = fontSize;
		this.scale = 1;
		gap = 20;
		calculateBounds();
	}
	
	private void calculateBounds(){
		font.setScale(fontSize);
		bounds = font.getBounds(label);
		isImageLarger = image.getHeight() > bounds.height ? true : false;
		setBounds(0, 0, image.getWidth() + bounds.width + gap, isImageLarger ? image.getHeight()
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
	
	public void setFontSize(float fontSize){
		this.fontSize = fontSize;
		calculateBounds();
	}

	public void scaleTo(float scale) {
		this.scale = scale;
	}

	public void setImageFirst(boolean isImageFirst) {
		this.isImageFirst = isImageFirst;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		font.setColor(color);
		font.setScale(scale * fontSize);
		image.setScale(scale);
		if (isImageFirst) {
			image.setPosition(getX(), getY() + getHeight() / 2 - image.getHeight() / 2);
			image.draw(batch, parentAlpha);
			font.draw(batch, label, getX() + image.getWidth() + gap, getY() + getHeight() / 2 + bounds.height / 2);
		} else {
			font.draw(batch, label, getX(), getY() + getHeight() / 2 + bounds.height / 2);
			image.setPosition(getX() + bounds.width + gap, getY() + getHeight() / 2 - image.getHeight() / 2);
			image.draw(batch, parentAlpha);
		}
		batch.flush();
	}
}
