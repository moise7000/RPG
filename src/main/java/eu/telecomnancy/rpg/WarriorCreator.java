package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.Strategy.CombatStrategy;

/**
 * Classe pour la création de personnages de type {@link Warrior}.
 * <p>
 * Cette classe implémente l'interface {@link CharacterCreator} et fournit des
 * méthodes pour instancier des guerriers avec différentes configurations.
 * </p>
 */
public class WarriorCreator implements CharacterCreator {

    /**
     * Crée un guerrier avec un nom spécifique.
     * <p>
     * Le guerrier est initialisé avec :
     * - 100 points de vie.
     * - Une force calculée comme {@code niveau * 10}.
     * </p>
     *
     * @param name le nom du guerrier à créer.
     * @return une instance de {@link Warrior} configurée avec les paramètres par défaut.
     */
    @Override
    public Warrior create(String name) {
        Warrior warrior = new Warrior(name);
        //TODO : Revoir l'initialisaiton du warrior en utilisant des constantes de GameConfiguration
        //TODO : Revoir la doc en conséquence
        warrior.setHealth(100);
        warrior.setStrength(warrior.getLevel() * 10);
        return warrior;
    }


    /**
     * Crée un guerrier avec un nom, une santé initiale et une stratégie de combat spécifiques.
     * <p>
     * Le guerrier est initialisé avec un niveau par défaut de 1, la santé et la stratégie
     * spécifiées, et une force calculée comme {@code niveau * 10}.
     * </p>
     *
     * @param name     le nom du guerrier.
     * @param health   les points de vie initiaux du guerrier.
     * @param strategy la stratégie de combat assignée au guerrier.
     * @return une instance de {@link Warrior} configurée avec les paramètres donnés.
     */
    @Override
    public Warrior create(String name, int health, CombatStrategy strategy) {
        Warrior warrior = new Warrior(name);
        warrior.setLevel(1);
        warrior.setHealth(health);
        warrior.setCombatStrategy(strategy);
        //TODO : Revoir l'initialisaiton du warrior en utilisant des constantes de GameConfiguration
        //TODO : Revoir la doc en conséquence
        warrior.setStrength(warrior.getLevel() * 10);
        return warrior;
    }


}
