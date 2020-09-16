package net.arsentic.library;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Oribuin
 * <p>
 * Reminder: Java Dumb
 */
public abstract class ArsenticPlugin extends JavaPlugin {

    private final Map<Class<? extends Manager>, Manager> managers;

    public ArsenticPlugin() {
        this.managers = new LinkedHashMap<>();
    }

    public abstract void enablePlugin();

    public abstract void disablePlugin();

    @Override
    public void onEnable() {
        this.enablePlugin();
        this.reload();
        getServer().getConsoleSender().sendMessage(HexUtils.colorify("&8[&b&lArsentic&8] &7-> The arsentic plugin, " + getDescription().getVersion() + ", has been &aenabled &7successfully!"));
    }

    @Override
    public void onDisable() {
        this.disablePlugin();
        getServer().getConsoleSender().sendMessage(HexUtils.colorify("&8[&b&lArsentic&8] &7-> The arsentic plugin, " + getDescription().getVersion() + ", has been &cdisabled &7successfully!"));
    }

    /**
     * Reload the plugin.
     */
    public void reload() {
        this.disableManagers();
        getServer().getScheduler().cancelTasks(this);
        managers.values().forEach(Manager::reload);
    }

    /**
     * Register all the plugin listeners.
     *
     * @param listeners The listeners being registered.
     */
    public void registerListeners(Listener... listeners) {
        Arrays.stream(listeners).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    /**
     * Register all the commands listed.
     *
     * @param commands The commands being registered.
     */
    public void registerCommands(ArsenticCommand... commands) {
        Arrays.stream(commands).forEach(ArsenticCommand::register);
    }

    /**
     * Disable all the managers.
     */
    private void disableManagers() {
        this.managers.values().forEach(Manager::disable);
    }


    /**
     * Gets a manager instance
     *
     * @param managerClass The class of the manager to get
     * @param <T>          extends Manager
     * @return A new or existing instance of the given manager class
     */
    @SuppressWarnings("unchecked")
    public final <T extends Manager> T getManager(Class<T> managerClass) {
        if (this.managers.containsKey(managerClass))
            return (T) this.managers.get(managerClass);

        try {
            T manager = managerClass.getConstructor(ArsenticPlugin.class).newInstance(this);
            this.managers.put(managerClass, manager);
            manager.reload();
            return manager;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ignored) {
            // Ignore it, Sorry :(
        }

        return (T) this.managers.get(managerClass);
    }

}
