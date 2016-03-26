package ph.intrepidstream.tycoons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ph.intrepidstream.tycoons.screens.ScreenEnum;
import ph.intrepidstream.tycoons.util.ScreenManager;

public class Tycoons extends Game {
    public static final boolean DEV_MODE = true;
    private final String TAG = Tycoons.class.getSimpleName();

    private SpriteBatch batch;
    private AssetManager assetManager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        assetManager = new AssetManager();
        ScreenManager.getInstance().initialize(this);
        ScreenManager.getInstance().showScreen(ScreenEnum.SPLASH_SCREEN);
        Gdx.app.log(TAG, "Game created");
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }
}
