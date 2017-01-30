package com.chaowang.ddgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy {

    private Vector2 position;
    Texture enemyTexture;
    Rectangle bounds;
    TextureRegion[]  frames;
    TextureRegion currentFrame;
    String movement;
    Player player;

    public Enemy(Vector2 position, Player player){
        enemyTexture = new Texture(Gdx.files.internal("android/assets/enemy.png"));
        this.position =  position;
        bounds = new Rectangle(position.x, position.y, 25, 25);
        this.player = player;
    }

    public void update(){
        bounds = new Rectangle(position.x, position.y, 25 ,25 );
        if(player.getPosition().x > position.x) {
            position.x++;
        }
        else{
            position.x--;
        }

        if(player.getPosition().y > position.y) {
            position.y++;
        }
        else{
            position.y--;
        }

    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Texture getEnemyTexture() {
        return enemyTexture;
    }

    public void setEnemyTexture(Texture enemyTexture) {
        this.enemyTexture = enemyTexture;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }
}
