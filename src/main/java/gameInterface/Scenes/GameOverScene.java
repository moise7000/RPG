package gameInterface.Scenes;

import eu.telecomnancy.rpg.GameCharacter;
import gameInterface.InterfaceConfiguration;
import gameInterface.Main;
import gameInterface.character.CharacterAnimation;
import gameInterface.helpers.ButtonStyleHelper;
import javafx.animation.FadeTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 * Crée la scène de "Game Over" qui s'affiche à la fin d'une partie.
 * <p>
 * Cette scène affiche un titre "Game Over", les statistiques du joueur (niveau et score),
 * ainsi qu'une animation du personnage. Elle propose également des boutons permettant de
 * rejouer ou de revenir au menu principal.
 *
 * <p><b>Utilisation :</b></p>
 * <pre>
 * VBox gameOverScene = GameOverScene.create(mainApp, config, character, level, score);
 * </pre>
 */
public class GameOverScene {

    /**
     * Crée la scène de "Game Over" avec un titre, les statistiques, une animation du personnage,
     * et des boutons pour rejouer ou retourner au menu principal.
     *
     * @param mainApp L'application principale, utilisée pour changer de scène.
     * @param config La configuration de l'interface, qui contient les styles et autres paramètres.
     * @param character Le personnage du joueur, pour afficher son animation.
     * @param level Le niveau atteint par le joueur dans la partie.
     * @param score Le score final du joueur.
     * @return Un VBox représentant la scène "Game Over".
     */
    public static VBox create(Main mainApp, InterfaceConfiguration config, GameCharacter character, int level, int score) {
        VBox root = new VBox(30);
        root.setStyle("-fx-alignment: center;");

        // Game Over Title with fade animation
        Label gameOverTitle = new Label(config.getGameOverLabel());
        gameOverTitle.setStyle(config.getGameOverStyle());
        FadeTransition fadeTitle = new FadeTransition(Duration.seconds(2), gameOverTitle);
        fadeTitle.setFromValue(0);
        fadeTitle.setToValue(1);
        fadeTitle.play();

        // Stats container
        VBox statsContainer = new VBox(15);
        statsContainer.setStyle("-fx-alignment: center; -fx-padding: 20; -fx-background-color: rgba(0,0,0,0.7); -fx-background-radius: 10;");

        Label levelLabel = new Label("Level Reached: " + level);
        Label scoreLabel = new Label("Final Score: " + score);

        levelLabel.setStyle("-fx-font-size: 24; -fx-text-fill: white;");
        scoreLabel.setStyle("-fx-font-size: 24; -fx-text-fill: white;");

        // Character animation setup
        CharacterAnimation characterAnimation = character.getAnimations();
        characterAnimation.getSpriteView().setScaleX(3.0);
        characterAnimation.getSpriteView().setScaleY(3.0);
        characterAnimation.setState(CharacterAnimation.CharacterState.IDLE);

        // Buttons
        HBox buttonContainer = new HBox(20);
        buttonContainer.setStyle("-fx-alignment: center;");

        Button retryButton = new Button("Try Again");
        Button mainMenuButton = new Button("Main Menu");

        ButtonStyleHelper.applyButtonStyle(java.util.List.of(retryButton, mainMenuButton), config.getButtonStyle());
        ButtonStyleHelper.applyHoverStyle(java.util.List.of(retryButton, mainMenuButton),
                config.getButtonStyle(),
                config.getButtonHoverStyle());

        retryButton.setOnAction(e -> mainApp.setSceneContent(
                CharacterSelectionScene.create(mainApp, config)
        ));

        mainMenuButton.setOnAction(e -> mainApp.setSceneContent(
                MainMenuScene.create(mainApp, config)
        ));

        buttonContainer.getChildren().addAll(retryButton, mainMenuButton);
        statsContainer.getChildren().addAll(levelLabel, scoreLabel);

        root.getChildren().addAll(
                gameOverTitle,
                characterAnimation.getSpriteView(),
                statsContainer,
                buttonContainer
        );

        return root;
    }
}