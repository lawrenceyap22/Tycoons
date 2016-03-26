package ph.intrepidstream.tycoons.gameobjects;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class ImageLabel extends Group {

    private Image icon;
    private Label textLabel;
    private Label.LabelStyle style;

    public ImageLabel(TextureAtlas.AtlasRegion iconRegion, String text, BitmapFont font, boolean isTextRightAligned) {
        icon = new Image(new TextureRegion(iconRegion));
        style = new Label.LabelStyle();
        style.font = font;
        textLabel = new Label(text, style);

        if (isTextRightAligned) {
            textLabel.setPosition(icon.getRight() + 30, 0);
        } else {
            icon.setPosition(textLabel.getRight() + 30, 0);
        }

        addActor(icon);
        addActor(textLabel);
    }

    public void setText(String text) {
        textLabel.setText(text);
    }

    public String getText() {
        return textLabel.getText().toString();
    }


}
