@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package dev.airdead.core.utility.player

import dev.airdead.common.utility.player.Player
import dev.airdead.core.utility.ClientAPI
import dev.airdead.core.utility.text.TextComponent
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.client.sound.PositionedSoundInstance
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.sound.SoundEvent
import java.util.*

object CraftPlayer : Player {

    val player: ClientPlayerEntity?
        get() = ClientAPI.minecraftPlayer

    fun hasPlayer() = player != null

    fun sendChatMessage(message: TextComponent) {
        ClientAPI.networkHandler?.onGameMessage(
            GameMessageS2CPacket(message, false)
        )
    }

    fun sendActionBarMessage(message: TextComponent) {
        ClientAPI.networkHandler?.onGameMessage(
            GameMessageS2CPacket(message, true)
        )
    }

    fun sendClientSideMessage(message: TextComponent) {
        player?.sendMessage(message)
    }

    fun playSoundStatic(event: SoundEvent, volume: Float, pitch: Float) {
        // sound handler can be null whenever switching devices or openal fails to
        // initialize the sound handler correctly, which is very common, so protect against that
        val soundHandler = ClientAPI.minecraft.soundManager ?: return

        PositionedSoundInstance.master(event, pitch, volume)?.let { soundHandler.play(it) }
    }

    fun playSoundStatic(registryEntry: RegistryEntry<SoundEvent>, volume: Float, pitch: Float) {
        playSoundStatic(registryEntry.value(), volume, pitch)
    }

    override val uuid: UUID
        get() = ClientAPI.minecraft.session.profile.id

    override val x: Double
        get() = player?.x
            ?: throw NullPointerException("CraftPlayer.getX() called with no existing Player")

    override val y: Double
        get() = player?.y
            ?: throw NullPointerException("CraftPlayer.getY() called with no existing Player")

    override val z: Double
        get() = player?.z
            ?: throw NullPointerException("CraftPlayer.getZ() called with no existing Player")

    override val prevX: Double
        get() = player?.prevX
            ?: throw NullPointerException("CraftPlayer.getPrevX() called with no existing Player")

    override val prevY: Double
        get() = player?.prevY
            ?: throw NullPointerException("CraftPlayer.getPrevY() called with no existing Player")

    override val prevZ: Double
        get() = player?.prevZ
            ?: throw NullPointerException("CraftPlayer.getPrevZ() called with no existing Player")

}