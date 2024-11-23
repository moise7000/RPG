package eu.telecomnancy.rpg.Strategy;

public class AgressiveStrategy implements CombatStrategy {
    private static final double DAMAGE_MULTIPLIER = 1.5;
    private static final double DEFENSE_MULTIPLIER = 1.3;

    @Override
    public int calculateDamageDealt(int baseDamage){
        return (int) (baseDamage * DAMAGE_MULTIPLIER);
    }

    @Override
    public int calculateDamageReceived(int inCommingDamage) {
        return (int) (inCommingDamage * DEFENSE_MULTIPLIER);
    }


}
