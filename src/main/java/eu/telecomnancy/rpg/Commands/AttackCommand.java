package eu.telecomnancy.rpg.Commands;

import eu.telecomnancy.rpg.GameCharacter;
import eu.telecomnancy.rpg.GameFacade;

public class AttackCommand implements Command{
    private final GameFacade game;
    private final String attackingTeam;
    private final int attackerIndex;
    private final String defendingTeam;
    private final int defenderIndex;
    private int previousDefenderHealth;


    public AttackCommand(GameFacade game,
                         String attackingTeam,
                         int attackerIndex,
                         String defendingTeam,
                         int defenderIndex){
        this.game = game;
        this.attackingTeam = attackingTeam;
        this.attackerIndex = attackerIndex;
        this.defendingTeam = defendingTeam;
        this.defenderIndex = defenderIndex;
    }


    @Override
    public void execute(){
        GameCharacter defender = game.getCharacterFromTeam(defendingTeam, defenderIndex);
        previousDefenderHealth = defender.getHealth();
        game.attackTeam(attackingTeam, attackerIndex, defendingTeam, defenderIndex);
    }

    @Override
    public void undo() {
        GameCharacter defender = game.getCharacterFromTeam(defendingTeam, defenderIndex);
        defender.setHealth(previousDefenderHealth);
    }
}
