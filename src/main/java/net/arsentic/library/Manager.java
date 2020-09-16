package net.arsentic.library;

public abstract class Manager {

    private final ArsenticPlugin plugin;

    public Manager(ArsenticPlugin plugin) {
        this.plugin = plugin;
    }

    public abstract void reload();

    public abstract void disable();

    // Java sucks
    public ArsenticPlugin getPlugin() {
        return plugin;
    }
}
