package me.bifrurcated.runnerjump.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import me.bifrurcated.runnerjump.actor.BackgroundSprite;
import me.bifrurcated.runnerjump.listener.ExitInputListener;
import me.bifrurcated.runnerjump.listener.PlayInputListener;

public class MenuStage extends Stage {

    private final BitmapFont font;
    private final Skin skinBtn;
    private final Texture backgroundTexture;

    public MenuStage() {
        backgroundTexture = new Texture(Gdx.files.internal("BACKGROUND.png"));
        BackgroundSprite backgroundSprite = new BackgroundSprite(backgroundTexture);

        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("skins/uiskin.atlas"));
        font = new BitmapFont(Gdx.files.internal("fonts/Arial.fnt"));
        skinBtn = new Skin(Gdx.files.internal("skins/uiskin.json"), atlas);

        TextButton playBtn = new TextButton("Играть", skinBtn);
        TextButton exitBtn = new TextButton("Выйти", skinBtn);
        playBtn.addListener(new PlayInputListener());
        exitBtn.addListener(new ExitInputListener());

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(playBtn).width(200f).padBottom(10f);
        table.row();
        table.add(exitBtn).width(200f);

        addActor(backgroundSprite);
        addActor(table);
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
        skinBtn.dispose();
        backgroundTexture.dispose();
    }
}
