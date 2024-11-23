package eu.telecomnancy.rpg.Decorator.Decorators;

import eu.telecomnancy.rpg.Decorator.CharacterDecorator;
import eu.telecomnancy.rpg.GameCharacter;
import eu.telecomnancy.rpg.GameConfiguration;

public class InvincibleDecorator extends CharacterDecorator {
    private final GameConfiguration config;

    public InvincibleDecorator(GameCharacter character) {
        super(character);
        this.config = GameConfiguration.getShared();
    }

    @Override
    public void receiveAttack(int incomingDamage) {
        super.receiveAttack(incomingDamage);
        int minimumHealth = config.getMinimumHealth(getLevel());
        if(getHealth() < minimumHealth){
            setHealth(getHealth());
        }
    }

    @Override
    public GameCharacter duplicate() {
        return new InvincibleDecorator(decoratedCharacter.duplicate());
    }
}
