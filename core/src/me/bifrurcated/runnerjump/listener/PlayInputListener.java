package me.bifrurcated.runnerjump.listener;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import me.bifrurcated.runnerjump.utils.ScreenManager;
import me.bifrurcated.runnerjump.screen.GameScreen;

public class PlayInputListener extends InputListener {

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        ScreenManager.setScreen(new GameScreen());
        return true;
    }

}
