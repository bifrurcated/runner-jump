package me.bifrurcated.runnerjump.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import me.bifrurcated.runnerjump.actor.BackgroundSprite;
import me.bifrurcated.runnerjump.actor.Tile;
import me.bifrurcated.runnerjump.actor.Unit;
import me.bifrurcated.runnerjump.listener.UnitInputListener;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

public class GameStage extends Stage {
    private final Texture[] heroAnimationTextures;
    private final Texture tileStart;
    private final Texture tileMiddle;
    private final Texture tileEnd;
    private final Texture background;
    private final Texture tileBarrier;
    private final ScheduledExecutorService executorService;

    public GameStage() {
        background = new Texture(Gdx.files.internal("BackgroundGame.png"));
        BackgroundSprite backgroundSprite = new BackgroundSprite(background);
        addActor(backgroundSprite);

        tileStart = new Texture(Gdx.files.internal("tiles/IndustrialTile_55.png"));
        tileMiddle = new Texture(Gdx.files.internal("tiles/IndustrialTile_56.png"));
        tileEnd = new Texture(Gdx.files.internal("tiles/IndustrialTile_57.png"));
        Tile startTile = new Tile(tileStart);
        startTile.setPosition(0,0);
        startTile.setScale(2);
        addActor(startTile);
        final int countTile = (int) (Gdx.graphics.getWidth() / (startTile.getWidth()*startTile.getScaleX()));
        for (int i = 1; i < countTile; i++) {
            Tile middleTile = new Tile(tileMiddle);
            middleTile.setScale(2);
            middleTile.setPosition(middleTile.getWidth()*2*i, 0);
            addActor(middleTile);
        }
        Tile endTile = new Tile(tileEnd);
        endTile.setScale(2);
        endTile.setPosition(endTile.getWidth()*2*countTile,0);
        addActor(endTile);

        tileBarrier = new Texture("tiles/IndustrialTile_09.png");
        final Vector2 startPosition = new Vector2(endTile.getX(),endTile.getHeight()*endTile.getScaleX());
        final float endX = startTile.getWidth()*-1f;
        final Tile barrierTile = new Tile(tileBarrier);
        barrierTile.setPosition(startPosition.x, startPosition.y);
        final MoveToAction moveToAction = Actions.moveTo(endX, startPosition.y, 5);
        barrierTile.addAction(moveToAction);
        addActor(barrierTile);

        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            if (!barrierTile.hasActions()) {
                barrierTile.setPosition(startPosition.x, startPosition.y);
                moveToAction.restart();
                barrierTile.addAction(moveToAction);
            }
        }, 0, 5, TimeUnit.SECONDS);

        heroAnimationTextures = new Texture[5];
        heroAnimationTextures[0] = new Texture(Gdx.files.internal("units/Cyborg_run.png"));
        heroAnimationTextures[1] = new Texture(Gdx.files.internal("units/Cyborg_jump.png"));
        heroAnimationTextures[2] = new Texture(Gdx.files.internal("units/Cyborg_death.png"));
        heroAnimationTextures[3] = new Texture(Gdx.files.internal("units/Cyborg_idle.png"));
        heroAnimationTextures[4] = new Texture(Gdx.files.internal("units/Cyborg_hurt.png"));
        Unit cyborg = new Unit();
        for (Texture texture: heroAnimationTextures) {
            cyborg.addAnimation(texture, 48,48);
        }
        cyborg.setAnimation(0);
        cyborg.setAnimationPlayMode(PlayMode.LOOP);
        cyborg.setX(0);
        cyborg.setY(startTile.getHeight()*startTile.getScaleX());
        cyborg.setSize(34,34);
        cyborg.setScale(3);
        cyborg.debug();
        addActor(cyborg);
        addListener(new UnitInputListener(cyborg));
    }

    @Override
    public void dispose() {
        super.dispose();
        background.dispose();

        tileStart.dispose();
        tileMiddle.dispose();
        tileEnd.dispose();
        tileBarrier.dispose();

        for (Texture texture: heroAnimationTextures) {
            texture.dispose();
        }

        executorService.shutdown();
        try {
            if(!executorService.awaitTermination(500, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException exception) {
            executorService.shutdownNow();
        }
    }
}
