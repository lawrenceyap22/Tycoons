package ph.intrepidstream.tycoons.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import ph.intrepidstream.tycoons.Tycoons;
import ph.intrepidstream.tycoons.assets.UIImage;
import ph.intrepidstream.tycoons.gameobjects.MarketSlider;

public class MarketScreen extends GameScreen {

    private static MarketScreen instance = null;

    private MarketSlider marketSlider;
    private Image actionButton;
    private Table resourceList;
    private TextureRegionDrawable buyResourceDefault;
    private TextureRegionDrawable buyResourceClicked;
    private TextureRegionDrawable sellResourceDefault;
    private TextureRegionDrawable sellResourceClicked;
    private boolean actionButtonTouchedDown;

    private MarketScreen(final Tycoons game) {
        super(game);
    }

    public static MarketScreen getInstance(final Tycoons game) {
        if (instance == null) {
            instance = new MarketScreen(game);
        }
        return instance;
    }

    @Override
    protected void initActors() {
        super.initActors();
        initResourceList();
        initDivider();
        initMarketSlider();
        initActionButton();
    }

    private void initResourceList() {
        TextureRegionDrawable bg = new TextureRegionDrawable(uiAtlas.findRegion(UIImage.RESOURCE_LIST));
        resourceList = new Table();
        resourceList.setBackground(bg);
        ScrollPane scrollPane = new ScrollPane(resourceList);
        scrollPane.setBounds(VIEWPORT_WIDTH / 2 - bg.getRegion().getRegionWidth() / 2, VIEWPORT_HEIGHT / 2 - 246, bg.getRegion().getRegionWidth(), 1036);
        stage.addActor(scrollPane);
    }

    private void initDivider() {
        Image divider = new Image(uiAtlas.findRegion(UIImage.DIVIDER_PROPERTY_MARKET));
        divider.setPosition(VIEWPORT_WIDTH / 2 - divider.getWidth() / 2,
                VIEWPORT_HEIGHT / 2 - divider.getHeight() - 266);
        divider.setTouchable(Touchable.disabled);
        stage.addActor(divider);
    }

    private void initMarketSlider() {
        marketSlider = new MarketSlider(30, 20, uiAtlas.findRegion(UIImage.SLIDER_MARKET), uiAtlas.findRegion(UIImage.SLIDER_MARKET_BUY), uiAtlas.findRegion(UIImage.SLIDER_MARKET_SELL));
        marketSlider.setPosition(VIEWPORT_WIDTH / 2 - marketSlider.getWidth() / 2, 450);
        marketSlider.addListener(new ClickListener() {
            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                super.touchDragged(event, x, y, pointer);
                changeActionButtonDrawable();
            }
        });
        stage.addActor(marketSlider);
    }

    private void initActionButton() {
        buyResourceDefault = new TextureRegionDrawable(uiAtlas.findRegion(UIImage.BUTTON_BUY_RESOURCE_DEFAULT));
        buyResourceClicked = new TextureRegionDrawable(uiAtlas.findRegion(UIImage.BUTTON_BUY_RESOURCE_CLICKED));
        sellResourceDefault = new TextureRegionDrawable(uiAtlas.findRegion(UIImage.BUTTON_SELL_RESOURCE_DEFAULT));
        sellResourceClicked = new TextureRegionDrawable(uiAtlas.findRegion(UIImage.BUTTON_SELL_RESOURCE_CLICKED));
        actionButton = new Image(buyResourceDefault);
        actionButtonTouchedDown = false;
        actionButton.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                actionButtonTouchedDown = true;
                actionButton.setDrawable(marketSlider.isBuy() ? buyResourceClicked : sellResourceClicked);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                actionButtonTouchedDown = false;
                actionButton.setDrawable(marketSlider.isBuy() ? buyResourceDefault : sellResourceDefault);
                super.touchUp(event, x, y, pointer, button);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (marketSlider.isBuy()) {
                    //TODO
                } else {

                }
            }
        });
        actionButton.setPosition(45, 300);
        stage.addActor(actionButton);
    }

    private void changeActionButtonDrawable() {
        if (actionButtonTouchedDown) {
            actionButton.setDrawable(marketSlider.isBuy() ? buyResourceClicked : sellResourceClicked);
        } else {
            actionButton.setDrawable(marketSlider.isBuy() ? buyResourceDefault : sellResourceDefault);
        }
    }
}
