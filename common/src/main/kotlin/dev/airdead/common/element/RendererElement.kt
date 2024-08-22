package dev.airdead.common.element

import dev.airdead.common.math.Matrix
import dev.airdead.common.math.V3

/**
 * Represents element for render.
 *
 * For render in world use [WorldElement]
 */
interface RendererElement : ParentElement {

    /**
     * Indicates whether the element is enabled.
     * When set to false, the element is not render.
     */
    var enabled: Boolean

    /**
     * The render location of the element.
     */
    var location: V3

    /**
     * Execute some action before render.
     *
     * @param action Action.
     */
    fun beforeRender(action: () -> Unit)

    /**
     * Execute some action after render.
     *
     * @param action Action.
     */
    fun afterRender(action: () -> Unit)

    /**
     * Execute some action before transform.
     *
     * @param action Action.
     */
    fun beforeTransform(action: () -> Unit)

    /**
     * Execute some action after transform.
     *
     * @param action Action.
     */
    fun afterTransform(action: () -> Unit)

    /**
     * Render the element.
     *
     * @param matrix The matrix.
     */
    fun render(matrix: Matrix)
}