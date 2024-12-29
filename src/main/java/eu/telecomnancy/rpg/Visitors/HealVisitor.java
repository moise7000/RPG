package eu.telecomnancy.rpg.Visitors;

import eu.telecomnancy.rpg.CharacterVisitor;
import eu.telecomnancy.rpg.GameConfiguration;
import eu.telecomnancy.rpg.Warrior;
import eu.telecomnancy.rpg.Wizard;

/**
 * Visiteur appliquant des soins aux personnages Warrior et Wizard.
 * <p>
 * Cette classe implémente l'interface {@link CharacterVisitor} pour restaurer la santé des personnages Warrior et Wizard en appliquant un montant de soin basé sur les valeurs de configuration définies dans {@link GameConfiguration}.
 * </p>
 */
public class HealVisitor implements CharacterVisitor {
    private final GameConfiguration config = GameConfiguration.getShared();

    /**
     * Applique des soins au personnage Warrior.
     *
     * @param warrior le personnage Warrior à visiter.
     */
    @Override
    public void visit(Warrior warrior) {
        //Values before visiting
        int currentHealth = warrior.getHealth();

        //Action
        int healAmount = config.getWarriorHealAmount();

        //Set
        warrior.setHealth(currentHealth + healAmount);
    }

    /**
     * Applique des soins au personnage Wizard.
     *
     * @param wizard le personnage Wizard à visiter.
     */
    @Override
    public void visit(Wizard wizard) {
        //Values before visiting
        int currentHealth = wizard.getHealth();

        //Action
        int healAmount = config.getWizardHealAmount();

        //Set
        wizard.setHealth(currentHealth + healAmount);

    }
}
