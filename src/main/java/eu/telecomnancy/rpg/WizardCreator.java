package eu.telecomnancy.rpg;

public class WizardCreator implements CharacterCreator {

    @Override
    public GameCharacter create(String name) {
        Wizard wizard = new Wizard(name);
        wizard.setHealth(80);
        wizard.setIntelligence(wizard.getIntelligence() * 15);
        return wizard;
    }
}
