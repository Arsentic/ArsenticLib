/**
 * @author Oribuin
 */
class Main : ArsenticPlugin(), Listener {
    override fun enablePlugin() {
        // Plugin will automatically save default config, reload managers and send startup messages

        // Get a manager through the ArsenticPlugin#getManager function
        getManager(ExampleManager::class)

        // Register commands & listeners in a vararg, aka 'Class...'
        registerCommands(CmdHello(this))
        registerListeners(this, this)
    }

    override fun disablePlugin() {
        // Plugin will automatically disable managers and send disable messages
        println("Goodbye!")
    }

    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {
        // RGB The Chat
        event.message = colorify(event.message)
    }
}