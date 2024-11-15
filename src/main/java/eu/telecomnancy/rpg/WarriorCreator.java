package eu.telecomnancy.rpg;

public class WarriorCreator implements CharacterCreator {

    @Override
    public GameCharacter createCreator(String name) {
        return new Warrior(name);
    }
}
