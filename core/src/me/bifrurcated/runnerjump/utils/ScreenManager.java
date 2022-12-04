package me.bifrurcated.runnerjump.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public final class ScreenManager {
    private static Game INSTANCE_GAME;

    /**
     * Initialize a game instance.
     * @param game instance of the base Game class
     */
    public static void initialize(Game game) {
        INSTANCE_GAME = game;
    }

    public static void setScreen(Screen screen) {
        INSTANCE_GAME.setScreen(screen);
    }
}
