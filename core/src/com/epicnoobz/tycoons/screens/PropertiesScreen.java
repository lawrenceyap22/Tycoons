package com.epicnoobz.tycoons.screens;

import com.epicnoobz.tycoons.Tycoons;

public class PropertiesScreen extends GameScreen{
	
	private static PropertiesScreen instance = null;

	public PropertiesScreen(Tycoons game) {
		super(game);
	}
	
	public static synchronized PropertiesScreen getScreen(Tycoons game){
		if(instance == null){
			instance = new PropertiesScreen(game);
		}
		return instance;
	}

}
