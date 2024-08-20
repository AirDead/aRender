package dev.airdead.common.element

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
     * Render the element
     *
     * @param matrix The matrix
     */
    fun render(matrix: Matrix)
}