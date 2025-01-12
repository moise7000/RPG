package gameInterface.Scenes;

import eu.telecomnancy.rpg.GameCharacter;
import gameInterface.InterfaceConfiguration;
import gameInterface.Main;

import gameInterface.Scenes.GameLoop.GameScene2;
import gameInterface.character.CharacterAnimation;
import gameInterface.helpers.ButtonStyleHelper;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.List;

/**
 * Crée la scène de sélection des personnages pour un jeu.
 * <p>
 * Cette scène permet aux joueurs de choisir un personnage parmi plusieurs options
 * et de visualiser les animations de chaque personnage. Lorsqu'un personnage est sélectionné,
 * la scène de jeu est lancée avec le personnage choisi.
 *
 * <p><b>Utilisation :</b></p>
 * <pre>
 * VBox selectionScene = CharacterSelectionScene.create(mainApp, config);
 * </pre>
 */
public class CharacterSelectionScene {

    /**
     * Crée la scène de sélection des personnages avec les prévisualisations et boutons de sélection.
     *
     * @param mainApp L'application principale, utilisée pour changer de scène.
     * @param config La configuration de l'interface, qui contient des paramètres tels que les styles et les informations sur les personnages.
     * @return Un VBox représentant la scène de sélection des personnages.
     */
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

        GameCharacter evilWizard = config.createEvilWizardGameCharacter();
        GameCharacter heroKnight = config.createHeroKnightGameCharacter();
        GameCharacter martialHero = config.createMartialHeroGaleCharacter();
        GameCharacter necromancer = config.createNecromancerGameCharacter();
        GameCharacter nightBorne = config.createNightBorneGameCharacter();

        setAllPreviewAnimations(evilWizard, heroKnight, martialHero, necromancer, nightBorne);



        // Selection buttons

        Button selectEvilWizard = new Button("Evil Wizard");
        Button selectHeroKnight = new Button("Hero Knight");
        Button selectMartialHero = new Button("Martial Hero");
        Button backButton = new Button(config.getExitButtonLabel());


        List<Button> buttons = List.of(
                selectEvilWizard,

                selectHeroKnight,
                selectMartialHero,
                backButton
        );

        ButtonStyleHelper.applyButtonStyle(buttons, config.getButtonStyle());
        ButtonStyleHelper.applyHoverStyle(buttons, config.getButtonStyle(), config.getButtonHoverStyle());

        // Add preview animations to container
        characterContainer.getChildren().addAll(

                createCharacterPreviewBox(evilWizard.getAnimations(), selectEvilWizard, config.getEvilWizardInfo()),
                createCharacterPreviewBox(heroKnight.getAnimations(), selectHeroKnight, config.getHeroKnightInfo()),
                createCharacterPreviewBox(martialHero.getAnimations(), selectMartialHero, config.getMartialHeroInfo())

        );



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


    /**
     * Initialise les animations de prévisualisation des personnages.
     *
     * @param evilWizard Le personnage du Sorcier Maléfique.
     * @param heroKnight Le personnage du Chevalier Héroïque.
     * @param martialHero Le personnage du Héros Martial.
     * @param necromancer Le personnage du Nécromancien.
     * @param nightBorne Le personnage du Night Borne.
     */
    private static void setAllPreviewAnimations( GameCharacter evilWizard, GameCharacter heroKnight, GameCharacter martialHero, GameCharacter necromancer, GameCharacter nightBorne) {

        evilWizard.getAnimations().setState(CharacterAnimation.CharacterState.IDLE);
        heroKnight.getAnimations().setState(CharacterAnimation.CharacterState.MOVE);
        martialHero.getAnimations().setState(CharacterAnimation.CharacterState.ATTACK);
        necromancer.getAnimations().setState(CharacterAnimation.CharacterState.MOVE);
        nightBorne.getAnimations().setState(CharacterAnimation.CharacterState.ATTACK);

    }

    /**
     * Crée un conteneur pour la prévisualisation d'un personnage, incluant son sprite et un bouton de sélection.
     *
     * @param character L'animation du personnage à afficher.
     * @param selectButton Le bouton permettant de sélectionner ce personnage.
     * @param characterInfo L'information texte à afficher sous la prévisualisation du personnage.
     * @return Un VBox contenant la prévisualisation du personnage et le bouton de sélection.
     */
    private static VBox createCharacterPreviewBox(CharacterAnimation character,
                                                  Button selectButton,
                                                  String characterInfo) {
        InterfaceConfiguration config = InterfaceConfiguration.getShared();
        VBox previewBox = new VBox(10);
        previewBox.setStyle("-fx-alignment: center;");

        HBox spriteContainer = new HBox();
        spriteContainer.setPrefSize(200, 200); // Fixed size for all sprites
        spriteContainer.setStyle("-fx-alignment: center;");
        spriteContainer.getChildren().add(character.getSpriteView());


        // Scale the sprite view to make it visible
        character.getSpriteView().setScaleX(2.0);
        character.getSpriteView().setScaleY(2.0);

        Text infoText = new Text(characterInfo);
        infoText.setStyle(config.getCharacterInfoStyle());
        //infoText.setWrappingWidth(200); // Optional: Limit the width of the text


        previewBox.getChildren().addAll(spriteContainer, selectButton, infoText);

        return previewBox;
    }


    /**
     * Lance le jeu avec le personnage sélectionné.
     *
     * @param mainApp L'application principale, utilisée pour démarrer la scène de jeu.
     * @param config La configuration de l'interface.
     * @param selectedCharacter Le personnage sélectionné par l'utilisateur.
     */
    private static void startGame(Main mainApp, InterfaceConfiguration config, GameCharacter selectedCharacter) {
        mainApp.stopParallax();
        selectedCharacter.getAnimations().setState(CharacterAnimation.CharacterState.IDLE);
        mainApp.setSceneContent(GameScene2.create(mainApp, config, selectedCharacter));
    }
}