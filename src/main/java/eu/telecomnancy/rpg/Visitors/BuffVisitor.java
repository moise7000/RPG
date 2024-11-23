package eu.telecomnancy.rpg.Visitors;

import eu.telecomnancy.rpg.CharacterVisitor;
import eu.telecomnancy.rpg.Warrior;
import eu.telecomnancy.rpg.Wizard;

public class BuffVisitor implements CharacterVisitor {



    @Override
    public void visit(Warrior warrior){

        //Values before visiting
        int currentLevel = warrior.getLevel();
        int currentHealth = warrior.getHealth();
        int currentStrength = warrior.getStrength();
        int currentExperiencePoints = warrior.getExperiencePoints();

        //Action
        int levelBuff = currentLevel + 1;
        int healthBuff = currentHealth + 10;
        int strengthBuff = currentStrength + 10;
        int experiencePointsBuff = currentExperiencePoints + 20;

        //Set
        warrior.setLevel(levelBuff);
        warrior.setHealth(healthBuff);
        warrior.setStrength(strengthBuff);
        warrior.setExperiencePoints(experiencePointsBuff);

        System.out.println("Buffing Warrior " + warrior.getName() +
                ": Strength +" + strengthBuff +
                ", Health +" + healthBuff);


    }

    @Override
    public void visit(Wizard wizard){

        //Values before visiting
        int currentLevel = wizard.getLevel();
        int currentHealth = wizard.getHealth();
        int currentIntelligence = wizard.getIntelligence();
        int currentExperiencePoints = wizard.getExperiencePoints();

        //Action
        int levelBuff = currentLevel + 1;
        int healthBuff = currentHealth + 50;
        int intelligenceBuff = currentIntelligence + 30;
        int experiencePointsBuff = currentExperiencePoints + 50;

        //Set
        wizard.setLevel(levelBuff);
        wizard.setHealth(healthBuff);
        wizard.setIntelligence(intelligenceBuff);
        wizard.setExperiencePoints(experiencePointsBuff);







    }




}
