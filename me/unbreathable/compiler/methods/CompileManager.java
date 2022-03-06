package me.unbreathable.compiler.methods;

import me.unbreathable.compiler.Compiler;
import me.unbreathable.compiler.util.FileUtil;

import java.io.File;
import java.util.ArrayList;

public class CompileManager {

    /**
     * The heart of the program: This method recompiles the template file
     * and overwrites the output file using the methods in the file itself.
     *
     * @param template Template file
     * @param output File to be overwritten using the template
     */
    public static void compile(File template, File output) {

        // Check if both files exist
        if(!checkFiles(template, output)) {
            return;
        }

        // Read content from original file
        ArrayList<String> content = FileUtil.readContent(template);

        // Content to be written into the new file
        ArrayList<String> newContent = new ArrayList<>();

        // Compile the file
        int count = 0;
        assert content != null;
        for(String query : content) {
            count++;

            MethodResult result = Compiler.getInstance().getManager().execute(query);
            if(result.getMessage() == null) {
                newContent.add(query);
                continue;
            }

            if(!result.wasSuccessful()) {
                System.out.println("ERROR: Line " + count + ": " + result.getMessage());
                return;
            }

            System.out.println("SUCCESS: Compiled line " + count + "!");
            newContent.addAll(result.getContent());

        }

        // Write new file
        FileUtil.writeContent(output, newContent);

    }

    /**
     * Check if template and output file exist
     * if the output file doesnt exist its going to create it
     *
     * @param template Template file
     * @param output Output file
     * @return If the files exist
     */
    public static boolean checkFiles(File template, File output) {

        // Check if the template file exists
        if(!template.exists()) {
            System.out.println("Template file doesn't exist yet.");
            return false;
        }

        // Check if the output file exists
        if(!output.exists()) {
            System.out.println("Output file doesn't exist yet. Trying to create it..");
            try {

                // If it doesn't exist try to create a new output file
                boolean created = output.createNewFile();
                if(!created) {
                    System.out.println("Something went wrong while creating the output file.");
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Something went wrong while creating the output file. (" + e.getMessage() + ")");
                return false;
            }
        }
        return true;
    }

}
