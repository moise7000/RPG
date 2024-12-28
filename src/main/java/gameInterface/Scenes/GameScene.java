package gameInterface.Scenes;

import gameInterface.InterfaceConfiguration;
import gameInterface.Main;
import gameInterface.character.CharacterAnimation;
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class GameScene {
    private static CharacterAnimation playerCharacter;

    public static VBox create(Main mainApp, InterfaceConfiguration config, CharacterAnimation selectedCharacter) {
        playerCharacter = selectedCharacter;
        setupCharacterAnimations(config); // Configure toutes les animations du personnage

        VBox root = new VBox(20);
        root.setStyle("-fx-alignment: center;");


        Pane gameContainer = new Pane();
        gameContainer.setPrefSize(config.getWindowWidth(), config.getWindowHeight());




        // Character Position
        playerCharacter.getSpriteView().setX(0); // Centre X
        playerCharacter.getSpriteView().setY(config.getWindowHeight() - 300); // Centre Y
        playerCharacter.getSpriteView().setScaleX(2.0); // Scale du sprite
        playerCharacter.getSpriteView().setScaleY(2.0); // Scale du sprite


        gameContainer.getChildren().add(playerCharacter.getSpriteView());

        // Création des boutons de contrôle
        HBox controlButtons = createControlButtons(config);

        // Bouton retour
        Button backButton = new Button(config.getExitButtonLabel());
        backButton.setStyle(config.getButtonStyle());
        backButton.setOnAction(e -> mainApp.setSceneContent(CharacterSelectionScene.create(mainApp, config)));

        // Espacement
        Region spacer = new Region();
        spacer.setPrefHeight(20);

        // Assemblage de la scène
        root.getChildren().addAll(gameContainer, controlButtons, spacer, backButton);

        return root;
    }

    private static void setupCharacterAnimations(InterfaceConfiguration config) {
        // Configuration des animations selon le type de personnage
        if (isWizardCharacter()) {
            setupWizardAnimations(config);
        } else if (isEvilWizardCharacter()) {
            setupEvilWizardAnimations(config);
        } else if (isHeroKnightCharacter()) {
            setupHeroKnightAnimations(config);
        } else if (isMartialHeroCharacter()) {
            setupMartialHeroAnimations(config);
        }
    }

    private static boolean isWizardCharacter() {
        return playerCharacter.getSpriteView().getImage().getUrl().contains("/Wizard/");
    }

    private static boolean isEvilWizardCharacter() {
        return playerCharacter.getSpriteView().getImage().getUrl().contains("/EvilWizard/");
    }

    private static boolean isHeroKnightCharacter() {
        return playerCharacter.getSpriteView().getImage().getUrl().contains("/HeroKnight/");
    }

    private static boolean isMartialHeroCharacter() {
        return playerCharacter.getSpriteView().getImage().getUrl().contains("/MartialHero/");
    }

    private static void setupWizardAnimations(InterfaceConfiguration config) {
        playerCharacter.addAnimation(
                CharacterAnimation.CharacterState.ATTACK,
                config.getWizardAttackSpritePath(),
                config.getWizardAttackSpriteFrameCount(),
                config.getWizardFrameWidth(),
                config.getWizardFrameHeight(),
                Duration.millis(100)
        );
    }

    private static void setupEvilWizardAnimations(InterfaceConfiguration config) {
        playerCharacter.addAnimation(
                CharacterAnimation.CharacterState.ATTACK,
                config.getEvilWizardAttackSpritePath(),
                config.getEvilWizardAttackSpriteFrameCount(),
                config.getEvilWizardFrameWidth(),
                config.getEvilWizardFrameHeight(),
                Duration.millis(100)
        );
    }

    private static void setupHeroKnightAnimations(InterfaceConfiguration config) {
        playerCharacter.addAnimation(
                CharacterAnimation.CharacterState.ATTACK,
                config.getHeroKnightAttackSpritePath(),
                config.getHeroKnightAttackSpriteFrameCount(),
                config.getHeroKnightFrameWidth(),
                config.getHeroKnightFrameHeight(),
                Duration.millis(100)
        );
    }

    private static void setupMartialHeroAnimations(InterfaceConfiguration config) {
        playerCharacter.addAnimation(
                CharacterAnimation.CharacterState.ATTACK,
                config.getMartialHeroAttackSpritePath(),
                config.getMartialHeroAttackSpriteFrameCount(),
                config.getMartialHeroFrameWidth(),
                config.getMartialHeroFrameHeight(),
                Duration.millis(100)
        );
    }

    private static void performAttackAnimation() {
        // Change l'état en ATTACK
        playerCharacter.setState(CharacterAnimation.CharacterState.ATTACK);

        // Crée une pause qui durera le temps de l'animation
        PauseTransition pause = new PauseTransition(Duration.millis(800)); // 8 frames * 100ms

        // Après la pause, retourne à l'état IDLE
        pause.setOnFinished(event -> playerCharacter.setState(CharacterAnimation.CharacterState.IDLE));

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
        attackButton.setStyle(config.getButtonStyle());
        recruitButton.setStyle(config.getButtonStyle());
        decoratorButton.setStyle(config.getButtonStyle());

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