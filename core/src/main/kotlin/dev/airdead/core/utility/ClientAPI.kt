@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package dev.airdead.core.utility

import dev.airdead.common.utility.Client
import dev.airdead.core.utility.player.CraftPlayer
import net.minecraft.client.MinecraftClient
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.hud.ChatHud
import net.minecraft.client.gui.screen.Screen
import net.minecraft.client.network.ClientPlayNetworkHandler
import net.minecraft.client.network.ClientPlayerEntity
import net.minecraft.client.option.GameOptions
import net.minecraft.client.util.GlfwUtil
import net.minecraft.client.world.ClientWorld

object ClientAPI : Client {

    val minecraft: MinecraftClient = MinecraftClient.getInstance()

    private var guiScaleValue: Int
        get() = settings.guiScale.value
        set(value) { settings.guiScale.value = value }

    override var guiScale: Int
        get() = guiScaleValue
        set(value) {
            guiScaleValue = value

            val window = minecraft.window
            val scaleFactor = window.calculateScaleFactor(value, minecraft.forcesUnicodeFont())
            window.scaleFactor = scaleFactor.toDouble()
        }

    override val isRunningOnMac: Boolean = MinecraftClient.IS_SYSTEM_MAC

    override val time: Long
        get() = (GlfwUtil.getTime() * 1000).toLong()

    var currentScreen: Screen?
        get() = minecraft.currentScreen
        set(value) {
            minecraft.setScreen(value)
        }

    override val player = CraftPlayer

    val chatHud: ChatHud = minecraft.inGameHud.chatHud
    val settings: GameOptions = minecraft.options
    val world: ClientWorld? = minecraft.world
    val networkHandler: ClientPlayNetworkHandler? = minecraft.networkHandler
    val minecraftPlayer: ClientPlayerEntity? = minecraft.player
    val textRenderer: TextRenderer = minecraft.textRenderer

    override fun isCallingFromMinecraftThread(): Boolean = minecraft.isOnThread
}