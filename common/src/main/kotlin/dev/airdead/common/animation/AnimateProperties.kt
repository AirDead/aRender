package dev.airdead.common.animation

import dev.airdead.common.misc.Interpolatable
import org.jetbrains.annotations.Range

/**
 * Container for animation properties.
 */
data class AnimateProperties(
    val interpolatable: MutableMap<Interpolatable, Interpolatable>
) {

    /**
     * Interpolates between key and value in maps of values [AnimateProperties].
     *
     * @param progress The interpolation progress (0.0 to 1.0).
     */
    fun interpolate(progress: @Range(from = 0, to = 1) Double) {
        interpolatable.forEach {
            it.key.interpolate(it.value, progress)
        }
    }
}