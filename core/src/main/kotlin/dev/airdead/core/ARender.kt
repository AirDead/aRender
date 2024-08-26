@file:Suppress("MemberVisibilityCanBePrivate", "UNUSED_PARAMETER")

package dev.airdead.core

import dev.airdead.common.Render
import dev.airdead.common.element.RendererElement
import dev.airdead.common.Matrix
import dev.airdead.common.element.InteractiveElement
import dev.airdead.common.misc.location.V2
import dev.airdead.common.utility.input.ClickContext
import dev.airdead.common.utility.input.MouseButton
import dev.airdead.core.utility.ClientAPI
import dev.airdead.core.utility.input.CraftMouse
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.minecraft.client.MinecraftClient
import org.lwjgl.glfw.GLFW

object ARender : Render() {

    var initialized = false
        private set(value) {
            if (field == value) return

            field = value

            if (value) registerCallbacks()
        }

    override val elements: MutableList<RendererElement> = mutableListOf()

    override fun init() {
        ClientTickEvents.START_CLIENT_TICK.register(::onClientTick)
    }

    override fun render(matrix: Matrix, tickDelta: Float) {
        elements.forEach { it.render(matrix, tickDelta) }
    }

    /**
     * Handles the client tick event. Initializes the HUD engine if it hasn't been initialized yet.
     * @param client The Minecraft client instance.
     */
    private fun onClientTick(client: MinecraftClient) {
        initialized = true
        handleClientTick()
    }

    /**
     * Registers the HUD render callback.
     * The callback is responsible for rendering the HUD.
     */
    private fun registerCallbacks() {
        HudRenderCallback.EVENT.register { drawContext, tickDelta ->
            render(CraftMatrix.fromStack(drawContext.matrices), tickDelta)
        }
    }

    /**
     * Handles the client tick event. Updates the mouse position and triggers the mouse move event.
     */
    private fun handleClientTick() {
        val (mouseX, mouseY) = mouse.run { x to y }

        elements.forEach {
            if (it is InteractiveElement) {
                it.handleMouseHover(mouseX.toDouble(), mouseY.toDouble())

                it.handleMouseClick(
                    ClickContext(
                        MouseButton.LEFT,
                        CraftMouse.isButtonDown(MouseButton.LEFT),
                        mouseX.toDouble(),
                        mouseY.toDouble()
                    )
                )

                it.handleMouseClick(
                    ClickContext(
                        MouseButton.RIGHT,
                        CraftMouse.isButtonDown(MouseButton.RIGHT),
                        mouseX.toDouble(),
                        mouseY.toDouble()
                    )
                )
            }
        }
    }

    override val mouse: V2
        get() = V2(CraftMouse.scaledX, CraftMouse.scaledY)

    override fun isMouseButtonClicked(button: Int): Boolean = GLFW.glfwGetMouseButton(ClientAPI.minecraft.window.handle, button) == GLFW.GLFW_PRESS

    override fun addElement(element: RendererElement) {
        elements.add(element)
    }

    override fun removeElement(element: RendererElement) {
        elements.remove(element)
    }

}