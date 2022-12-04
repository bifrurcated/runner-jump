package me.bifrurcated.runnerjump;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import me.bifrurcated.runnerjump.RunnerJump;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("runner-jump");
		config.setWindowedMode(1334, 750);
		config.useVsync(true);
		new Lwjgl3Application(new RunnerJump(), config);
	}
}
