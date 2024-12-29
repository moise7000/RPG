package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.Strategy.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
import static java.lang.System.out;



public class CombatStrategyTest {
    private Warrior warrior;
    private Wizard wizard;
    private static final int INITIAL_HEALTH = 100;
    private static final int BASE_DAMAGE = 20;

    @Before
    public void setUp() {
        // Using factories to create the characters
        CharacterCreator warriorCreator = new WarriorCreator();
        CharacterCreator wizardCreator = new WizardCreator();

        warrior = (Warrior) warriorCreator.create("Conan");
        wizard = (Wizard) wizardCreator.create("Gandalf");

        // Initialization of stats for testing
        warrior.setHealth(INITIAL_HEALTH);
        wizard.setHealth(INITIAL_HEALTH);
    }

    @Test
    public void testAggressiveStrategy() {
        // Configuration of the aggressive strategy
        warrior.setCombatStrategy(new AggressiveStrategy());
        wizard.setCombatStrategy(new NeutralStrategy());

        // Configuration of the aggressive strategy
        int damageDealt = warrior.attack(BASE_DAMAGE);



        wizard.receiveAttack(damageDealt);



        // Verify that the damage inflicted is increased (1.5x)
        assertEquals(70, wizard.getHealth()); // 100 - (20 * 1.5) = 70

        // Test the increased vulnerability
        int counterDamage = wizard.attack(BASE_DAMAGE);


        warrior.receiveAttack(counterDamage);

        // The warrior in aggressive mode receives more damage (1.3x)
        assertEquals(74, warrior.getHealth()); // 100 - (20 * 1.3) = 74
    }

    @Test
    public void testDefensiveStrategy() {
        // Setting up the defensive strategy
        warrior.setCombatStrategy(new DefensiveStrategy());
        wizard.setCombatStrategy(new NeutralStrategy());

        // Testing reduced damage in defensive mode
        int damageDealt = warrior.attack(BASE_DAMAGE);
        assertEquals(14, damageDealt); //BASE_DAMAGE = 20 and DEFENSIVE_DAMAGE_MULTIPLIER = 0,7 -> 20*0,7=14
        wizard.receiveAttack(damageDealt);

        // Verifying that the inflicted damage is reduced (0.7x)
        assertEquals(86, wizard.getHealth()); // 100 - (20 * 0.7) = 86

        // Testing improved defense
        int counterDamage = wizard.attack(BASE_DAMAGE);
        assertEquals(BASE_DAMAGE, counterDamage); // wizard strategy is neutral so is "strength" still

        warrior.receiveAttack(counterDamage);


        // The warrior in defensive mode receives less damage (0.6x)
        assertEquals(88, warrior.getHealth()); // 100 - (20 * 0.6) = 88
    }

    @Test
    public void testNeutralStrategy() {
        // Configuring the neutral strategy
        warrior.setCombatStrategy(new NeutralStrategy());
        wizard.setCombatStrategy(new NeutralStrategy());

        // Testing normal damage
        int damageDealt = warrior.attack(BASE_DAMAGE);
        wizard.receiveAttack(damageDealt);

        // Verifying that the damage is normal (1x)
        assertEquals(80, wizard.getHealth()); // 100 - 20 = 80

        // Testing normal defense
        int counterDamage = wizard.attack(BASE_DAMAGE);
        warrior.receiveAttack(counterDamage);

        // The warrior receives normal damage (1x)
        assertEquals(80, warrior.getHealth()); // 100 - 20 = 80
    }
}