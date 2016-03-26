package ph.intrepidstream.tycoons.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import ph.intrepidstream.tycoons.Tycoons;
import ph.intrepidstream.tycoons.assets.PropertyImage;
import ph.intrepidstream.tycoons.assets.ScreenImage;
import ph.intrepidstream.tycoons.assets.UIImage;
import ph.intrepidstream.tycoons.util.ScreenManager;

public class LoadingScreen extends AbstractScreen {

    private static LoadingScreen instance = null;

    private LoadingScreen(final Tycoons game) {
        super(game);
    }

    public static LoadingScreen getInstance(final Tycoons game) {
        if (instance == null) {
            instance = new LoadingScreen(game);
        }
        return instance;
    }

    @Override
    protected void initActors() {
        Image titleImage = new Image(game.getAssetManager().get(ScreenImage.TITLE_SCREEN, Texture.class));
        stage.addActor(titleImage);

        loadAssets();
    }

    private void loadAssets() {
        AssetManager assetManager = game.getAssetManager();
        assetManager.load(UIImage.ATLAS_FILE, TextureAtlas.class);
        assetManager.load(PropertyImage.ATLAS_FILE, TextureAtlas.class);
        assetManager.load("font/tycoons.fnt", BitmapFont.class);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (game.getAssetManager().update()) {
            ScreenManager.getInstance().loadScreens(ScreenEnum.HOME_SCREEN, ScreenEnum.PROPERTY_SCREEN, ScreenEnum.UPGRADE_SCREEN, ScreenEnum.MARKET_SCREEN);
            ScreenManager.getInstance().showScreen(ScreenEnum.HOME_SCREEN, true);
        }
    }
}
