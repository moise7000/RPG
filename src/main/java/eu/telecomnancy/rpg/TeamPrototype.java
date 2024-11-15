package eu.telecomnancy.rpg;

import java.util.ArrayList;
import java.util.List;

public class TeamPrototype  implements Cloneable {
    private List<GameCharacter> characters;

    public TeamPrototype(List<GameCharacter> characters) {
        this.characters = characters;
    }

    public Team duplicate() {
        List<GameCharacter> clonedCharacters = new ArrayList<>();
        clonedCharacters.addAll(characters);

        return new Team(clonedCharacters);


    }
}
