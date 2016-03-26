package ph.intrepidstream.tycoons.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameProgressBar extends Actor {

    private TextureRegion backgroundTextureRegion;
    private TextureRegion progressTextureRegion;
    private TextureRegion highlightTextureRegion;
    private boolean isVertical;
    private int value;
    private int max;
    private int increment;
    private int progressLength;
    private BitmapFont font;
    private String text;
    private GlyphLayout glyphLayout;

    public GameProgressBar(int maxValue, int increment,
                           TextureAtlas.AtlasRegion backgroundAtlasRegion, TextureAtlas.AtlasRegion progressAtlasRegion, TextureAtlas.AtlasRegion highlightAtlasRegion, boolean isVertical) {
        if (maxValue <= 0)
            throw new IllegalArgumentException("max must be > 0");
        if (increment <= 0)
            throw new IllegalArgumentException("increment must be > 0");
        this.max = maxValue;
        this.increment = increment;
        this.backgroundTextureRegion = new TextureRegion(backgroundAtlasRegion);
        this.progressTextureRegion = new TextureRegion(progressAtlasRegion);
        this.isVertical = isVertical;
        this.progressLength = isVertical ? progressAtlasRegion.getRegionHeight() : progressAtlasRegion
                .getRegionWidth();
        this.highlightTextureRegion = highlightAtlasRegion != null ? new TextureRegion(highlightAtlasRegion) : highlightAtlasRegion;
        setBounds(0, 0, backgroundAtlasRegion.getRegionWidth(),
                backgroundAtlasRegion.getRegionHeight());
        setValue(0);
    }

    public void setValue(int value) {
        if (value % increment != 0)
            throw new IllegalArgumentException(
                    "value must be a factor of increment");
        this.value = MathUtils.clamp(value, 0, max);
        if (isVertical) {
            progressTextureRegion.setV(progressTextureRegion.getV2() - (value * 1.0f / max)
                    * progressLength / progressTextureRegion.getTexture().getHeight());
        } else {
            progressTextureRegion.setU2(progressTextureRegion.getU() + (value * 1.0f / max)
                    * progressLength / progressTextureRegion.getTexture().getWidth());
        }
    }

    public int getValue() {
        return value;
    }

    public boolean isFull() {
        if (value == max)
            return true;
        return false;
    }

    public void setText(String text, BitmapFont font) {
        if (font == null) {
            throw new IllegalArgumentException("font cannot be null");
        }

        if (glyphLayout == null) {
            glyphLayout = new GlyphLayout();
        }
        this.font = font;
        this.text = text;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(backgroundTextureRegion, getX(), getY());
        batch.draw(progressTextureRegion, getX(), getY());
        if (highlightTextureRegion != null)
            batch.draw(highlightTextureRegion, getX(), getY());

        if (text != null && !text.isEmpty()) {
            font.setColor(Color.WHITE);
            glyphLayout.setText(font, text);
            font.draw(batch, glyphLayout, getX() + getWidth() / 2 - glyphLayout.width / 2,
                    getY() + getHeight() / 2 + glyphLayout.height / 2);
        }
    }
}
