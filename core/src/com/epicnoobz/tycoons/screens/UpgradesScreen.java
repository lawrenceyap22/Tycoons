package com.epicnoobz.tycoons.screens;

import com.epicnoobz.tycoons.Tycoons;

public class UpgradesScreen extends GameScreen{

	private static UpgradesScreen instance = null;
	
	public UpgradesScreen(Tycoons game) {
		super(game);
	}
	
	public static synchronized UpgradesScreen getScreen(Tycoons game){
		if(instance == null){
			instance = new UpgradesScreen(game);
		}
		return instance;
	}

}
