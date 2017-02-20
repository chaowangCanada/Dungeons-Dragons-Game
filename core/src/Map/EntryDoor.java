package Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class EntryDoor extends Door{

    Texture entryDoor;

    public EntryDoor(Vector2 position){
        super(position);
        entryDoor = new Texture(Gdx.files.internal("android/assets/map/entryDoor.png"));
    }

    public EntryDoor(Vector2 position, Vector2 size){
        super(position, size);
        entryDoor = new Texture(Gdx.files.internal("android/assets/map/entryDoor.png"));
    }

    public void draw(SpriteBatch batch){

        batch.draw(entryDoor, position.x, position.y, size.x, size.y);
    }

}
