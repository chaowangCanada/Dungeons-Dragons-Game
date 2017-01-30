package com.chaowang.ddgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DDGame extends ApplicationAdapter {
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture mario;
	private Sprite sprite;
    private Vector2 position;
    private Player player;
    private Tree tree, tree1;
    private ShapeRenderer sr;
    private ArrayList<Tree> trees;
    private Iterator<Tree> treeIterator;

    private Enemy enemy;
    private ArrayList<Enemy> enemies;
    private Iterator<Enemy> enemyIterator;

    Stage stage;
    Label label;
    Label.LabelStyle style;
    BitmapFont font;

	@Override
	public void create () {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(1, h/w);
		batch = new SpriteBatch();
        mario = new Texture(Gdx.files.internal("android/assets/Mario.png"));
        sr = new ShapeRenderer();
        tree = new Tree(new Vector2(100, 100),new Vector2(50, 100));
        tree1 = new Tree(new Vector2(200, 100),new Vector2(50, 100));
        trees = new ArrayList<Tree>();
        trees.add(tree);
        trees.add(tree1);

        enemies = new ArrayList<Enemy>();

        if(Gdx.files.local("player.dat").exists()){
            try{
                player = new Player(new Vector2(Gdx.graphics.getWidth() /2 , Gdx.graphics.getHeight() /2 ), "android/assets/Mario.png");
                player.setPosition(Player.readPlayer());
            } catch (ClassNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
            System.out.println("Player exist, reading file");
        }
        else{
            player = new  Player(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2), "android/assets/Mario.png");
            try{
                Player.savePlayer(player);
            } catch (IOException e){
                e.printStackTrace();
            }

            System.out.println("Player does not exist, create player and save player ");
        }
        enemy = new Enemy(new Vector2(50, 50), player);
        enemies.add(enemy);

        //stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        stage = new Stage();
        font = new BitmapFont(Gdx.files.internal("android/assets/font.fnt"),false);
        style = new Label.LabelStyle(font, Color.BLACK);

        label = new Label("Insert Text here", style);
        label.setPosition(10,Gdx.graphics.getHeight()- 20);

        stage.addActor(label);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player.update();
        // for Android app
//        if(Gdx.input.isTouched()){
//            System.out.println("Application clicked");
//        }
//        System.out.println("mouse x : "+ Gdx.input.getX() + "mouse y : "+ Gdx.input.getY());
        tree.update();
        stage.act();

		batch.begin();
        batch.draw(player.getCurrentFrame(),player.getPosition().x, player.getPosition().y );
		//batch.draw(player.getTexture(), player.getPosition().x, player.getPosition().y);  // if player contains texture (not serializable)
        //tree.draw(batch);  //draw 1 tree

        treeIterator = trees.iterator();
        while(treeIterator.hasNext()){
            Tree cur = treeIterator.next();
            cur.draw(batch);
            cur.update();
            if(player.getBounds().overlaps(cur.getBounds())){
                player.reAdjust();
            }
        }

        enemyIterator = enemies.iterator();
        while(enemyIterator.hasNext()){
            Enemy cur = enemyIterator.next();

            cur.update();
            batch.draw(cur.getEnemyTexture(),cur.getPosition().x,cur.getPosition().y, 25, 25);

            if(player.getBounds().overlaps(cur.getBounds())){
                System.out.println("Player hit!");
            }
        }

        stage.draw();
		batch.end();

        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(Color.BLUE);
        sr.rect(player.getPosition().x, player.getPosition().y, player.getCurrentFrame().getRegionWidth(), player.getCurrentFrame().getRegionHeight());
        sr.setColor(Color.RED);
        sr.rect(tree.getPosition().x, tree.getPosition().y, tree.getSize().x, tree.getSize().y);
        sr.end();
	}
	
	@Override
	public void dispose () {
        try {
            Player.savePlayer(player);
        } catch (IOException e){
            e.printStackTrace();
        }
	}
}
