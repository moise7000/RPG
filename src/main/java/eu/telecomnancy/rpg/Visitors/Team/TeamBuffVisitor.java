package eu.telecomnancy.rpg.Visitors.Team;


import eu.telecomnancy.rpg.GameCharacter;
import eu.telecomnancy.rpg.GameConfiguration;
import eu.telecomnancy.rpg.Team;
import eu.telecomnancy.rpg.Visitors.BuffVisitor;

/**
 * Visiteur appliquant des buffs aux membres d'une équipe.
 * <p>
 * Cette classe implémente l'interface {@link TeamVisitor} pour appliquer des modifications spécifiques aux
 * attributs de base des personnages d'une équipe, en utilisant les valeurs de configuration définies
 * dans {@link GameConfiguration}.
 * </p>
 */
public class TeamBuffVisitor implements TeamVisitor {

    /**
     *  Applique un buff à chaque membre d'une équipe.
     *
     *   @param team l'équipe à visiter */
    @Override
    public void visit(Team team) {
        BuffVisitor buffVisitor = new BuffVisitor();


        for (GameCharacter character : team.getPlayers()) {
            character.accept(buffVisitor);
        }
    }
}
