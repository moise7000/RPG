package eu.telecomnancy.rpg.Strategy;

public class DefensiveStrategy implements CombatStrategy {
    private static final double DAMAGE_MULTIPLIER = 0.7;
    private static final double DEFENSE_MULTIPLIER = 0.6;

    @Override
    public int calculateDamageDealt(int baseDamage) {
        return (int) (baseDamage * DAMAGE_MULTIPLIER);
    }

    @Override
    public int calculateDamageReceived(int incomingDamage) {
        return (int) (incomingDamage * DEFENSE_MULTIPLIER);
    }

}
