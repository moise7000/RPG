package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.Strategy.*;
import org.testng.annotations.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CombatStrategyTest {

    @Test
    public void testStrategyChanges() {
        // Character Creation
        Warrior warrior = new Warrior("Conan");
        Wizard wizard = new Wizard("Gandalf");

        // Initial configuration
        warrior.setHealth(100);
        wizard.setHealth(100);
        int baseWarriorDamage = 20;

        // Test with neutral strategy (default)
        int neutralDamage = warrior.attack(baseWarriorDamage);
        wizard.receiveAttack(neutralDamage);
        assertEquals(80, wizard.getHealth()); // 100 - 20

        // Shift to aggressive strategy
        warrior.setCombatStrategy(new AggressiveStrategy());
        int aggressiveDamage = warrior.attack(baseWarriorDamage);
        wizard.receiveAttack(aggressiveDamage);
        assertEquals(50, wizard.getHealth()); // 80 - (20 * 1.5)

        // Testing for increased vulnerability in aggressive mode
        warrior.setCombatStrategy(new AggressiveStrategy());
        wizard.setCombatStrategy(new DefensiveStrategy());

        int wizardBaseDamage = 15;
        int wizardDefensiveDamage = wizard.attack(wizardBaseDamage);
        warrior.receiveAttack(wizardDefensiveDamage);

        // The warrior in aggressive mode receives more damage
        // even if the magician is in defensive mode
        assertTrue(warrior.getHealth() < 90);
    }


    @Test
    public void testAggressiveStrategy() {
        // Character Creation
        Warrior warrior = new Warrior("Conan");
        Wizard wizard = new Wizard("Gandalf");

        // Initial configuration
        warrior.setHealth(100);
        wizard.setHealth(100);

        int baseWarriorDamage = 20;
        int baseWizardDamage = 20;

        warrior.setCombatStrategy(new AggressiveStrategy());

        


    }
}