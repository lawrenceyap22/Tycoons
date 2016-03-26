package ph.intrepidstream.tycoons.gameobjects;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import ph.intrepidstream.tycoons.screens.ScreenEnum;
import ph.intrepidstream.tycoons.util.ScreenManager;

public class ScreenTabs extends Group {

    private class Tab {
        private Image actor;
        private TextureRegionDrawable active;
        private TextureRegionDrawable inactive;

        public Tab(TextureAtlas.AtlasRegion activeRegion, TextureAtlas.AtlasRegion inactiveRegion) {
            this.active = new TextureRegionDrawable(activeRegion);
            this.inactive = new TextureRegionDrawable(inactiveRegion);
            actor = new Image(inactive);
            addOnClickListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (actor.getDrawable().equals(inactive)) {
                        setActive(true);
                    }
                }
            });
        }

        /**
         * Adds ClickListener to the tab
         *
         * @param listener the listener
         */
        public void addOnClickListener(ClickListener listener) {
            actor.addListener(listener);
        }

        /**
         * Sets if the tab is active
         *
         * @param isActive true if the tab is active
         */
        public void setActive(boolean isActive) {
            actor.setDrawable(isActive ? active : inactive);
        }
    }

    private Array<Tab> tabs;

    public ScreenTabs() {
        tabs = new Array<>();
    }

    /**
     * Adds a tab to the tab group
     *
     * @param screen              the screen to be shown when clicked
     * @param activeAtlasRegion   the texture region when active
     * @param inactiveAtlasRegion the texture region when inactive
     */
    public void addTab(final ScreenEnum screen, TextureAtlas.AtlasRegion activeAtlasRegion, TextureAtlas.AtlasRegion inactiveAtlasRegion) {
        final Tab tab = new Tab(activeAtlasRegion, inactiveAtlasRegion);
        tab.addOnClickListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().showScreen(screen);
                deactivateOtherTabs(tab);
            }
        });
        if (tabs.size > 0) {
            tab.actor.setPosition(tabs.get(tabs.size - 1).actor.getRight(), 0);
        } else {
            tab.actor.setPosition(0, 0);
        }
        setWidth(getWidth() + tab.actor.getWidth());
        tabs.add(tab);
        addActor(tab.actor);

        //Sets the default screen tab to active
        if (screen == ScreenEnum.HOME_SCREEN) {
            tab.setActive(true);
        }
    }

    /**
     * Deactivates other tabs
     *
     * @param currentTab the tab that will remain active
     */
    private void deactivateOtherTabs(Tab currentTab) {
        for (Tab tab : tabs) {
            if (tab != currentTab) {
                tab.setActive(false);
            }
        }
    }


}
