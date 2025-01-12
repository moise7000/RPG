package eu.telecomnancy.rpg.Commands;

import eu.telecomnancy.rpg.GameCharacter;
import eu.telecomnancy.rpg.GameFacade;

import java.util.ArrayList;
import java.util.List;

/**
 * Commande permettant de soigner tous les personnages d'une équipe.
 * <p>
 * Cette commande suit le pattern Command et permet également d'annuler
 * l'action de soin en restaurant les points de vie initiaux de chaque personnage.
 */
public class HealTeamCommand implements Command{
    private final GameFacade game;
    private final String teamName;
    private final List<Integer> previousHealthValues;

    /**
     * Constructeur de {@code HealTeamCommand}.
     *
     * @param game     Instance du jeu (GameFacade) permettant d'accéder aux équipes et aux actions.
     * @param teamName Nom de l'équipe à soigner.
     */
    public HealTeamCommand(GameFacade game, String teamName) {
        this.game = game;
        this.teamName = teamName;
        this.previousHealthValues = new ArrayList<>();
    }


    /**
     * Exécute la commande de soin pour toute l'équipe.
     * <p>
     * Avant d'appliquer le soin, les points de vie de chaque personnage sont sauvegardés
     * afin de pouvoir être restaurés en cas d'annulation.
     */
    @Override
    public void execute() {
        List<GameCharacter> team = game.getTeams().get(teamName);
        previousHealthValues.clear();
        team.forEach(character -> previousHealthValues.add(character.getHealth()));
        game.healTeam(teamName);
    }

    /**
     * Annule la commande de soin en restaurant les points de vie
     * de chaque membre de l'équipe à leur état initial.
     */
    @Override
    public void undo() {
        List<GameCharacter> team = game.getTeams().get(teamName);
        for (int i = 0; i < team.size(); i++) {
            team.get(i).setHealth(previousHealthValues.get(i));
        }
    }
}
