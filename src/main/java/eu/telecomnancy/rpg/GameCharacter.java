package eu.telecomnancy.rpg;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString
@Getter
@Setter
public abstract class GameCharacter {
    private final String name;
    private int health;
    private int experiencePoints;
    private int level;

    public GameCharacter(String name) {
        this.name = name;
        this.experiencePoints = 0;
        this.level = 1;
    }

}
