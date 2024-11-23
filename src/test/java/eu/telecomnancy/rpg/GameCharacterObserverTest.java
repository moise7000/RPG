package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.*;

import eu.telecomnancy.rpg.Observers.CharacterEvent;
import eu.telecomnancy.rpg.Observers.CharacterObserver;
import eu.telecomnancy.rpg.Observers.CharacterSubject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameCharacterObserverTest {
    private Warrior warrior;
    private Wizard wizard;
    private TestObserver testObserver;

    private static class TestObserver implements CharacterObserver {
        private int levelUpCount = 0;
        private int deathCount = 0;
        private GameCharacter lastUpdatedCharacter = null;
        private CharacterEvent lastEvent = null;

        @Override
        public void update(GameCharacter character, CharacterEvent event) {
            lastUpdatedCharacter = character;
            lastEvent = event;

            if (event == CharacterEvent.LEVEL_UP) {
                levelUpCount++;
            } else if (event == CharacterEvent.DEATH) {
                deathCount++;
            }
        }

        public void reset() {
            levelUpCount = 0;
            deathCount = 0;
            lastUpdatedCharacter = null;
            lastEvent = null;
        }
    }

    @BeforeEach
    void setUp() {
        warrior = new Warrior("TestWarrior");
        wizard = new Wizard("TestWizard");
        testObserver = new TestObserver();
    }

    @Test
    void testObserverRegistration() {
        warrior.addObserver(testObserver);
        assertTrue(warrior instanceof CharacterSubject);

        // Check that the observer is registered correctly
        warrior.notifyObservers(CharacterEvent.LEVEL_UP);
        assertEquals(1, testObserver.levelUpCount);

        warrior.removeObserver(testObserver);
        warrior.notifyObservers(CharacterEvent.LEVEL_UP);
        assertEquals(1, testObserver.levelUpCount); // Le compte ne devrait pas augmenter
    }

    @Test
    void testDeathNotification() {
        warrior.addObserver(testObserver);

        // Simulate a fatal attack
        warrior.receiveAttack(1000); // Dégâts suffisants pour tuer le personnage

        assertEquals(1, testObserver.deathCount);
        assertEquals(warrior, testObserver.lastUpdatedCharacter);
        assertEquals(CharacterEvent.DEATH, testObserver.lastEvent);
    }

    @Test
    void testMultipleObservers() {
        TestObserver observer1 = new TestObserver();
        TestObserver observer2 = new TestObserver();

        warrior.addObserver(observer1);
        warrior.addObserver(observer2);

        warrior.notifyObservers(CharacterEvent.LEVEL_UP);

        assertEquals(1, observer1.levelUpCount);
        assertEquals(1, observer2.levelUpCount);
    }

    @Test
    void testNoNotificationAfterRemoval() {
        warrior.addObserver(testObserver);
        warrior.removeObserver(testObserver);

        warrior.notifyObservers(CharacterEvent.LEVEL_UP);

        assertEquals(0, testObserver.levelUpCount);
        assertNull(testObserver.lastUpdatedCharacter);
    }
}
