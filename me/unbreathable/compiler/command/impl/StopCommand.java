package me.unbreathable.compiler.command.impl;

import me.unbreathable.compiler.Compiler;
import me.unbreathable.compiler.command.Command;

public class StopCommand extends Command {

    public StopCommand() {
        super(new String[] {"stop"}, "stop", "stop", "Stops the watching of any files.");
    }

    @Override
    public void execute(String[] args) {
        Compiler.getInstance().getWatchManager().end();
    }
}
