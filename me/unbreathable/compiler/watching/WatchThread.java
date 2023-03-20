package me.unbreathable.compiler.watching;

import java.io.File;

public class WatchThread extends Thread {

    // The template file
    final File template;

    public WatchThread(File template) {
        this.template = template;
    }

    public void run() {
        Watcher.watch(template);
    }

}
