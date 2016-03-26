package ph.intrepidstream.tycoons.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import ph.intrepidstream.tycoons.Tycoons;
import ph.intrepidstream.tycoons.assets.ScreenImage;
import ph.intrepidstream.tycoons.util.ScreenManager;

public class SplashScreen extends AbstractScreen {

    private static SplashScreen instance = null;

    private Image splashImage;

    private SplashScreen(final Tycoons game) {
        super(game);
    }

    public static SplashScreen getInstance(final Tycoons game) {
        if (instance == null) {
            instance = new SplashScreen(game);
        }
        return instance;
    }

    @Override
    protected void initActors() {
        loadScreenImages();
        splashImage = new Image(game.getAssetManager().get(ScreenImage.SPLASH_SCREEN, Texture.class));
        stage.addActor(splashImage);
    }

    @Override
    public void show() {
        super.show();
        splashImage.addAction(Actions.sequence(Actions.fadeIn(0.75f), Actions.delay(1.75f), Actions.fadeOut(0.75f), new Action() {
            @Override
            public boolean act(float delta) {
                ScreenManager.getInstance().showScreen(ScreenEnum.LOADING_SCREEN, true);
                return true;
            }
        }));
    }

    private void loadScreenImages() {
        AssetManager assetManager = game.getAssetManager();
        assetManager.load(ScreenImage.TITLE_SCREEN, Texture.class);
        assetManager.load(ScreenImage.SPLASH_SCREEN, Texture.class);
        assetManager.finishLoading();
    }
}