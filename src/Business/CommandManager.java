package Business;

import Data.Command;
import Data.MacroCommand;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandManager {

    private HashMap<String, Command> commands;

    public CommandManager() {
        commands = new HashMap<>();
    }

    public void addCommands(String commandID, Command command) {
        this.commands.put(commandID, command);
    }

    public Command getBasicCommand(String commandID) {
        Command command = this.commands.get(commandID);
        if (command instanceof MacroCommand) {
            return null;
        } else if (command instanceof Command) {
            return null;
        } else {
            return null;
        }
    }
}
