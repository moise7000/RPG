package gameInterface.Scenes;

import gameInterface.InterfaceConfiguration;
import gameInterface.Main;
import gameInterface.Settings;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class SettingsScene {

    public static VBox create(Main mainApp, InterfaceConfiguration config) {
        Settings settings = Settings.getInstance();

        VBox settingsLayout = new VBox(20);
        settingsLayout.setStyle("-fx-alignment: center; -fx-padding: 20px;");

        // Titre
        Text title = new Text("Paramètres");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        // Volume musique
        Slider musicSlider = new Slider(0, 100, settings.getMusicVolume());
        musicSlider.setShowTickLabels(true);
        musicSlider.valueProperty().addListener((obs, oldVal, newVal) -> settings.setMusicVolume(newVal.doubleValue()));

        // Volume effets sonores
        Slider effectsSlider = new Slider(0, 100, settings.getSoundEffectsVolume());
        effectsSlider.setShowTickLabels(true);
        effectsSlider.valueProperty().addListener((obs, oldVal, newVal) -> settings.setSoundEffectsVolume(newVal.doubleValue()));

        // Résolution
        ComboBox<String> resolutionCombo = new ComboBox<>();
        resolutionCombo.getItems().addAll("1280x720", "1920x1080", "2560x1440", "3840x2160");
        resolutionCombo.setValue(settings.getResolution());
        resolutionCombo.setOnAction(e -> settings.setResolution(resolutionCombo.getValue()));

        // Plein écran
        CheckBox fullscreenCheckbox = new CheckBox("Mode Plein Écran");
        fullscreenCheckbox.setSelected(settings.isFullscreen());
        fullscreenCheckbox.setOnAction(e -> settings.setFullscreen(fullscreenCheckbox.isSelected()));

        // Qualité graphique
        ComboBox<String> qualityCombo = new ComboBox<>();
        qualityCombo.getItems().addAll("Low", "Medium", "High", "Ultra");
        qualityCombo.setValue(settings.getGraphicsQuality());
        qualityCombo.setOnAction(e -> settings.setGraphicsQuality(qualityCombo.getValue()));

        // Vitesse du jeu
        Slider speedSlider = new Slider(0.5, 1.5, settings.getGameSpeed());
        speedSlider.setShowTickLabels(true);
        speedSlider.valueProperty().addListener((obs, oldVal, newVal) -> settings.setGameSpeed(newVal.doubleValue()));

        // Langue
        ComboBox<String> languageCombo = new ComboBox<>();
        languageCombo.getItems().addAll("English", "Français", "Español");
        languageCombo.setValue(settings.getLanguage());
        languageCombo.setOnAction(e -> settings.setLanguage(languageCombo.getValue()));

        // Affichage FPS
        CheckBox fpsCheckbox = new CheckBox("Afficher les FPS");
        fpsCheckbox.setSelected(settings.isShowFPS());
        fpsCheckbox.setOnAction(e -> settings.setShowFPS(fpsCheckbox.isSelected()));

        // Bouton de réinitialisation
        Button resetButton = new Button("Réinitialiser");
        resetButton.setOnAction(e -> {
            settings.resetDefaults();
            musicSlider.setValue(settings.getMusicVolume());
            effectsSlider.setValue(settings.getSoundEffectsVolume());
            resolutionCombo.setValue(settings.getResolution());
            fullscreenCheckbox.setSelected(settings.isFullscreen());
            qualityCombo.setValue(settings.getGraphicsQuality());
            speedSlider.setValue(settings.getGameSpeed());
            languageCombo.setValue(settings.getLanguage());
            fpsCheckbox.setSelected(settings.isShowFPS());
        });

        // Bouton de retour
        Button backButton = new Button("Retour");
        backButton.setOnAction(e -> mainApp.setSceneContent(MainMenuScene.create(mainApp, config)));

        // Ajouter les contrôles
        settingsLayout.getChildren().addAll(
                title,
                new Text("Volume Musique"), musicSlider,
                new Text("Volume Effets Sonores"), effectsSlider,
                new Text("Résolution"), resolutionCombo,
                fullscreenCheckbox,
                new Text("Qualité Graphique"), qualityCombo,
                new Text("Vitesse du Jeu"), speedSlider,
                new Text("Langue"), languageCombo,
                fpsCheckbox,
                resetButton,
                backButton
        );

        return settingsLayout;
    }
}
