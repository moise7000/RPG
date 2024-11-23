package eu.telecomnancy.rpg;

public interface CharacterCreator {
    public GameCharacter create(String name);


    default GameCharacter create(String name, int level) {
        GameCharacter character = this.create(name);
        character.setLevel(level);
        return character;
    }
}
