package net.arsentic.library

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

/**
 * @author Oribuin
 */
abstract class ArsenticCommand(open val plugin: ArsenticLib, private val name: String) : TabExecutor {
    fun register() {
        val cmd = Bukkit.getPluginCommand(name)
        if (cmd != null) {
            cmd.setExecutor(this)
            cmd.tabCompleter = this
        }
    }

    /**
     * Execute the plugin's command
     *
     * @param sender The person sending the command
     * @param args The arguments provided in the command.
     */

    abstract fun executeCommand(sender: CommandSender, args: Array<String>)

    /**
     * The tab complete for the command executed.
     *
     * @param sender The person typing the command.
     * @param args The arguments provided by sender
     *
     * @return Potentially null List<String>?
     */
    abstract fun tabComplete(sender: CommandSender, args: Array<String>): List<String>?

    // Register Command
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        executeCommand(sender, args)
        return true
    }

    // Register command tab complete
    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<String>): List<String>? {
        return tabComplete(sender, args)
    }

    // Get the command via name
    val command: Command?
        get() = plugin.getCommand(name)

}