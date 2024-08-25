package dev.airdead.common.utility.client

import java.io.File
import java.net.URI

interface Desktop {
    val isLinux: Boolean
    val isXdg: Boolean
    val isKde: Boolean
    val isGnome: Boolean
    val isMac: Boolean
    val isWindows: Boolean

    var clipboard: String

    fun browse(uri: URI): Boolean
    fun open(file: File): Boolean
    fun edit(file: File): Boolean
}