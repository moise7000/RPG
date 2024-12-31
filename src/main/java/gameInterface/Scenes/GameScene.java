package gameInterface.Scenes;

import eu.telecomnancy.rpg.GameCharacter;
import gameInterface.InterfaceConfiguration;
import gameInterface.Main;
import gameInterface.character.CharacterAnimation;
import gameInterface.helpers.ButtonStyleHelper;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.List;

public class GameScene {
    private static CharacterAnimation playerCharacterAnimation;
    private static GameCharacter playerCharacter;

    public static VBox create(Main mainApp, InterfaceConfiguration config, GameCharacter selectedCharacter) {
        playerCharacterAnimation = selectedCharacter.getAnimations();
        playerCharacter = selectedCharacter;


        VBox root = new VBox(20);
        root.setStyle("-fx-alignment: center;");


        Pane gameContainer = new Pane();
        gameContainer.setPrefSize(config.getWindowWidth(), config.getWindowHeight());




        // Character Position
        playerCharacterAnimation.getSpriteView().setX(0); // Centre X
        playerCharacterAnimation.getSpriteView().setY(config.getWindowHeight() - 300); // Centre Y
        playerCharacterAnimation.getSpriteView().setScaleX(2.0); // Scale du sprite
        playerCharacterAnimation.getSpriteView().setScaleY(2.0); // Scale du sprite


        gameContainer.getChildren().add(playerCharacterAnimation.getSpriteView());

        // Création des boutons de contrôle
        HBox controlButtons = createControlButtons(config);

        // Bouton retour
        Button backButton = new Button(config.getExitButtonLabel());
        backButton.setStyle(config.getButtonStyle());
        backButton.setOnAction(e -> mainApp.setSceneContent(CharacterSelectionScene.create(mainApp, config)));

        ButtonStyleHelper.applyHoverStyle(backButton, config.getButtonStyle(), config.getButtonHoverStyle());

        // Espacement
        Region spacer = new Region();
        spacer.setPrefHeight(20);

        // Assemblage de la scène
        root.getChildren().addAll(gameContainer, controlButtons, spacer, backButton);

        return root;
    }








    private static void performAttackAnimation() {
        // Change l'état en ATTACK
        playerCharacterAnimation.setState(CharacterAnimation.CharacterState.ATTACK);

        // Crée une pause qui durera le temps de l'animation
        int attackAnimationFrameCount = playerCharacterAnimation.getFrameCount(playerCharacterAnimation.getCurrentState());
        PauseTransition pause = new PauseTransition(Duration.millis(attackAnimationFrameCount * 100));

        // Après la pause, retourne à l'état IDLE
        pause.setOnFinished(event -> playerCharacterAnimation.setState(CharacterAnimation.CharacterState.IDLE));

        // Lance la pause
        pause.play();
    }



    private static HBox createControlButtons(InterfaceConfiguration config) {
        HBox buttonContainer = new HBox(20);
        buttonContainer.setStyle("-fx-alignment: center;");

        // Création des boutons
        Button attackButton = new Button(config.getAttackButtonLabel());
        Button recruitButton = new Button(config.getRecruitButtonLabel());
        Button decoratorButton = new Button("Add Decorator");

        // Style des boutons
        List<Button> controlButtons = List.of(
                attackButton,
                recruitButton,
                decoratorButton
        );

        ButtonStyleHelper.applyButtonStyle(controlButtons, config.getButtonStyle());
        ButtonStyleHelper.applyHoverStyle(controlButtons, config.getButtonStyle(), config.getButtonHoverStyle());


        // Action du bouton Attack
        attackButton.setOnAction(e -> performAttackAnimation());

        // Action du bouton Recruit (à implémenter selon vos besoins)
        recruitButton.setOnAction(e -> {
            // TODO: Implémenter la logique de recrutement
        });

        // Action du bouton Add Decorator (à implémenter selon vos besoins)
        decoratorButton.setOnAction(e -> {
            // TODO: Implémenter la logique d'ajout de décorateur
        });

        buttonContainer.getChildren().addAll(attackButton, recruitButton, decoratorButton);
        return buttonContainer;
    }


}