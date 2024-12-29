package eu.telecomnancy.rpg;

import eu.telecomnancy.rpg.Strategy.CombatStrategy;

public interface CharacterCreator {
    public GameCharacter create(String name);


    public GameCharacter create(String name, int health, CombatStrategy strategy);

    default GameCharacter create(String name, int level) {
        GameCharacter character = this.create(name);
        character.setLevel(level);
        return character;
    }
}
