package me.unbreathable.compiler.watching;

import me.unbreathable.compiler.methods.CompileManager;

import java.io.File;
import java.util.ArrayList;

public class WatchManager {

    // List of all files currently being watched
    private final ArrayList<WatchThread> threads = new ArrayList<>();

    /**
     * Add a file to the watch list
     *
     * @param template Template file
     */
    public void add(File template) {
        ArrayList<WatchThread> toAdd = new ArrayList<>();

        // Compile for the first time
        String output = CompileManager.compile(false, template);

        // Watch the main directory
        toAdd.add(new WatchThread(output, template, template.getParentFile()));

        System.out.println("Reading " + template.getName() + " for watchable directories...");
        // Watch all directories mentioned in the file
        for(String s : CompileManager.compile(true, template).split(";")) {
            if(s.isEmpty()) continue;
            System.out.println("Watching " + s);
            toAdd.add(new WatchThread(output, template, new File(s)));
        }

        // Add threads
        threads.addAll(toAdd);

        // Start the threads
        for(WatchThread t : toAdd) {
            t.start();
        }
    }

    /**
     * End the watching process for all files
     */
    public void end() {

        // Destroy all threads
        for(WatchThread thread : threads) {
            thread.interrupt();
        }

        threads.clear();
    }

}
