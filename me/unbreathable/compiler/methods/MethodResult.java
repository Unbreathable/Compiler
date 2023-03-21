package me.unbreathable.compiler.methods;

import java.io.File;
import java.util.ArrayList;

public class MethodResult {

    private final boolean success, watch;
    private final String message;
    private final ArrayList<String> content;

    /**
     * Result for methods
     *
     * @param message Error message
     */
    public MethodResult(String message) {
        this.success = false;
        this.watch = false;
        this.message = message;
        this.content = null;
    }

    /**
     * Result for methods
     *
     * @param message Success message
     * @param content File content to be added to the file
     */
    public MethodResult(String message, ArrayList<String> content) {
        this.success = true;
        this.watch = false;
        this.message = message;
        this.content = content;
    }

    /**
     * Result for methods
     *
     * @param directory The directory to watch
     */
    public MethodResult(File directory) {
        this.success = true;
        this.watch = true;
        this.message = directory.getPath();
        this.content = null;
    }

    public boolean wasSuccessful() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isWatch() {
        return watch;
    }

    public ArrayList<String> getContent() {
        return content;
    }

}
