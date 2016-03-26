package ph.intrepidstream.tycoons.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ph.intrepidstream.tycoons.Tycoons;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 270;
        config.height = 480;
        new LwjglApplication(new Tycoons(), config);
    }
}
