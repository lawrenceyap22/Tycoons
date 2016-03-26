package ph.intrepidstream.tycoons.screens;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.ObjectMap;
import ph.intrepidstream.tycoons.Tycoons;
import ph.intrepidstream.tycoons.assets.PropertyImage;
import ph.intrepidstream.tycoons.assets.UIImage;
import ph.intrepidstream.tycoons.gameobjects.GameProgressBar;
import ph.intrepidstream.tycoons.gameobjects.Property;
import ph.intrepidstream.tycoons.gameobjects.Resource;

public class PropertyScreen extends GameScreen {

    private static PropertyScreen instance = null;

    private TextureAtlas propertyAtlas;
    private Table propertiesTable;
    private GameProgressBar resourceCollectProgressBar;
    private Group requiresGroup;
    private Group producesGroup;
    private Image sellPropertyImage;
    private Array<Property> properties;

    private PropertyScreen(final Tycoons game) {
        super(game);
    }

    public static PropertyScreen getInstance(final Tycoons game) {
        if (instance == null) {
            instance = new PropertyScreen(game);
        }
        return instance;
    }

    @Override
    protected void initActors() {
        super.initActors();
        initProperties();
        initPropertiesTable();
        initDivider();
        initResourceCollect();
        initRequires();
        initProduces();
        initSellProperty();
    }

