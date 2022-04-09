package me.unbreathable.compiler;

import me.unbreathable.compiler.command.CommandManager;
import me.unbreathable.compiler.methods.MethodManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Compiler {

    private static Compiler instance;

    private final String name = "compiler", version = "1.0.1", author = "Unbreathable";

    private MethodManager manager;
    private CommandManager commandManager;

    // Start the base of the program
    public Compiler() {
        System.out.println(" ");
        System.out.println(name + " [" + version + "]");
        System.out.println("Created by " + author);
        System.out.println("Use 'help' for a list of commands.");
        System.out.println(" ");

        // Attach instance
        instance = this;
    }

    // Init the application
    public void init() throws IOException {

        // Register the manages
        manager = new MethodManager();
        commandManager = new CommandManager();

        // Register the reader that reads the console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        // Read the console until dead
        while((line = reader.readLine()) != null) {
            if(!commandManager.execute(line.split(" "))) {
                System.out.println("Invalid command! Use 'help' for a list of commands.");
            }
        }

    }

    public String getVersion() {
        return version;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public MethodManager getManager() {
        return manager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public static Compiler getInstance() {
        return instance;
    }

}
