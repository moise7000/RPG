package eu.telecomnancy.rpg.Observers;

import eu.telecomnancy.rpg.GameCharacter;

public interface CharacterObserver {
    void update(GameCharacter character, CharacterEvent event);
}
