package eu.telecomnancy.rpg.Strategy;

import eu.telecomnancy.rpg.GameConfiguration;

public class AggressiveStrategy implements CombatStrategy {
    private final GameConfiguration config = GameConfiguration.getShared();

    @Override
    public int calculateDamageDealt(int baseDamage){
        return (int) (baseDamage * config.getAggressiveDamageMultiplier());
    }

    @Override
    public int calculateDamageReceived(int incomingDamage) {
        return (int) (incomingDamage * config.getAggressiveDefenseMultiplier());
    }


}
