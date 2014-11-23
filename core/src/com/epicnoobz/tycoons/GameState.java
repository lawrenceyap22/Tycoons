package com.epicnoobz.tycoons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.epicnoobz.tycoons.objects.Property;
import com.epicnoobz.tycoons.objects.Resource;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

public class GameState {
	private long money;
	private int techs;
	private Array<Property> properties;
	private long[] resources;
	private int nClicksForTech;

	private GameState() {
		money = 0;
		techs = 0;
		properties = new Array<Property>();
		resources = new long[Resource.values().length];
		nClicksForTech = 200;
	}

	private static GameState newGame() {
		GameState newGame = new GameState();
		return newGame.save() ? newGame : null;
	}

	public static GameState loadGame() {
		FileHandle file = Gdx.files.local("data/tycoons.sav");
		if (file.exists()) {
			Kryo kryo = new Kryo();
			Input input = new Input(file.read());
			return kryo.readObject(input, GameState.class);
		} else
			return newGame();
	}

	public boolean save() {
		Output output = null;
		try {
			FileHandle file = Gdx.files.local("data/tycoons.sav");
			Kryo kryo = new Kryo();
			output = new Output(file.write(false));
			kryo.writeObject(output, this);
		} catch (Exception e) {
			return false;
		} finally {
			if (output != null)
				output.close();
		}
		return true;
	}

	public long getMoney() {
		return money;
	}

	public void calculateMoney(long money) {
		this.money += money;
	}

	public int getTechs() {
		return techs;
	}

	public void checkToAddTech() {
		if (nClicksForTech == 0)
			this.techs++;
	}

	public void subClicksForTech() {
		nClicksForTech--;
		if (nClicksForTech < 0) {
			nClicksForTech = 200 + nClicksForTech;
		}
	}

	public int getPropertiesSize() {
		return properties.size;
	}

	public long getResource(Resource resource) {
		return resources[resource.getIndex()];
	}

	public void calculateResource(Resource resource, int size) {
		resources[resource.getIndex()] += size;
	}

}