package com.Lyeeedar.Lirard.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.Lyeeedar.Lirard.MainGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.useGL30 = true;
		config.foregroundFPS = 0;
		config.vSyncEnabled = false;
		new LwjglApplication(new MainGame(), config);
	}
}
