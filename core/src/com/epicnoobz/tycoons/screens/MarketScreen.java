package com.epicnoobz.tycoons.screens;

import com.epicnoobz.tycoons.Tycoons;

public class MarketScreen extends GameScreen{

	private static MarketScreen instance =  null;
	
	public MarketScreen(Tycoons game) {
		super(game);
	}
	
	public static synchronized MarketScreen getScreen(Tycoons game){
		if(instance == null){
			instance = new MarketScreen(game);
		}
		return instance;
	}

}
