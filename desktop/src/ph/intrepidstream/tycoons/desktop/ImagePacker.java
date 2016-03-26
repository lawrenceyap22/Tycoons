package ph.intrepidstream.tycoons.desktop;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

public class ImagePacker {

    public static void main(String[] args) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxHeight = 1024;
        settings.maxWidth = 1024;
        TexturePacker.process(settings, "images/properties", "android/assets/images", "properties-packed");

        settings.maxHeight = 2048;
        settings.maxWidth = 2048;
        TexturePacker.process(settings, "images/ui", "android/assets/images", "ui-packed");
    }

}
