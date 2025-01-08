package gameInterface.Scenes.GameLoop;

import eu.telecomnancy.rpg.GameCharacter;
import eu.telecomnancy.rpg.GameConfiguration;
import gameInterface.InterfaceConfiguration;
import gameInterface.Main;
import gameInterface.Scenes.CharacterSelectionScene;
import gameInterface.Scenes.GameOverScene;
import gameInterface.Scenes.VisitorSelectionPopup;
import gameInterface.character.CharacterAnimation;
import gameInterface.helpers.ButtonStyleHelper;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameScene2 {
    private static CharacterAnimation playerCharacterAnimation;
    private static GameCharacter playerCharacter;
    private static List<CharacterAnimation> enemyAnimations;
    //private static List<GameCharacter> enemies;
    private static boolean isPlayerTurn = true;
    private static double playerInitialX;
    private static double playerInitialY;
    private static Label statusLabel; // Affiche le niveau et le statut du joueur
    private static List<Rectangle> enemyHealthBars;
    private static Rectangle playerHealthBar;
    private static Rectangle enemyHealthBar;
    private static final double HEALTH_BAR_WIDTH = 200;
    private static final double HEALTH_BAR_HEIGHT = 20;
    private static Main mainApp;
    private static Label turnLabel;
    private static int currentLevel = 1;
    private static Pane gameContainer;




    private static GameManager gameManager = GameManager.getInstance();


    public static  VBox create(Main mainApp, InterfaceConfiguration config, GameCharacter selectedCharacter) {
        PositionManager positionManager = PositionManager.getInstance(config.getWindowWidth(), config.getWindowHeight());


        System.out.println(mainApp.getClass());

        playerCharacter = selectedCharacter;
        playerCharacterAnimation = selectedCharacter.getAnimations();


        gameManager.initializeGame(selectedCharacter);
        gameManager.setMainApp(mainApp);








        //enemies = createEnemies(currentLevel);
        enemyAnimations = new ArrayList<>();


        VBox root = new VBox(20);
        root.setStyle("-fx-alignment: center;");


        Pane gameContainer = new Pane();
        gameContainer.setPrefSize(config.getWindowWidth(), config.getWindowHeight());

        playerHealthBar = new Rectangle(HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);
        playerHealthBar.setStyle("-fx-fill: green; -fx-stroke: black;");
        playerHealthBar.setX(20);
        playerHealthBar.setY(20);

// Enemy health bar
        enemyHealthBar = new Rectangle(HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);
        enemyHealthBar.setStyle("-fx-fill: red; -fx-stroke: black;");
        enemyHealthBar.setX(config.getWindowWidth() - HEALTH_BAR_WIDTH - 20);
        enemyHealthBar.setY(20);

        gameContainer.getChildren().addAll(playerHealthBar, enemyHealthBar);


        // Créer le label de statut
        statusLabel = new Label();
        statusLabel.setStyle(config.getLevelStyle());
        updateStatusLabel(); // Mettre à jour le texte initial du label


        // Characters Position
        setupEntitiesPosition();




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
        root.getChildren().addAll(statusLabel, gameContainer, controlButtons, spacer, backButton );

        return root;
    }





    private static void setupEnemiesPosition() {
        InterfaceConfiguration config = InterfaceConfiguration.getShared();
        PositionManager positionManager = PositionManager.getInstance(config.getWindowWidth(), config.getWindowHeight());
        for (int i = 0; i < gameManager.getEnemies().size(); i++) {
            GameCharacter enemy = gameManager.getEnemies().get(i);
            CharacterAnimation enemyAnim = enemy.getAnimations();

            // Utiliser le PositionManager pour positionner l'ennemi
            positionManager.setupEnemyPosition(enemyAnim, enemy, i);

            // Ajouter l'animation à la liste des animations d'ennemis
            enemyAnimations.add(enemyAnim);
        }
    }

    private static void setupEntitiesPosition() {
        InterfaceConfiguration config = InterfaceConfiguration.getShared();
        PositionManager positionManager = PositionManager.getInstance(config.getWindowWidth(), config.getWindowHeight());
        positionManager.setupPlayerPosition(gameManager.getPlayerAnimation());

        setupEnemiesPosition();

    }







    private static void p() {

    }


    private static void performPlayerAttack() {
        InterfaceConfiguration config = InterfaceConfiguration.getShared();
        CharacterAnimationManager characterAnimationManager = CharacterAnimationManager.getInstance();


        double initialPosition = gameManager.getPlayerAnimation().getSpriteView().getTranslateX();
        double targetX = config.getWindowWidth() - 300;
        Duration moveDuration = Duration.seconds(1.5);
        Duration attackDuration = Duration.seconds(1);



        List<CharacterAnimationManager.AnimationStep> sequence =
                characterAnimationManager.createAttackSequence(
                        targetX,
                        initialPosition,
                        moveDuration,
                        attackDuration
                );


        characterAnimationManager.performAnimationSequence(
                CharacterAnimationManager.AnimationDirection.RIGHT,
                gameManager.getPlayerAnimation(),
                sequence,
                GameScene2::performEnemiesAttack);

        gameManager.processPlayerAttack();

        //TODO: update healthBar
    }


    public static void performEnemiesAttack() {
        InterfaceConfiguration config = InterfaceConfiguration.getShared();
        CharacterAnimationManager characterAnimationManager = CharacterAnimationManager.getInstance();

        GameCharacter attackingEnemy = gameManager.getEnemies().get(0);
        double playerPosition = gameManager.getPlayerAnimation().getSpriteView().getX();
        double attackingEnemyPosition = attackingEnemy.getAnimations().getSpriteView().getX();

        double attackingSpace = 150;

        double initialPosition = attackingEnemy.getAnimations().getSpriteView().getTranslateX();

        double targetX = playerPosition - attackingEnemyPosition + attackingSpace;

        System.out.println("Window width "+ config.getWindowWidth());
        System.out.println("playerPosition: " + playerPosition);
        System.out.println("enemyPosition: " + attackingEnemyPosition);
        System.out.println("targetX: " + targetX);
        System.out.println("initialPosition: " + initialPosition);


        Duration moveDuration = Duration.seconds(1.5);
        Duration attackDuration = Duration.seconds(1);

        List<CharacterAnimationManager.AnimationStep> sequence =
                characterAnimationManager.createAttackSequence(
                        targetX,
                        initialPosition,
                        moveDuration,
                        attackDuration
                );

        characterAnimationManager.performAnimationSequence(
                CharacterAnimationManager.AnimationDirection.LEFT,
                attackingEnemy.getAnimations(),
                sequence, null);
    }











    private static void performRunAndAttackAnimation(InterfaceConfiguration config) {
        isPlayerTurn = false;
        double targetX = config.getWindowWidth() - 300; // Position proche des ennemis
        double durationInSeconds = 1.5; // Durée du déplacement vers l'ennemi

        // Animation de course
        playerCharacterAnimation.setState(CharacterAnimation.CharacterState.MOVE);

        // Déplacement progressif avec TranslateTransition
        TranslateTransition moveToEnemy = new TranslateTransition(Duration.seconds(durationInSeconds), playerCharacterAnimation.getSpriteView());
        moveToEnemy.setToX(targetX);

        // Une fois que le déplacement est terminé
        moveToEnemy.setOnFinished(event -> {
            updateStatusLabel();
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
                int damageForEnemy = playerCharacter.attack(GameConfiguration.getShared().getBaseDamageAmount());
                System.out.println("Damage: " + damageForEnemy);

                Random rand = new Random();
                //int randomIndex = rand.nextInt(enemies.size()); // Obtenez le GameCharacter aléatoire
                //GameCharacter randomEnemy = enemies.get(0);

                //randomEnemy.receiveAttack(damageForEnemy);



                //updateHealthBars();
            });

            // Remettre les ennemis à l'état IDLE après l'animation Hit
            PauseTransition attackPause = new PauseTransition(Duration.millis(attackDurationMillis));
            attackPause.setOnFinished(event3 -> {
                updateStatusLabel();
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

                });

                returnToStart.play();
            });

            hitDelay.play(); // Démarrer le délai pour l'animation Hit
            attackPause.play(); // Démarrer le délai pour la fin de l'animation d'attaque
        });

        moveToEnemy.play();

        PauseTransition enemyTurnDelay = new PauseTransition(Duration.seconds(3.5));
        //enemyTurnDelay.setOnFinished(event -> performEnemyTurn(config));
        enemyTurnDelay.play();
    }



