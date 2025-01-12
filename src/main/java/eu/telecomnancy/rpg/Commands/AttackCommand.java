package eu.telecomnancy.rpg.Commands;

import eu.telecomnancy.rpg.GameCharacter;
import eu.telecomnancy.rpg.GameFacade;

/**
 * Commande permettant d'effectuer une attaque entre deux personnages.
 * Cette commande suit le pattern Command et permet d'annuler l'attaque
 * en restaurant les points de vie du défenseur à leur état précédent.
 */
public class AttackCommand implements Command{
    private final GameFacade game;
    private final String attackingTeam;
    private final int attackerIndex;
    private final String defendingTeam;
    private final int defenderIndex;
    private int previousDefenderHealth;


    /**
     * Constructeur pour la commande d'attaque.
     *
     * @param game           Instance du jeu (GameFacade) permettant d'accéder aux fonctionnalités.
     * @param attackingTeam  Nom de l'équipe qui attaque.
     * @param attackerIndex  Index du personnage attaquant dans son équipe.
     * @param defendingTeam  Nom de l'équipe qui se défend.
     * @param defenderIndex  Index du personnage défenseur dans son équipe.
     */
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


    /**
     * Exécute la commande d'attaque en infligeant des dégâts au défenseur.
     * Les points de vie du défenseur avant l'attaque sont sauvegardés
     * pour permettre une éventuelle annulation.
     */
    @Override
    public void execute(){
        GameCharacter defender = game.getCharacterFromTeam(defendingTeam, defenderIndex);
        previousDefenderHealth = defender.getHealth();
        System.out.println("in execute");
        game.attackTeam(attackingTeam, attackerIndex, defendingTeam, defenderIndex);
    }


    /**
     * Annule l'attaque en restaurant les points de vie du défenseur
     * à leur valeur précédente avant l'attaque.
     */
    @Override
    public void undo() {
        GameCharacter defender = game.getCharacterFromTeam(defendingTeam, defenderIndex);
        defender.setHealth(previousDefenderHealth);
    }
}
