package me.unbreathable.compiler;

import java.awt.*;
import java.io.Console;
import java.io.IOException;

public class Start {

    public static void main(String[] args) throws IOException {
        Console console = System.console();
        if(console == null && !GraphicsEnvironment.isHeadless()){
            String filename = new java.io.File(Start.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();
            Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + filename + "\""});
        } else {
            Compiler compiler = new Compiler();
            compiler.init();
            System.out.println("Program has ended, please type 'exit' to close the console");
        }

    }

}
