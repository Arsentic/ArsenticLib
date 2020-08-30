/**
 * @author Oribuin
 */
class ExampleManager(plugin: Main) : Manager(plugin) {
    override fun reload() {
        plugin.reloadConfig()
    }

    override fun disable() {
        plugin.saveConfig()
    }
}