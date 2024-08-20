package dev.airdead.common.element

import dev.airdead.common.animation.AnimationChain
import dev.airdead.common.animation.Easing
import dev.airdead.common.animation.Easings
import dev.airdead.common.math.Matrix
import dev.airdead.common.math.V3

/**
 * Represents element for render
 */
interface RendererElement : ParentElement {

    /**
     * Indicates whether the element is enabled.
     * When set to false, the element is not render
     */
    var enabled: Boolean

    /**
     * The render location of the element.
     */
    var location: V3

    /**
     * Creates an animation for the element.
     *
     * @param duration The duration of the animation.
     * @param easing The easing function for the animation.
     * @param updateProperties A lambda to update the properties of the element.
     * @return The animation chain.
     */
    fun animate(duration: Double, easing: Easing = Easings.NONE, updateProperties: RendererElement.() -> Unit): AnimationChain

    /**
     * Render the element
     *
     * @param matrix The matrix
     */
    fun render(matrix: Matrix)
}