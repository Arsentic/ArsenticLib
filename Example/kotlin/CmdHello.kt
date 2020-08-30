/**
 * @author Oribuin
 */
class CmdHello(plugin: Main) : ArsenticCommand(plugin, "hello") {
    override fun executeCommand(sender: CommandSender, args: Array<String>) {
        sender.sendMessage(colorify("Hello " + sender.name + "!"))
    }

    override fun tabComplete(sender: CommandSender, args: Array<String>): List<String>? {
        return null
    }
}