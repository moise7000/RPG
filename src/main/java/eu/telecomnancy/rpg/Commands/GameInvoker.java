package eu.telecomnancy.rpg.Commands;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GameInvoker {
    private final Queue<Command> commandQueue;
    private final Stack<Command> executedCommands;

    public GameInvoker() {
        this.commandQueue = new LinkedList<>();
        this.executedCommands = new Stack<>();
    }

    public void addCommand(Command command) {this.commandQueue.add(command);}

    public void executeNextCommand() {
        if(!this.executedCommands.isEmpty()) {
            Command command = this.commandQueue.poll();
            command.execute();
            executedCommands.push(command);
        }
    }

    public void executeAllCommands() {
        while(!this.executedCommands.isEmpty()) {
            executeNextCommand();
        }
    }

    public void undoLastCommand() {
        if(!this.executedCommands.isEmpty()) {
            Command command = this.executedCommands.pop();
            command.undo();
        }
    }

    public void clearQueue() {commandQueue.clear();}

    public boolean hasCommands() {return !commandQueue.isEmpty();}


}
