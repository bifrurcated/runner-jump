package me.bifrurcated.runnerjump.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import me.bifrurcated.runnerjump.stage.GameStage;

public class GameScreen implements Screen {

    private final Stage gameStage;
    public GameScreen() {
        gameStage = new GameStage();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(gameStage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        gameStage.act(delta);
        gameStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameStage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        gameStage.dispose();
    }

    @Override
    public void dispose() {
        gameStage.dispose();
    }
}
