package util;

import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Created by Chao on 12/02/2017.
 */

public class LocalImageButton extends ImageButton {
    public LocalImageButton(Skin skin) {
        super(skin);
    }

    public LocalImageButton(Skin skin, String styleName) {
        super(skin, styleName);
    }

    public LocalImageButton(ImageButtonStyle style) {
        super(style);
    }

    public LocalImageButton(Drawable imageUp) {
        super(imageUp);
    }

    public LocalImageButton(Drawable imageUp, Drawable imageDown) {
        super(imageUp, imageDown);
    }

    public LocalImageButton(Drawable imageUp, Drawable imageDown, Drawable imageChecked) {
        super(imageUp, imageDown, imageChecked);
    }

    public boolean addLocalListener(LocalClickListener listener, int index) {
        listener.setIndex(index);
        return super.addListener(listener);
    }
}
