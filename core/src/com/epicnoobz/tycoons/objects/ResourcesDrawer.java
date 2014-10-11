package com.epicnoobz.tycoons.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.FloatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.epicnoobz.tycoons.Tycoons;

public class ResourcesDrawer extends Group {
	TextureRegion background;
	TextureRegion resourceList;
	Image button;
	Drawable buttonUp;
	Drawable buttonDown;
	int nRowResourcesToShow, nRowResources;
	float resourceListHeightPerResource;
	boolean isOpen;
	FloatAction slideAction;

	public ResourcesDrawer(Texture background, Texture resourceList, TextureRegion buttonUp, TextureRegion buttonDown,
			int nRowResources) {
		this(new TextureRegion(background), new TextureRegion(resourceList), new TextureRegionDrawable(buttonUp),
				new TextureRegionDrawable(buttonDown), nRowResources);
	}

	public ResourcesDrawer(TextureRegion background, TextureRegion resourceList, Drawable buttonUp,
			Drawable buttonDown, int nRowResources) {
		this.background = background;
		this.resourceList = resourceList;
		this.buttonUp = buttonUp;
		this.buttonDown = buttonDown;
		this.nRowResources = nRowResources;
		resourceListHeightPerResource = resourceList.getRegionHeight() * 1.0f / nRowResources;
		nRowResourcesToShow = 3;

		setBounds(0, 0, background.getRegionWidth(), background.getRegionHeight());
		addButton();
		isOpen = true;
	}

	private void addButton() {
		button = new Image(buttonUp);
		button.setPosition(getCenterX() - button.getWidth() / 2, 0);
		button.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				isOpen = !isOpen;
				slideAction = new FloatAction(resourceList.getV2(), isOpen ? 1f : resourceListHeightPerResource
						* nRowResourcesToShow / resourceList.getTexture().getHeight());
				slideAction.setDuration(0.5f);
				ResourcesDrawer.this.addAction(Actions.moveTo(ResourcesDrawer.this.getX(), ResourcesDrawer.this.getY()
						+ (isOpen ? -1 : 1) * resourceListHeightPerResource * (nRowResources - nRowResourcesToShow),
						0.5f));
				ResourcesDrawer.this.addAction(slideAction);
				button.setDrawable(isOpen ? buttonUp : buttonDown);
				Gdx.app.log(Tycoons.TAG, "Drawer clicked!");
			}
		});
		addActor(button);
	}

	public void setNResourcesToShow(int nResourcesToShow) {
		this.nRowResourcesToShow = nResourcesToShow;
	}

	public float getVisibleHeight() {
		return resourceList.getRegionHeight() + getResourceListOffset();
	}

	private float getResourceListOffset() {
		return button.getHeight() * 2 / 3;
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		if (slideAction != null)
			resourceList.setV2(slideAction.getValue());
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(background, getX(), getY());
		batch.draw(resourceList, getCenterX() - resourceList.getRegionWidth() / 2, getY() + getResourceListOffset());
		super.draw(batch, parentAlpha);
	}

}
