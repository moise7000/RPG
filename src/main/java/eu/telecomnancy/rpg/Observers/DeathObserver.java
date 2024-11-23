package eu.telecomnancy.rpg.Observers;


import eu.telecomnancy.rpg.GameCharacter;

public class DeathObserver implements CharacterObserver {
    @Override
    public void update(GameCharacter character, CharacterEvent event) {
        if (event == CharacterEvent.DEATH) {
            System.out.println(character.getName() + " has fallen in battle!");
        }
    }
}
