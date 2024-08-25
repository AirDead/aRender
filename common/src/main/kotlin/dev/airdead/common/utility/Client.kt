package dev.airdead.common.utility

import dev.airdead.common.utility.player.Player

interface Client {

    val player: Player

    var guiScale: Int
    val isRunningOnMac: Boolean
    val time: Long

    fun isCallingFromMinecraftThread(): Boolean
}