    private void initProperties() {
        propertyAtlas = game.getAssetManager().get(PropertyImage.ATLAS_FILE, TextureAtlas.class);
        properties = new Array<>();
        properties.add(new Property("Coal Mine", 1, 100, propertyAtlas.findRegion(PropertyImage.COAL_MINE)));
        properties.add(new Property("Cattle Farm", 1, 10, propertyAtlas.findRegion(PropertyImage.CATTLE_FARM)));
        properties.add(new Property("Bakery", 1, 1000, propertyAtlas.findRegion(PropertyImage.BAKERY)));
        properties.add(new Property("Bar", 1, 500, propertyAtlas.findRegion(PropertyImage.BAR)));
        properties.add(new Property("Fruit Orchard", 1, 300, propertyAtlas.findRegion(PropertyImage.FRUIT_ORCHARD)));
        properties.add(new Property("Green House", 1, 400, propertyAtlas.findRegion(PropertyImage.GREEN_HOUSE)));
        properties.add(new Property("Metal Ore Mine", 1, 200, propertyAtlas.findRegion(PropertyImage.METAL_ORE_MINE)));

        for (final Property property : properties) {
            property.initProductionProgressBar(uiAtlas.findRegion(UIImage.PROGRESS_PROPERTY_TIME_EMPTY), uiAtlas.findRegion(UIImage.PROGRESS_PROPERTY_TIME_FULL));
            property.getProductionProgressBar().addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (property.getTimeRemainingString().equals("COLLECT")) {
                        ArrayMap<Resource, Integer> products = property.getProducts();
                        ArrayMap.Entries<Resource, Integer> productEntries = products.entries();
                        while (productEntries.hasNext()) {
                            ObjectMap.Entry<Resource, Integer> product = productEntries.next();
                            Resource resource = product.key;
                            int quantity = product.value;
                            if (resource != Resource.CASH) {
                                //TODO resourcesDrawer.setResource(resourcesDrawer.getResource(resource) + quantity, resource);
                            } else {
                                //TODO game.state.calculateMoney(quantity);
                            }
                        }
                    }
                }
            });
            property.registerStartTime();
        }
    }

    private void initPropertiesTable() {
        propertiesTable = new Table();
        ScrollPane scrollPane = new ScrollPane(propertiesTable);
        scrollPane.setBounds(VIEWPORT_WIDTH / 2 - 472,
                VIEWPORT_HEIGHT / 2 - 246, 944, 1036);
        // size based on building images + pads
        propertiesTable.setSize(944, 1554);
        propertiesTable.top();

        int property_ctr = 0;
        GameProgressBar leftPropertyProgressBar = null;
        for (Property property : properties) {
            //add property at the left side
            if (property_ctr % 2 == 0) {
                if (property_ctr != 0) {
                    propertiesTable.row();
                }
                propertiesTable.add(new Image(property.getTexture())).pad(20, 1, 20, 1);
                propertiesTable.add().width(68);
                leftPropertyProgressBar = property.getProductionProgressBar();
            }
            //add property at the right side and add the progress bars for left and right side
            else {
                propertiesTable.add(new Image(property.getTexture())).pad(20, 1, 20, 1);
                propertiesTable.row();
                propertiesTable.add(leftPropertyProgressBar).pad(0,
                        0, 20, 0);
                propertiesTable.add().width(68);
                propertiesTable.add(property.getProductionProgressBar()).padBottom(20);
            }
            property_ctr++;
        }
        if (property_ctr % 2 == 1) {
            propertiesTable.row();
            propertiesTable.add(leftPropertyProgressBar).pad(0,
                    0, 20, 0);
        }
        stage.addActor(scrollPane);
    }

    private void initDivider() {
        Image divider = new Image(uiAtlas.findRegion(UIImage.DIVIDER_PROPERTY_MARKET));
        divider.setPosition(VIEWPORT_WIDTH / 2 - divider.getWidth() / 2,
                VIEWPORT_HEIGHT / 2 - divider.getHeight() - 266);
        divider.setTouchable(Touchable.disabled);
        stage.addActor(divider);
    }

    private void initResourceCollect() {
        resourceCollectProgressBar = new GameProgressBar(10, 1,
                uiAtlas.findRegion(UIImage.PROGRESS_RESOURCE_COLLECTED_EMPTY),
                uiAtlas.findRegion(UIImage.PROGRESS_RESOURCE_COLLECTED_FULL),
                uiAtlas.findRegion(UIImage.PROGRESS_RESOURCE_COLLECTED_HIGHLIGHT), false);
        resourceCollectProgressBar.setPosition(
                VIEWPORT_WIDTH / 2 - resourceCollectProgressBar.getWidth() / 2,
                VIEWPORT_HEIGHT / 2 - resourceCollectProgressBar.getHeight() - 302);
        resourceCollectProgressBar.setTouchable(Touchable.disabled);
        stage.addActor(resourceCollectProgressBar);
    }

    private void initRequires() {
        requiresGroup = new Group();
        Image bg = new Image(uiAtlas.findRegion(UIImage.PANEL_REQUIRED_RESOURCE));
        requiresGroup.setBounds(50, resourceCollectProgressBar.getY() - bg.getHeight() - 20,
                bg.getWidth(), bg.getHeight());
        requiresGroup.addActor(bg);
        requiresGroup.setTouchable(Touchable.disabled);
        stage.addActor(requiresGroup);
    }

    private void initProduces() {
        producesGroup = new Group();
        Image bg = new Image(uiAtlas.findRegion(UIImage.PANEL_PRODUCED_RESOURCE));
        producesGroup.setBounds(requiresGroup.getRight() + 10, resourceCollectProgressBar.getY()
                - bg.getHeight() - 20, bg.getWidth(), bg.getHeight());
        producesGroup.addActor(bg);
        producesGroup.setTouchable(Touchable.disabled);
        stage.addActor(producesGroup);
    }

    private void initSellProperty() {
        final TextureRegionDrawable sellPropertyImageClicked = new TextureRegionDrawable(
                uiAtlas.findRegion(UIImage.BUTTON_SELL_PROPERTY_CLICKED));
        final TextureRegionDrawable sellPropertyImageNeutral = new TextureRegionDrawable(
                uiAtlas.findRegion(UIImage.BUTTON_SELL_PROPERTY_DEFAULT));
        sellPropertyImage = new Image(sellPropertyImageNeutral);
        sellPropertyImage.addListener(new ClickListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                sellPropertyImage.setDrawable(sellPropertyImageClicked);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchUp(InputEvent event, float x, float y,
                                int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
                sellPropertyImage.setDrawable(sellPropertyImageNeutral);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                //TODO sell property
            }

        });
        sellPropertyImage.setPosition(880, 370);
        stage.addActor(sellPropertyImage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        for (Property property : properties) {
            GameProgressBar productionProgress = property.getProductionProgressBar();
            if (property.getShouldStartProduction()) {
                property.setTimeElapsed();
                productionProgress.setValue(MathUtils.ceil(property.getTimeElapsed()));
                productionProgress.setText(property.getTimeRemainingString(), font);
            }
        }

    }
}
