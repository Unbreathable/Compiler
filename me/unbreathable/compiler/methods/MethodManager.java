package me.unbreathable.compiler.methods;

import me.unbreathable.compiler.Compiler;
import me.unbreathable.compiler.methods.impl.InjectMethod;

import java.util.ArrayList;

public class MethodManager {

    private final ArrayList<Method> methods = new ArrayList<>();

    /**
     * Manager for all methods in the application
     * Methods are used to interact with the program using the template file
     */
    public MethodManager() {

        // Add all methods
        methods.add(new InjectMethod());
    }

    /**
     * Method for processing a line of text in the template file
     *
     * @param query The line of text
     * @return The result
     */
    public MethodResult execute(String query) {

        // Check if the query contains the name tag
        if(query.contains("@" + Compiler.getInstance().getName())) {

            // Get the command name
            String[] args = query.split(":");

            // Check for fitting commands
            for (String cmd : args) {

                // Check if there is a command in the query
                for (Method method : methods) {
                    if (cmd.startsWith(method.getAlias())) {

                        // Return error if the method name isn't mentioned
                        if(!cmd.contains("(") || !cmd.contains(")")) {
                            return new MethodResult("Please correct your syntax. (Bracket missing)");
                        }

                        // Get the command args
                        String[] commandArgs = cmd.split("\\(");

                        // Get elements
                        commandArgs = commandArgs[1].split("\\)");

                        // Get the fields
                        commandArgs = commandArgs[0].split(",");

                        // Execute the command
                        return method.execute(commandArgs);
                    }
                }
            }

            // No command found
            return new MethodResult("No method found.");
        }

        // Return nothing
        return new MethodResult(null);
    }

    public ArrayList<Method> getMethods() {
        return methods;
    }
}
