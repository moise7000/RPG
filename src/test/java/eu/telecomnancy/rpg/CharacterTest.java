package eu.telecomnancy.rpg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class CharacterTest {
    @Test
    void testCreateCharacter() {
        Warrior warrior = new Warrior("test");
        assertEquals(0, warrior.getHealth());
        
    }


    @Test
    void testGetName() {
        Warrior warrior = new Warrior("Hercules");
        assertEquals("Hercules", warrior.getName());
    }


    @Test
    void testSetHealth() {
        Warrior warrior = new Warrior("Hercules");
        warrior.setHealth(10);
        assertEquals(10, warrior.getHealth());

    }

    @Test
    void testSetExperiencePoint() {
        Warrior warrior = new Warrior("Hercules");
        warrior.setExperiencePoints(10);
        assertEquals(10, warrior.getExperiencePoints());
    }

    @Test
    void testGetLevel() {
        Warrior warrior = new Warrior("Hercules");
        assertEquals(1, warrior.getLevel());
    }

    @Test
    void testSetLevel() {
        Warrior warrior = new Warrior("Hercules");

        assertEquals(1, warrior.getLevel());

        warrior.setLevel(2);

        assertEquals(2, warrior.getLevel());
    }

    
}
