package eu.telecomnancy.rpg.Visitors.Team;

import eu.telecomnancy.rpg.GameCharacter;
import eu.telecomnancy.rpg.Team;
import eu.telecomnancy.rpg.Visitors.BuffVisitor;

public class TeamBuffVisitor implements TeamVisitor {
    @Override
    public void visit(Team team) {
        BuffVisitor buffVisitor = new BuffVisitor();


        for (GameCharacter character : team.getPlayers()) {
            character.accept(buffVisitor);
        }
    }
}
