package eu.telecomnancy.rpg;

/**
 * Interface représentant un visiteur de personnages.
 * <p>
 * Un visiteur est utilisé pour effectuer des actions spécifiques sur les différents types de personnages
 * du jeu (comme les {@link Warrior} ou les {@link Wizard}).
 * </p>
 */
public interface CharacterVisitor {

    /**
     * Méthode appelée pour visiter un personnage de type {@link Warrior}.
     *
     * @param warrior le personnage Warrior à visiter.
     */
    void visit(Warrior warrior);

    /**
     * Méthode appelée pour visiter un personnage de type {@link Wizard}.
     *
     * @param wizard le personnage Wizard à visiter.
     */
    void visit(Wizard wizard);
}
