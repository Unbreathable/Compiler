package me.unbreathable.compiler.util;

import java.io.*;
import java.util.ArrayList;

public class FileUtil {

    /**
     * Reads content from a file
     *
     * @param file The file
     * @return The content
     */
    public static ArrayList<String> readContent(File file)  {
        try {
            ArrayList<String> content = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line = reader.readLine()) != null) {
                content.add(line);
            }
            reader.close();
            return content;
        } catch (Exception ignored) {}
        return null;
    }

    /**
     * Writes content to a file
     *
     * @param file The file
     * @param content The content to be written
     */
    public static void writeContent(File file, ArrayList<String> content) {
        try {
            PrintWriter writer = new PrintWriter(file);
            for(String s : content) {
                writer.println(s);
            }
            writer.flush();
            writer.close();
        } catch (Exception ignored) {}
    }

}
