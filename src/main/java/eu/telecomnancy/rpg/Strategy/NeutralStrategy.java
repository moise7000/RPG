package eu.telecomnancy.rpg.Strategy;

/**
 * Implémentation de la stratégie de combat neutre.
 * <p>
 * Cette stratégie n'applique aucune modification aux dégâts infligés
 * ou reçus par le personnage. Elle représente une approche équilibrée et sans biais.
 * </p>
 */
public class NeutralStrategy implements CombatStrategy {

    /**
     * Calcule les dégâts infligés par un personnage en mode neutre.
     * <p>
     * Les dégâts infligés ne sont pas modifiés et correspondent directement
     * aux dégâts de base.
     * </p>
     *
     * @param baseDamage les dégâts de base que le personnage inflige.
     * @return les dégâts réellement infligés, qui sont égaux aux dégâts de base.
     */
    @Override
    public int calculateDamageDealt(int baseDamage) {return baseDamage;}


    /**
     * Calcule les dégâts reçus par un personnage en mode neutre.
     * <p>
     * Les dégâts subis ne sont pas modifiés et correspondent directement
     * aux dégâts initiaux.
     * </p>
     *
     * @param incomingDamage les dégâts de base que le personnage devrait recevoir.
     * @return les dégâts réellement subis, qui sont égaux aux dégâts de base.
     */
    @Override
    public int calculateDamageReceived(int incomingDamage) {return incomingDamage;}
}
