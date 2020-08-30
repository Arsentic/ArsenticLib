/**
 * @author Oribuin
 */
public class CmdHello extends ArsenticCommand {
    public CmdHello(Main plugin) {
        super(plugin, "hello");
    }

    @Override
    public void executeCommand(@NotNull CommandSender sender, @NotNull String[] args) {
        sender.sendMessage(HexUtils.colorify("Hello " + sender.getName() + "!"));
    }

    @Override
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String[] args) {
        return null;
    }
}
