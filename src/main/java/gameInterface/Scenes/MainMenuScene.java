package gameInterface.Scenes;

import gameInterface.InterfaceConfiguration;
import gameInterface.Main;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MainMenuScene {

    public static VBox create(Main mainApp, InterfaceConfiguration config) {
        // Boutons
        Button playButton = new Button(config.getPlayButtonLabel());
        Button settingsButton = new Button(config.getSettingsButtonLabel());
        Button creditsButton = new Button(config.getCreditsButtonLabel());

        playButton.setStyle(config.getButtonStyle());
        settingsButton.setStyle(config.getButtonStyle());
        creditsButton.setStyle(config.getButtonStyle());


        VBox menu = new VBox(20, playButton, settingsButton, creditsButton);
        menu.setTranslateY(100);
        menu.setStyle("-fx-alignment: center;");

        // Actions des boutons
        creditsButton.setOnAction(e -> mainApp.setSceneContent(CreditsScene.create(mainApp, config)));
        settingsButton.setOnAction(e -> mainApp.setSceneContent(SettingsScene.create(mainApp, config)));

        return menu;
    }
}
