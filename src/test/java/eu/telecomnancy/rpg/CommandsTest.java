package eu.telecomnancy.rpg.Commands;

import eu.telecomnancy.rpg.*;
import eu.telecomnancy.rpg.Decorator.DecoratorType;
import eu.telecomnancy.rpg.Strategy.NeutralStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Une implémentation simple de GameFacade pour les tests
class TestGameFacade extends GameFacade {
    private final Map<String, List<GameCharacter>> teams = new HashMap<>();

    public TestGameFacade() {
        teams.put("TeamA", new ArrayList<>());
        teams.put("TeamB", new ArrayList<>());
    }

    public void addCharacterToTeam(String teamName, GameCharacter character) {
        teams.get(teamName).add(character);
    }

    @Override
    public Map<String, List<GameCharacter>> getTeams() {
        return teams;
    }

    @Override
    public GameCharacter getCharacterFromTeam(String teamName, int index) {
        return teams.get(teamName).get(index);
    }

    @Override
    public void attackTeam(String attackingTeam, int attackerIndex,
                           String defendingTeam, int defenderIndex) {
        GameCharacter attacker = getCharacterFromTeam(attackingTeam, attackerIndex);
        GameCharacter defender = getCharacterFromTeam(defendingTeam, defenderIndex);
        defender.setHealth(defender.getHealth() - attacker.getAttackPower());
    }

    @Override
    public void healTeam(String teamName) {
        for (GameCharacter character : teams.get(teamName)) {
            character.setHealth(100); // Heal to full health
        }
    }

    @Override
    public void addArmorToCharacter(String teamName, int characterIndex) {
        GameCharacter character = getCharacterFromTeam(teamName, characterIndex);
        character.setHealth(character.getHealth() + 20); // Simple armor implementation
    }
}

class CommandTests {
    private TestGameFacade gameFacade;
    private GameInvoker gameInvoker;
    private static final String TEAM_A = "TeamA";
    private static final String TEAM_B = "TeamB";

    @BeforeEach
    void setUp() {
        gameFacade = new TestGameFacade();
        gameInvoker = new GameInvoker();

        // Initialiser les personnages de test
        NeutralStrategy neutralStrategy = new NeutralStrategy();
        CharacterCreator creator = new WarriorCreator();

        Warrior attacker = (Warrior) creator.create("Hercules", 100, neutralStrategy);
        Warrior defender = (Warrior) creator.create("Achilles", 100, neutralStrategy);


        gameFacade.addCharacterToTeam(TEAM_A, attacker);
        gameFacade.addCharacterToTeam(TEAM_B, defender);
    }

    @Test
    void testAttackCommand() {
        // Arrange
        GameCharacter defender = gameFacade.getCharacterFromTeam(TEAM_B, 0);
        int initialHealth = defender.getHealth();
        AttackCommand attackCommand = new AttackCommand(gameFacade, TEAM_A, 0, TEAM_B, 0);

        // Act
        attackCommand.execute();

        // Assert
        assertEquals(initialHealth - 10, defender.getHealth(),
                "La santé du défenseur devrait être réduite de 20 points");

        // Test undo
        attackCommand.undo();
        assertEquals(initialHealth, defender.getHealth(),
                "La santé du défenseur devrait être restaurée après undo");
    }

    @Test
    void testHealTeamCommand() {
        // Arrange
        GameCharacter character = gameFacade.getCharacterFromTeam(TEAM_A, 0);
        character.setHealth(50); // Blesser le personnage
        HealTeamCommand healCommand = new HealTeamCommand(gameFacade, TEAM_A);

        // Act
        healCommand.execute();

        // Assert
        assertEquals(100, character.getHealth(),
                "Le personnage devrait être complètement soigné");

        // Test undo
        healCommand.undo();
        assertEquals(50, character.getHealth(),
                "La santé devrait revenir à la valeur précédente");
    }

    @Test
    void testAddDecoratorCommand() {
        // Arrange
        GameCharacter character = gameFacade.getCharacterFromTeam(TEAM_A, 0);
        int initialHealth = character.getHealth();
        AddDecoratorCommand decoratorCommand = new AddDecoratorCommand(
                gameFacade, TEAM_A, 0, DecoratorType.ARMOR
        );

        // Act
        decoratorCommand.execute();

        // Assert
        assertEquals(initialHealth + 20, character.getHealth(),
                "L'armure devrait augmenter la santé de 20 points");
    }



    @Test
    void testGameInvokerSequence() {
        // Arrange
        GameCharacter defender = gameFacade.getCharacterFromTeam(TEAM_B, 0);
        GameCharacter attacker = gameFacade.getCharacterFromTeam(TEAM_A, 0);
        int initialHealth = defender.getHealth();

        AttackCommand attack1 = new AttackCommand(gameFacade, TEAM_A, 0, TEAM_B, 0);
        AttackCommand attack2 = new AttackCommand(gameFacade, TEAM_A, 0, TEAM_B, 0);

        // Act & Assert - Test d'ajout et d'exécution
        gameInvoker.addCommand(attack1);
        gameInvoker.addCommand(attack2);
        assertTrue(gameInvoker.hasCommands(), "La file devrait contenir des commandes");

        gameInvoker.executeNextCommand();
        System.out.println(defender.getHealth());

        assertEquals(initialHealth - 10, defender.getHealth(),
                "La première attaque devrait réduire la santé de 20");
//
        gameInvoker.executeNextCommand();
        assertEquals(initialHealth - 20, defender.getHealth(),
                "La deuxième attaque devrait réduire la santé de 20 supplémentaires");

        // Test undo
        gameInvoker.undoLastCommand();
        assertEquals(initialHealth - 10, defender.getHealth(),
                "L'undo devrait annuler la dernière attaque");

         //Test clear
        gameInvoker.clearQueue();
        assertFalse(gameInvoker.hasCommands(), "La file devrait être vide après clear");
    }

    @Test
    void testComplexCommandSequence() {
        // Arrange
        GameCharacter attacker = gameFacade.getCharacterFromTeam(TEAM_A, 0);
        GameCharacter defender = gameFacade.getCharacterFromTeam(TEAM_B, 0);
        int initialDefenderHealth = defender.getHealth();

        // Créer une séquence de commandes
        AttackCommand attackCommand = new AttackCommand(gameFacade, TEAM_A, 0, TEAM_B, 0);
        AddDecoratorCommand armorCommand = new AddDecoratorCommand(
                gameFacade, TEAM_B, 0, DecoratorType.ARMOR
        );
        HealTeamCommand healCommand = new HealTeamCommand(gameFacade, TEAM_B);

        // Act
        gameInvoker.addCommand(attackCommand);  // -10 health
        gameInvoker.addCommand(armorCommand);   // +10 health
        gameInvoker.addCommand(healCommand);    // full health

        gameInvoker.executeAllCommands();

        // Assert
        assertEquals(100, defender.getHealth(),
                "La santé finale devrait être pleine après la séquence");
    }
}