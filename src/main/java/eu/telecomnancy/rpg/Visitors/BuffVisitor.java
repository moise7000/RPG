package eu.telecomnancy.rpg.Visitors;

import eu.telecomnancy.rpg.CharacterVisitor;
import eu.telecomnancy.rpg.GameConfiguration;
import eu.telecomnancy.rpg.Warrior;
import eu.telecomnancy.rpg.Wizard;

/**
 * Visiteur appliquant des buffs aux personnages Warrior et Wizard.
 * <p>
 * Cette classe implémente l'interface {@link CharacterVisitor} pour appliquer des modifications spécifiques aux
 * attributs de base des personnages Warrior et Wizard, en utilisant les valeurs de configuration définies dans {@link GameConfiguration}.
 * </p>
 */
public class BuffVisitor implements CharacterVisitor {
    private final GameConfiguration config = GameConfiguration.getShared();


    /**
     * Applique des buffs au personnage Warrior.
     *
     * @param warrior le personnage Warrior à visiter.
     */
    @Override
    public void visit(Warrior warrior){

        //Values before visiting
        int currentLevel = warrior.getLevel();
        int currentHealth = warrior.getHealth();
        int currentStrength = warrior.getStrength();
        int currentExperiencePoints = warrior.getExperiencePoints();

        //Action
        int levelBuff = currentLevel + config.getLevelBuff();
        int healthBuff = currentHealth + config.getWarriorHealthBuff();
        int strengthBuff = currentStrength + config.getWarriorStrengthBuff();
        int experiencePointsBuff = currentExperiencePoints + config.getWarriorXpBuff();

        //Set
        warrior.setLevel(levelBuff);
        warrior.setHealth(healthBuff);
        warrior.setStrength(strengthBuff);
        warrior.setExperiencePoints(experiencePointsBuff);

        System.out.println("Buffing Warrior " + warrior.getName() +
                ": Strength +" + strengthBuff +
                ", Health +" + healthBuff);


    }

    /**
     * Applique des buffs au personnage Wizard.
     *
     * @param wizard le personnage Wizard à visiter.
     */
    @Override
    public void visit(Wizard wizard){

        //Values before visiting
        int currentLevel = wizard.getLevel();
        int currentHealth = wizard.getHealth();
        int currentIntelligence = wizard.getIntelligence();
        int currentExperiencePoints = wizard.getExperiencePoints();

        //Action
        int levelBuff = currentLevel + config.getLevelBuff();
        int healthBuff = currentHealth + config.getWizardHealthBuff();
        int intelligenceBuff = currentIntelligence + config.getWizardIntelligenceBuff();
        int experiencePointsBuff = currentExperiencePoints + config.getWizardXpBuff();

        //Set
        wizard.setLevel(levelBuff);
        wizard.setHealth(healthBuff);
        wizard.setIntelligence(intelligenceBuff);
        wizard.setExperiencePoints(experiencePointsBuff);


    }

}
