package gameInterface.Scenes;

import gameInterface.InterfaceConfiguration;
import gameInterface.Main;
import gameInterface.character.CharacterAnimation;
import gameInterface.helpers.ButtonStyleHelper;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.List;

public class CharacterSelectionScene {
    public static VBox create(Main mainApp, InterfaceConfiguration config) {
        VBox root = new VBox(20);
        root.setStyle("-fx-alignment: center;");

        // Title
        Text title = new Text(config.getSelectCharacterButtonLabel());
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
        Button backButton = new Button(config.getExitButtonLabel());


        List<Button> buttons = List.of(selectEvilWizard, selectWizard, selectHeroKnight, selectMartialHero, backButton);

        ButtonStyleHelper.applyButtonStyle(buttons, config.getButtonStyle());
        ButtonStyleHelper.applyHoverStyle(buttons, config.getButtonStyle(), config.getButtonHoverStyle());

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

        character.setState(CharacterAnimation.CharacterState.ATTACK);

        return character;
    }

    private static CharacterAnimation createEvilWizardCharacter(InterfaceConfiguration config) {
        CharacterAnimation character = config.getEvilWizardAnimations();
        character.setState(CharacterAnimation.CharacterState.IDLE);
        return character;
    }




    private static CharacterAnimation createHeroKnightCharacter(InterfaceConfiguration config) {
        CharacterAnimation character = config.getHeroKnightAnimations();
        character.setState(CharacterAnimation.CharacterState.IDLE);
        return character;
    }

    private static CharacterAnimation createMartialHeroCharacter(InterfaceConfiguration config) {
        CharacterAnimation character = config.getMartialHeroAnimations();
        character.setState(CharacterAnimation.CharacterState.ATTACK);
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
        selectedCharacter.setState(CharacterAnimation.CharacterState.IDLE);
        mainApp.setSceneContent(GameScene.create(mainApp, config, selectedCharacter));
    }
}