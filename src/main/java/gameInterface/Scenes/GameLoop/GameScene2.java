package gameInterface.Scenes.GameLoop;

import eu.telecomnancy.rpg.GameCharacter;
import eu.telecomnancy.rpg.GameConfiguration;
import gameInterface.InterfaceConfiguration;
import gameInterface.Main;
import gameInterface.Scenes.*;
import gameInterface.character.CharacterAnimation;
import gameInterface.helpers.ButtonStyleHelper;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private static final double CORNER_RADIUS = 8;
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



        setupHealthBars(gameContainer);
        updateHealthBars();










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
        gameManager.setGameContainer(gameContainer);

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

    private static void setupHealthBars(Pane gameContainer) {
        InterfaceConfiguration config = InterfaceConfiguration.getShared();

        playerHealthBar = new Rectangle(HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);
        playerHealthBar.setStyle("-fx-fill: rgb(1,135,1); -fx-stroke: #3c1d1d;");
        playerHealthBar.setX(20);
        playerHealthBar.setY(20);
        playerHealthBar.setArcWidth(CORNER_RADIUS);
        playerHealthBar.setArcHeight(CORNER_RADIUS);


        enemyHealthBar = new Rectangle(HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT);
        enemyHealthBar.setStyle("-fx-fill: rgb(147,2,2); -fx-stroke: #3c1d1d;");
        enemyHealthBar.setX(config.getWindowWidth() - HEALTH_BAR_WIDTH - 20);
        enemyHealthBar.setY(20);
        enemyHealthBar.setArcWidth(CORNER_RADIUS);
        enemyHealthBar.setArcHeight(CORNER_RADIUS);

        gameContainer.getChildren().addAll(playerHealthBar, enemyHealthBar);






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
                () -> {
                    gameManager.processPlayerAttack();
                    performEnemiesAttack();
                    updateHealthBars();
                });


        System.out.println("Animation and player attack is finished");

        //TODO: update healthBar
    }


    public static void performEnemiesAttack() {
        InterfaceConfiguration config = InterfaceConfiguration.getShared();
        CharacterAnimationManager characterAnimationManager = CharacterAnimationManager.getInstance();


        //TODO: check if the enemey team is wiped
        System.out.println(gameManager.getCurrentLevel());

        if(gameManager.allEnemiesAreDead()) {
            System.out.println("All enemies are dead");
            VBox gameWonScene = GameWonScene.create(
                    gameManager.getMainApp(),
                    config,
                    gameManager.getPlayerCharacter(),
                    gameManager.getCurrentLevel(),
                    gameManager.getScore()
            );

            gameManager.getMainApp().setSceneContent(gameWonScene);
            return;
        }



        GameCharacter attackingEnemy = gameManager.getEnemies().get(0);
        double playerPosition = gameManager.getPlayerAnimation().getSpriteView().getX();
        double attackingEnemyPosition = attackingEnemy.getAnimations().getSpriteView().getX();

        double attackingSpace = 150;

        double initialPosition = attackingEnemy.getAnimations().getSpriteView().getTranslateX();

        double targetX = playerPosition - attackingEnemyPosition + attackingSpace;




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
                sequence,
                () -> {
                    gameManager.processEnemyAttack();
                    updateHealthBars();
                    gameManager.setPlayerTurn(true);
                });




        System.out.println("Player Health:" + gameManager.getPlayerCharacter().getHealth());
        System.out.println("Enemy Health:" + attackingEnemy.getHealth());

    }







    private static void handleLevelTransition() {


        SequentialTransition sequence = new SequentialTransition();

        // Animation de mort
        ParallelTransition deathAnimations = new ParallelTransition();
        for (GameCharacter enemy : gameManager.getEnemies()) {
            CharacterAnimation enemyAnim = enemy.getAnimations();

            PauseTransition deathAnimation = new PauseTransition(Duration.ZERO);
            deathAnimation.setOnFinished(e -> enemyAnim.setState(CharacterAnimation.CharacterState.DEATH));

            int deathFrames = enemyAnim.getFrameCount(CharacterAnimation.CharacterState.DEATH);
            deathAnimations.getChildren().add(deathAnimation);
        }
        sequence.getChildren().add(deathAnimations);

        // Attendre la fin de l'animation DEATH
        sequence.getChildren().add(new PauseTransition(Duration.seconds(1.5)));

        // Fade out
        ParallelTransition fadeOuts = new ParallelTransition();
        for (GameCharacter enemy : gameManager.getEnemies()) {
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), enemy.getAnimations().getSpriteView());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOuts.getChildren().add(fadeOut);
        }
        sequence.getChildren().add(fadeOuts);

        // Popup de niveau
        sequence.getChildren().add(new PauseTransition(Duration.seconds(0.5)));
        sequence.setOnFinished(e -> showLevelUpPopup());

        sequence.play();
    }


    public static void handleLevelTransition2() {

        System.out.println("Current level:" + gameManager.getCurrentLevel());
        System.out.println("Enemy team size:" + gameManager.getEnemies().size());

        for (GameCharacter enemy : gameManager.getEnemies()) {

            enemy.getAnimations().setState(CharacterAnimation.CharacterState.DEATH);
            int deathDuration = enemy.getAnimations().getFrameCount(CharacterAnimation.CharacterState.DEATH);

            FadeTransition fadeOut = new FadeTransition(Duration.millis(deathDuration * 100), enemy.getAnimations().getSpriteView());

            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> {gameManager.setEnemies(new ArrayList<>());});
            fadeOut.play();


        }


    }

    private static void showLevelUpPopup() {
        Label levelPopup = createLevelPopup();
        gameManager.getGameContainer().getChildren().add(levelPopup);

        Timeline popupAnimation = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(levelPopup.scaleXProperty(), 0),
                        new KeyValue(levelPopup.scaleYProperty(), 0)),
                new KeyFrame(Duration.seconds(0.3),
                        new KeyValue(levelPopup.scaleXProperty(), 1.2),
                        new KeyValue(levelPopup.scaleYProperty(), 1.2)),
                new KeyFrame(Duration.seconds(0.4),
                        new KeyValue(levelPopup.scaleXProperty(), 1),
                        new KeyValue(levelPopup.scaleYProperty(), 1)),
                new KeyFrame(Duration.seconds(1.5)),
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(levelPopup.scaleXProperty(), 0),
                        new KeyValue(levelPopup.scaleYProperty(), 0))
        );

        popupAnimation.setOnFinished(e -> {
            gameManager.getGameContainer().getChildren().remove(levelPopup);
            setupNewLevel();
        });
        popupAnimation.play();
    }

    private static void setupNewLevel() {
        // Nettoyer les anciens ennemis
        for (GameCharacter enemy : gameManager.getEnemies()) {
            gameManager.getGameContainer().getChildren().remove(enemy.getAnimations().getSpriteView());
        }
        enemyAnimations.clear();

        // Créer les nouveaux ennemis
        gameManager.handleLevelComplete();
        setupEnemiesPosition();

        // Mise à jour de l'interface
        //updateHealthBars();
        updateStatusLabel();

        // Reset des positions et états

        gameManager.getPlayerAnimation().setState(CharacterAnimation.CharacterState.IDLE);

        // Faire apparaître les nouveaux ennemis avec fade in
        for (GameCharacter enemy : gameManager.getEnemies()) {
            CharacterAnimation enemyAnim = enemy.getAnimations();
            enemyAnim.getSpriteView().setOpacity(0);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), enemyAnim.getSpriteView());
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.play();
        }
    }

    private static Label createLevelPopup() {
        Label popup = new Label("Level " + (gameManager.getCurrentLevel() + 1));
        popup.setStyle("-fx-background-color: rgba(0,0,0,0.8); " +
                "-fx-text-fill: white; " +
                "-fx-padding: 20px 40px; " +
                "-fx-font-size: 24px; " +
                "-fx-background-radius: 10px;");
        popup.setLayoutX(gameManager.getGameContainer().getWidth()/2 - 100);
        popup.setLayoutY(gameManager.getGameContainer().getHeight()/2 - 50);
        return popup;
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
            if (gameManager.isPlayerTurn()) {

                updateStatusLabel();

                performPlayerAttack();



            }

        });


        recruitButton.setOnAction(e -> {
            if (gameManager.isPlayerTurn()) {

                handleRecruit();
            }

        });


        visitorButton.setOnAction(e -> {
            if (gameManager.isPlayerTurn()) {
                VisitorSelectionPopup.show(config);
            }

        });


        mainApp.getScene().setOnKeyPressed(event -> {
            if (gameManager.isPlayerTurn()) {
                switch (event.getCode()) {
                    case A -> {

                        if (gameManager.isPlayerTurn()) {

                            attackButton.fire();
                            gameManager.setPlayerTurn(false);
                        }

                    }
                    case R -> {
                        if (gameManager.isPlayerTurn()) {
                            recruitButton.fire();

                        }

                    }
                    case B -> {
                        if (gameManager.isPlayerTurn()) {
                            visitorButton.fire();
                        }

                    }
                    case W -> {
                        if (gameManager.isPlayerTurn()) {
                            VBox gameWonScene = GameWonScene.create(
                                    mainApp,
                                    config,
                                    gameManager.getPlayerCharacter(),
                                    gameManager.getCurrentLevel(),
                                    gameManager.getScore()
                            );

                            mainApp.setSceneContent(gameWonScene);

                        }

                    }

                    case ESCAPE -> {
                        mainApp.setSceneContent(CharacterSelectionScene.create(mainApp, config));
                    }

                }
            }
        });


        buttonContainer.getChildren().addAll(attackButton, recruitButton, visitorButton);
        return buttonContainer;
    }





    private static void updateStatusLabel() {
        String turnText = isPlayerTurn ? "Your turn !" : "Enemies turn...";
        statusLabel.setText("Level: " + currentLevel+ " "  + turnText);
    }



    private static void updateHealthBars() {

        double playerHealthPercentage = gameManager.getPlayerHealthPercentage();
        double enemyHealthPercentage = gameManager.getEnemiesHealthPercentage();

        System.out.println("Player health bar :" + playerHealthPercentage);
        System.out.println("Enemies health bar :" + enemyHealthPercentage);



        playerHealthBar.setWidth(gameManager.getPlayerCharacter().getHealth());
        enemyHealthBar.setWidth(gameManager.getEnemiesTotalHealth());
    }










}