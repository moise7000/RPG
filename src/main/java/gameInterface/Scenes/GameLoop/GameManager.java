package gameInterface.Scenes.GameLoop;

import eu.telecomnancy.rpg.GameCharacter;
import eu.telecomnancy.rpg.GameConfiguration;
import gameInterface.InterfaceConfiguration;
import gameInterface.Main;
import gameInterface.Scenes.GameOverScene;
import gameInterface.character.CharacterAnimation;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.ArrayList;

/**
 * Gère l'état du jeu, y compris le personnage du joueur, les ennemis, les niveaux, et la gestion des tours.
 * Cette classe contient la logique pour les attaques du joueur et des ennemis, le passage entre les niveaux,
 * et le suivi de la santé et du score du joueur.
 */
public class GameManager {
    private static GameManager instance;
    private GameCharacter playerCharacter;
    private List<GameCharacter> enemies;
    private boolean isPlayerTurn;
    private int currentLevel;
    private int score;
    private Main mainApp;
    private Pane gameContainer;

    private GameManager() {
        this.isPlayerTurn = true;
        this.currentLevel = 1;
        this.score = 0;
        this.mainApp = null;
        this.gameContainer = null;
    }

    /**
     * Obtient l'instance unique de {@code GameManager}.
     *
     * @return L'instance unique de {@code GameManager}.
     */
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    /**
     * Initialise le jeu avec le personnage du joueur.
     *
     * @param player Le personnage du joueur
     */
    public void initializeGame(GameCharacter player) {
        this.playerCharacter = player;
        this.enemies = new ArrayList<>();
        this.isPlayerTurn = true;

        createEnemies(1);
    }

    /**
     * Définit l'application principale du jeu.
     *
     * @param mainApp L'application principale du jeu
     */
    public void setMainApp(Main mainApp) {this.mainApp = mainApp;}

    /**
     * Obtient l'application principale du jeu.
     *
     * @return L'application principale du jeu
     */
    public Main getMainApp() {return this.mainApp;}

    /**
     * Définit le conteneur de jeu.
     *
     * @param gameContainer Le conteneur de jeu (panneau graphique)
     */
    public void setGameContainer(Pane gameContainer) {this.gameContainer = gameContainer;}

    /**
     * Obtient le conteneur de jeu.
     *
     * @return Le conteneur de jeu
     */
    public Pane getGameContainer() {return this.gameContainer;}

    /**
     * Crée une liste d'ennemis en fonction du niveau actuel.
     *
     * @param level Le niveau actuel
     */
    public void createEnemies(int level) {
        List<GameCharacter> enemyList = new ArrayList<>();
        int enemyCount = Math.min(level, 3);

//        for (int i = 0; i < enemyCount; i++) {
//            if (Math.random() < 0.5) {
//                GameCharacter enemy = InterfaceConfiguration.getShared().createNecromancerGameCharacter();
//                enemy.setHealth(50 * level);
//                enemyList.add(enemy);
//            } else {
//                GameCharacter enemy = InterfaceConfiguration.getShared().createNightBorneGameCharacter();
//                enemy.setHealth(50 * level);
//                enemyList.add(enemy);
//            }
//        }
        GameCharacter enemy = InterfaceConfiguration.getShared().createNecromancerGameCharacter();
        //enemy.setHealth(50 * level);
        this.enemies.add(enemy);

    }

    public void setEnemies(List<GameCharacter> enemies) {this.enemies = enemies;}
    public void resetEnemies() {this.enemies.clear();}

    public void processPlayerAttack() {
        if (!isPlayerTurn || playerCharacter.getHealth() <= 0) return;

        int damage = playerCharacter.attack(GameConfiguration.getShared().getBaseDamageAmount());

        for (GameCharacter enemy : enemies) {
            enemy.receiveAttack(damage);
            System.out.println("In process Player attack, enemy health: "+ enemy.getHealth());
        }

        isPlayerTurn = false;
        checkGameStatus();
    }

    public void processEnemyAttack() {
        if (isPlayerTurn || playerCharacter.getHealth() <= 0) return;

        for (GameCharacter enemy : enemies) {
            if (enemy.getHealth() > 0) {
                int damage = enemy.attack(GameConfiguration.getShared().getBaseDamageAmount());
                playerCharacter.receiveAttack(damage);
            }
        }

        isPlayerTurn = true;
        checkGameStatus();
    }

    private void checkGameStatus() {
        if (playerCharacter.getHealth() <= 0) {
            handleGameOver();
            return;
        }

        boolean allEnemiesDead = enemies.stream()
                .allMatch(enemy -> enemy.getHealth() <= 0);

        if (allEnemiesDead) {
            handleLevelComplete();
        }
    }

    public boolean allEnemiesAreDead() {
        return enemies.stream().allMatch(enemy -> enemy.getHealth() <= 0);
    }




    void handleLevelComplete() {
        currentLevel++;
        score += 100 * currentLevel;
        //enemies = new ArrayList<>();
        isPlayerTurn = true;
    }




    private void handleGameOver() {
        System.out.println("Game Over");

        InterfaceConfiguration config = InterfaceConfiguration.getShared();
        mainApp.setSceneContent(GameOverScene.create(mainApp, config, playerCharacter, currentLevel,score));
    }

    // Getters
    public GameCharacter getPlayerCharacter() { return playerCharacter; }
    public CharacterAnimation getPlayerAnimation() {return playerCharacter.getAnimations();}



    public boolean canPlayerRecruitMember() {
        //TODO: Implémenter le test sur la possibilité de recrutement du joueur.
        return isPlayerTurn;
    }

    public void recruitMember(GameCharacter member) {
        //TODO: Ajouter un membre dans l'équipe du joueur.
    }


    public double getPlayerHealthPercentage() {
        //TODO: change GameCharacter to give them a max health and then the fuction will be juste p.getHealth / p.maxHealth * 100
        return (double) playerCharacter.getHealth() / 200;
    }

    public double getEnemiesTotalHealth() {
        return enemies.stream().mapToDouble(GameCharacter::getHealth).sum();
    }

    public double getEnemiesMaxHealth() {
        return enemies.stream().mapToDouble(GameCharacter::getMaxHealth).sum();
    }

    public double getEnemiesHealthPercentage() {
        return getEnemiesTotalHealth() / getEnemiesMaxHealth();
    }




    public void killPlayer() {
        playerCharacter.setHealth(0);
        checkGameStatus();
    }







    public List<GameCharacter> getEnemies() { return enemies; }
    public boolean isPlayerTurn() { return isPlayerTurn; }
    public void setPlayerTurn(boolean playerTurn) { this.isPlayerTurn = playerTurn; }
    public void isNotPlayerTurn() {this.isPlayerTurn = false;}
    public int getCurrentLevel() { return currentLevel; }
    public int getScore() { return score; }
}