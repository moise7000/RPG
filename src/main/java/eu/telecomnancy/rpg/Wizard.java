package eu.telecomnancy.rpg;

import java.util.Random;

import lombok.Getter;
import lombok.Setter;

public class Wizard extends GameCharacter {
    @Getter
    @Setter
    private int intelligence;

    public Wizard(String name) {
        super(name);
        intelligence = getLevel() * 10+new Random().nextInt(10);
    }

    
}
