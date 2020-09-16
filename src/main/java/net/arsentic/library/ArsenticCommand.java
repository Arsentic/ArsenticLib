package net.arsentic.library;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.event.Listener;

import java.util.List;

/**
 * @author Oribuin
 *
 * Reminder: Java Dumb
 */
public abstract class ArsenticCommand implements TabExecutor, Listener {

    private final ArsenticPlugin plugin;
    private final String name;

    // Define prefix
    public String prefix = "&8[&b&lArsentic&8] &7➜";

    public ArsenticCommand(ArsenticPlugin plugin, String name) {
        this.plugin = plugin;
        this.name = name;
    }

    public void register() {
        PluginCommand cmd = Bukkit.getPluginCommand(name);
        if (cmd != null) {
            cmd.setExecutor(this);
            cmd.setTabCompleter(this);
        }

        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Execute the plugin's command
     *
     * @param sender The person sending the command
     * @param args   The arguments provided in the command.
     */

    public abstract void executeCommand(CommandSender sender, String[] args);

    /**
     * The tab complete for the command executed.
     *
     * @param sender The person typing the command.
     * @param args   The arguments provided by sender
     * @return Potentially null List<String>?
     */
    public abstract List<String> tabComplete(CommandSender sender, String[] args);

    // Execute the command.
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        executeCommand(sender, args);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return tabComplete(sender, args);
    }

    // Java suck
    public ArsenticPlugin getPlugin() {
        return plugin;
    }
}
