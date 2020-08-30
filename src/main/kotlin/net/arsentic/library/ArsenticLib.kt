package net.arsentic.library

import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import kotlin.reflect.KClass

open class ArsenticLib : JavaPlugin() {
    private val kotlinManagers = mutableMapOf<KClass<out Manager>, Manager>()
    private val javaManagers = mutableMapOf<Class<out Manager>, Manager>()


    open fun enablePlugin() {
        this.saveDefaultConfig()
        this.reload()
        server.consoleSender.sendMessage(HexUtils.colorify("&8[&b&lArsentic&8] &7-> The arsentic plugin, ${description.name}, has been &aenabled &7successfully!"))
    }

    open fun disablePlugin() {
        this.disableManagers()
        server.consoleSender.sendMessage(HexUtils.colorify("&8[&b&lArsentic&8] &7-> The arsentic plugin, ${description.name} has been &cdisabled &7successfully!"))
    }

    fun registerListeners(vararg listeners: Listener) {
        for (listener in listeners) {
            Bukkit.getPluginManager().registerEvents(listener, this)
        }
    }

    fun registerCommands(vararg commands: ArsenticCommand) {
        for (cmd in commands) {
            cmd.register()
        }
    }


    private fun reload() {
        this.disableManagers()
        this.server.scheduler.cancelTasks(this)
        this.kotlinManagers.values.forEach { manager -> manager.reload() }
        this.javaManagers.values.forEach { manager -> manager.reload() }

    }

    private fun disableManagers() {
        this.kotlinManagers.values.forEach { manager -> manager.reload() }
        this.javaManagers.values.forEach { manager -> manager.reload() }
    }

    override fun onEnable() {
        this.enablePlugin()
    }

    override fun onDisable() {
        this.disablePlugin()
    }

    fun <M : Manager> getManager(managerClass: KClass<M>): M {
        synchronized(this.kotlinManagers) {
            @Suppress("UNCHECKED_CAST")
            if (this.kotlinManagers.containsKey(managerClass))
                return this.kotlinManagers[managerClass] as M

            return try {
                val manager = managerClass.constructors.first().call(this)
                manager.reload()
                kotlinManagers[managerClass] = manager
                manager
            } catch (ex: ReflectiveOperationException) {
                error("Failed to load manager for ${managerClass.simpleName}")
            }
        }
    }

    /**
     * Gets a manager instance
     *
     * @param managerClass The class of the manager to get
     * @param <T> extends Manager
     * @return A new or existing instance of the given manager class
     */
    fun <T : Manager> getManager(managerClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (javaManagers.containsKey(managerClass))
            return javaManagers[managerClass] as T

        return try {
            val manager = managerClass.getConstructor(ArsenticCommand::class.java).newInstance(this)
            javaManagers[managerClass] = manager
            manager.reload()
            manager
        } catch (ex: ReflectiveOperationException) {
            error("Failed to load manager for ${managerClass.simpleName}")
        }
    }
}