//    private static void performEnemyTurn(InterfaceConfiguration config) {
//        // Trouver l'ennemi avec la santé la plus basse
//        GameCharacter weakestEnemy = enemies.stream()
//                .min((e1, e2) -> Integer.compare(e1.getHealth(), e2.getHealth()))
//                .orElse(null);
//
//        if (weakestEnemy == null) {
//            return; // Aucun ennemi
//        }
//
//        CharacterAnimation enemyAnimation = weakestEnemy.getAnimations();
//
//
//        // Position cible (joueur)
//        double targetX = playerInitialX - config.getWindowWidth() + 320; // Ajustez pour viser la position du joueur
//        double durationInSeconds = 1.5; // Durée du déplacement vers le joueur
//
//        System.out.println(playerInitialX);
//
//        // Animation de déplacement vers le joueur
//        enemyAnimation.setState(CharacterAnimation.CharacterState.MOVE);
//        TranslateTransition moveToPlayer = new TranslateTransition(Duration.seconds(durationInSeconds), enemyAnimation.getSpriteView());
//        moveToPlayer.setToX(targetX);
//
//        moveToPlayer.setOnFinished(event -> {
//            // Jouer l'animation d'attaque
//            enemyAnimation.setState(CharacterAnimation.CharacterState.ATTACK);
//            int attackAnimationFrameCount = enemyAnimation.getFrameCount(enemyAnimation.getCurrentState());
//            double attackDurationMillis = attackAnimationFrameCount * 100;
//
//            // Animation Hit sur le joueur
//            PauseTransition hitDelay = new PauseTransition(Duration.millis(attackDurationMillis / 2));
//            hitDelay.setOnFinished(event2 -> {
//
//                //TODO: Hit animation
//                performHitAnimation();
//
//                //TODO: processDamage on player
//                int damageForPlayer = weakestEnemy.attack(GameConfiguration.getShared().getBaseDamageAmount());
//
//
//
//
//                playerCharacter.receiveAttack(damageForPlayer * 1007);
//                if (playerCharacter.getHealth() <= 0) {
//                    mainApp.setSceneContent(GameOverScene.create(mainApp, config, playerCharacter, currentLevel, 1));
//                }
//                updateHealthBars();
//
//
//
//
//
//
//                updateStatusLabel();
//            });
//
//
//
//            // Retour de l'ennemi à sa position initiale
//            PauseTransition attackPause = new PauseTransition(Duration.millis(attackDurationMillis));
//            attackPause.setOnFinished(event3 -> {
//                enemyAnimation.setState(CharacterAnimation.CharacterState.MOVE);
//                if (weakestEnemy.getName().equals("Necromancer")) {
//                    enemyAnimation.getSpriteView().setScaleX(2.0);
//                } else {
//                    enemyAnimation.getSpriteView().setScaleX(4.0);
//                }
//                TranslateTransition returnToStart = new TranslateTransition(Duration.seconds(durationInSeconds), enemyAnimation.getSpriteView());
//                returnToStart.setToX(100);
//                returnToStart.setOnFinished(event4 -> {
//                    enemyAnimation.setState(CharacterAnimation.CharacterState.IDLE);
//                    if (weakestEnemy.getName().equals("Necromancer")) {
//                        enemyAnimation.getSpriteView().setScaleX(-2.0);
//                    } else {
//                        enemyAnimation.getSpriteView().setScaleX(-4.0);
//                    }
//
//                    isPlayerTurn = true; // Retour au tour du joueur
//                    updateStatusLabel();
//                });
//                returnToStart.play();
//            });
//
//            hitDelay.play();
//            attackPause.play();
//        });
//
//        moveToPlayer.play();
//
//    }








