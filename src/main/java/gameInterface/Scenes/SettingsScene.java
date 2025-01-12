package gameInterface.Scenes;

import gameInterface.InterfaceConfiguration;
import gameInterface.Main;
import gameInterface.Settings;
import gameInterface.helpers.ButtonStyleHelper;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsScene {

    public static VBox create(Main mainApp, InterfaceConfiguration config) {
        Settings settings = Settings.getInstance();

        VBox settingsLayout = new VBox(20);
        settingsLayout.setStyle("-fx-alignment: center; -fx-padding: 20px;");

        // Titre
        Text title = new Text("Parameters");
        title.setStyle(config.getGameTitleStyle());

        // Volume musique
        Slider musicSlider = new Slider(0, 1, 0.5);
        musicSlider.setShowTickLabels(true);
        musicSlider.valueProperty().addListener((obs, oldVal, newVal) -> mainApp.setMusicVolume(newVal.doubleValue()));







        // Bouton de réinitialisation
        Button resetButton = new Button("Reset");
        resetButton.setOnAction(e -> {
            settings.resetDefaults();
            musicSlider.setValue(0.5);
        });

        // Bouton de retour
        Button backButton = new Button("Exit");



        backButton.setOnAction(e -> mainApp.setSceneContent(MainMenuScene.create(mainApp, config)));

        List<Button> buttons = Arrays.asList(resetButton, backButton);
        ButtonStyleHelper.applyButtonStyle(buttons, config.getButtonStyle());
        ButtonStyleHelper.applyHoverStyle(buttons, config.getButtonStyle(), config.getButtonHoverStyle());

        // Ajouter les contrôles
        settingsLayout.getChildren().addAll(
                title,
                new Text("Music volume"), musicSlider,
                resetButton,
                backButton
        );

        return settingsLayout;
    }
}
