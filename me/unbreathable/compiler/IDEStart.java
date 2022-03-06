package me.unbreathable.compiler;

import java.io.IOException;

public class IDEStart {

    public static void main(String[] args) throws IOException {
        Compiler compiler = new Compiler();
        compiler.init();
        System.out.println("Program has ended, please type 'exit' to close the console");
    }

}
