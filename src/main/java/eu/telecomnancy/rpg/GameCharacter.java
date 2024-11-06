package eu.telecomnancy.rpg;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@ToString

public abstract class GameCharacter {
    @Getter
    private final String name;
    @Getter
    @Setter
    private int health;
    @Getter
    @Setter
    private int experiencePoints;
    @Getter
    @Setter
    private int level;

    public GameCharacter(String name) {
        this.name = name;
        this.experiencePoints = 0;
        this.level = 1;
    }

}
