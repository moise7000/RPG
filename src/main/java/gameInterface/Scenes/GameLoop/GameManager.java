package gameInterface.Scenes.GameLoop;

import eu.telecomnancy.rpg.GameCharacter;
import eu.telecomnancy.rpg.GameConfiguration;
import gameInterface.InterfaceConfiguration;
import gameInterface.character.CharacterAnimation;

import java.util.List;
import java.util.ArrayList;

public class GameManager {
    private static GameManager instance;
    private GameCharacter playerCharacter;
    private List<GameCharacter> enemies;
    private boolean isPlayerTurn;
    private int currentLevel;
    private int score;

    private GameManager() {
        this.isPlayerTurn = true;
        this.currentLevel = 1;
        this.score = 0;
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void initializeGame(GameCharacter player) {
        this.playerCharacter = player;
        this.enemies = new ArrayList<>();
        this.isPlayerTurn = true;

        createEnemies(1);
    }

    private void createEnemies(int level) {
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
        GameCharacter enemy = InterfaceConfiguration.getShared().createNightBorneGameCharacter();
        enemy.setHealth(50 * level);
        this.enemies.add(enemy);

    }

    public void processPlayerAttack() {
        if (!isPlayerTurn || playerCharacter.getHealth() <= 0) return;

        int damage = playerCharacter.attack(GameConfiguration.getShared().getBaseDamageAmount());

        for (GameCharacter enemy : enemies) {
            enemy.receiveAttack(damage);
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

    private void handleLevelComplete() {
        currentLevel++;
        score += 100 * currentLevel;
        enemies = new ArrayList<>();
        isPlayerTurn = true;
    }

    private void handleGameOver() {
        // Cette méthode sera appelée par GameScene
    }

    // Getters
    public GameCharacter getPlayerCharacter() { return playerCharacter; }
    public CharacterAnimation getPlayerAnimation() {return playerCharacter.getAnimations();}




    public List<GameCharacter> getEnemies() { return enemies; }
    public boolean isPlayerTurn() { return isPlayerTurn; }
    public int getCurrentLevel() { return currentLevel; }
    public int getScore() { return score; }
}