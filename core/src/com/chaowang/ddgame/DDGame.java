package com.chaowang.ddgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class DDGame extends Game {
	private OrthographicCamera camera;

    Game game;


    @Override
	public void create () {
		game = this;
        setScreen(new MainMenu(game));


//		float w = Gdx.graphics.getWidth();
//		float h = Gdx.graphics.getHeight();
//
//		camera = new OrthographicCamera(1, h/w);




    }

	@Override
	public void render () {
        super.render();
	}
	
	@Override
	public void dispose () {
    }
}
