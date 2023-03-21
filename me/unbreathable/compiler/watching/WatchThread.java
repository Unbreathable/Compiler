package me.unbreathable.compiler.watching;

import java.io.File;

public class WatchThread extends Thread {

    // The template file
    final File template;
    final File directory;
    final String output;

    public WatchThread(String output, File template, File directory) {
        this.output = output;
        this.template = template;
        this.directory = directory;
    }

    public void run() {
        Watcher.watch(output, template, directory);
    }

}
