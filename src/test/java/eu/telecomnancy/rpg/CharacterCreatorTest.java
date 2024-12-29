package eu.telecomnancy.rpg;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CharacterCreatorTest {

    @Test
    void testCreateWarriorWithDefaultAttributes() {
        // Test creation of Warrior with default attributes
        CharacterCreator creator = new WarriorCreator();
        Warrior warrior = (Warrior) creator.create("Conan");

        assertNotNull(warrior, "Warrior should not be null");
        assertEquals("Conan", warrior.getName(), "Warrior's name should match");
        assertEquals(100, warrior.getHealth(), "Warrior's default health should be 100");
        assertEquals(10, warrior.getStrength(), "Warrior's default strength should match");
        assertEquals(1, warrior.getLevel(), "Warrior's default level should be 1");
    }


    @Test
    void testFactoryMethodProducesCorrectTypeWarrior() {

        CharacterCreator creator = new WarriorCreator();


        GameCharacter character = creator.create("WarriorX");

        // Assert: Verify the object is of the correct type
        assertNotNull(character, "The created character should not be null");
        assertTrue(character instanceof Warrior, "The created character should be of type Warrior");
    }

    @Test
    void testFactoryMethodProducesCorrectTypeWizard() {

        CharacterCreator creator = new WizardCreator();
        GameCharacter character = creator.create("WarriorX");

        // Assert: Verify the object is of the correct type
        assertNotNull(character, "The created character should not be null");
        assertTrue(character instanceof Wizard, "The created character should be of type Warrior");
    }

    @Test
    void testFactoryAbstractionIsRespected() {

        CharacterCreator creator = new WarriorCreator();
        GameCharacter character = creator.create("Hero");

        // Assert: Ensure the creation process respects abstraction
        assertNotNull(character, "The created character should not be null");
        assertEquals("Hero", character.getName(), "The character's name should match the input");
    }

    @Test
    void testDifferentFactoriesProduceDifferentObjects() {
        // Arrange: Create multiple factories
        CharacterCreator warriorCreator = new WarriorCreator();
        CharacterCreator wizardCreator = new WizardCreator(); // Assuming WizardCreator exists

        // Act: Create objects from different factories
        GameCharacter warrior = warriorCreator.create("WarriorA");
        GameCharacter wizard = wizardCreator.create("WizardB");

        // Assert: Ensure the objects are of different types
        assertTrue(warrior instanceof Warrior, "The object created by WarriorCreator should be a Warrior");
        assertTrue(wizard instanceof Wizard, "The object created by WizardCreator should be a Wizard");
        assertNotSame(warrior.getClass(), wizard.getClass(), "The objects should have different classes");
    }



}
