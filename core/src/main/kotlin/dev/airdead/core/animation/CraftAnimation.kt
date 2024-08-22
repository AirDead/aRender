package dev.airdead.core.animation

import dev.airdead.common.animation.AnimateProperties
import dev.airdead.common.animation.Animation
import kotlin.math.min

@Suppress("UNCHECKED_CAST")
class CraftAnimation<T>(
    override val startState: T,
    override val endState: T,
    override val duration: Double,
    override val easing: (Double) -> Double,
    override val applyProperties: (T) -> Unit
) : Animation<T> {

    override var elapsedTime: Double = 0.0

    override fun update(tickDelta: Float): Boolean {
        elapsedTime += tickDelta.toDouble()
        val progress = min(elapsedTime / duration, 1.0)
        applyProperties(interpolateProperties(startState, endState, easing(progress)))
        return progress >= 1.0
    }

    override fun reset(): Animation<T> {
        elapsedTime = 0.0
        return this
    }

    /**
     * Interpolates between the start and end states based on the given progress.
     *
     * @param startState The initial state.
     * @param endState The final state.
     * @param progress The progress of the animation, ranging from 0.0 to 1.0.
     * @return The interpolated state.
     * @throws IllegalArgumentException if the property type is unknown.
     */
    private fun interpolateProperties(startState: T, endState: T, progress: Double): T {
        return when (startState) {
            is AnimateProperties -> startState.interpolate(endState as AnimateProperties, progress) as T
            else -> throw IllegalArgumentException("Unknown property type")
        }
    }
}