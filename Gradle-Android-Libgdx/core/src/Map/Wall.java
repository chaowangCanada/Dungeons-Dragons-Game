package Map;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.chaowang.ddgame.PublicParameter;

public class Wall {

    Vector2 position, size;
    Texture wall;
    Rectangle bounds;

    public Wall(Vector2 position, Vector2 size){
        this.position = position;
        this.size = size;
        bounds = new Rectangle(position.x, position.y, size.x , size.y );
        wall = new Texture(Gdx.files.internal("android/assets//map/wall.png"));
    }

    public Wall(Vector2 position) {
        this(position, new Vector2(PublicParameter.mapPixelSize,PublicParameter.mapPixelSize));
    }

    public void update(){
        bounds.set(position.x, position.y, size.x , size.y);

    }

    public void draw(SpriteBatch batch){

        batch.draw(wall, position.x, position.y, size.x, size.y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

}
