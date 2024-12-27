package gameInterface.Scenes;

import gameInterface.InterfaceConfiguration;
import gameInterface.Main;
import gameInterface.character.CharacterAnimation;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class CharacterSelectionScene {
    public static VBox create(Main mainApp, InterfaceConfiguration config) {
        VBox root = new VBox(20);
        root.setStyle("-fx-alignment: center;");

        // Title
        Text title = new Text("Select Your Character");
        title.setStyle("-fx-font-family: " + config.getFontName() + "; -fx-font-size: " + config.getFontSize() + "px; -fx-fill: white;");

        // Character preview container
        HBox characterContainer = new HBox(50);
        characterContainer.setStyle("-fx-alignment: center;");

        // Create character previews with all animations
        CharacterAnimation wizard = createWizardCharacter(config);
        CharacterAnimation evilWizard = createEvilWizardCharacter(config);
        CharacterAnimation heroKnight = createHeroKnightCharacter(config);
        CharacterAnimation martialHero = createMartialHeroCharacter(config);

        // Selection buttons
        Button selectWizard = new Button("Wizard");
        Button selectEvilWizard = new Button("Evil Wizard");
        Button selectHeroKnight = new Button("Hero Knight");
        Button selectMartialHero = new Button("Martial Hero");

        selectWizard.setStyle(config.getButtonStyle());
        selectEvilWizard.setStyle(config.getButtonStyle());
        selectHeroKnight.setStyle(config.getButtonStyle());
        selectMartialHero.setStyle(config.getButtonStyle());

        // Add preview animations to container
        characterContainer.getChildren().addAll(
                createCharacterPreviewBox(wizard, selectWizard),
                createCharacterPreviewBox(evilWizard, selectEvilWizard),
                createCharacterPreviewBox(heroKnight, selectHeroKnight),
                createCharacterPreviewBox(martialHero, selectMartialHero)
        );


        selectWizard.setOnAction(e -> startGame(mainApp, config, wizard));
        selectEvilWizard.setOnAction(e -> startGame(mainApp, config, evilWizard));
        selectHeroKnight.setOnAction(e -> startGame(mainApp, config, heroKnight));
        selectMartialHero.setOnAction(e -> startGame(mainApp, config, martialHero));

        Button backButton = new Button(config.getExitButtonLabel());
        backButton.setStyle(config.getButtonStyle());

        backButton.setOnAction(e -> mainApp.setSceneContent(MainMenuScene.create(mainApp, config)));

        Region spacer1 = new Region();
        spacer1.setPrefHeight(100);

        Region spacer2 = new Region();
        spacer2.setPrefHeight(150);

        root.getChildren().addAll(spacer2, title, characterContainer, spacer1, backButton);
        return root;
    }


    private static CharacterAnimation createWizardCharacter(InterfaceConfiguration config) {
        CharacterAnimation character = config.getWizardAnimations();

        character.setState(CharacterAnimation.CharacterState.DEATH);

        return character;
    }


    private static CharacterAnimation createWizardCharacter2(InterfaceConfiguration config) {
        CharacterAnimation character = new CharacterAnimation();

        // Ajout de toutes les animations
        character.addAnimation(
                CharacterAnimation.CharacterState.IDLE,
                config.getWizardIdleSpritePath(),
                config.getWizardIdleSpriteFrameCount(),
                config.getWizardFrameWidth(),
                config.getWizardFrameHeight(),
                Duration.millis(100)
        );

        character.addAnimation(
                CharacterAnimation.CharacterState.ATTACK,
                config.getWizardAttackSpritePath(),
                config.getWizardAttackSpriteFrameCount(),
                config.getWizardFrameWidth(),
                config.getWizardFrameHeight(),
                Duration.millis(100)
        );

        character.addAnimation(
                CharacterAnimation.CharacterState.MOVE,
                config.getWizardMoveSpritePath(),
                config.getWizardMoveSpriteFrameCount(),
                config.getWizardFrameWidth(),
                config.getWizardFrameHeight(),
                Duration.millis(100)
        );

        // Animation de l'attaque par défaut
        character.setState(CharacterAnimation.CharacterState.ATTACK);
        return character;
    }

    private static CharacterAnimation createHeroKnightCharacter(InterfaceConfiguration config) {
        return config.getHeroKnightIdleAnimation();
    }

    private static CharacterAnimation createMartialHeroCharacter(InterfaceConfiguration config) {
        return config.getMartialHeroIdleAnimation();
    }

    private static CharacterAnimation createEvilWizardCharacter(InterfaceConfiguration config) {
        CharacterAnimation character = new CharacterAnimation();

        // Add idle animation
        character.addAnimation(
                CharacterAnimation.CharacterState.IDLE,
                config.getEvilWizardIdleSpritePath(),
                config.getEvilWizardIdleSpriteFrameCount(),
                config.getEvilWizardFrameWidth(),
                config.getEvilWizardFrameHeight(),
                Duration.millis(100)
        );

        character.setState(CharacterAnimation.CharacterState.IDLE);
        return character;
    }

    private static VBox createCharacterPreviewBox(CharacterAnimation character, Button selectButton) {
        VBox previewBox = new VBox(10);
        previewBox.setStyle("-fx-alignment: center;");

        HBox spriteContainer = new HBox();
        spriteContainer.setPrefSize(200, 200); // Fixed size for all sprites
        spriteContainer.setStyle("-fx-alignment: center;");
        spriteContainer.getChildren().add(character.getSpriteView());


        // Scale the sprite view to make it visible
        character.getSpriteView().setScaleX(2.0);
        character.getSpriteView().setScaleY(2.0);


        previewBox.getChildren().addAll(spriteContainer, selectButton);

        return previewBox;
    }


    private static void startGame(Main mainApp, InterfaceConfiguration config, CharacterAnimation selectedCharacter) {
        // Créer et transitionner vers la scène de jeu avec le personnage sélectionné
        mainApp.setSceneContent(GameScene.create(mainApp, config, selectedCharacter));
    }
}