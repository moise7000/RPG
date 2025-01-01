package gameInterface.Scenes;

import eu.telecomnancy.rpg.GameCharacter;
import gameInterface.InterfaceConfiguration;
import gameInterface.Main;
import gameInterface.character.CharacterAnimation;
import gameInterface.helpers.ButtonStyleHelper;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class GameScene {
    private static CharacterAnimation playerCharacterAnimation;
    private static GameCharacter playerCharacter;
    private static List<CharacterAnimation> enemyAnimations;
    private static List<GameCharacter> enemies;
    private static boolean isPlayerTurn = true;
    private static double playerInitialX;
    private static double playerInitialY;

    private static Label turnLabel;
    private static int currentLevel = 1;
    private static Pane gameContainer;
    private static Main mainApp;

    public static VBox create(Main mainApp, InterfaceConfiguration config, GameCharacter selectedCharacter) {
        playerCharacterAnimation = selectedCharacter.getAnimations();
        playerCharacter = selectedCharacter;
        enemies = createEnemies(currentLevel);
        enemyAnimations = new ArrayList<>();


        VBox root = new VBox(20);
        root.setStyle("-fx-alignment: center;");


        Pane gameContainer = new Pane();
        gameContainer.setPrefSize(config.getWindowWidth(), config.getWindowHeight());




        // Characters Position
        setupPlayerPosition(config);
        setupEnemiesPosition(config);



        gameContainer.getChildren().add(playerCharacterAnimation.getSpriteView());
        for (CharacterAnimation enemyAnim : enemyAnimations) {
            gameContainer.getChildren().add(enemyAnim.getSpriteView());
        }

        // Création des boutons de contrôle
        HBox controlButtons = createControlButtons(config, mainApp);

        // Bouton retour
        Button backButton = new Button(config.getExitButtonLabel());
        backButton.setStyle(config.getExitButtonStyle());
        backButton.setOnAction(e -> {
            isPlayerTurn = true;
            mainApp.setSceneContent(CharacterSelectionScene.create(mainApp, config));

                }
            );

        ButtonStyleHelper.applyHoverStyle(backButton, config.getExitButtonStyle(), config.getExitButtonHoverStyle());

        // Espacement
        Region spacer = new Region();
        spacer.setPrefHeight(20);


        // Assemblage de la scène
        root.getChildren().addAll(gameContainer, controlButtons, spacer, backButton );

        return root;
    }




    private static void setupPlayerPosition(InterfaceConfiguration config) {
        playerInitialX = 0;
        playerInitialY = config.getWindowHeight() - 300;
        playerCharacterAnimation.getSpriteView().setX(playerInitialX);
        playerCharacterAnimation.getSpriteView().setY(playerInitialY);
        playerCharacterAnimation.getSpriteView().setScaleX(2.0);
        playerCharacterAnimation.getSpriteView().setScaleY(2.0);
    }





    private static void performAttackAnimation() {
        playerCharacterAnimation.setState(CharacterAnimation.CharacterState.ATTACK);
        int attackAnimationFrameCount = playerCharacterAnimation.getFrameCount(playerCharacterAnimation.getCurrentState());
        PauseTransition pause = new PauseTransition(Duration.millis(attackAnimationFrameCount * 100));
        pause.setOnFinished(event -> playerCharacterAnimation.setState(CharacterAnimation.CharacterState.IDLE));
        pause.play();
    }




    private static void performRunAndAttackAnimation(InterfaceConfiguration config) {
        double targetX = config.getWindowWidth() - 300; // Position proche des ennemis
        double durationInSeconds = 1.5; // Durée du déplacement vers l'ennemi

        // Animation de course
        playerCharacterAnimation.setState(CharacterAnimation.CharacterState.MOVE);

        // Déplacement progressif avec TranslateTransition
        TranslateTransition moveToEnemy = new TranslateTransition(Duration.seconds(durationInSeconds), playerCharacterAnimation.getSpriteView());
        moveToEnemy.setToX(targetX);

        // Une fois que le déplacement est terminé
        moveToEnemy.setOnFinished(event -> {
            // Jouer l'animation d'attaque
            playerCharacterAnimation.setState(CharacterAnimation.CharacterState.ATTACK);
            int attackAnimationFrameCount = playerCharacterAnimation.getFrameCount(playerCharacterAnimation.getCurrentState());
            double attackDurationMillis = attackAnimationFrameCount * 100;

            // Calculer le moment pour déclencher l'animation Hit des ennemis (à la moitié de l'animation d'attaque)
            PauseTransition hitDelay = new PauseTransition(Duration.millis(attackDurationMillis / 2));
            hitDelay.setOnFinished(event2 -> {
                for (CharacterAnimation enemyAnim : enemyAnimations) {
                    enemyAnim.setState(CharacterAnimation.CharacterState.HIT);
                }
            });

            // Remettre les ennemis à l'état IDLE après l'animation Hit
            PauseTransition attackPause = new PauseTransition(Duration.millis(attackDurationMillis));
            attackPause.setOnFinished(event3 -> {
                for (CharacterAnimation enemyAnim : enemyAnimations) {
                    enemyAnim.setState(CharacterAnimation.CharacterState.IDLE);
                }

                // Préparer pour le retour
                playerCharacterAnimation.setState(CharacterAnimation.CharacterState.MOVE);
                playerCharacterAnimation.getSpriteView().setScaleX(-2.0); // Flip horizontal pour le retour

                // Animation de retour à la position initiale
                TranslateTransition returnToStart = new TranslateTransition(Duration.seconds(durationInSeconds), playerCharacterAnimation.getSpriteView());
                returnToStart.setToX(playerInitialX);
                returnToStart.setOnFinished(event4 -> {
                    // Remettre l'état à IDLE après le retour
                    playerCharacterAnimation.setState(CharacterAnimation.CharacterState.IDLE);
                    playerCharacterAnimation.getSpriteView().setScaleX(2.0); // Reflip pour regarder à droite
                    isPlayerTurn = true; // Permet au joueur de jouer à nouveau
                });

                returnToStart.play();
            });

            hitDelay.play(); // Démarrer le délai pour l'animation Hit
            attackPause.play(); // Démarrer le délai pour la fin de l'animation d'attaque
        });

        moveToEnemy.play();
    }









    private static void setupEnemiesPosition(InterfaceConfiguration config) {
        double startX = config.getWindowWidth() - 200;
        double startY = config.getWindowHeight() - 300;

        for (int i = 0; i < enemies.size(); i++) {
            CharacterAnimation enemyAnim = enemies.get(i).getAnimations();
            GameCharacter enemy = enemies.get(i);
            if (Objects.equals(enemy.getName(), "Necromancer")) {
                enemyAnim.getSpriteView().setX(startX  - (i * 100));
                enemyAnim.getSpriteView().setY(startY);
                enemyAnim.getSpriteView().setScaleX(-2.0); // Flip sprite to face left
                enemyAnim.getSpriteView().setScaleY(2.0);
            } else {
                enemyAnim.getSpriteView().setX(startX  - (i * 100));
                enemyAnim.getSpriteView().setY(startY + 80);
                enemyAnim.getSpriteView().setScaleX(-2.0); // Flip sprite to face left
                enemyAnim.getSpriteView().setScaleY(2.0);
            }

            enemyAnimations.add(enemyAnim);
        }
    }
    private static List<GameCharacter> createEnemies(int level) {
        List<GameCharacter> enemyList = new ArrayList<>();
        int enemyCount = Math.min(level, 3);

        double p = 0.5; // Probabilité de tomber sur "1" (entre 0.0 et 1.0)



        for (int i = 0; i < enemyCount; i++) {
            Random random = new Random();
            if (random.nextDouble() < p) {
                GameCharacter enemy = InterfaceConfiguration.getShared().createNecromancerGameCharacter(); // Create with appropriate parameters
                enemy.setHealth(50 * level); // Scale health with level
                enemyList.add(enemy);
            } else {
                GameCharacter enemy = InterfaceConfiguration.getShared().createNightBorneGameCharacter(); // Create with appropriate parameters
                enemy.setHealth(50 * level); // Scale health with level
                enemyList.add(enemy);
            }

        }
        return enemyList;
    }





    private static void handleRecruit(Main mainApp, InterfaceConfiguration config) {
//        if (levelSystem.canRecruitMember()) {
//            GameCharacter clonedPlayerCharacter = playerCharacter.duplicate();
//            levelSystem.recruitMember(clonedPlayerCharacter);
//        }
        mainApp.setSceneContent(GameOverScene.create(mainApp, config, playerCharacter, 1,1));

    }




    private static HBox createControlButtons(InterfaceConfiguration config, Main mainApp) {
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
        attackButton.setOnAction(e -> {
            if (isPlayerTurn) {
                isPlayerTurn = false;
                //performAttackAnimation();
                performRunAndAttackAnimation(config);
            }

        });

        // Action du bouton Recruit (à implémenter selon vos besoins)
        recruitButton.setOnAction(e -> {
            if (isPlayerTurn) {
                isPlayerTurn = false;
                handleRecruit(mainApp, config);
            }

        });

        // Action du bouton Add Decorator (à implémenter selon vos besoins)
        decoratorButton.setOnAction(e -> {
            // TODO: Implémenter la logique d'ajout de décorateur

        });

        buttonContainer.getChildren().addAll(attackButton, recruitButton, decoratorButton);
        return buttonContainer;
    }


    static void killEnemies() {
        for (GameCharacter enemy : enemies ) {
            CharacterAnimation enemyAnimation = enemy.getAnimations();
            enemyAnimation.setState(CharacterAnimation.CharacterState.DEATH);
            int deathAnimationFrameCount = enemyAnimation.getFrameCount(enemyAnimation.getCurrentState());
            PauseTransition pause = new PauseTransition(Duration.millis(deathAnimationFrameCount * 100));
            pause.setOnFinished(event -> enemyAnimation.setState(CharacterAnimation.CharacterState.IDLE));
            pause.play();
        }
    }









}