package gameInterface.Scenes;

import gameInterface.InterfaceConfiguration;
import gameInterface.Main;
import gameInterface.helpers.ButtonStyleHelper;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.List;

public class MainMenuScene {

    public static VBox create(Main mainApp, InterfaceConfiguration config) {

        Label gameTitle = new Label(config.getGameTitle());
        gameTitle.setStyle(config.getGameTitleStyle());

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), gameTitle);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setCycleCount(1);
        fadeTransition.play();

        // Boutons
        Button playButton = new Button(config.getPlayButtonLabel());
        Button settingsButton = new Button(config.getSettingsButtonLabel());
        Button creditsButton = new Button(config.getCreditsButtonLabel());
        Button closeButton = new Button(config.getQuitButtonLabel());

        List<Button> buttons = List.of(playButton, settingsButton, creditsButton, closeButton);

        ButtonStyleHelper.applyButtonStyle(buttons, config.getButtonStyle());
        ButtonStyleHelper.applyHoverStyle(buttons, config.getButtonStyle(), config.getButtonHoverStyle());


        VBox menu = new VBox(20, gameTitle, playButton, settingsButton, creditsButton, closeButton);
        //menu.setTranslateY(200);
        menu.setStyle("-fx-alignment: center;");

        // Actions des boutons
        creditsButton.setOnAction(e -> mainApp.setSceneContent(CreditsScene.create(mainApp, config)));
        settingsButton.setOnAction(e -> mainApp.setSceneContent(SettingsScene.create(mainApp, config)));
        playButton.setOnAction(e -> mainApp.setSceneContent(CharacterSelectionScene.create(mainApp, config)));
        closeButton.setOnAction(e -> Platform.exit());
        return menu;
    }
}
