package eu.telecomnancy.rpg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CharacterTest {
    @Test
    void testCreateCharacter() {
        Warrior warrior = new Warrior("test");
        assertEquals(0, warrior.getHealth());
        
    }

    
}
