package eu.telecomnancy.rpg.Strategy;

import eu.telecomnancy.rpg.GameConfiguration;

/**
 * Implémentation de la stratégie de combat défensive.
 * <p>
 * Cette stratégie réduit les dégâts reçus par le personnage au détriment
 * de la puissance de ses attaques, le rendant plus résistant mais moins offensif.
 * </p>
 */
public class DefensiveStrategy implements CombatStrategy {

    /** Référence à la configuration globale du jeu. */
    private final GameConfiguration config = GameConfiguration.getShared();

    /**
     * Calcule les dégâts infligés par un personnage en mode défensif.
     * <p>
     * Les dégâts infligés sont réduits en fonction d'un multiplicateur
     * défini dans la configuration du jeu.
     * </p>
     *
     * @param baseDamage les dégâts de base que le personnage inflige.
     * @return les dégâts réellement infligés après application de la stratégie défensive.
     */
    @Override
    public int calculateDamageDealt(int baseDamage) {
        return (int) (baseDamage * config.getDefensiveDamageMultiplier());
    }


    /**
     * Calcule les dégâts reçus par un personnage en mode défensif.
     * <p>
     * Les dégâts subis sont réduits en fonction d'un multiplicateur
     * défini dans la configuration du jeu, reflétant une défense accrue.
     * </p>
     *
     * @param incomingDamage les dégâts de base que le personnage devrait recevoir.
     * @return les dégâts réellement subis après application de la stratégie défensive.
     */
    @Override
    public int calculateDamageReceived(int incomingDamage) {
        return (int) (incomingDamage * config.getDefensiveDefenseMultiplier());
    }

}
