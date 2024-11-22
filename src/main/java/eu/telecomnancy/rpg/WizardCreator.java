package eu.telecomnancy.rpg;

public class WizardCreator implements CharacterCreator {



    @Override
    public GameCharacter create(String name) {
        return new Wizard(name);
    }
}
