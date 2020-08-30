/**
 * @author Oribuin
 */
public class Main extends ArsenticLib implements Listener {

    @Override
    public void enablePlugin() {
        // Plugin will automatically save default config, reload managers and send startup messages

        // Get a manager through the ArsenticPlugin#getManager function
        getManager(ExampleManager.class);

        // Register commands & listeners in a vararg, aka 'Class...'
        this.registerCommands(new CmdHello(this));
        this.registerListeners(this, this);
    }

    @Override
    public void disablePlugin() {
        // Plugin will automatically disable managers and send disable messages
        System.out.println("Goodbye!");
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        // RGB The Chat
        event.setMessage(HexUtils.colorify(event.getMessage()));
    }
}
