package eu.telecomnancy.rpg;

import java.util.Random;

public class Warrior extends GameCharacter {
    private int strength;

    public Warrior(String name) {
        super(name);
        GameConfiguration config = GameConfiguration.getShared();
        setHealth(config.getWarriorHealthForLevel(getLevel()));
        strength = config.getWarriorStrengthForLevel(getLevel());
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {this.strength = strength;}

    @Override
    public void levelUp() {
        super.setLevel(getLevel() + 1);
        GameConfiguration config = GameConfiguration.getShared();
        setHealth(config.getWarriorHealthForLevel(getLevel()));
        strength = config.getWarriorStrengthForLevel(getLevel());
    }

    @Override
    public Warrior duplicate() {
        Warrior clonedWarrior = new Warrior(this.getName());

        clonedWarrior.strength = this.strength;
        clonedWarrior.setHealth(this.getHealth());
        clonedWarrior.setExperiencePoints(this.getExperiencePoints());
        return clonedWarrior;

    }

    @Override
    public void accept(CharacterVisitor visitor) {visitor.visit(this);}


}
