package eu.telecomnancy.rpg;

public class WizardCreator implements CharacterCreator {

    

    @Override
    public GameCharacter createCreator() {
        return new Wizard("Sorcier");
    }
}
