package dev.airdead.common.animation

import dev.airdead.common.Render
import dev.airdead.common.element.RendererElement

/**
 * Creates an animation for the element.
 *
 * @param duration The duration of the animation.
 * @param easing The easing function for the animation.
 * @param updateProperties A lambda to update the properties of the element.
 *
 * @return The animation chain.
 */
fun RendererElement.animate(
    duration: Double = 0.0,
    easing: Easing = Easings.NONE,
    updateProperties: RendererElement.() -> Unit
) = Render.instance.animationManager.create(this, duration, easing, updateProperties)

interface AnimationManager {
    /**
     * Creates an animation for the element.
     *
     * @param element Element instance.
     * @param duration The duration of the animation.
     * @param easing The easing function for the animation.
     * @param updateProperties A lambda to update the properties of the element.
     *
     * @return The animation chain.
     */
    fun create(
        element: RendererElement,
        duration: Double = 0.0,
        easing: Easing = Easings.NONE,
        updateProperties: RendererElement.() -> Unit
    ): AnimationChain

    /**
     * Get animation properties for the element.
     *
     * @param element Element instance.
     *
     * @return Instance of [AnimateProperties].
     */
    fun animationProperties(element: RendererElement): AnimateProperties
}