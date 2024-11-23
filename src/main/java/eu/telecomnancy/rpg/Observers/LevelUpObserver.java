package eu.telecomnancy.rpg.Observers;


import eu.telecomnancy.rpg.GameCharacter;

public class LevelUpObserver implements CharacterObserver {
    @Override
    public void update(GameCharacter character, CharacterEvent event) {
        if (event == CharacterEvent.LEVEL_UP) {
            System.out.println(character.getName() + " has reached level " + character.getLevel() + "!");
        }
    }
}
