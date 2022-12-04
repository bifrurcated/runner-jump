package me.bifrurcated.runnerjump.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Unit extends Actor {

    private final List<Animation<TextureRegion>> animations;
    private Animation<TextureRegion> currentAnimation;
    private float elapseTime;
    private int currentIndex;
    private float tileWidth, tileHeight;
    private boolean onceAnimation;
    private float endY;
    private float startY;

    public Unit() {
        animations = new LinkedList<>();
    }

    public void addAnimation(Texture texture, int tileWidth, int tileHeight) {
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        TextureRegion[] animationFrames = Arrays.stream(TextureRegion.split(texture, tileWidth, tileHeight))
                .flatMap(Arrays::stream)
                .toArray(TextureRegion[]::new);

        animations.add(new Animation<>(1f/animationFrames.length, animationFrames));

        setCurrentAnimationIfNull();
    }

    public void addAnimation(TextureAtlas textureAtlas, int countFramesPerSecond) {
        System.out.println(textureAtlas.getRegions().get(0).packedHeight);
        System.out.println(textureAtlas.getRegions().get(0).originalHeight);
        animations.add(new Animation<>(1f/countFramesPerSecond, textureAtlas.getRegions()));

        setCurrentAnimationIfNull();
    }

    private void setCurrentAnimationIfNull() {
        if (currentAnimation == null) {
            currentAnimation = animations.get(0);
        }
    }

    public void setAnimation(int index) {
        setAnimation(index, false);
    }

    public void setAnimation(int index, boolean once) {
        currentAnimation = animations.get(index);
        elapseTime = 0;
        if (once) {
            onceAnimation = true;
            if (index == 1) {
                startY = getY();
                endY = getY() + 100;
                addAction(Actions.moveTo(getX(), endY, currentAnimation.getAnimationDuration()/2f));
            }
        } else {
            currentIndex = index;
        }
    }

    public void setAnimationPlayMode(PlayMode playMode) {
        currentAnimation.setPlayMode(playMode);
    }

    public boolean isAnimationFinished() {
        return currentAnimation.isAnimationFinished(elapseTime);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(currentAnimation.getKeyFrame(elapseTime),
                getX(), getY(),
                getOriginX(), getOriginY(),
                tileWidth, tileHeight,
                getScaleX(), getScaleY(),
                getRotation());

        if (onceAnimation && currentAnimation.isAnimationFinished(elapseTime)) {
            currentAnimation = animations.get(currentIndex);
            onceAnimation = false;
        }
        if (onceAnimation && getY() == endY) {
            addAction(Actions.moveTo(getX(), startY, currentAnimation.getAnimationDuration()/2f));
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        elapseTime += delta;
    }
}
