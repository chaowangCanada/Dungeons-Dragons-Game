package com.chaowang.ddgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MapEditorScreen implements Screen{
    Game game;

    Stage stage;
    SpriteBatch batch;

    public MapEditorScreen(Game game) {
        this.game = game;
    }
    
	@Override
	public void show() {
		// TODO Auto-generated method stub
        stage = new Stage(new ScreenViewport());

        batch = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.input.setInputProcessor(stage);
        stage.act();

        batch.begin();
        stage.draw();
        stage.setDebugAll(true);
        batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
