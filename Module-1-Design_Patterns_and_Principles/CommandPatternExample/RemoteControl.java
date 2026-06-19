package CommandPatternExample;

import java.util.Stack;

public class RemoteControl {
    private Command currentCommand;
    private Stack<Command> commandHistory;
    private Stack<Command> undoHistory;
    
    public RemoteControl() {
        this.commandHistory = new Stack<>();
        this.undoHistory = new Stack<>();
    }
    
    public void setCommand(Command command) {
        this.currentCommand = command;
        System.out.println("Command set: " + command.getClass().getSimpleName());
    }
    
    public void pressButton() {
        if (currentCommand != null) {
            currentCommand.execute();
            commandHistory.push(currentCommand);
            undoHistory.clear();
        } else {
            System.out.println("No command set!");
        }
    }
    
    public void pressUndo() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pop();
            lastCommand.undo();
            undoHistory.push(lastCommand);
            System.out.println("Undo performed!");
        } else {
            System.out.println("Nothing to undo!");
        }
    }
    
    public void pressRedo() {
        if (!undoHistory.isEmpty()) {
            Command command = undoHistory.pop();
            command.execute();
            commandHistory.push(command);
            System.out.println("Redo performed!");
        } else {
            System.out.println("Nothing to redo!");
        }
    }
}