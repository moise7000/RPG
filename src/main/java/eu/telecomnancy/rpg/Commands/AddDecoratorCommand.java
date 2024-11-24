package eu.telecomnancy.rpg.Commands;

import eu.telecomnancy.rpg.Decorator.DecoratorType;
import eu.telecomnancy.rpg.GameFacade;

public class AddDecoratorCommand implements Command {
    private final GameFacade game;
    private final String teamName;
    private final int characterIndex;
    private final DecoratorType type;

    public AddDecoratorCommand(GameFacade game, String teamName, int characterIndex, DecoratorType type) {
        this.game = game;
        this.teamName = teamName;
        this.characterIndex = characterIndex;
        this.type = type;
    }


    @Override
    public void execute() {
        switch (type) {
            case ARMOR :
                game.addArmorToCharacter(teamName, characterIndex);
                break;
            case INVINCIBLE:
                game.addInvincibilityToCharacter(teamName, characterIndex);
                break;

        }
    }

    @Override
    public void undo() {
        //game.removeDecoratorFromCharacter(teamName, characterIndex);
    }
}
