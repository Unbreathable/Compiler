package me.unbreathable.compiler.command.impl;

import me.unbreathable.compiler.command.Command;
import me.unbreathable.compiler.methods.CompileManager;

import java.io.File;

public class CompileCommand extends Command {

    /**
     * Command for compiling a file using input and output file.
     */
    public CompileCommand() {
        super(new String[] {"compile", "cmp"}, "compile", "compile <input: File> <output: File>", "Compile a template file to a output file.");
    }

    @Override
    public void execute(String[] args) {

        if(args.length <= 1) {
            System.out.println("Please use: " + this.getUsage());
        } else {
            CompileManager.compile(new File(args[0]), new File(args[1]));
        }

    }
}
