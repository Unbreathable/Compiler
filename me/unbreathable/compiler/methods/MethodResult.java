package me.unbreathable.compiler.methods;

import java.util.ArrayList;

public class MethodResult {

    private final boolean success;
    private final String message;
    private final ArrayList<String> content;

    /**
     * Result for methods
     *
     * @param message Error message
     */
    public MethodResult(String message) {
        this.success = false;
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
        this.message = message;
        this.content = content;
    }

    public boolean wasSuccessful() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<String> getContent() {
        return content;
    }

}
