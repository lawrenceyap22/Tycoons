package com.epicnoobz.tycoons.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameProgressBar extends Actor {

	TextureRegion background;
	TextureRegion progress;
	TextureRegion highlight;
	boolean isVertical;
	float value;
	float min;
	float max;
	float stepSize;
	float progressSize;

	public GameProgressBar(float min, float max, float stepSize, Texture background, Texture progress,
			boolean isVertical) {
		this(min, max, stepSize, new TextureRegion(background), new TextureRegion(progress), isVertical);
	}

	public GameProgressBar(float min, float max, float stepSize, TextureRegion background, TextureRegion progress,
			boolean isVertical) {
		if (min > max)
			throw new IllegalArgumentException("min must be < max");
		if (stepSize <= 0)
			throw new IllegalArgumentException("stepSize must be > 0");
		this.min = min;
		this.max = max;
		this.stepSize = stepSize;
		this.background = background;
		this.progress = progress;
		this.isVertical = isVertical;
		this.progressSize = isVertical ? progress.getRegionHeight() : progress.getRegionWidth();
		this.highlight = null;
		setValue(min);
	}

	public void addHighlight(Texture highlight) {
		addHighlight(new TextureRegion(highlight));
	}

	public void addHighlight(TextureRegion highlight) {
		this.highlight = highlight;
		setHighlightRegionToValue();
	}

	public void addStep() {
		setValue(value + stepSize);
	}

	public void setValue(float value) {
		if (value % stepSize != 0)
			throw new IllegalArgumentException("value must be a factor of stepSize");
		this.value = MathUtils.clamp(value, min, max);
		if (isVertical) {
			progress.setV(progress.getV2() - (value - min) / (max - min) * progressSize
					/ progress.getTexture().getHeight());
			setHighlightRegionToValue();
		}
	}

	public float getValue() {
		return value;
	}

	public boolean isFull() {
		if (value == max)
			return true;
		return false;
	}

	private void setHighlightRegionToValue() {
		if (highlight != null)
			highlight.setV(progress.getV());
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(background, getX(), getY());
		batch.draw(progress, getX(), getY());
		batch.draw(highlight, getX(), getY());
	}
}
