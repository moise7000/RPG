package eu.telecomnancy.rpg.Decorator.Decorators;

import eu.telecomnancy.rpg.Decorator.CharacterDecorator;
import eu.telecomnancy.rpg.GameCharacter;
import eu.telecomnancy.rpg.GameConfiguration;

/**
 * Décorateur pour rendre un personnage invincible.
 * <p>
 * Le décorateur modifie le comportement du personnage en lui garantissant un minimum
 * de points de vie (HP). Si les points de vie du personnage descendent en dessous d'un
 * certain seuil, ils sont automatiquement rétablis au minimum permis pour son niveau.
 * </p>
 */
public class InvincibleDecorator extends CharacterDecorator {
    private final GameConfiguration config;

    /**
     * Constructeur du décorateur invincible.
     *
     * @param character Le personnage à décorer.
     */
    public InvincibleDecorator(GameCharacter character) {
        super(character);
        this.config = GameConfiguration.getShared();
    }


    /**
     * Redéfinit le comportement de réception des attaques.
     * <p>
     * Applique les dégâts comme d'habitude, puis vérifie si les points de vie du
     * personnage sont tombés en dessous du minimum permis pour son niveau. Si c'est
     * le cas, ils sont rétablis au minimum.
     * </p>
     *
     * @param incomingDamage Les dégâts entrants.
     */

    @Override
    public void receiveAttack(int incomingDamage) {
        super.receiveAttack(incomingDamage);
        int minimumHealth = config.getMinimumHealth(getLevel());
        if(getHealth() < minimumHealth){
            setHealth(minimumHealth);
        }
    }


    /**
     * Crée une copie du personnage décoré avec le même comportement invincible.
     *
     * @return Une nouvelle instance de {@code InvincibleDecorator} appliquée à une copie
     *         du personnage décoré.
     */
    @Override
    public GameCharacter duplicate() {
        return new InvincibleDecorator(decoratedCharacter.duplicate());
    }
}
