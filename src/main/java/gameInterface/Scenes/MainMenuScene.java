package gameInterface.Scenes;

import gameInterface.InterfaceConfiguration;
import gameInterface.Main;
import gameInterface.helpers.ButtonStyleHelper;
import gameInterface.helpers.animatedText.FullAnimatedText;
import gameInterface.helpers.animatedText.ReflectiveText;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.List;

/**
 * Crée la scène du menu principal, qui s'affiche au lancement du jeu.
 * <p>
 * Cette scène comprend un titre du jeu, des boutons permettant de jouer, accéder aux paramètres,
 * consulter les crédits ou quitter le jeu. Un effet de transition de fondu est appliqué au titre.
 *
 * <p><b>Utilisation :</b></p>
 * <pre>
 * VBox mainMenu = MainMenuScene.create(mainApp, config);
 * </pre>
 */
public class MainMenuScene {

    /**
     * Crée la scène du menu principal avec un titre, des boutons pour jouer, accéder aux paramètres,
     * consulter les crédits ou quitter le jeu. Le titre du jeu apparaît avec une transition de fondu.
     *
     * @param mainApp L'application principale, utilisée pour changer de scène.
     * @param config La configuration de l'interface, qui contient les styles et autres paramètres.
     * @return Un VBox représentant le menu principal.
     */
    public static VBox create(Main mainApp, InterfaceConfiguration config) {


        Label gameTitle = ReflectiveText.createReflectiveText(config.getGameTitle());
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

        Region spacer = new Region();
        spacer.setPrefHeight(50);

        VBox menu = new VBox(20, gameTitle, spacer, playButton, settingsButton, creditsButton, closeButton);
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
