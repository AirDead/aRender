package dev.airdead.core.utility.screen

import dev.airdead.core.utility.client.CraftResolution
import kotlin.math.min

@Suppress("unused")
enum class GuiScale {
    AUTO,
    SMALL,
    MEDIUM,
    LARGE,
    VERY_LARGE;

    companion object {

        @JvmStatic
        fun fromNumber(number: Int): GuiScale = entries[number]

        @JvmOverloads
        @JvmStatic
        fun scaleForScreenSize(step: Int = 650): GuiScale {
            val width = CraftResolution.viewportWidth
            val height = CraftResolution.viewportHeight
            return fromNumber(min((width / step).coerceIn(1, 4), (height / (step / 16 * 9)).coerceIn(1, 4)))
        }
    }
}
