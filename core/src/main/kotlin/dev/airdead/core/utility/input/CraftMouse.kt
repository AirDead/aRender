package dev.airdead.core.utility.input

import dev.airdead.common.utility.input.Mouse
import dev.airdead.common.utility.input.MouseButton
import dev.airdead.common.utility.input.RawMouse
import dev.airdead.common.utility.input.ScaledMouse
import dev.airdead.core.utility.ClientAPI
import dev.airdead.core.utility.client.CraftResolution
import org.lwjgl.glfw.GLFW
import kotlin.math.max

object CraftMouse : Mouse {
    override val raw: RawMouse = CraftRawMouse
    override val scaled: ScaledMouse = CraftScaledMouse

    override val trueX: Double
        get() = raw.x
    override val trueY: Double
        get() = raw.y

    override val scaledX: Double
        get() = scaled.x
    override val scaledY: Double
        get() = scaled.y


    override fun isButtonDown(button: MouseButton): Boolean {
        return when(button) {
            MouseButton.LEFT -> GLFW.glfwGetMouseButton(ClientAPI.minecraft.window.handle, GLFW.GLFW_MOUSE_BUTTON_1) == GLFW.GLFW_PRESS
            MouseButton.RIGHT -> GLFW.glfwGetMouseButton(ClientAPI.minecraft.window.handle, GLFW.GLFW_MOUSE_BUTTON_2) == GLFW.GLFW_PRESS

            else -> false
        }
    }
}

object CraftRawMouse : RawMouse {

    override val x: Double
        get() = ClientAPI.minecraft.mouse.x

    override val y: Double
        get() = ClientAPI.minecraft.mouse.y

}

object CraftScaledMouse : ScaledMouse {

    override val x: Double
        get() = CraftRawMouse.x * CraftResolution.scaledWidth / max(1, CraftResolution.windowWidth)

    override val y: Double
        get() = CraftRawMouse.y * CraftResolution.scaledHeight / max(1, CraftResolution.windowHeight)

}