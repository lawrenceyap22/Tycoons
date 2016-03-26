package ph.intrepidstream.tycoons.screens;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import ph.intrepidstream.tycoons.Tycoons;
import ph.intrepidstream.tycoons.assets.UIImage;
import ph.intrepidstream.tycoons.gameobjects.Coin;
import ph.intrepidstream.tycoons.gameobjects.GameProgressBar;

public class HomeScreen extends GameScreen implements Coin.CoinClickedEvent {

    private static HomeScreen instance = null;

    private static final int NUMBER_OF_CLICKS_FOR_TECH_RESEARCH = 200;
    private static final int MAX_COMBO_MULTIPLIER = 5;
    private static final int NUMBER_OF_CLICKS_FOR_COMBO = 10;
    private static final float COMBO_TIME_EXPIRE = 2f;

    private Coin coin;
    private GameProgressBar researchBar;
    private int currentComboMultiplier;
    private int nCoinClicks;
    private float comboTimeDuration;

    private HomeScreen(final Tycoons game) {
        super(game);
        currentComboMultiplier = 1;
        comboTimeDuration = COMBO_TIME_EXPIRE;
    }

    public static HomeScreen getInstance(final Tycoons game) {
        if (instance == null) {
            instance = new HomeScreen(game);
        }
        return instance;
    }

    @Override
    protected void initActors() {
        super.initActors();
        initBG();
        initCoin();
        initResearchBar();
    }

    private void initBG() {
        Image homeBG = new Image(uiAtlas.findRegion(UIImage.BG_HOME_TAB));
        homeBG.setPosition(VIEWPORT_WIDTH / 2 - homeBG.getWidth() / 2,
                VIEWPORT_HEIGHT / 2 - homeBG.getHeight() / 2);
        homeBG.setTouchable(Touchable.disabled);
        stage.addActor(homeBG);
    }

    private void initCoin() {
        coin = new Coin(this, uiAtlas.findRegion(UIImage.BUTTON_COIN_DEFAULT), uiAtlas.findRegion(UIImage.BUTTON_COIN_CLICKED));
        coin.setPosition(VIEWPORT_WIDTH / 2 - coin.getWidth() / 2 - 90, VIEWPORT_HEIGHT / 2 - coin.getHeight() * 2 / 3 + 100);
        stage.addActor(coin);
    }

    private void initResearchBar() {
        researchBar = new GameProgressBar(200, 1, uiAtlas.findRegion(UIImage.PROGRESS_RESEARCH_EMPTY),
                uiAtlas.findRegion(UIImage.PROGRESS_RESEARCH_BAR),
                uiAtlas.findRegion(UIImage.PROGRESS_RESEARCH_HIGHLIGHT), true);
        researchBar.setPosition(coin.getRight() - 80, VIEWPORT_HEIGHT / 2
                - researchBar.getHeight() / 2 - 50);
        researchBar.setTouchable(Touchable.disabled);
        stage.addActor(researchBar);
    }

    @Override
    public void performAction() {
        //TODO
    }
}
