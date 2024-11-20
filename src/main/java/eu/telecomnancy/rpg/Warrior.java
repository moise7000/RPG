package eu.telecomnancy.rpg;

import java.util.Random;

public class Warrior extends GameCharacter {
    private int strength;

    public Warrior(String name) {
        super(name);
        strength = getLevel() * 10 + new Random().nextInt(10);
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public Warrior duplicate() {
        Warrior clonedWarrior = new Warrior(this.getName());
        clonedWarrior.strength = this.strength;
        clonedWarrior.setHealth(this.getHealth());
        clonedWarrior.setExperiencePoints(this.getExperiencePoints());
        return clonedWarrior;

    }


}
