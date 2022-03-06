package me.unbreathable.compiler.command;

public abstract class Command {

    public final String[] aliases;
    private final String name, usage, description;

    /**
     * Command constructor
     *
     * @param aliases Aliases of the command
     * @param name Name of the command
     * @param usage Usage of the command
     * @param description Description of the command
     */
    protected Command(String[] aliases, String name, String usage, String description) {
        this.aliases = aliases;
        this.name = name;
        this.usage = usage;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        return usage;
    }

    public abstract void execute(String[] args);

}
