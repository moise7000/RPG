package gameInterface.Scenes;

import gameInterface.InterfaceConfiguration;
import gameInterface.Main;
import gameInterface.character.CharacterAnimation;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class CharacterSelectionScene {
    public static VBox create(Main mainApp, InterfaceConfiguration config) {
        VBox root = new VBox(20);
        root.setStyle("-fx-alignment: center;");

        // Title
        Text title = new Text("Select Your Character");
        title.setStyle("-fx-font-family: 'GothicPixels'; -fx-font-size: 32px; -fx-fill: white;");

        // Character preview container
        HBox characterContainer = new HBox(50);
        characterContainer.setStyle("-fx-alignment: center;");

        // Create character previews with all animations
        CharacterAnimation wizard = createWizardCharacter(config);
        CharacterAnimation evilWizard = createEvilWizardCharacter(config);

        // Selection buttons
        Button selectWizard = new Button("Select Wizard");
        Button selectEvilWizard = new Button("Select Evil Wizard");

        selectWizard.setStyle(config.getButtonStyle());
        selectEvilWizard.setStyle(config.getButtonStyle());

        // Add preview animations to container
        characterContainer.getChildren().addAll(
                createCharacterPreviewBox(wizard, selectWizard),
                createCharacterPreviewBox(evilWizard, selectEvilWizard)
        );

        root.getChildren().addAll(title, characterContainer);
        return root;
    }

    private static CharacterAnimation createWizardCharacter(InterfaceConfiguration config) {
        CharacterAnimation character = new CharacterAnimation();

        // Add idle animation
        character.addAnimation(
                CharacterAnimation.CharacterState.IDLE,
                config.getWizardIdleSpritePath(),
                config.getWizardIdleSpriteFrameCount(),
                config.getWizardFrameWidth(),
                config.getWizardFrameHeight(),
                Duration.millis(100)
        );

        character.setState(CharacterAnimation.CharacterState.IDLE);
        return character;
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

        // Scale the sprite view to make it visible
        character.getSpriteView().setScaleX(2.0);
        character.getSpriteView().setScaleY(2.0);

        previewBox.getChildren().addAll(character.getSpriteView(), selectButton);
        return previewBox;
    }
}