package eu.telecomnancy.rpg.Strategy;

import eu.telecomnancy.rpg.GameConfiguration;

public class DefensiveStrategy implements CombatStrategy {


    private final GameConfiguration config = GameConfiguration.getShared();

    @Override
    public int calculateDamageDealt(int baseDamage) {
        return (int) (baseDamage * config.getDefensiveDamageMultiplier());
    }

    @Override
    public int calculateDamageReceived(int incomingDamage) {
        return (int) (incomingDamage * config.getDefensiveDamageMultiplier());
    }

}
