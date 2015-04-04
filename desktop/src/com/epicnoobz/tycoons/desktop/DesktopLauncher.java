package com.epicnoobz.tycoons.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.epicnoobz.tycoons.Tycoons;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 270;
		config.height = 480;
		new LwjglApplication(new Tycoons(), config);
	}
}
