package me.unbreathable.compiler.methods;

public abstract class Method {

    private final String alias, usage, description;

    public Method(String alias, String usage, String description) {
        this.alias = alias;
        this.usage = usage;
        this.description = description;
    }

    public abstract MethodResult execute(String[] args);

    public String getAlias() {
        return alias;
    }

    public String getDescription() {
        return description;
    }

    public String getUsage() {
        return usage;
    }
}
