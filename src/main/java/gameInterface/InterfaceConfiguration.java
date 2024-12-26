package gameInterface;

import java.util.Arrays;
import java.util.List;

public class InterfaceConfiguration {

    private static InterfaceConfiguration shared;

    private final double ZOOM = 2.1;




    private static final class Window {
        static final String WINDOW_TITLE = "RPG";
        static final int WINDOW_WIDTH = 620;
        static final int WINDOW_HEIGHT = 360;
        static final int WINDOW_X = 0;
        static final int WINDOW_Y = 0;
        static final int WINDOW_BORDER_WIDTH = 10;
        static final int WINDOW_BORDER_HEIGHT = 10;
        static final int WINDOW_BORDER_COLOR = 0x000000;
        static final int WINDOW_BACKGROUND_COLOR = 0xFFFFFF;
        static final int WINDOW_FONT_SIZE = 16;
    }

    private static final class UserInterfaceConfiguration {

        static final String PLAY = "Play";
        static final String CREDITS = "Credits";
        static final String QUIT = "Quit";
        static final String SAVE = "Save";
        static final String LOAD = "Load";
        static final String SETTINGS = "Settings";
        static final String EXIT = "Exit";
        static final String BACKGROUND_IMAGE_PATH = "file:src/assets/background/NightForest/NightForestWhitMist.png";
        static final int BACKGROUND_IMAGE_WIDTH = 620;
        static final int BACKGROUND_IMAGE_HEIGHT = 360;



    }

    public double getZoom() {return ZOOM;}

    public String getWindowTitle() {return Window.WINDOW_TITLE;}
    public String getPlayButtonLabel() {return UserInterfaceConfiguration.PLAY;}
    public String getCreditsButtonLabel() {return UserInterfaceConfiguration.CREDITS;}
    public String getQuitButtonLabel() {return UserInterfaceConfiguration.QUIT;}
    public String getSaveButtonLabel() {return UserInterfaceConfiguration.SAVE;}
    public String getLoadButtonLabel() {return UserInterfaceConfiguration.LOAD;}
    public String getSettingsButtonLabel() {return UserInterfaceConfiguration.SETTINGS;}
    public String getExitButtonLabel() {return UserInterfaceConfiguration.EXIT;}








    public double getWindowWidth() {return Window.WINDOW_WIDTH * ZOOM;}
    public double getWindowHeight() {return Window.WINDOW_HEIGHT * ZOOM;}
    public int getWindowX() {return Window.WINDOW_X;}
    public int getWindowY() {return Window.WINDOW_Y;}
    public int getWindowBorderWidth() {return Window.WINDOW_BORDER_WIDTH;}
    public int getWindowBorderHeight() {return Window.WINDOW_BORDER_HEIGHT;}
    public int getWindowBorderColor() {return Window.WINDOW_BORDER_COLOR;}
    public int getWindowBackgroundColor() {return Window.WINDOW_BACKGROUND_COLOR;}
    public int getWindowFontSize() {return Window.WINDOW_FONT_SIZE;}


    public static InterfaceConfiguration getShared() {
        if (shared == null) {
            shared = new InterfaceConfiguration();
        }
        return shared;
    }


    private static final class ParallaxConfiguration {
        static final List<String> PARALLAX_LAYERS = Arrays.asList(
                "file:src/assets/background/NightForest/Layers/1.png",
                "file:src/assets/background/NightForest/Layers/2.png",
                "file:src/assets/background/NightForest/Layers/3.png",
                "file:src/assets/background/NightForest/Layers/4.png",
                "file:src/assets/background/NightForest/Layers/5.png",
                "file:src/assets/background/NightForest/Layers/6.png"
        );;

        static final List<Double> PARALLAX_SPEEDS = Arrays.asList(0.1, 0.2, 0.3, 0.4, 0.5, 0.6);;

    }

    public List<String> getParallaxLayers() {return ParallaxConfiguration.PARALLAX_LAYERS;}
    public List<Double> getParallaxSpeeds() {return ParallaxConfiguration.PARALLAX_SPEEDS;}



    private static final class CreditsConfiguration {
        static final String CREDITS_TEXT = "RPG by Ewan Decima";
        static final String CREDITS_LINK = "https://github.com/ewan-decima";
        static final String FONT = "Font : GothicPixels by LingDong Huang";

        static final String temp = "Crédits\n\nDéveloppement : Ton Nom\nGraphismes : Artiste Nom\nMusique : Compositeur Nom\n";
    }


    public String getCreditsText() {return CreditsConfiguration.CREDITS_TEXT;}



    private static final class SettingsConfiguration {}





    private static final class StylesConfiguration {
        static final String BUTTON_STYLE = "-fx-font-family: 'GothicPixels';" +
                "-fx-font-size: 25px;" +
                "-fx-font-weight: bold;" +
                "-fx-background-color: #000000;" +
                "-fx-text-fill: #FFFFFF;";

        static final String FONT_PATH = "file:src/assets/fonts/GothicPixels.ttf";
        

    }


    public String getButtonStyle() {return StylesConfiguration.BUTTON_STYLE;}
    public String getFontPath() {return StylesConfiguration.FONT_PATH;}

}
