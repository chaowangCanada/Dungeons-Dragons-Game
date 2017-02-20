package Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ExitDoor extends Door{

    Texture exitDoor;

    public ExitDoor(Vector2 position, Vector2 size){
        super(position, size);
        exitDoor = new Texture(Gdx.files.internal("android/assets/map/exitDoor.png"));
    }

    public ExitDoor(Vector2 position) {
        super(position);
        exitDoor = new Texture(Gdx.files.internal("android/assets/map/exitDoor.png"));
    }

    public void draw(SpriteBatch batch){

        batch.draw(exitDoor, position.x, position.y, size.x, size.y);
    }

}
