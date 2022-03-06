package me.unbreathable.compiler.command.impl;

import me.unbreathable.compiler.command.Command;
import me.unbreathable.compiler.methods.CompileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WatchCommand extends Command {

    /**
     * Command for watching the input and output file, also other files in the directory if needed
     */
    public WatchCommand() {
        super(new String[] {"watch", "live-compile"}, "watch", "watch <input: File> <output: File> <toWatch: File...>", "Re-compiles the output file every time the input file changes. (Live-Compiling)");
    }

    @Override
    public void execute(String[] args) {

        // Get template and output file
        File template = new File(args[0]);
        File output = new File(args[1]);

        // Get other files attached
        ArrayList<String> otherFiles = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(args, 2, args.length)));

        // Check if template and output file exist
        if(!CompileManager.checkFiles(template, output)) {
            return;
        }

        try {

            // Get the path of the template file
            final Path path = FileSystems.getDefault().getPath(template.getAbsolutePath().replace(template.getName(), ""));

            // Create a new watch service
            final WatchService watchService = FileSystems.getDefault().newWatchService();

            // Add a watch service for the ENTRY_MODIFY event
            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

            // Just using while(true) here since it should run forever (might make this into a thread in the future)
            while (true) {

                // Get the watch key
                final WatchKey wk = watchService.take();
                for (WatchEvent<?> event : wk.pollEvents()) {

                    // Get the path of the changed directory
                    final Path changed = (Path) event.context();

                    // Check if any of the other files to watch have changed
                    for(String file : otherFiles) {

                        // Check if the file changed is the file given in the for loop
                        if (changed.endsWith(file)) {

                            // Recompile the output file
                            System.out.println("SUCCESS: Detected modification in template file. Recompiling..");
                            CompileManager.compile(template, output);
                            System.out.println("SUCCESS: Compiling finished!");
                            break;
                        }
                    }

                    // Check if the changed file is equal to the template file
                    if (changed.endsWith(template.getName())) {

                        // Recompile the output file
                        System.out.println("SUCCESS: Detected modification in template file. Recompiling..");
                        CompileManager.compile(template, output);
                        System.out.println("SUCCESS: Compiling finished!");
                    }
                }

                // Check if the key is still valid and reset the key
                boolean valid = wk.reset();
                if (!valid) {
                    System.out.println("ERROR: Watch service key unregistered.");
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
