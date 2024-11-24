package eu.telecomnancy.rpg.Commands;

import eu.telecomnancy.rpg.GameCharacter;
import eu.telecomnancy.rpg.GameFacade;

import java.util.ArrayList;
import java.util.List;

public class HealTeamCommand implements Command{
    private final GameFacade game;
    private final String teamName;
    private final List<Integer> previousHealthValues;

    public HealTeamCommand(GameFacade game, String teamName) {
        this.game = game;
        this.teamName = teamName;
        this.previousHealthValues = new ArrayList<>();
    }


    @Override
    public void execute() {
        List<GameCharacter> team = game.getTeams().get(teamName);
        previousHealthValues.clear();
        team.forEach(character -> previousHealthValues.add(character.getHealth()));
        game.healTeam(teamName);
    }

    @Override
    public void undo() {
        List<GameCharacter> team = game.getTeams().get(teamName);
        for (int i = 0; i < team.size(); i++) {
            team.get(i).setHealth(previousHealthValues.get(i));
        }
    }
}
