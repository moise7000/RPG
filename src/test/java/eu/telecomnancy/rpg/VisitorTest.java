package eu.telecomnancy.rpg;
import eu.telecomnancy.rpg.Visitors.*;
import eu.telecomnancy.rpg.Visitors.Team.TeamBuffVisitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VisitorTest {
    private Warrior warrior;
    private Wizard wizard;
    private Team heroTeam;
    private Team wizardTeam;

    @BeforeEach
    void setUp() {
        // Creating characters with controlled initial states
        warrior = new Warrior("Aragorn");
        warrior.setHealth(50);
        warrior.setLevel(2);
        warrior.setStrength(20);

        wizard = new Wizard("Gandalf");
        wizard.setHealth(40);
        wizard.setLevel(3);
        wizard.setIntelligence(25);

        // Creation of teams
        heroTeam = new Team.TeamBuilder()
                .setName("Hero Team")
                .addPlayer(warrior)
                .addPlayer(wizard)
                .build();

        wizardTeam = new Team.TeamBuilder()
                .setName("Wizard Team")
                .addPlayer(new Wizard("Merlin"))
                .addPlayer(new Wizard("Saruman"))
                .build();
    }

    @Test
    void testBuffVisitor() {
        // Arrange
        BuffVisitor buffVisitor = new BuffVisitor();

        // Initial states
        int initialWarriorHealth = warrior.getHealth();
        int initialWarriorStrength = warrior.getStrength();
        int initialWarriorLevel = warrior.getLevel();
        int initialWarriorExperiencePoints = warrior.getExperiencePoints();

        int initialWizardHealth = wizard.getHealth();
        int initialWizardIntelligence = wizard.getIntelligence();
        int initialWizardExperiencePoints = wizard.getExperiencePoints();
        int initialWizardLevel = wizard.getLevel();



        // Act
        buffVisitor.visit(warrior);
        buffVisitor.visit(wizard);

        // Assert - Warrior
        assertEquals(initialWarriorHealth + 10,
                warrior.getHealth(),
                "Warrior health should be buffed");
        assertEquals(initialWarriorStrength + 10,
                warrior.getStrength(),
                "Warrior strength should be buffed");

        assertEquals(initialWarriorLevel + 1, warrior.getLevel(),"Warrior level should be buffed");

        assertEquals(initialWarriorExperiencePoints + 20, warrior.getExperiencePoints(),
                "Warrior experience points should be buffed");

        // Assert - Wizard
        assertEquals(initialWizardHealth + 50,
                wizard.getHealth(),
                "Wizard health should be buffed");
        assertEquals(initialWizardIntelligence + 30,
                wizard.getIntelligence(),
                "Wizard intelligence should be buffed");
        assertEquals(initialWizardLevel + 1,
                wizard.getLevel(),
                "Wizard level should be buffed");

        assertEquals(initialWizardExperiencePoints + 50,
                wizard.getExperiencePoints(),
                "Wizard experience points should be buffed");
    }

    @Test
    void testDamageVisitor() {
        // Arrange
        DamageVisitor damageVisitor = new DamageVisitor();
        int initialWarriorHealth = warrior.getHealth();
        int initialWizardHealth = wizard.getHealth();

        // Act
        damageVisitor.visit(warrior);
        damageVisitor.visit(wizard);

        // Assert
        assertTrue(warrior.getHealth() < initialWarriorHealth,
                "Warrior health should decrease");
        assertTrue(wizard.getHealth() < initialWizardHealth,
                "Wizard health should decrease");
        assertTrue(warrior.getHealth() >= 0,
                "Warrior health should not go below 0");
        assertTrue(wizard.getHealth() >= 0,
                "Wizard health should not go below 0");
    }

    @Test
    void testWarriorHealVisitor() {
        // Arrange
        // Reduce health first
        warrior.setHealth(10);


        HealVisitor healVisitor = new HealVisitor();

        int initialWarriorHealth = warrior.getHealth();

        // Act
        healVisitor.visit(warrior);


        // Assert
        assertTrue(warrior.getHealth() > 10,
                "Warrior health should increase");


        assertEquals(initialWarriorHealth + 5,
                warrior.getHealth(),
                "Warriorhealth should increase");
    }

    @Test
    void testWizardHealVisitor() {
        // Arrange
        // Reduce health first

        wizard.setHealth(5);

        HealVisitor healVisitor = new HealVisitor();

        int initialWizardHealth = wizard.getHealth();

        // Act

        healVisitor.visit(wizard);

        // Assert

        assertTrue(wizard.getHealth() > 5,
                "Wizard health should increase");

        assertEquals(initialWizardHealth + 10,
                wizard.getHealth(),
                "Wizard health should increase");
    }

    @Test
    void testTeamBuffVisitor() {
        // Arrange
        TeamBuffVisitor teamBuffVisitor = new TeamBuffVisitor();

        // Store initial states
        int initialWarriorHealth = warrior.getHealth();
        int initialWarriorStrength = warrior.getStrength();
        int initialWizardHealth = wizard.getHealth();
        int initialWizardIntelligence = wizard.getIntelligence();

        // Act
        teamBuffVisitor.visit(heroTeam);

        // Assert

    }
}





