package com.epicnoobz.tycoons.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.TimeUtils;
import com.epicnoobz.tycoons.Tycoons;

public class Property {
	private String name;
	private int level;
	private boolean shouldStartProduction = true;
	private float timeToCollect;
	private float timeElapsed;
	private float timeRemaining;
	private String timeRemainingString;
	private Array<ArrayMap<Resource, Integer>> requirements;
	private Array<ArrayMap<Resource, Integer>> products;
	private TextureRegion texture;
	private GameProgressBar productionProgress;
	private long startTime;

	public Property(String name, int level, float timeToCollect,
			TextureRegion texture) {
		this.name = name;
		this.level = level;
		this.timeToCollect = timeToCollect;
		this.texture = texture;
	}
	
	public void registerStartTime(){
		startTime = TimeUtils.millis();
	}
	
	public void setTimeElapsed(){
		if(timeElapsed != timeToCollect){
			timeElapsed = (float)Math.ceil(convertToSeconds("milliseconds",TimeUtils.timeSinceMillis(startTime)));
		}
		
		timeRemaining = timeToCollect - timeElapsed;
		if(timeRemaining < 0){
			timeRemaining = 0;
		}
		setTimeRemainingString(timeRemaining);
	}
	
	private void setTimeRemainingString(double timeRemaining){
		int days = 0;
		int hours = 0;
		int minutes = 0;
		int seconds = 0;
		if(timeRemaining < 60){
			seconds = (int)timeRemaining;
		}
		if(timeRemaining >= 60){
			seconds = (int)(timeRemaining % 60);
			minutes = (int)(timeRemaining / 60);
		}
		else if (minutes >= 60){
			minutes = (int)(minutes % 60);
			hours = (int)(minutes / 60);
		}
		else if (hours >= 24){
			hours = (int)(hours % 24);
			days = (int)(hours / 24);
		}
		timeRemainingString = "";
		if(days > 0){
			timeRemainingString += days + "D ";
		}
		if(hours > 0){
			timeRemainingString += hours + "H ";
		}
		if(minutes > 0){
			timeRemainingString += minutes + "M ";
		}
		if(seconds > 0){
			timeRemainingString += seconds + "S ";
		}
		else if(seconds == 0 && minutes == 0){
			timeRemainingString += "0S";
		}
		if(timeRemaining == 0){
			timeRemainingString = "COLLECT";
		}
	}
	
	public float convertToSeconds(String type, float value){
		if(type.equals("minutes")){
			return value * 60;
		}
		else if(type.equals("milliseconds")){
			return value / 1000;
		}
		return value;
	}
	
	public float getTimeElapsed() {
		return timeElapsed;
	}
	
	public boolean getShouldStartProduction() {
		return shouldStartProduction;
	}

	public void setShouldStartProduction(boolean shouldStartProduction) {
		this.shouldStartProduction = shouldStartProduction;
	}
	
	public void initProductionProgressBar(TextureRegion barTimeLeftEmpty, TextureRegion barTimeLeftFull){
		productionProgress = new GameProgressBar(0,timeToCollect,1,barTimeLeftEmpty,barTimeLeftFull,false);
		productionProgress.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(timeRemainingString.equals("COLLECT")){
					registerStartTime();
					timeElapsed = 0;
				}
			}
		});
	}
	
	public GameProgressBar getProductionProgress() {
		return productionProgress;
	}

	public float getTimeRemaining() {
		return timeRemaining;
	}
	
	public String getTimeRemainingString() {
		return timeRemainingString;
	}

	public void setTimeRemaining(float timeRemaining) {
		this.timeRemaining = timeRemaining;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public float getTimeToCollect() {
		return timeToCollect;
	}

	public void setTimeToCollect(float timeToCollect) {
		this.timeToCollect = timeToCollect;
	}

	public Array<ArrayMap<Resource, Integer>> getRequirements() {
		return requirements;
	}

	public void setRequirements(Array<ArrayMap<Resource, Integer>> requirements) {
		this.requirements = requirements;
	}

	public Array<ArrayMap<Resource, Integer>> getProducts() {
		return products;
	}

	public void setProducts(Array<ArrayMap<Resource, Integer>> products) {
		this.products = products;
	}

	public TextureRegion getTexture() {
		return texture;
	}

	public void setTexture(TextureRegion texture) {
		this.texture = texture;
	}

}
