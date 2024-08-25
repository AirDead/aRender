package dev.airdead.core.utility.client

import dev.airdead.common.utility.client.Resolution
import dev.airdead.core.utility.ClientAPI

object CraftResolution : Resolution {

    override val windowWidth: Int
        get() = ClientAPI.minecraft.window.width
    override val windowHeight: Int
        get() = ClientAPI.minecraft.window.height

    override val viewportWidth: Int
        get() = ClientAPI.minecraft.window.framebufferWidth
    override val viewportHeight: Int
        get() = ClientAPI.minecraft.window.framebufferHeight

    override val scaledWidth: Int
        get() = ClientAPI.minecraft.window.scaledWidth
    override val scaledHeight: Int
        get() = ClientAPI.minecraft.window.scaledHeight

    override val scaleFactor: Double
        get() = ClientAPI.minecraft.window.scaleFactor

}