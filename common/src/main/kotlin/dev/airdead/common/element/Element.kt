package dev.airdead.common.element

import dev.airdead.common.math.Matrix
import dev.airdead.common.math.V3

/**
 * Represents a basic element for rendering
 */
interface Element {

    /**
     * Element size
     */
    var size: V3

    /**
     * Render the element
     *
     * @param matrix The matrix
     */
    fun render(matrix: Matrix)
}