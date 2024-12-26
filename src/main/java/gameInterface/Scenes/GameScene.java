package gameInterface.Scenes;



import eu.telecomnancy.rpg.GameConfiguration;
import gameInterface.InterfaceConfiguration;
import gameInterface.Main;
import gameInterface.character.CharacterAnimation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

public class GameScene {
    private static List<CharacterAnimation> playerTeam = new ArrayList<>();
    private static List<CharacterAnimation> enemyTeam = new ArrayList<>();
    private static int currentLevel = 1;
    private static int playerTurn = 0;
    private static VBox root;
    private static HBox battleField;
    private static VBox actionMenu;
    private static Text levelText;
    private static Text turnText;

    public static VBox create(Main mainApp, InterfaceConfiguration config) {
        GameConfiguration gameConfig = GameConfiguration.getShared();
        root = new VBox(20);
        root.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20;");
        root.setAlignment(Pos.CENTER);

        // Level and Turn Information
        HBox infoBox = createInfoBox(config);

        // Battlefield setup
        battleField = new HBox(100);
        battleField.setAlignment(Pos.CENTER);

        // Team containers
        VBox playerTeamContainer = new VBox(10);
        VBox enemyTeamContainer = new VBox(10);
        playerTeamContainer.setAlignment(Pos.CENTER);
        enemyTeamContainer.setAlignment(Pos.CENTER);

        battleField.getChildren().addAll(playerTeamContainer, enemyTeamContainer);

        // Action menu
        actionMenu = createActionMenu(config, gameConfig, mainApp);

        // Initialize first level
        initializeLevel(config, gameConfig, playerTeamContainer, enemyTeamContainer);

        root.getChildren().addAll(infoBox, battleField, actionMenu);
        return root;
    }

    private static HBox createInfoBox(InterfaceConfiguration config) {
        HBox infoBox = new HBox(50);
        infoBox.setAlignment(Pos.CENTER);

        levelText = new Text("Level: " + currentLevel);
        levelText.setStyle("-fx-font-family: " + config.getFontName() + "; -fx-font-size: 24px; -fx-fill: white;");

        turnText = new Text("Turn: Player");
        turnText.setStyle("-fx-font-family: " + config.getFontName() + "; -fx-font-size: 24px; -fx-fill: white;");

        infoBox.getChildren().addAll(levelText, turnText);
        return infoBox;
    }

    private static VBox createActionMenu(InterfaceConfiguration config,
                                         GameConfiguration gameConfig,
                                         Main mainApp) {
        VBox menu = new VBox(10);
        menu.setAlignment(Pos.CENTER);

        Button attackButton = new Button(config.getAttackButtonLabel());
        Button recruitButton = new Button(config.getRecruitButtonLabel());
        Button endTurnButton = new Button("End Turn");

        attackButton.setStyle(config.getButtonStyle());
        recruitButton.setStyle(config.getButtonStyle());
        endTurnButton.setStyle(config.getButtonStyle());

        // Action handlers
        attackButton.setOnAction(e -> handleAttack());
        recruitButton.setOnAction(e -> showRecruitmentMenu(config, gameConfig));
        endTurnButton.setOnAction(e -> handleEndTurn());

        menu.getChildren().addAll(attackButton, recruitButton, endTurnButton);
        return menu;
    }

    private static void initializeLevel(InterfaceConfiguration interfaceConfig,
                                        GameConfiguration gameConfig,
                                        VBox playerTeamContainer,
                                        VBox enemyTeamContainer) {
        // Clear existing teams
        playerTeam.clear();
        enemyTeam.clear();

        // Add initial player character
        CharacterAnimation playerChar = interfaceConfig.getWizardIdleAnimation();
        playerTeam.add(playerChar);

        // Create enemy team based on level
        int enemyCount = Math.min(currentLevel + 1, gameConfig.getMaxTeamSize());
        for (int i = 0; i < enemyCount; i++) {
            CharacterAnimation enemyChar = interfaceConfig.getEvilWizardIdleAnimation();
            enemyTeam.add(enemyChar);
        }

        // Update UI
        updateTeamDisplay(playerTeamContainer, enemyTeamContainer);

        // Level transition animation
        playLevelStartAnimation();
    }

    private static void updateTeamDisplay(VBox playerTeamContainer, VBox enemyTeamContainer) {
        playerTeamContainer.getChildren().clear();
        enemyTeamContainer.getChildren().clear();

        for (CharacterAnimation character : playerTeam) {
            VBox characterBox = createCharacterBox(character);
            playerTeamContainer.getChildren().add(characterBox);
        }

        for (CharacterAnimation character : enemyTeam) {
            VBox characterBox = createCharacterBox(character);
            enemyTeamContainer.getChildren().add(characterBox);
        }
    }

