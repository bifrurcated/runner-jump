package me.bifrurcated.runnerjump.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class ExitInputListener extends InputListener {

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        event.getStage().dispose();
        Gdx.app.exit();
        return true;
    }

}