//    private static void setupEnemiesPosition(InterfaceConfiguration config) {
//        double startX = config.getWindowWidth() - 200;
//        double startY = config.getWindowHeight() - 400;
//
//        for (int i = 0; i < enemies.size(); i++) {
//            CharacterAnimation enemyAnim = enemies.get(i).getAnimations();
//            GameCharacter enemy = enemies.get(i);
//            if (Objects.equals(enemy.getName(), "Necromancer")) {
//                enemyAnim.getSpriteView().setX(startX  - (i * 100));
//                enemyAnim.getSpriteView().setY(startY);
//                enemyAnim.getSpriteView().setScaleX(-2.0); // Flip sprite to face left
//                enemyAnim.getSpriteView().setScaleY(2.0);
//            } else {
//                enemyAnim.getSpriteView().setX(startX  - (i * 100));
//                enemyAnim.getSpriteView().setY(startY + 80);
//                enemyAnim.getSpriteView().setScaleX(-4.0); // Flip sprite to face left
//                enemyAnim.getSpriteView().setScaleY(4.0);
//
//
//            }
//
//            enemyAnimations.add(enemyAnim);
//        }
//    }










    private static void handleRecruit() {
        InterfaceConfiguration config = InterfaceConfiguration.getShared();
        GameConfiguration gameConfig = GameConfiguration.getShared();

//        if (levelSystem.canRecruitMember()) {
//            GameCharacter clonedPlayerCharacter = playerCharacter.duplicate();
//            levelSystem.recruitMember(clonedPlayerCharacter);
//        }

        if(gameManager.canPlayerRecruitMember()) {
            GameCharacter clonedPlayerMember = gameManager.getPlayerCharacter().duplicate();
            gameManager.recruitMember(clonedPlayerMember);
            //TODO: Mettre à jour la postion de léquipe joueur

        }


        updateStatusLabel();
       //mainApp.setSceneContent(GameOverScene.create(mainApp, config, playerCharacter, 1,1));

    }






    private static HBox createControlButtons(InterfaceConfiguration config, Main mainApp) {
        HBox buttonContainer = new HBox(20);
        buttonContainer.setStyle("-fx-alignment: center;");

        // Création des boutons
        Button attackButton = new Button(config.getAttackButtonLabel());
        Button recruitButton = new Button(config.getRecruitButtonLabel());
        Button visitorButton = new Button(config.getAddVisitorButtonLabel());

        // Style des boutons
        List<Button> controlButtons = List.of(
                attackButton,
                recruitButton,
                visitorButton
        );

        ButtonStyleHelper.applyButtonStyle(controlButtons, config.getButtonStyle());
        ButtonStyleHelper.applyHoverStyle(controlButtons, config.getButtonStyle(), config.getButtonHoverStyle());


        // Action du bouton Attack
        attackButton.setOnAction(e -> {
            if (isPlayerTurn) {
                isPlayerTurn = false;
                updateStatusLabel();

                performPlayerAttack();


            }

        });


        recruitButton.setOnAction(e -> {
            if (isPlayerTurn) {
                isPlayerTurn = false;
                handleRecruit();
            }

        });


        visitorButton.setOnAction(e -> {
            if (isPlayerTurn) {
                VisitorSelectionPopup.show(config);
            }

        });

        buttonContainer.getChildren().addAll(attackButton, recruitButton, visitorButton);
        return buttonContainer;
    }





    private static void updateStatusLabel() {
        String turnText = isPlayerTurn ? "Your turn !" : "Enemies turn...";
        statusLabel.setText("Level: " + currentLevel+ " "  + turnText);
    }



//    private static void updateHealthBars() {
//        // Update player health bar
//        double playerHealthPercentage = (double) playerCharacter.getHealth() / 200;
//        playerHealthBar.setWidth(HEALTH_BAR_WIDTH * Math.max(0, playerHealthPercentage));
//
//        // Update enemy health bar - calculate total enemy health percentage
//        double totalEnemyHealth = enemies.stream().mapToDouble(GameCharacter::getHealth).sum();
//
//        double totalMaxEnemyHealth = enemies.stream().mapToDouble(GameCharacter::getMaxHealth).sum();
//
//        double enemyHealthPercentage = totalEnemyHealth / totalMaxEnemyHealth;
//        enemyHealthBar.setWidth(HEALTH_BAR_WIDTH * Math.max(0, enemyHealthPercentage));
//    }










}