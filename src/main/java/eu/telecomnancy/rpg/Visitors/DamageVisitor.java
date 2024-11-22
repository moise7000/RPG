package eu.telecomnancy.rpg.Visitors;

import eu.telecomnancy.rpg.Warrior;
import eu.telecomnancy.rpg.Wizard;

public class DamageVisitor implements CharacterVisitor{
    @Override
    public void visit(Warrior warrior){
        //Values before visiting
        int currentHealth = warrior.getHealth();

        //Action
        int damageAmount = 5;

        //Set
        warrior.setHealth(currentHealth - damageAmount);
    }

    @Override
    public void visit(Wizard wizard){
        //Values before visiting
        int currentHealth = wizard.getHealth();

        //Action
        int damageAmount = 5;

        //Set
        wizard.setHealth(currentHealth - damageAmount);
    }
}
