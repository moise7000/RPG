package eu.telecomnancy.rpg;


import eu.telecomnancy.rpg.Strategy.CombatStrategy;
import gameInterface.character.CharacterAnimation;

/**
 * Interface pour la création de personnages dans le jeu.
 * <p>
 * Cette interface définit des méthodes de fabrication pour instancier des objets
 * de type {@link GameCharacter} avec différentes configurations. Elle prend en charge
 * des paramètres tels que le nom, la santé, la stratégie de combat ou le niveau.
 * </p>
 */
public interface CharacterCreator {

    /**
     * Crée un personnage avec un nom spécifique.
     *
     * @param name le nom du personnage à créer.
     * @return une instance de {@link GameCharacter} configurée avec le nom donné.
     */
    public GameCharacter create(String name);


    /**
     * Crée un personnage avec un nom, une santé initiale et une stratégie de combat spécifiques.
     *
     * @param name     le nom du personnage.
     * @param health   les points de vie initiaux du personnage.
     * @param strategy la stratégie de combat assignée au personnage.
     * @return une instance de {@link GameCharacter} configurée avec les paramètres donnés.
     */
    public GameCharacter create(String name, int health, CombatStrategy strategy);


    public GameCharacter create(String name, int health, CombatStrategy strategy, CharacterAnimation animations);

    /**
     * Crée un personnage avec un nom et un niveau spécifiés.
     * <p>
     * Cette méthode utilise la méthode {@link #create(String)} pour initialiser le personnage,
     * puis ajuste son niveau au niveau donné.
     * </p>
     *
     * @param name  le nom du personnage.
     * @param level le niveau initial du personnage.
     * @return une instance de {@link GameCharacter} configurée avec le niveau spécifié.
     */
    default GameCharacter create(String name, int level) {
        GameCharacter character = this.create(name);
        character.setLevel(level);
        return character;
    }
}
