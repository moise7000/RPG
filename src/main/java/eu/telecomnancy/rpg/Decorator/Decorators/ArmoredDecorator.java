package eu.telecomnancy.rpg.Decorator.Decorators;

import eu.telecomnancy.rpg.Decorator.CharacterDecorator;
import eu.telecomnancy.rpg.GameCharacter;
import eu.telecomnancy.rpg.GameConfiguration;

/**
 * La classe {@code ArmoredDecorator} est une implémentation concrète du modèle de conception {@link CharacterDecorator}.
 * Ce décorateur réduit les dégâts reçus par un personnage en fonction de son niveau, en utilisant les valeurs de configuration
 * définies dans {@link GameConfiguration}.
 *
 * <p>Exemple d'utilisation :</p>
 * <pre>
 *     GameCharacter guerrier = new Warrior("Héros");
 *     GameCharacter guerrierArmure = new ArmoredDecorator(guerrier);
 *     guerrierArmure.receiveAttack(50);
 * </pre>
 *
 * @see CharacterDecorator
 * @see GameCharacter
 * @see GameConfiguration
 */
public class ArmoredDecorator extends CharacterDecorator {
    private final GameConfiguration config;


    /**
     * Construit un {@code ArmoredDecorator} qui ajoute une protection d'armure à un {@link GameCharacter}.
     *
     * @param character Le personnage à décorer avec la protection d'armure.
     */
    public ArmoredDecorator(GameCharacter character) {
        super(character);
        this.config = GameConfiguration.getShared();
    }


    /**
     * Réduit les dégâts reçus avant de les transmettre au personnage décoré.
     * Le facteur de réduction est déterminé en fonction du niveau du personnage et est
     * récupéré depuis la configuration {@link GameConfiguration}.
     *
     * @param incomingDamage La quantité de dégâts reçus.
     */
    @Override
    public void receiveAttack(int incomingDamage) {
        double reduction = config.getArmorDamageReduction(getLevel());
        int reducedDamage = (int)(incomingDamage * (1 - reduction));
        super.receiveAttack(reducedDamage);
    }


    /**
     * Crée un duplicata de ce personnage décoré, incluant le {@code ArmoredDecorator}.
     * Le personnage dupliqué conserve le comportement de réduction des dégâts.
     *
     * @return Une nouvelle instance de {@link GameCharacter} avec les mêmes propriétés et la même armure.
     */
    @Override
    public GameCharacter duplicate() {
        return new ArmoredDecorator(decoratedCharacter.duplicate());
    }
}
