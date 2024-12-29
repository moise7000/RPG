package eu.telecomnancy.rpg;
import eu.telecomnancy.rpg.Decorator.Decorators.ArmoredDecorator;
import eu.telecomnancy.rpg.Decorator.Decorators.InvincibleDecorator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecoratorPatternTest {

    private static final int HEALT_TEST = 100;
    private static final int LOW_HEALT_TEST = 20;


    @Test
    void testDecoratedAndSourceAreDifferent() {
        Warrior warrior = new Warrior("Warrior A");
        warrior.setHealth(HEALT_TEST);
        ArmoredDecorator armoredWarrior = new ArmoredDecorator(warrior);
        assertNotEquals(warrior, armoredWarrior, "Warrior should not be decorated.");
    }

    @Test
    void testArmoredDecoratorDamageReduction() {
        // Arrange: Create a Warrior and wrap it with an ArmoredDecorator
        Warrior warrior = new Warrior("Warrior A");
        warrior.setHealth(HEALT_TEST);

        ArmoredDecorator armoredWarrior = new ArmoredDecorator(warrior);

        // Act: Simulate receiving an attack
        int incomingDamage = 50;
        armoredWarrior.receiveAttack(incomingDamage);

        // Assert: Verify that the damage was reduced
        int expectedHealth = HEALT_TEST - (int) (incomingDamage * (1 - 0.3)); // 30% reduction = 0,25 + (0,05*1)
        assertEquals(expectedHealth, armoredWarrior.getHealth(), "Health should be reduced with damage reduction applied.");
    }

    @Test
    void testInvincibleDecoratorHealthMaintenance() {
        // Arrange: Create a Warrior and wrap it with an InvincibleDecorator
        Warrior warrior = new Warrior("Warrior B");
        warrior.setHealth(LOW_HEALT_TEST); // Low initial health
        warrior.setLevel(5);

        InvincibleDecorator invincibleWarrior = new InvincibleDecorator(warrior);

        // Act: Simulate receiving a lethal attack
        int incomingDamage = 30;
        invincibleWarrior.receiveAttack(incomingDamage);

        // Assert: Verify that health does not drop below the minimum
        int minimumHealth = GameConfiguration.getShared().getMinimumHealth(5);
        assertEquals(minimumHealth, invincibleWarrior.getHealth(), "Health should not drop below the minimum.");
    }

    @Test
    void testChainingDecorators() {
        // Arrange: Create a Warrior and apply both decorators
        Warrior warrior = new Warrior("Warrior C");
        warrior.setHealth(100);
        warrior.setLevel(5);
        ArmoredDecorator armoredWarrior = new ArmoredDecorator(warrior);
        InvincibleDecorator invincibleArmoredWarrior = new InvincibleDecorator(armoredWarrior);

        // Act: Simulate receiving a lethal attack
        int incomingDamage = 100;
        invincibleArmoredWarrior.receiveAttack(incomingDamage);

        // Assert: Verify health reduction and invincibility
        int reducedDamage = (int) (incomingDamage * (1 - 0.2)); // 20% reduction
        int expectedHealth = Math.max(reducedDamage - warrior.getHealth(), GameConfiguration.getShared().getMinimumHealth(5));
        assertEquals(GameConfiguration.getShared().getMinimumHealth(5), invincibleArmoredWarrior.getHealth(),
                "Health should not drop below the minimum after applying both decorators.");
    }

    @Test
    void testDynamicAdditionAndRemovalOfDecorator() {
        // Arrange: Create a Warrior and dynamically add/remove a decorator
        Warrior warrior = new Warrior("Warrior D");
        warrior.setHealth(HEALT_TEST);
        warrior.setLevel(5);

        ArmoredDecorator armoredWarrior = new ArmoredDecorator(warrior);

        // Act: Decorate, attack, and then undecorate
        armoredWarrior.receiveAttack(50);
        int healthAfterArmor = armoredWarrior.getHealth();

        warrior.setHealth(healthAfterArmor);
        warrior.receiveAttack(50); // Simulate attack after undecorating

        // Assert: Verify behavior with and without decorator
        //assertEquals(100 - (int) (50 * (1 - 0.3)), healthAfterArmor, "Health should be reduced correctly with ArmoredDecorator.");
        assertEquals(healthAfterArmor - 50, warrior.getHealth(), "Health should reflect full damage after decorator is removed.");
    }

    @Test
    void testDuplicatingCharacterWithDecorators() {
        // Arrange: Create a Warrior with a decorator
        Warrior warrior = new Warrior("Warrior E");
        warrior.setHealth(100);
        warrior.setLevel(5);
        ArmoredDecorator armoredWarrior = new ArmoredDecorator(warrior);

        // Act: Duplicate the decorated character
        GameCharacter duplicate = armoredWarrior.duplicate();

        // Assert: Verify that the duplicate has the same properties
        assertNotSame(armoredWarrior, duplicate, "Duplicate should be a new instance.");
        assertEquals(armoredWarrior.getHealth(), duplicate.getHealth(), "Health should be the same in the duplicate.");
        assertTrue(duplicate instanceof ArmoredDecorator, "Duplicate should also be decorated.");
    }
}
