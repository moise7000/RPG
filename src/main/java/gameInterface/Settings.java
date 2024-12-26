package gameInterface;



public class Settings {
    private static Settings instance;

    private double musicVolume = 50.0;
    private double soundEffectsVolume = 50.0;
    private String resolution = "1920x1080";
    private boolean fullscreen = true;
    private String graphicsQuality = "High";
    private double gameSpeed = 1.0;
    private String language = "English";
    private boolean showFPS = false;

    private Settings() {
        // Private constructor to enforce singleton pattern
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    // Getters and setters
    public double getMusicVolume() { return musicVolume; }
    public void setMusicVolume(double musicVolume) { this.musicVolume = musicVolume; }

    public double getSoundEffectsVolume() { return soundEffectsVolume; }
    public void setSoundEffectsVolume(double soundEffectsVolume) { this.soundEffectsVolume = soundEffectsVolume; }

    public String getResolution() { return resolution; }
    public void setResolution(String resolution) { this.resolution = resolution; }

    public boolean isFullscreen() { return fullscreen; }
    public void setFullscreen(boolean fullscreen) { this.fullscreen = fullscreen; }

    public String getGraphicsQuality() { return graphicsQuality; }
    public void setGraphicsQuality(String graphicsQuality) { this.graphicsQuality = graphicsQuality; }

    public double getGameSpeed() { return gameSpeed; }
    public void setGameSpeed(double gameSpeed) { this.gameSpeed = gameSpeed; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public boolean isShowFPS() { return showFPS; }
    public void setShowFPS(boolean showFPS) { this.showFPS = showFPS; }

    public void resetDefaults() {
        musicVolume = 50.0;
        soundEffectsVolume = 50.0;
        resolution = "1920x1080";
        fullscreen = true;
        graphicsQuality = "High";
        gameSpeed = 1.0;
        language = "English";
        showFPS = false;
    }
}
