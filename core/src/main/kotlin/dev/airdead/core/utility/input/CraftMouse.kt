package dev.airdead.core.utility.input

import dev.airdead.common.utility.input.Mouse
import dev.airdead.common.utility.input.RawMouse
import dev.airdead.common.utility.input.ScaledMouse
import dev.airdead.core.utility.ClientAPI
import dev.airdead.core.utility.client.CraftResolution
import kotlin.math.max

object CraftMouse : Mouse {
    override val raw: RawMouse = CraftRawMouse
    override val scaled: ScaledMouse = CraftScaledMouse

    override fun getTrueX(): Double = raw.x
    override fun getTrueY(): Double = raw.y

    override fun getScaledX(): Double = scaled.x
    override fun getScaledY(): Double = scaled.y
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