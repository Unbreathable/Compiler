package me.unbreathable.compiler.methods;

import me.unbreathable.compiler.Compiler;
import me.unbreathable.compiler.util.FileUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class CompileManager {

    /**
     * The heart of the program: This method recompiles the template file
     * and overwrites the output file using the methods in the file itself.
     *
     * @param template Template file
     */
    public static String compile(File template) {

        // Check if both files exist
        if(!template.exists()) {
            System.out.println("ERROR: The specified template file does not exist!");
            return "";
        }

        // Read content from original file
        ArrayList<String> content = FileUtil.readContent(template);

        // Check for header
        assert content != null;
        if(!content.get(0).startsWith("@output")) {
            return "";
        }

        // Get output file
        File output = new File(template.getParent(), content.get(0).replace("@output ", ""));
        content.remove(0);

        // Check if output file exists
        if(!createOutput(output)) {
            return "";
        }

        // Content to be written into the new file
        ArrayList<String> newContent = new ArrayList<>();

        // Compile the file
        int count = 0;
        for(String query : content) {
            count++;

            MethodResult result = Compiler.getInstance().getManager().execute(query, template.getParentFile());
            if(result.getMessage() == null) {
                newContent.add(query);
                continue;
            }

            if(!result.wasSuccessful()) {
                System.out.println("ERROR: Line " + count + ": " + result.getMessage());
                return "";
            }

            System.out.println("SUCCESS: Compiled line " + count + "!");
            newContent.addAll(result.getContent());

        }

        // Write new file
        FileUtil.writeContent(output, newContent);

        return output.getName();
    }

    /**
     * Creates the output file if it doesn't exist
     *
     * @param output The output file
     * @return If the file exists
     */
    public static boolean createOutput(File output) {

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
                e.printStackTrace();
                System.out.println("Something went wrong while creating the output file. (" + e.getMessage() + ")");
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if the file is a template file
     *
     * @param template The file
     */
    public static boolean checkHeader(File template) {
        try {

            // Read first line of the file
            BufferedReader reader = new BufferedReader(new FileReader(template));
            String line = reader.readLine();
            reader.close();

            // Check if the first line starts with @output (header for template files)
            return line.startsWith("@output");
        } catch (Exception e) {
            return false;
        }
    }

}
