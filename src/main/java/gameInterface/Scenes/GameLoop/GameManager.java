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

    public void setMainApp(Main mainApp) {this.mainApp = mainApp;}
    public Main getMainApp() {return this.mainApp;}

    public void setGameContainer(Pane gameContainer) {this.gameContainer = gameContainer;}
    public Pane getGameContainer() {return this.gameContainer;}

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
        GameCharacter enemy = InterfaceConfiguration.getShared().createNecromancerGameCharacter();
        //enemy.setHealth(50 * level);
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

    public boolean allEnemiesAreDead() {
        return enemies.stream().allMatch(enemy -> enemy.getHealth() <= 0);
    }




    void handleLevelComplete() {
        currentLevel++;
        score += 100 * currentLevel;
        enemies = new ArrayList<>();
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






    public void killPlayer() {
        playerCharacter.setHealth(0);
        checkGameStatus();
    }







    public List<GameCharacter> getEnemies() { return enemies; }
    public boolean isPlayerTurn() { return isPlayerTurn; }
    public int getCurrentLevel() { return currentLevel; }
    public int getScore() { return score; }
}