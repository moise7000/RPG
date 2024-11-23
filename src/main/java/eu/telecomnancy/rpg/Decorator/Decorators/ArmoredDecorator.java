package eu.telecomnancy.rpg.Decorator.Decorators;

import eu.telecomnancy.rpg.Decorator.CharacterDecorator;
import eu.telecomnancy.rpg.GameCharacter;
import eu.telecomnancy.rpg.GameConfiguration;

public class ArmoredDecorator extends CharacterDecorator {
    private final GameConfiguration config;


    public ArmoredDecorator(GameCharacter character) {
        super(character);
        this.config = GameConfiguration.getShared();
    }

    @Override
    public void receiveAttack(int incomingDamage) {
        double reduction = config.getArmorDamageReduction(getLevel());
        int reducedDamage = (int)(incomingDamage * (1 - reduction));
        super.receiveAttack(reducedDamage);
    }

    @Override
    public GameCharacter duplicate() {
        return new ArmoredDecorator(decoratedCharacter.duplicate());
    }
}
