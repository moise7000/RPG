package eu.telecomnancy.rpg.Visitors;

import eu.telecomnancy.rpg.Warrior;
import eu.telecomnancy.rpg.Wizard;

public class HealVisitor implements CharacterVisitor {
    @Override
    public void visit(Warrior warrior) {
        //Values before visiting
        int currentHealth = warrior.getHealth();

        //Action
        int healAmount = 5;

        //Set
        warrior.setHealth(currentHealth + healAmount);
    }

    @Override
    public void visit(Wizard wizard) {
        //Values before visiting
        int currentHealth = wizard.getHealth();

        //Action
        int healAmount = 10;

        //Set
        wizard.setHealth(currentHealth + healAmount);

    }
}
