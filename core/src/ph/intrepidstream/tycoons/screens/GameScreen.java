package ph.intrepidstream.tycoons.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import ph.intrepidstream.tycoons.Tycoons;
import ph.intrepidstream.tycoons.assets.UIImage;
import ph.intrepidstream.tycoons.gameobjects.ImageLabel;
import ph.intrepidstream.tycoons.gameobjects.ScreenTabs;

public abstract class GameScreen extends AbstractScreen {

    protected TextureAtlas uiAtlas;
    protected BitmapFont font;
    private static Button settings;
    private static ScreenTabs screenTabs = null;
    private ImageLabel moneyLabel;

    public GameScreen(final Tycoons game) {
        super(game);
    }

    @Override
    protected void initActors() {
        uiAtlas = game.getAssetManager().get(UIImage.ATLAS_FILE, TextureAtlas.class);
        font = game.getAssetManager().get("font/tycoons.fnt", BitmapFont.class);
        initBG();
        initTabBG();
        initScreenTabs();
        initLabels();
    }

    private void initBG() {
        Image mainBG = new Image(uiAtlas.findRegion(UIImage.BG_ALL));
        mainBG.setTouchable(Touchable.disabled);
        stage.addActor(mainBG);
    }

    private void initTabBG() {
        Image tabBG = new Image(uiAtlas.findRegion(UIImage.BG_TAB));
        tabBG.setPosition(VIEWPORT_WIDTH / 2 - tabBG.getWidth() / 2,
                VIEWPORT_HEIGHT - tabBG.getHeight());
        tabBG.setTouchable(Touchable.disabled);
        stage.addActor(tabBG);
    }

    private void initScreenTabs() {
        if (screenTabs == null) {
            screenTabs = new ScreenTabs();
            screenTabs.addTab(ScreenEnum.HOME_SCREEN, uiAtlas.findRegion(UIImage.BUTTON_HOME_TAB_ACTIVE), uiAtlas.findRegion(UIImage.BUTTON_HOME_TAB_INACTIVE));
            screenTabs.addTab(ScreenEnum.PROPERTY_SCREEN, uiAtlas.findRegion(UIImage.BUTTON_PROPERTIES_TAB_ACTIVE), uiAtlas.findRegion(UIImage.BUTTON_PROPERTIES_TAB_INACTIVE));
            screenTabs.addTab(ScreenEnum.UPGRADE_SCREEN, uiAtlas.findRegion(UIImage.BUTTON_UPGRADES_TAB_ACTIVE), uiAtlas.findRegion(UIImage.BUTTON_UPGRADES_TAB_INACTIVE));
            screenTabs.addTab(ScreenEnum.MARKET_SCREEN, uiAtlas.findRegion(UIImage.BUTTON_MARKET_TAB_ACTIVE), uiAtlas.findRegion(UIImage.BUTTON_MARKET_TAB_INACTIVE));
            screenTabs.setPosition(VIEWPORT_WIDTH / 2 - screenTabs.getWidth() / 2, 0);
        }
    }

    private void initLabels() {
        moneyLabel = new ImageLabel(uiAtlas.findRegion(UIImage.ICON_MONEY), "1234", font, true);
        moneyLabel.setPosition(50, 1800);
        stage.addActor(moneyLabel);
    }

    @Override
    public void show() {
        super.show();
        stage.addActor(screenTabs);
    }

    @Override
    public void dispose() {
        super.dispose();
        screenTabs = null;
    }
}
