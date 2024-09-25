package eu.telecomnancy.rpg;

import java.util.Random;

import lombok.Getter;
import lombok.Setter;

public class Warrior extends GameCharacter {
    @Getter
    @Setter
    private int strength;

    public Warrior(String name) {
        super(name);
        strength = getLevel() * 10+new Random().nextInt(10);
    }

    
}
