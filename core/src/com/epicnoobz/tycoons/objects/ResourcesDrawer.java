package com.epicnoobz.tycoons.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.epicnoobz.tycoons.Tycoons;

public class ResourcesDrawer extends Group {
	TextureRegion background;
	TextureRegion resourceList;
	Image button;
	Drawable buttonUp;
	Drawable buttonDown;
	int nRowResourcesToShow, nRowResources;
	long[] ownedResources;
	float resourceListHeightPerResource;
	float resourceListPositionY;
	boolean isOpen;
	boolean isPositionFixed = false;
	BitmapFont font;

	public ResourcesDrawer(Texture background, Texture resourceList, TextureRegion buttonUp, TextureRegion buttonDown,
			BitmapFont font, int nRowResources) {
		this(new TextureRegion(background), new TextureRegion(resourceList), new TextureRegionDrawable(buttonUp),
				new TextureRegionDrawable(buttonDown), font, nRowResources);
	}

	public ResourcesDrawer(TextureRegion background, TextureRegion resourceList, Drawable buttonUp,
			Drawable buttonDown, BitmapFont font, int nRowResources) {
		this.background = background;
		this.resourceList = resourceList;
		this.buttonUp = buttonUp;
		this.buttonDown = buttonDown;
		this.nRowResources = nRowResources;
		this.font = font;
		ownedResources = new long[Resource.values().length];
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
				ResourcesDrawer.this.clearActions();
				isOpen = !isOpen;
				ResourcesDrawer.this.addAction(Actions.moveTo(ResourcesDrawer.this.getX(), ResourcesDrawer.this.getY()
						+ (isOpen ? -1 : 1) * resourceListHeightPerResource * (nRowResources - nRowResourcesToShow),
						0.5f));
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
	public void setPosition(float x, float y){
		super.setPosition(x, y);
		if(!isPositionFixed){
			resourceListPositionY = y;
			isPositionFixed = true;
		}
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.draw(background, getX(), getY());
		super.draw(batch, parentAlpha);
		Rectangle scissors = new Rectangle();
		Rectangle clipBounds = new Rectangle(getX(), getY() + getResourceListOffset(), resourceList.getRegionWidth(),
				resourceList.getRegionHeight());
		ScissorStack.calculateScissors(getStage().getCamera(), batch.getTransformMatrix(), clipBounds, scissors);
		ScissorStack.pushScissors(scissors);
		batch.draw(resourceList, getCenterX() - resourceList.getRegionWidth() / 2, resourceListPositionY + getResourceListOffset());
		printResources(batch);
		batch.flush();
		ScissorStack.popScissors();
	}
	
	public void setResources(int n, Resource resource){
		ownedResources[resource.getIndex()] = n;
	}
	
	private void printResources(Batch batch){
		int i = 0;
		for(int x = 180; x < 694 && i < ownedResources.length; x+=513){
			for(float y = 1870;y > 536.2; y-=148.2){
				font.draw(batch, ownedResources[i] + "", x, y);
				i++;
			}
		}
	}

}
