package eu.telecomnancy.rpg.Commands;

import eu.telecomnancy.rpg.Decorator.DecoratorType;
import eu.telecomnancy.rpg.GameFacade;

/**
 * Commande permettant d'ajouter un décorateur à un personnage dans le jeu.
 * Cette commande suit le pattern Command et permet également de définir
 * une action "undo" pour annuler l'ajout du décorateur.
 */
public class AddDecoratorCommand implements Command {
    private final GameFacade game;
    private final String teamName;
    private final int characterIndex;
    private final DecoratorType type;

    /**
     * Constructeur pour la commande d'ajout de décorateur.
     *
     * @param game          Instance du jeu (GameFacade) permettant d'accéder aux fonctionnalités.
     * @param teamName      Nom de l'équipe à laquelle appartient le personnage.
     * @param characterIndex Index du personnage dans l'équipe.
     * @param type          Type de décorateur à ajouter.
     */
    public AddDecoratorCommand(GameFacade game, String teamName, int characterIndex, DecoratorType type) {
        this.game = game;
        this.teamName = teamName;
        this.characterIndex = characterIndex;
        this.type = type;
    }


    /**
     * Exécute la commande pour ajouter un décorateur au personnage.
     * Le comportement varie en fonction du type de décorateur.
     */
    @Override
    public void execute() {
        switch (type) {
            case ARMOR :
                game.addArmorToCharacter(teamName, characterIndex);
                break;
            case INVINCIBLE:
                //game.addInvincibilityToCharacter(teamName, characterIndex);
                break;

        }
    }

    /**
     * Annule la commande en supprimant le décorateur ajouté au personnage.
     * La méthode est actuellement non implémentée.
     */
    @Override
    public void undo() {
        //game.removeDecoratorFromCharacter(teamName, characterIndex);
    }
}
