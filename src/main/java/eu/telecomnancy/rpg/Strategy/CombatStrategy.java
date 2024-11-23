package eu.telecomnancy.rpg.Strategy;

public interface CombatStrategy {
    int calculateDamageDealt(int baseDamage);
    int calculateDamageReceived(int incomingDamage);
}
