package me.unbreathable.compiler.watching;

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

        // Create a new thread
        WatchThread thread = new WatchThread(template);
        threads.add(thread);

        // Start the thread
        thread.start();
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
