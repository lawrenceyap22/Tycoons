package com.epicnoobz.tycoons.objects;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;

public class Property {
	private String name;
	private int level;
	private float timeToCollect;
	private Array<ArrayMap<Resource, Integer>> requirements;
	private Array<ArrayMap<Resource, Integer>> products;
	
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
	
	
}
