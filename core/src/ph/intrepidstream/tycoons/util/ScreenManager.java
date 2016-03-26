package ph.intrepidstream.tycoons.util;

import com.badlogic.gdx.Screen;
import ph.intrepidstream.tycoons.Tycoons;
import ph.intrepidstream.tycoons.screens.AbstractScreen;
import ph.intrepidstream.tycoons.screens.ScreenEnum;

public class ScreenManager {

    private static ScreenManager instance = null;

    private Tycoons game;

    private ScreenManager() {
    }

    /**
     * @return the instance
     */
    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    /**
     * Call this during the creation of the game
     *
     * @param game the game
     */
    public void initialize(Tycoons game) {
        this.game = game;
    }

    /**
     * Show screen
     *
     * @param screen screen to be shown, the previous screen will not be disposed
     * @see #showScreen(ScreenEnum, boolean)
     */
    public void showScreen(ScreenEnum screen) {
        showScreen(screen, false);
    }

    /**
     * Show screen
     *
     * @param screen               screen to be shown
     * @param disposeCurrentScreen if the current screen should be disposed
     */
    public void showScreen(ScreenEnum screen, boolean disposeCurrentScreen) {
        Screen currentScreen = game.getScreen();
        AbstractScreen newScreen = screen.getScreen(game);

        if (currentScreen == null || currentScreen != newScreen) {
            game.setScreen(newScreen);

            if (disposeCurrentScreen && currentScreen != null) {
                currentScreen.dispose();
            }
        }
    }

    /**
     * Loads the screen for the first time
     *
     * @param screen screen to be loaded
     */
    public void loadScreen(ScreenEnum screen) {
        screen.getScreen(game);
    }

    /**
     * Loads multiple screens for the first time
     *
     * @param screens screens to be loaded
     */
    public void loadScreens(ScreenEnum... screens) {
        for (ScreenEnum screen : screens) {
            loadScreen(screen);
        }
    }

}
