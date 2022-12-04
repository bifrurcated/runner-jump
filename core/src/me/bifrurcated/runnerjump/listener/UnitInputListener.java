package me.bifrurcated.runnerjump.listener;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import me.bifrurcated.runnerjump.actor.Unit;

public class UnitInputListener extends InputListener {

    private final Unit unit;
    private float endY;
    private float startY;

    public UnitInputListener(Unit unit) {
        this.unit = unit;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        if (unit.isAnimationFinished()) {
            unit.setAnimation(1, true);
        }
        return true;
    }
}
