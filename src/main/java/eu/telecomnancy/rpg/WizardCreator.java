package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.Strategy.CombatStrategy;

public class WizardCreator implements CharacterCreator {

    @Override
    public GameCharacter create(String name) {
        Wizard wizard = new Wizard(name);
        wizard.setHealth(80);
        wizard.setIntelligence(wizard.getIntelligence() * 15);
        return wizard;
    }

    @Override
    public GameCharacter create(String name, int health, CombatStrategy strategy) {
        Wizard wizard = new Wizard(name);
        wizard.setLevel(1);
        wizard.setHealth(health);
        wizard.setCombatStrategy(strategy);
        wizard.setIntelligence(wizard.getIntelligence() * 15);
        return wizard;
    }
}
