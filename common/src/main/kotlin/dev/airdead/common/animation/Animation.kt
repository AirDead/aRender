package dev.airdead.common.animation

/**
 * A class representing an animation that interpolates between two states over a specified duration.
 */
interface Animation {

    /**
     * The initial state of the animation.
     */
    val properties: AnimateProperties

    /**
     * The duration of the animation in seconds.
     */
    val duration: Double

    /**
     * A function that defines the easing curve of the animation.
     */
    val easing: (Double) -> Double

    /**
     * The elapsed time since the start of the animation.
     */
    var elapsedTime: Double

    /**
     * Updates the animation state based on the time delta since the last update.
     *
     * @param tickDelta The time delta since the last update in seconds.
     * @return `true` if the animation has completed, `false` otherwise.
     */
    fun update(tickDelta: Float): Boolean

    /**
     * Resets the animation to its initial state.
     *
     * @return The current instance of the [Animation] class.
     */
    fun reset(): Animation
}