package eu.telecomnancy.rpg.Visitors.Team;

import eu.telecomnancy.rpg.Team;

/**
 * Interface représentant un visiteur de {@link Team}.
 * <p>
 * Cette interface définit une méthode visit() permettant d'appliquer des opérations spécifiques aux équipes de personnages.
 * </p>
 */
public interface TeamVisitor {

    /**
     * Visite une équipe de personnages.
     *
     * @param team l'équipe à visiter.
     */
    void visit(Team team);
}
