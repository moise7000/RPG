package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.Strategy.CombatStrategy;

public class WarriorCreator implements CharacterCreator {

    @Override
    public Warrior create(String name) {
        Warrior warrior = new Warrior(name);

        warrior.setHealth(100);
        warrior.setStrength(warrior.getLevel() * 10);
        return warrior;
    }


    @Override
    public Warrior create(String name, int health, CombatStrategy strategy) {
        Warrior warrior = new Warrior(name);
        warrior.setLevel(1);
        warrior.setHealth(health);
        warrior.setCombatStrategy(strategy);
        warrior.setStrength(warrior.getLevel() * 10);
        return warrior;
    }


}
