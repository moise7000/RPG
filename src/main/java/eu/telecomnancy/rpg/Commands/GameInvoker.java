package eu.telecomnancy.rpg.Commands;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * La classe {@code GameInvoker} gère l'exécution des commandes dans le jeu.
 * Elle suit le pattern Command en stockant, exécutant et annulant des commandes.
 * <p>
 * Les commandes à exécuter sont placées dans une file d'attente, tandis que
 * les commandes déjà exécutées sont stockées dans une pile pour permettre
 * leur annulation.
 */
public class GameInvoker {
    private final Queue<Command> commandQueue;
    private final Stack<Command> executedCommands;

    /**
     * Constructeur de {@code GameInvoker}.
     * Initialise la file d'attente des commandes et la pile des commandes exécutées.
     */
    public GameInvoker() {
        this.commandQueue = new LinkedList<>();
        this.executedCommands = new Stack<>();
    }

    /**
     * Ajoute une commande à la file d'attente pour une exécution future.
     *
     * @param command La commande, à ajouter.
     */
    public void addCommand(Command command) {this.commandQueue.add(command);}

    /**
     * Exécute la prochaine commande dans la file d'attente.
     * Une fois exécutée, la commande est stockée dans la pile
     * pour pouvoir être annulée ultérieurement.
     */
    public void executeNextCommand() {
        if(!this.commandQueue.isEmpty()) {
            Command command = this.commandQueue.poll();
            command.execute();
            executedCommands.push(command);
        }
    }

    /**
     * Exécute toutes les commandes restantes dans la file d'attente.
     */
    public void executeAllCommands() {
        while(!this.commandQueue.isEmpty()) {
            executeNextCommand();
        }
    }

    /**
     * Annule la dernière commande exécutée en appelant sa méthode {@code undo()}.
     * Si aucune commande n'a été exécutée, cette méthode ne fait rien.
     */
    public void undoLastCommand() {
        if(!this.executedCommands.isEmpty()) {
            Command command = this.executedCommands.pop();
            command.undo();
        }
    }

    /**
     * Vide la file d'attente des commandes sans les exécuter.
     */
    public void clearQueue() {commandQueue.clear();}

    /**
     * Vérifie s'il reste des commandes dans la file d'attente.
     *
     * @return {@code true} si la file d'attente contient des commandes, {@code false} sinon.
     */
    public boolean hasCommands() {return !commandQueue.isEmpty();}


}
