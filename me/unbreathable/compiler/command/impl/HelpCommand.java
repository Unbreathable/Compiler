package me.unbreathable.compiler.command.impl;

import me.unbreathable.compiler.Compiler;
import me.unbreathable.compiler.command.Command;

public class HelpCommand extends Command {

    /**
     * Command for printing all commands to help the user understand the commands.
     */
    public HelpCommand() {
        super(new String[]{"help", "?"}, "help", "help", "Shows this page.");
    }

    @Override
    public void execute(String[] args) {

        // Print all commands
        System.out.println(" ");
        System.out.println("Here is a list of commands:");
        for(Command command : Compiler.getInstance().getCommandManager().getCommands()) {
            System.out.println("-> " + command.getUsage() + " (" +  command.getDescription() + ")");
        }
        System.out.println(" ");
    }
}
