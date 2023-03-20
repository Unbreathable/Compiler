package me.unbreathable.compiler.command.impl;

import me.unbreathable.compiler.Compiler;
import me.unbreathable.compiler.command.Command;
import me.unbreathable.compiler.methods.CompileManager;

import java.io.File;

public class StartCommand extends Command {

    public StartCommand() {
        super(new String[] {"start"}, "start", "start <directory: File>", "Starts the compiler in the specified directory.");
    }

    @Override
    public void execute(String[] args) {

        // Combine args (to fix spaces)
        StringBuilder builder = new StringBuilder();
        for (String arg : args) {
            builder.append(arg).append(" ");
        }

        // Get the directory
        File file = new File(builder.substring(0, builder.length() - 1));

        // Check if the directory exists
        if (!file.isDirectory()) {
            System.out.println("ERROR: The specified directory does not exist!");
            return;
        }

        // Scan the directory for files
        scanDirectory(file);

    }

    private void scanDirectory(File directory) {
        File[] files = directory.listFiles();

        // Check if the directory is empty
        if(files == null) {
            return;
        }

        // Loop through all files
        for(File file : files) {
            if(file.isDirectory()) {
                scanDirectory(file);
                continue;
            }

            // Check if the file is a template file
            if(CompileManager.checkHeader(file)) {

                // Watch the file
                System.out.println("Watching file " + file.getName());
                Compiler.getInstance().getWatchManager().add(file);
            }
        }
    }
}
