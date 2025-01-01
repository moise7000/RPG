package gameInterface;

import eu.telecomnancy.rpg.CharacterCreator;
import eu.telecomnancy.rpg.GameCharacter;
import eu.telecomnancy.rpg.Team;
import eu.telecomnancy.rpg.WarriorCreator;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class LevelSystem {
    private static final int MAX_TEAM_SIZE = 3;
    private Team playerTeam;
    private Team enemyTeam;
    private int currentLevel = 1;
    private Label statusLabel;

    public LevelSystem(GameCharacter initialCharacter) {
        playerTeam = new Team.TeamBuilder()
                .setName("Player Team")
                .addPlayer(initialCharacter)
                .build();
        generateEnemyTeam();
        statusLabel = new Label("Level " + currentLevel);
    }

    private void generateEnemyTeam() {
        Team.TeamBuilder enemyBuilder = new Team.TeamBuilder()
                .setName("Enemy Team Level " + currentLevel);

        // Scale enemies based on level
        for (int i = 0; i < Math.min(currentLevel, 3); i++) {
            GameCharacter enemy = createEnemy();
            enemyBuilder.addPlayer(enemy);
        }
        enemyTeam = enemyBuilder.build();
    }

    private GameCharacter createEnemy() {
        // Create scaled enemy based on current level
        // Implementation depends on your GameCharacter constructor
        return InterfaceConfiguration.getShared().createNecromancerGameCharacter();


    }

    public boolean canRecruitMember() {
        return playerTeam.getPlayers().size() < MAX_TEAM_SIZE;
    }

    public void recruitMember(GameCharacter character) {
        if (canRecruitMember()) {
            playerTeam.getPlayers().add(character);
        }
    }

    public boolean processBattle() {
        for (GameCharacter playerChar : playerTeam.getPlayers()) {
            for (GameCharacter enemyChar : enemyTeam.getPlayers()) {
                // Process attacks and update health
                // Return false if player team is defeated
            }
        }

        // If enemy team is defeated, advance to next level
        currentLevel++;
        generateEnemyTeam();
        updateStatus();
        return true;
    }

    private void updateStatus() {
        statusLabel.setText("Level " + currentLevel +
                " | Team Size: " + playerTeam.getPlayers().size() +
                "/" + MAX_TEAM_SIZE);
    }

    public Label getStatusLabel() {
        return statusLabel;
    }
}