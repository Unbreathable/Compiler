package me.unbreathable.compiler.methods.impl;

import me.unbreathable.compiler.Compiler;
import me.unbreathable.compiler.methods.Method;
import me.unbreathable.compiler.methods.MethodResult;
import me.unbreathable.compiler.util.FileUtil;

import java.io.File;

public class InjectMethod extends Method {

    /**
     * Method for injecting text from another file into the output file
     */
    public InjectMethod() {
        super("inject", "inject(filename: Path)", "Injects contents from a different file into the selected template.");
    }

    @Override
    public MethodResult execute(String[] args, File directory) {

        File target = target(args, directory);

        if(!target.exists()) {
            return new MethodResult(target.getAbsolutePath() + " not found.");
        }

        return new MethodResult("Injected text.", FileUtil.readContent(target));
    }

    @Override
    public MethodResult toWatch(String[] args, File directory) {
        return new MethodResult(target(args, directory).getParentFile());
    }

    /**
     * Get the file to inject
     *
     * @param args The arguments
     * @param directory The directory
     * @return The file
     */
    private File target(String[] args, File directory) {
        File target;
        if(args[0].startsWith("@origin") && Compiler.origin != null) {

            // Use the origin directory
            target = new File(args[0].replace("@origin", Compiler.origin.getPath()));
        } else {

            // Use the parent directory
            target = new File(directory, args[0]);
        }

        return target;
    }
}
