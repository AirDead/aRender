package dev.airdead.common.animation

import dev.airdead.common.element.RendererElement

/**
 * A chain of animations to be applied sequentially to an element.
 */
interface AnimationChain {

    /**
     * The element to which the animations are applied.
     */
    val element: RendererElement

    /**
     * The initial animation to start the chain.
     */
    val initialAnimation: Animation<AnimateProperties>

    /**
     * Indicates whether the animation chain should loop.
     */
    var loop: Boolean

    /**
     * The number of times the animation chain should repeat.
     */
    var repeatCount: Boolean

    /**
     * The current repeat count of the animation chain.
     */
    var currentRepeat: Boolean

    /**
     * Indicates whether the animation chain has been cancelled.
     */
    var cancelled: Boolean

    /**
     * Adds a new animation to the chain with the specified duration and easing.
     *
     * @param duration The duration of the animation.
     * @param easing The easing function to be used for the animation.
     * @param updateProperties A lambda to update the properties of the element.
     * @return The updated AnimationChain.
     */
    fun then(duration: Double, easing: Easing = Easings.NONE, updateProperties: RendererElement.() -> Unit): AnimationChain

    /**
     * Adds a new animation to the chain with the specified duration and no easing.
     *
     * @param duration The duration of the animation.
     * @return The updated AnimationChain.
     */
    fun then(duration: Double = 0.0): AnimationChain

    /**
     * Sets the animation chain to loop.
     *
     * @return The updated AnimationChain.
     */
    fun loop(): AnimationChain

    /**
     * Sets the number of times the animation chain should repeat.
     *
     * @param count The number of times to repeat the animation chain.
     * @return The updated AnimationChain.
     */
    fun repeat(count: Int = 1): AnimationChain

    /**
     * Updates the current animation in the chain based on the tick delta.
     *
     * @param tickDelta The time delta since the last update.
     * @return True if all animations are complete or cancelled, false otherwise.
     */
    fun update(tickDelta: Float): Boolean
}