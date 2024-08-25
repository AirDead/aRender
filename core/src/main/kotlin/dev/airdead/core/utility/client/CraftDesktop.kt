@file:Suppress("unused")

package dev.airdead.core.utility.client

import dev.airdead.common.utility.client.Desktop
import dev.airdead.core.utility.ClientAPI
import java.io.File
import java.io.IOException
import java.net.URI
import java.util.concurrent.TimeUnit

object CraftDesktop : Desktop {

    private var linux: Boolean = false
    private var xdg: Boolean = false
    private var kde: Boolean = false
    private var gnome: Boolean = false
    private var mac: Boolean = false
    private var windows: Boolean = false

    override val isLinux: Boolean
        get() = linux
    override val isXdg: Boolean
        get() = xdg
    override val isKde: Boolean
        get() = kde
    override val isGnome: Boolean
        get() = gnome
    override val isMac: Boolean
        get() = mac
    override val isWindows: Boolean
        get() = windows

    override var clipboard: String
        get() = ClientAPI.minecraft.keyboard.clipboard
        set(value) {
            ClientAPI.minecraft.keyboard.clipboard = value
        }

    init {
        val osName = try {
            System.getProperty("os.name")
        } catch (e: SecurityException) {
            null
        }
        linux = osName != null && (osName.startsWith("Linux") || osName.startsWith("LINUX"))
        mac = osName != null && osName.startsWith("Mac")
        windows = osName != null && osName.startsWith("Windows")
        if (isLinux) {
            System.getenv("XDG_SESSION_ID")?.let {
                xdg = it.isNotEmpty()
            }
            System.getenv("GDMSESSION")?.lowercase()?.let {
                gnome = "gnome" in it
                kde = "kde" in it
            }
        } else {
            xdg = false
            kde = false
            gnome = false
        }
    }

    override fun browse(uri: URI): Boolean = browseDesktop(uri) || openSystemSpecific(uri.toString())
    override fun open(file: File): Boolean = openDesktop(file) || openSystemSpecific(file.path)
    override fun edit(file: File): Boolean = editDesktop(file) || openSystemSpecific(file.path)

    private fun openSystemSpecific(file: String): Boolean {
        return when {
            isLinux -> listOf("xdg-open", "kde-open", "gnome-open").any { runCommand(it, file, checkExitStatus = true) }
            isMac -> runCommand("open", file)
            isWindows -> runCommand("rundll32", "url.dll,FileProtocolHandler", file)
            else -> false
        }
    }

    private fun browseDesktop(uri: URI): Boolean {
        return if (!java.awt.Desktop.isDesktopSupported()) false else try {
            if (!java.awt.Desktop.getDesktop().isSupported(java.awt.Desktop.Action.BROWSE)) {
                return false
            }

            java.awt.Desktop.getDesktop().browse(uri)
            true
        } catch (e: Throwable) {
            false
        }
    }

    private fun openDesktop(file: File): Boolean {
        return if (!java.awt.Desktop.isDesktopSupported()) false else try {
            if (!java.awt.Desktop.getDesktop().isSupported(java.awt.Desktop.Action.OPEN))
                return false
            java.awt.Desktop.getDesktop().open(file)
            true
        } catch (e: Throwable) {
            false
        }
    }

    private fun editDesktop(file: File): Boolean {
        return if (!java.awt.Desktop.isDesktopSupported()) false else try {
            if (!java.awt.Desktop.getDesktop().isSupported(java.awt.Desktop.Action.EDIT))
                return false
            java.awt.Desktop.getDesktop().edit(file)
            true
        } catch (e: Throwable) {
            false
        }
    }

    /**
     * Runs the given command with arguments via [Runtime.exec].
     *
     * If [checkExitStatus] is true, the method will wait for the process to exit (but at most a few seconds) and then
     * return `false` if the process exit code is non-zero (`true` if the process did not exit in time).
     */
    private fun runCommand(vararg command: String, checkExitStatus: Boolean = false): Boolean {
        return try {
            val process = Runtime.getRuntime().exec(command) ?: return false
            if (checkExitStatus) {
                if (process.waitFor(3, TimeUnit.SECONDS)) {
                    process.exitValue() == 0
                } else {
                    true // still running, assume success
                }
            } else {
                process.isAlive
            }
        } catch (e: IOException) {
            false
        }
    }
}