    private static VBox createCharacterBox(CharacterAnimation character) {
        VBox box = new VBox(5);
        box.setAlignment(Pos.CENTER);

        // Health bar
        ProgressBar healthBar = new ProgressBar(1.0);
        healthBar.setStyle("-fx-accent: #00ff00;");
        healthBar.setPrefWidth(100);

        character.getSpriteView().setScaleX(2.0);
        character.getSpriteView().setScaleY(2.0);

        box.getChildren().addAll(character.getSpriteView(), healthBar);
        return box;
    }

    private static void handleAttack() {
        if (playerTurn >= playerTeam.size()) return;

        // Get attacking character
        CharacterAnimation attacker = playerTeam.get(playerTurn);

        // Play attack animation
        attacker.setState(CharacterAnimation.CharacterState.ATTACK);

        // After animation completes, return to idle
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(800),
                e -> attacker.setState(CharacterAnimation.CharacterState.IDLE)));
        timeline.play();

        // Move to next character's turn
        playerTurn++;
        updateTurnText();
    }

    private static void showRecruitmentMenu(InterfaceConfiguration interfaceConfig,
                                            GameConfiguration gameConfig) {
        if (playerTeam.size() >= gameConfig.getMaxTeamSize()) return;

        VBox recruitMenu = new VBox(10);
        recruitMenu.setStyle("-fx-background-color: rgba(0, 0, 0, 0.9); -fx-padding: 20;");
        recruitMenu.setAlignment(Pos.CENTER);

        // Add recruitment options
        Button recruitWarrior = new Button("Recruit Warrior");
        Button recruitWizard = new Button("Recruit Wizard");
        Button cancel = new Button("Cancel");

        recruitWarrior.setStyle(interfaceConfig.getButtonStyle());
        recruitWizard.setStyle(interfaceConfig.getButtonStyle());
        cancel.setStyle(interfaceConfig.getButtonStyle());

        recruitWarrior.setOnAction(e -> {
            addCharacterToTeam(interfaceConfig.getHeroKnightIdleAnimation());
            root.getChildren().remove(recruitMenu);
        });

        recruitWizard.setOnAction(e -> {
            addCharacterToTeam(interfaceConfig.getWizardIdleAnimation());
            root.getChildren().remove(recruitMenu);
        });

        cancel.setOnAction(e -> root.getChildren().remove(recruitMenu));

        recruitMenu.getChildren().addAll(recruitWarrior, recruitWizard, cancel);

        if (!root.getChildren().contains(recruitMenu)) {
            root.getChildren().add(recruitMenu);
        }
    }

    private static void addCharacterToTeam(CharacterAnimation character) {
        playerTeam.add(character);
        updateTeamDisplay((VBox)battleField.getChildren().get(0),
                (VBox)battleField.getChildren().get(1));
    }

    private static void handleEndTurn() {
        // Reset player turn counter
        playerTurn = 0;
        updateTurnText();

        // Enemy team actions
        for (CharacterAnimation enemy : enemyTeam) {
            enemy.setState(CharacterAnimation.CharacterState.ATTACK);
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(800),
                    e -> enemy.setState(CharacterAnimation.CharacterState.IDLE)));
            timeline.play();
        }

        // Check win condition
        if (enemyTeam.isEmpty()) {
            currentLevel++;
            levelText.setText("Level: " + currentLevel);
            initializeLevel(
                    InterfaceConfiguration.getShared(),
                    GameConfiguration.getShared(),
                    (VBox)battleField.getChildren().get(0),
                    (VBox)battleField.getChildren().get(1)
            );
        }
    }

    private static void updateTurnText() {
        if (playerTurn >= playerTeam.size()) {
            turnText.setText("Turn: End Turn");
        } else {
            turnText.setText("Turn: Player " + (playerTurn + 1));
        }
    }

    private static void playLevelStartAnimation() {
        Text levelStartText = new Text("Level " + currentLevel);
        levelStartText.setStyle("-fx-font-family: 'GothicPixels'; -fx-font-size: 48px; -fx-fill: white;");
        levelStartText.setOpacity(0);

        root.getChildren().add(levelStartText);

        Timeline fadeIn = new Timeline(
                new KeyFrame(Duration.ZERO, e -> levelStartText.setOpacity(1.0)),
                new KeyFrame(Duration.seconds(2), e -> {
                    root.getChildren().remove(levelStartText);
                })
        );
        fadeIn.play();
    }
}