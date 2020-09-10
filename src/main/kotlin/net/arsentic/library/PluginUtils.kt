package net.arsentic.library

import org.bukkit.inventory.ItemStack
import java.text.SimpleDateFormat
import java.util.*

object PluginUtils {

    @JvmStatic
    fun formatTime(long: Long): String {
        return SimpleDateFormat("HH:mm dd/m/yyyy").format(Date(long))
    }

    @JvmStatic
    fun transformName(itemStack: ItemStack): String {
        return itemStack.type.name.replace("_", " ").toLowerCase().capitalize()
    }


    @JvmStatic
    fun isNumber(string: String): Boolean {
        try {
            Integer.parseInt(string)
        } catch (ex: NumberFormatException) {
            return false
        }

        return true
    }
}
