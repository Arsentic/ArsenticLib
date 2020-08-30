/**
 * @author Oribuin
 */
public class ExampleManager extends Manager {
    public ExampleManager(Main plugin) {
        super(plugin);
    }

    @Override
    public void reload() {
        getPlugin().reloadConfig();
    }

    @Override
    public void disable() {
        getPlugin().saveConfig();
    }
}
