package eu.telecomnancy.rpg;

public class WarriorCreator implements CharacterCreator {

    @Override
    public GameCharacter createCreator() {
        return new Warrior("Guerrier");
    }
}
