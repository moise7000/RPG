package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.Strategy.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;



public class CombatStrategyTest {
    private Warrior warrior;
    private Wizard wizard;
    private static final int INITIAL_HEALTH = 100;
    private static final int BASE_DAMAGE = 20;

    @Before
    public void setUp() {
        // Utilisation des factories pour créer les personnages
        CharacterCreator warriorCreator = new WarriorCreator();
        CharacterCreator wizardCreator = new WizardCreator();

        warrior = (Warrior) warriorCreator.create("Conan");
        wizard = (Wizard) wizardCreator.create("Gandalf");

        // Initialisation des stats pour les tests
        warrior.setHealth(INITIAL_HEALTH);
        wizard.setHealth(INITIAL_HEALTH);
    }

    @Test
    public void testAggressiveStrategy() {
        // Configuration de la stratégie agressive
        warrior.setCombatStrategy(new AggressiveStrategy());
        wizard.setCombatStrategy(new NeutralStrategy());

        // Test des dégâts infligés en mode agressif
        int damageDealt = warrior.attack(BASE_DAMAGE);
        wizard.receiveAttack(damageDealt);

        // Vérification que les dégâts infligés sont augmentés (1.5x)
        assertEquals(70, wizard.getHealth()); // 100 - (20 * 1.5) = 70

        // Test de la vulnérabilité accrue
        int counterDamage = wizard.attack(BASE_DAMAGE);
        warrior.receiveAttack(counterDamage);

        // Le guerrier en mode agressif reçoit plus de dégâts (1.3x)
        assertEquals(74, warrior.getHealth()); // 100 - (20 * 1.3) = 74
    }

    @Test
    public void testDefensiveStrategy() {
        // Configuration de la stratégie défensive
        warrior.setCombatStrategy(new DefensiveStrategy());
        wizard.setCombatStrategy(new NeutralStrategy());

        // Test des dégâts réduits en mode défensif
        int damageDealt = warrior.attack(BASE_DAMAGE);
        wizard.receiveAttack(damageDealt);

        // Vérification que les dégâts infligés sont réduits (0.7x)
        assertEquals(86, wizard.getHealth()); // 100 - (20 * 0.7) = 86

        // Test de la défense améliorée
        int counterDamage = wizard.attack(BASE_DAMAGE);
        warrior.receiveAttack(counterDamage);

        // Le guerrier en mode défensif reçoit moins de dégâts (0.6x)
        assertEquals(88, warrior.getHealth()); // 100 - (20 * 0.6) = 88
    }

    @Test
    public void testNeutralStrategy() {
        // Configuration de la stratégie neutre
        warrior.setCombatStrategy(new NeutralStrategy());
        wizard.setCombatStrategy(new NeutralStrategy());

        // Test des dégâts normaux
        int damageDealt = warrior.attack(BASE_DAMAGE);
        wizard.receiveAttack(damageDealt);

        // Vérification que les dégâts sont normaux (1x)
        assertEquals(80, wizard.getHealth()); // 100 - 20 = 80

        // Test de la défense normale
        int counterDamage = wizard.attack(BASE_DAMAGE);
        warrior.receiveAttack(counterDamage);

        // Le guerrier reçoit des dégâts normaux (1x)
        assertEquals(80, warrior.getHealth()); // 100 - 20 = 80
    }
}