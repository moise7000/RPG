package eu.telecomnancy.rpg.Strategy;

public class NeutralStrategy implements CombatStrategy {
    @Override
    public int calculateDamageDealt(int baseDamage) {return baseDamage;}

    @Override
    public int calculateDamageReceived(int incomingDamage) {return incomingDamage;}
}
