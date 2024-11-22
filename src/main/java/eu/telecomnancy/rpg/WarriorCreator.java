package eu.telecomnancy.rpg;

public class WarriorCreator implements CharacterCreator {

    @Override
    public Warrior create(String name) {
        return new Warrior(name);
    }




}
