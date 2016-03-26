package ph.intrepidstream.tycoons.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Coin extends Image {

    private TextureRegionDrawable defaultCoinDrawable;
    private TextureRegionDrawable clickedCoinDrawable;
    private Circle clickBounds;
    private CoinClickedEvent clickedEvent;

    public Coin(CoinClickedEvent clickedEvent, TextureAtlas.AtlasRegion defaultAtlasRegion, TextureAtlas.AtlasRegion clickedAtlasRegion) {
        this.clickedEvent = clickedEvent;
        defaultCoinDrawable = new TextureRegionDrawable(defaultAtlasRegion);
        clickedCoinDrawable = new TextureRegionDrawable(clickedAtlasRegion);
        setDrawable(defaultCoinDrawable);
        setSize(defaultCoinDrawable.getRegion().getRegionWidth(), defaultCoinDrawable.getRegion().getRegionHeight());
        addOnClickListener();
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        clickBounds = new Circle(new Vector2(getWidth() / 2, getHeight() / 2), 354);
    }

    /**
     * Adds ClickListener
     */
    private void addOnClickListener() {
        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                if (clickBounds.contains(x, y)) {
                    setDrawable(clickedCoinDrawable);
                    return super.touchDown(event, x, y, pointer, button);
                }
                return false;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                setDrawable(defaultCoinDrawable);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (clickBounds.contains(x, y)) {
                    clickedEvent.performAction();
                }
            }

        });
    }

    public interface CoinClickedEvent {
        void performAction();
    }


}
