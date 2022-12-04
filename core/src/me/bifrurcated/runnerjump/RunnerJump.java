package me.bifrurcated.runnerjump;

import com.badlogic.gdx.Game;
import me.bifrurcated.runnerjump.screen.MainMenuScreen;
import me.bifrurcated.runnerjump.utils.ScreenManager;

public class RunnerJump extends Game {

	@Override
	public void create () {
		ScreenManager.initialize(this);
		ScreenManager.setScreen(new MainMenuScreen());
	}

}
