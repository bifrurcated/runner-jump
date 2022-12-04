package me.bifrurcated.runnerjump.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import me.bifrurcated.runnerjump.stage.MenuStage;

public class MainMenuScreen implements Screen {

    private final Stage menuStage;

    public MainMenuScreen() {
        menuStage = new MenuStage();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(menuStage);
    }

    @Override
    public void render(float delta) {
        menuStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        menuStage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        menuStage.dispose();
    }

    @Override
    public void dispose() {
        menuStage.dispose();
    }
}
