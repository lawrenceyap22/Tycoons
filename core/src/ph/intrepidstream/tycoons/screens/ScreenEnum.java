package ph.intrepidstream.tycoons.screens;

import ph.intrepidstream.tycoons.Tycoons;

public enum ScreenEnum {

    SPLASH_SCREEN {
        @Override
        public AbstractScreen getScreen(Tycoons game) {
            return SplashScreen.getInstance(game);
        }
    }, LOADING_SCREEN {
        @Override
        public AbstractScreen getScreen(Tycoons game) {
            return LoadingScreen.getInstance(game);
        }
    }, HOME_SCREEN {
        @Override
        public AbstractScreen getScreen(Tycoons game) {
            return HomeScreen.getInstance(game);
        }
    }, PROPERTY_SCREEN {
        @Override
        public AbstractScreen getScreen(Tycoons game) {
            return PropertyScreen.getInstance(game);
        }
    }, UPGRADE_SCREEN {
        @Override
        public AbstractScreen getScreen(Tycoons game) {
            return UpgradeScreen.getInstance(game);
        }
    }, MARKET_SCREEN {
        @Override
        public AbstractScreen getScreen(Tycoons game) {
            return MarketScreen.getInstance(game);
        }
    };

    public abstract AbstractScreen getScreen(Tycoons game);

}
