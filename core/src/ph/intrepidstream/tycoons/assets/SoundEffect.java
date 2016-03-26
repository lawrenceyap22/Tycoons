package ph.intrepidstream.tycoons.assets;

public enum SoundEffect {

    COIN_TAP("");

    private String fileName;

    SoundEffect(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

}