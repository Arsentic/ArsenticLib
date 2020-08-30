package net.arsentic.library

/**
 * @author Oribuin, Esophose
 */
abstract class Manager(protected val plugin: ArsenticLib) {
    abstract fun reload()

    abstract fun disable()
}
