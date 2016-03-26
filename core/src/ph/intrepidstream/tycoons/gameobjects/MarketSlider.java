package ph.intrepidstream.tycoons.gameobjects;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MarketSlider extends Group {

    private Image background;
    private Image knob;
    private int maxBuy;
    private int maxSell;
    private boolean isBuy;
    private int value;
    private TextureRegionDrawable buyKnobDrawable;
    private TextureRegionDrawable sellKnobDrawable;

    public MarketSlider(int maxBuy, int maxSell, TextureAtlas.AtlasRegion backgroundAtlasRegion, TextureAtlas.AtlasRegion buyKnobAtlasRegion, TextureAtlas.AtlasRegion sellKnobAtlasRegion) {
        this.maxBuy = maxBuy;
        this.maxSell = maxSell;
        background = new Image(backgroundAtlasRegion);
        buyKnobDrawable = new TextureRegionDrawable(buyKnobAtlasRegion);
        sellKnobDrawable = new TextureRegionDrawable(sellKnobAtlasRegion);
        background.setTouchable(Touchable.disabled);
        setSize(background.getWidth(), background.getHeight());
        addActor(background);
        initKnob();
    }

    private void initKnob() {
        knob = new Image(buyKnobDrawable);
        knob.addListener(new ClickListener() {
            float diffX;

            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                diffX = x;
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y,
                                     int pointer) {
                super.touchDragged(event, x, y, pointer);
                knob.setX(MathUtils.clamp(knob.getX() + x - diffX, 0,
                        getWidth() - knob.getWidth()));
                isBuy = knob.getX() >= getWidth() / 2 - knob.getWidth() / 2;
                knob.setDrawable(isBuy ? buyKnobDrawable : sellKnobDrawable);
                float calculatedValue = ((knob.getX() + knob.getWidth() / 2) - getWidth() / 2) / (getWidth() / 2 - knob.getWidth() / 2) * (isBuy ? maxBuy : maxSell);
                value = isBuy ? MathUtils.ceilPositive(calculatedValue) : MathUtils.floorPositive(calculatedValue);
            }

        });
        knob.setX(getWidth() / 2 - knob.getWidth() / 2);
        addActor(knob);
    }

    public void addListener(ClickListener listener) {
        knob.addListener(listener);
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setMaxBuy(int maxBuy) {
        this.maxBuy = maxBuy;
    }

    public void setMaxSell(int maxSell) {
        this.maxSell = maxSell;
    }

    public int getValue() {
        return value;
    }

}
