package eu.telecomnancy.rpg.Visitors;

import eu.telecomnancy.rpg.CharacterVisitor;
import eu.telecomnancy.rpg.GameConfiguration;
import eu.telecomnancy.rpg.Warrior;
import eu.telecomnancy.rpg.Wizard;

public class HealVisitor implements CharacterVisitor {
    private final GameConfiguration config = GameConfiguration.getShared();


    @Override
    public void visit(Warrior warrior) {
        //Values before visiting
        int currentHealth = warrior.getHealth();

        //Action
        int healAmount = config.getWarriorHealAmount();

        //Set
        warrior.setHealth(currentHealth + healAmount);
    }

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
