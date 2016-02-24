package com.epicnoobz.tycoons.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
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
	boolean isPositionFixed;
	BitmapFont font;
	Rectangle scissors;
	Rectangle clipBounds;

	public ResourcesDrawer(AssetManager assetManager, int nRowResources) {
		TextureAtlas atlas = assetManager.get("images/images-packed.atlas", TextureAtlas.class);
		background = new TextureRegion(assetManager.get("images/Drawer_HomeTab_BG.png", Texture.class));
		resourceList = new TextureRegion(assetManager.get("images/Drawer_HomeTab_ResourceList.png", Texture.class));
		buttonUp = new TextureRegionDrawable(atlas.findRegion("Button_HomeTabDrawer_Pullup"));
		buttonDown = new TextureRegionDrawable(atlas.findRegion("Button_HomeTabDrawer_Pulldown"));
		font = assetManager.get("font/tycoons.fnt", BitmapFont.class);
		this.nRowResources = nRowResources;
		ownedResources = new long[Resource.values().length];
		resourceListHeightPerResource = resourceList.getRegionHeight() * 1.0f / nRowResources;
		nRowResourcesToShow = 3;
		setBounds(0, 0, background.getRegionWidth(), background.getRegionHeight());
		addButton();
		isOpen = true;
		isPositionFixed = false;
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
						0.25f));
				button.setDrawable(isOpen ? buttonUp : buttonDown);
				Gdx.app.log(Tycoons.TAG, "Drawer clicked!");
			}
		});
		addActor(button);
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		if (!isPositionFixed) {
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
		scissors = new Rectangle();
		clipBounds = new Rectangle(getX(), getY() + getResourceListOffset(), resourceList.getRegionWidth(),
				resourceList.getRegionHeight());
		ScissorStack.calculateScissors(getStage().getCamera(), batch.getTransformMatrix(), clipBounds, scissors);
		ScissorStack.pushScissors(scissors);
		batch.draw(resourceList, getCenterX() - resourceList.getRegionWidth() / 2, resourceListPositionY
				+ getResourceListOffset());
		printResources(batch);
		batch.flush();
		ScissorStack.popScissors();
	}

	public void setNResourcesToShow(int nResourcesToShow) {
		this.nRowResourcesToShow = nResourcesToShow;
	}

	public float getVisibleHeight() {
		return resourceList.getRegionHeight() + getResourceListOffset();
	}

	public void setResource(long n, Resource resource) {
		ownedResources[resource.getIndex()] = n;
	}
	public long getResource(Resource resource){
		return ownedResources[resource.getIndex()];
	}

	public boolean isOpen() {
		return isOpen;
	}

	private float getResourceListOffset() {
		return button.getHeight() * 2 / 3;
	}

	private void printResources(Batch batch) {
		float x = 180;
		float y = resourceListPositionY + resourceList.getRegionHeight();
		font.setColor(Color.WHITE);
		font.setScale(1f);
		for (int i = 0; i < ownedResources.length; i++) {
			font.draw(batch, ownedResources[i] + "", x + (i / nRowResources) * resourceList.getRegionWidth() / 2, y
					- (i % nRowResources) * resourceListHeightPerResource);
		}
	}

}
