package dev.airdead.core.animation

import dev.airdead.common.animation.AnimateProperties
import dev.airdead.common.animation.Animation
import kotlin.math.min

class CraftAnimation(
    override val properties: AnimateProperties,
    override val duration: Double,
    override val easing: (Double) -> Double
) : Animation {

    override var elapsedTime: Double = 0.0

    override fun update(tickDelta: Float): Boolean {
        elapsedTime += tickDelta.toDouble()

        val progress = min(elapsedTime / duration, 1.0)
        properties.interpolate(easing(progress))

        return progress >= 1.0
    }

    override fun reset(): Animation {
        elapsedTime = 0.0
        return this
    }
}