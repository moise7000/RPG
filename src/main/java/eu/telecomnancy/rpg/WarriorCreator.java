package eu.telecomnancy.rpg;

public class WarriorCreator implements CharacterCreator {

    @Override
    public Warrior create(String name) {
        Warrior warrior = new Warrior(name);

        warrior.setHealth(100);
        warrior.setStrength(warrior.getLevel() * 10);
        return warrior;
    }




}
