package me.unbreathable.compiler.methods.impl;

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
    public MethodResult execute(String[] args) {

        File target = new File(args[0]);

        if(!target.exists()) {
            return new MethodResult(target.getAbsolutePath() + " not found.");
        }

        return new MethodResult("Injected text.", FileUtil.readContent(target));
    }
}
