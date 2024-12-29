package eu.telecomnancy.rpg.Strategy;

/**
 * Interface représentant une stratégie de combat pour un personnage.
 * <p>
 * Les implémentations de cette interface définissent des comportements spécifiques
 * pour calculer les dégâts infligés et reçus en fonction de la stratégie choisie.
 * </p>
 */
public interface CombatStrategy {

    /**
     * Calcule les dégâts infligés par un personnage en utilisant la stratégie de combat.
     *
     * @param baseDamage les dégâts de base que le personnage inflige.
     * @return les dégâts réellement infligés après application de la stratégie.
     */
    int calculateDamageDealt(int baseDamage);

    /**
     * Calcule les dégâts reçus par un personnage en utilisant la stratégie de combat.
     *
     * @param incomingDamage les dégâts de base que le personnage devrait recevoir.
     * @return les dégâts réellement subis après application de la stratégie.
     */
    int calculateDamageReceived(int incomingDamage);
}
