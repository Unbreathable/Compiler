package me.unbreathable.compiler.watching;

import me.unbreathable.compiler.methods.CompileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class Watcher {

    public static void watch(String output, File template, File directory) {

        // Check if template and output file exist
        if (!template.exists()) {
            return;
        }

        try {

            // Get the path of the template file
            final Path path = FileSystems.getDefault().getPath(directory.getPath());

            // Create a new watch service
            final WatchService watchService = FileSystems.getDefault().newWatchService();

            // Add a watch service for the ENTRY_MODIFY event
            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            String prefix = "[" + template.getName() + "] ";

            // Get the watch key
            final WatchKey wk = watchService.take();
            long lastModified = System.currentTimeMillis();

            while (true) {

                // Variable for already compiling
                boolean compiling = false;

                // Go through all events
                for (WatchEvent<?> event : wk.pollEvents()) {

                    // Check if the path is equal to the output file
                    final Path filePath = (Path) event.context();

                    // Check if it has already been recompiled once
                    if(filePath.getFileName().toString().equalsIgnoreCase(output)) {
                        continue;
                    }

                    // Check if the file has been modified recently
                    if(System.currentTimeMillis() - lastModified < 1000) {
                        continue;
                    }

                    // Check if it has already been recompiled once
                    if (!compiling) {

                        // Recompile the file
                        System.out.println(prefix + "Detected modification in " + filePath.getFileName() + ". Recompiling..");
                        output = CompileManager.compile(false, template, prefix);
                        System.out.println(prefix + "Compiling finished!");

                        // Set compiling state
                        compiling = true;
                        lastModified = System.currentTimeMillis();
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
