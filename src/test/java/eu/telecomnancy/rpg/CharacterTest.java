package eu.telecomnancy.rpg;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {
    private Warrior warrior;
    private Wizard wizard;
    private Warrior secondWarrior;

    @Before
    public void setUp() {

        CharacterCreator warriorCreator = new WarriorCreator();
        CharacterCreator wizardCreator = new WizardCreator();

        warrior = (Warrior) warriorCreator.create("Hercules");
        wizard = (Wizard) wizardCreator.create("Gandalf");
        secondWarrior = (Warrior) warriorCreator.create("Achilles");


    }


    @Test
    public void testCreateWarriorCharacter() {assertNotEquals(warrior, null);}

    @Test
    public void testCreateSecondWarriorCharacter() {assertNotEquals(secondWarrior, null);}

    @Test
    public void testWarriorsAreNotEqual() {assertNotEquals(warrior, secondWarrior);}


    @Test
    public void testCreateWizardCharacter() {assertNotEquals(wizard, null);}


    @Test
    public void testGetWarriorName() {assertEquals("Hercules", warrior.getName());}

    @Test
    public void testGetWizardName() {assertEquals("Gandalf", wizard.getName());}


    @Test
    public void testSetWarriorHealth() {
        int initialHealth = warrior.getHealth();
        warrior.setHealth(initialHealth + 10);
        assertNotEquals(initialHealth, warrior.getHealth());
        assertEquals(110, warrior.getHealth());

    }

    @Test
    public void testSetWizardHealth() {
        int initialHealth = wizard.getHealth();
        wizard.setHealth(initialHealth + 10);
        assertNotEquals(initialHealth, wizard.getHealth());
        assertEquals(90, wizard.getHealth());

    }



    @Test
    public void testGetWarriorLevel() {assertEquals(1, warrior.getLevel());}

    @Test
    public void testGetWizardLevel() {assertEquals(1, wizard.getLevel());}

    @Test
    public void testSetWarriorLevel() {
        warrior.setLevel(2);
        assertEquals(2, warrior.getLevel());
    }

    @Test
    public void testSetWizardLevel() {
        warrior.setLevel(3);
        assertEquals(3, warrior.getLevel());
    }

    
}
