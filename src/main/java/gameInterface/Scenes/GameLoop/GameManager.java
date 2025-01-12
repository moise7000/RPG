package gameInterface.Scenes.GameLoop;

import eu.telecomnancy.rpg.*;
import gameInterface.InterfaceConfiguration;
import gameInterface.Main;
import gameInterface.Scenes.GameOverScene;
import gameInterface.character.CharacterAnimation;
import javafx.scene.layout.Pane;

import java.util.*;

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
    private Team teamPlayer;
    private int teamHealth;

    private GameManager() {
        this.isPlayerTurn = true;
        this.currentLevel = 1;
        this.score = 0;
        this.mainApp = null;
        this.gameContainer = null;
        this.teamPlayer = null;
        this.teamHealth = 0;
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
        this.currentLevel = 1;
        this.score = 0;
        this.teamPlayer = new Team.TeamBuilder().addPlayer(player).build();
        this.teamHealth = player.getHealth();

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

    /**
     * Gère l'attaque du joueur pendant son tour.
     * L'attaque est effectuée contre tous les ennemis, et la santé des ennemis est réduite en conséquence.
     */
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

    /**
     * Gère l'attaque des ennemis pendant leur tour.
     * Les ennemis attaquent le joueur si ce dernier est toujours en vie.
     */
    public void processEnemyAttack() {
        if (isPlayerTurn || playerCharacter.getHealth() <= 0) return;

        for (GameCharacter enemy : enemies) {
            if (enemy.getHealth() > 0) {
                int damage = enemy.attack(GameConfiguration.getShared().getBaseDamageAmount());
                playerCharacter.receiveAttack(damage);
                teamHealth -= damage;


            }
        }

        isPlayerTurn = true;
        checkGameStatus();
    }


    /**
     * Vérifie l'état du jeu après chaque attaque (du joueur ou des ennemis).
     * Si le joueur est mort, le jeu est terminé. Si tous les ennemis sont morts, le niveau est terminé.
     */
    private void checkGameStatus() {

        if (teamHealth <= 0) {
            handleGameOver();
            return;
        }

        boolean allEnemiesDead = enemies.stream()
                .allMatch(enemy -> enemy.getHealth() <= 0);

        if (allEnemiesDead) {
            handleLevelComplete();
        }
    }

    /**
     * Vérifie si tous les ennemis sont morts.
     *
     * @return true si tous les ennemis sont morts, false sinon.
     */
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



    /**
     * Vérifie si le joueur peut recruter un nouveau membre pour son équipe.
     *
     * @return true si le joueur peut recruter un membre, false sinon.
     */
    public boolean canPlayerRecruitMember() {
        return isPlayerTurn && teamPlayer.getPlayers().size() < GameConfiguration.getShared().getMaxTeamSize();
    }

    /**
     * Recrute un nouveau membre pour l'équipe du joueur.
     * Cette méthode ajoute un membre aléatoire à l'équipe du joueur.
     */
    public void recruitMember() {
        //TODO: Ajouter un membre dans l'équipe du joueur.
        InterfaceConfiguration config = InterfaceConfiguration.getShared();
        List<GameCharacter> members = new ArrayList<>();
        Warrior heroKnight = (Warrior) config.createHeroKnightGameCharacter();
        Wizard wizard = (Wizard) config.createEvilWizardGameCharacter();
        Warrior martialHero = (Warrior) config.createMartialHeroGaleCharacter();

        members.add(heroKnight);
        members.add(wizard);
        members.add(wizard);
        members.add(wizard);
        members.add(martialHero);
        members.add(martialHero);

        Collections.shuffle(members);



        teamPlayer.getPlayers().add(members.get(0));
        teamHealth += members.get(0).getHealth();

    }

    /**
     * Sélectionne un attaquant aléatoire dans l'équipe du joueur.
     *
     * @return Un tuple contenant le personnage attaquant et son index dans l'équipe.
     */
    public AbstractMap.SimpleEntry<GameCharacter, Integer> getAttackerFromTeam() {
        Random random = new Random();
        int index = random.nextInt(getTeamPlayerSize() + 1);
        return new AbstractMap.SimpleEntry<>(teamPlayer.getPlayers().get(index), index);
    }

    /**
     * Obtient la taille actuelle de l'équipe du joueur.
     *
     * @return La taille de l'équipe du joueur.
     */
    public int getTeamPlayerSize() {
        return teamPlayer.getPlayers().size();
    }



    /**
     * Obtient la somme de la santé de tous les membres de l'équipe du joueur.
     *
     * @return La santé totale de l'équipe du joueur.
     */
    public int getTeamPlayerHealth() {
        int health = playerCharacter.getHealth();
        for (GameCharacter ally : teamPlayer.getPlayers()) {
            health += ally.getHealth();
        }
        return health;
    }





    /**
     * Obtient la santé totale de tous les ennemis.
     *
     * @return La somme de la santé de tous les ennemis.
     */
    public double getEnemiesTotalHealth() {
        return enemies.stream().mapToDouble(GameCharacter::getHealth).sum();
    }





    /**
     * Obtient le total des dégâts que l'équipe du joueur peut infliger à l'ensemble des ennemis.
     *
     * @return Le total des dégâts infligés par l'équipe du joueur.
     */
    public int getTotalTeamPlayerDamage() {
        int damage = 0;
        for (GameCharacter ally : teamPlayer.getPlayers()) {
            damage += ally.attack(GameConfiguration.getShared().getBaseDamageAmount());
        }
        damage += playerCharacter.attack(GameConfiguration.getShared().getBaseDamageAmount());
        return damage;
    }



    /**
     * Tue le personnage du joueur, mettant sa santé à zéro.
     */
    public void killPlayer() {
        playerCharacter.setHealth(0);
        checkGameStatus();
    }

    public Team getTeamPlayer() {return teamPlayer;}






    public List<GameCharacter> getEnemies() { return enemies; }
    public boolean isPlayerTurn() { return isPlayerTurn; }
    public void setPlayerTurn(boolean playerTurn) { this.isPlayerTurn = playerTurn; }
    public void isNotPlayerTurn() {this.isPlayerTurn = false;}
    public int getCurrentLevel() { return currentLevel; }
    public int getScore() { return score; }
}