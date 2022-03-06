package me.unbreathable.compiler.command;

import me.unbreathable.compiler.command.impl.CompileCommand;
import me.unbreathable.compiler.command.impl.HelpCommand;
import me.unbreathable.compiler.command.impl.MethodsCommand;
import me.unbreathable.compiler.command.impl.WatchCommand;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandManager {

    // List for storing all commands
    private final ArrayList<Command> commands = new ArrayList<>();

    /**
     * The command manager manages all commands registered.
     */
    public CommandManager() {

        // Add all commands
        commands.add(new HelpCommand());
        commands.add(new MethodsCommand());
        commands.add(new CompileCommand());
        commands.add(new WatchCommand());
    }

    /**
     * Checks for a command in the array and runs it if any command exists
     *
     * @param args Array for the arguments (split by spaces)
     * @return If a command was found or not
     */
    public boolean execute(String[] args) {

        // Check if there even is a command
        if(args.length == 0) {
            return false;
        }

        // Get all commands
        for(Command command : commands) {

            // Check for aliases
            for(String alias : command.aliases) {

                // Execute the command if the alias matches
                if(alias.equalsIgnoreCase(args[0])) {
                    command.execute(Arrays.copyOfRange(args, 1, args.length));
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }
}
