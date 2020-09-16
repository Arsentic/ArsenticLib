package net.arsentic.library;

import org.apache.commons.lang.StringUtils;
import org.bukkit.inventory.ItemStack;

import java.text.SimpleDateFormat;
import java.util.Date;

// Damn i hate static but like, gotta do it
public final class PluginUtils {

    public static String formatTime(Long time) {
        return new SimpleDateFormat("HH:mm dd/m/yyy").format(new Date(time));
    }

    public static String transformName(ItemStack itemStack) {
        return StringUtils.capitalize(itemStack.getType().name().replace("_", " ").toLowerCase());
    }

    public static boolean isNumber(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return false;
        }

        return true;
    }
}
