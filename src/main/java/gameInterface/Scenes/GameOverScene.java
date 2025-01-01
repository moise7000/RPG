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

public class GameOverScene {
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