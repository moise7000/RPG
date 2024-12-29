package eu.telecomnancy.rpg.Strategy;

import eu.telecomnancy.rpg.GameConfiguration;


/**
 * Implémentation de la stratégie de combat agressive.
 * <p>
 * Cette stratégie augmente les dégâts infligés par le personnage au détriment
 * de sa défense, le rendant plus vulnérable aux attaques.
 * </p>
 */
public class AggressiveStrategy implements CombatStrategy {
    private final GameConfiguration config = GameConfiguration.getShared();


    /**
     * Calcule les dégâts infligés par un personnage en mode agressif.
     * <p>
     * Les dégâts infligés sont augmentés en fonction d'un multiplicateur
     * défini dans la configuration du jeu.
     * </p>
     *
     * @param baseDamage les dégâts de base que le personnage inflige.
     * @return les dégâts réellement infligés après application de la stratégie agressive.
     */
    @Override
    public int calculateDamageDealt(int baseDamage){
        return (int) (baseDamage * config.getAggressiveDamageMultiplier());
    }

    /**
     * Calcule les dégâts reçus par un personnage en mode agressif.
     * <p>
     * Les dégâts subis sont augmentés en fonction d'un multiplicateur
     * défini dans la configuration du jeu, reflétant une réduction de la défense.
     * </p>
     *
     * @param incomingDamage les dégâts de base que le personnage devrait recevoir.
     * @return les dégâts réellement subis après application de la stratégie agressive.
     */
    @Override
    public int calculateDamageReceived(int incomingDamage) {
        return (int) (incomingDamage * config.getAggressiveDefenseMultiplier());
    }


}
