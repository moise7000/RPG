package eu.telecomnancy.rpg.Decorator;

import eu.telecomnancy.rpg.CharacterVisitor;
import eu.telecomnancy.rpg.GameCharacter;
import eu.telecomnancy.rpg.Observers.CharacterEvent;
import eu.telecomnancy.rpg.Observers.CharacterObserver;

public abstract class CharacterDecorator extends GameCharacter {
    protected GameCharacter decoratedCharacter;

    public CharacterDecorator(GameCharacter character) {
        super(character.getName());
        this.decoratedCharacter = character;
        this.setHealth(character.getHealth());
        this.setLevel(character.getLevel());
        this.setExperiencePoints(character.getExperiencePoints());
    }


    @Override
    public void levelUp() {
        decoratedCharacter.levelUp();
        this.setHealth(decoratedCharacter.getHealth());
        this.setLevel(decoratedCharacter.getLevel());
        this.setExperiencePoints(decoratedCharacter.getExperiencePoints());
    }

    @Override
    public void accept(CharacterVisitor visitor) {decoratedCharacter.accept(visitor);}

    @Override
    public void addObserver(CharacterObserver observer) {decoratedCharacter.addObserver(observer);}

    @Override
    public void removeObserver(CharacterObserver observer) {decoratedCharacter.removeObserver(observer);}

    @Override
    public void notifyObservers(CharacterEvent event){decoratedCharacter.notifyObservers(event);}

    public GameCharacter getDecoratedCharacter() {return decoratedCharacter;}

    public void setDecoratedCharacter(GameCharacter character) {this.decoratedCharacter = character;}

    public GameCharacter removeDecoratedCharacter() {return decoratedCharacter;}
}
