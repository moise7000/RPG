package eu.telecomnancy.rpg.Visitors;

import eu.telecomnancy.rpg.CharacterVisitor;
import eu.telecomnancy.rpg.GameConfiguration;
import eu.telecomnancy.rpg.Warrior;
import eu.telecomnancy.rpg.Wizard;

/**
 * Visiteur appliquant des dégâts aux personnages Warrior et Wizard.
 * <p>
 * Cette classe implémente l'interface {@link CharacterVisitor} pour réduire la santé des personnages Warrior et Wizard en appliquant un montant de dégâts basé sur les valeurs de configuration définies dans {@link GameConfiguration}.
 * </p>
 */
public class DamageVisitor implements CharacterVisitor {
    private final GameConfiguration config = GameConfiguration.getShared();

    /**
     * Applique des dégâts au personnage Warrior.
     *
     * @param warrior le personnage Warrior à visiter.
     */
    @Override
    public void visit(Warrior warrior){
        //Values before visiting
        int currentHealth = warrior.getHealth();

        //Action
        int damageAmount = config.getBaseDamageAmount();

        //Set
        warrior.setHealth(currentHealth - damageAmount);
    }

    /**
     * Applique des dégâts au personnage Wizard.
     *
     * @param wizard le personnage Wizard à visiter.
     */
    @Override
    public void visit(Wizard wizard){
        //Values before visiting
        int currentHealth = wizard.getHealth();

        //Action
        int damageAmount = config.getBaseDamageAmount();

        //Set
        wizard.setHealth(currentHealth - damageAmount);
    }
}
