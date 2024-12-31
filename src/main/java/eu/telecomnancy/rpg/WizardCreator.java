package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.Strategy.CombatStrategy;
import gameInterface.character.CharacterAnimation;

/**
 * Classe pour la création de personnages de type {@link Wizard}.
 * <p>
 * Cette classe implémente l'interface {@link CharacterCreator} et fournit des
 * méthodes pour instancier des mages avec différentes configurations.
 * </p>
 */
public class WizardCreator implements CharacterCreator {

    /**
     * Crée un mage avec un nom spécifique.
     * <p>
     * Le mage est initialisé avec :
     * - 80 points de vie.
     * - Une intelligence calculée comme {@code niveau * 15}.
     * </p>
     *
     * @param name le nom du mage à créer.
     * @return une instance de {@link Wizard} configurée avec les paramètres par défaut.
     */
    @Override
    public GameCharacter create(String name) {
        Wizard wizard = new Wizard(name);
        //TODO : Revoir l'initialisaiton du wizard en utilisant des constantes de GameConfiguration
        //TODO : Revoir la doc en conséquence
        wizard.setHealth(80);
        wizard.setIntelligence(wizard.getIntelligence() * 15);
        return wizard;
    }

    /**
     * Crée un mage avec un nom, une santé initiale et une stratégie de combat spécifiques.
     * <p>
     * Le mage est initialisé avec un niveau par défaut de 1, la santé et la stratégie
     * spécifiées, et une intelligence calculée comme {@code niveau * 15}.
     * </p>
     *
     * @param name     le nom du mage.
     * @param health   les points de vie initiaux du mage.
     * @param strategy la stratégie de combat assignée au mage.
     * @return une instance de {@link Wizard} configurée avec les paramètres donnés.
     */
    @Override
    public GameCharacter create(String name, int health, CombatStrategy strategy) {
        Wizard wizard = new Wizard(name);
        wizard.setLevel(1);
        wizard.setHealth(health);
        wizard.setCombatStrategy(strategy);
        //TODO : Revoir l'initialisaiton du wizard en utilisant des constantes de GameConfiguration
        //TODO : Revoir la doc en conséquence
        wizard.setIntelligence(wizard.getIntelligence() * 15);
        return wizard;
    }

    @Override
    public GameCharacter create(String name, int health, CombatStrategy strategy, CharacterAnimation animations) {
        Wizard wizard = new Wizard(name);
        wizard.setLevel(1);
        wizard.setHealth(health);
        wizard.setCombatStrategy(strategy);
        //TODO : Revoir l'initialisaiton du wizard en utilisant des constantes de GameConfiguration
        //TODO : Revoir la doc en conséquence
        wizard.setIntelligence(wizard.getIntelligence() * 15);
        wizard.setAnimations(animations);
        return wizard;
    }
}
