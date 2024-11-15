package eu.telecomnancy.rpg;

public class WizardCreator implements CharacterCreator {



    @Override
    public GameCharacter createCreator(String name) {
        return new Wizard(name);
    }
}
