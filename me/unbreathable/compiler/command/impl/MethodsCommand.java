package me.unbreathable.compiler.command.impl;

import me.unbreathable.compiler.Compiler;
import me.unbreathable.compiler.command.Command;
import me.unbreathable.compiler.methods.Method;

public class MethodsCommand extends Command {

    /**
     * Command for listing all methods. More than inject might be added soon
     */
    public MethodsCommand() {
        super(new String[]{"methods"}, "methods", "methods", "Shows a list of methods and a quick description for them.");
    }

    @Override
    public void execute(String[] args) {

        // Print all methods
        System.out.println(" ");
        System.out.println("Here is a list of methods:");
        for(Method method : Compiler.getInstance().getManager().getMethods()) {
            System.out.println("-> " + method.getUsage() + " - " +  method.getDescription());
        }
        System.out.println(" ");

    }
}
