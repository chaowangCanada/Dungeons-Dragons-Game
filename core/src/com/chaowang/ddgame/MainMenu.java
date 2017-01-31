package com.chaowang.ddgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by Chao on 31/01/2017.
 */

public class MainMenu implements Screen{

    Stage stage;
    Label label;
    Label.LabelStyle style;
    BitmapFont font;

    TextureAtlas buttonAtlas;
    TextButton.TextButtonStyle buttonStyle;
    TextButton button;
    Skin skin;

    SpriteBatch batch;
    Game game;

    public MainMenu(Game game) {
        this.game = game;
    }

    @Override
    public void show() {

        //stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        stage = new Stage();
        font = new BitmapFont(Gdx.files.internal("android/assets/font.fnt"),false);
        style = new Label.LabelStyle(font, Color.BLACK);

        label = new Label("Dongeons & Dragon game ", style);
        label.setPosition((Gdx.graphics.getWidth() / 2) - (label.getWidth() / 2), Gdx.graphics.getHeight()- 30);

        stage.addActor(label);

        skin  = new Skin();
        buttonAtlas = new TextureAtlas("android/assets/buttons/button.pack");
        skin.addRegions(buttonAtlas);

        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = skin.getDrawable("button");
        buttonStyle.over = skin.getDrawable("buttonpressed");
        buttonStyle.down = skin.getDrawable("buttonpressed");
        buttonStyle.font = font;

        button = new TextButton("PLAY", buttonStyle);
        button.setWidth(Gdx.graphics.getWidth() / 3 );
        button.setHeight(Gdx.graphics.getHeight() / 6);
        button.setPosition((Gdx.graphics.getWidth() / 2 ) - button.getWidth() / 2 , (Gdx.graphics.getHeight() / 2 ) - button.getHeight() / 2);

        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);

        button.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new PlayScreen(game));
                return true;
            }
        });

        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();

        batch.begin();
        stage.draw();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
