package dev.airdead.common

import dev.airdead.common.element.RendererElement
import dev.airdead.common.math.V2

abstract class Render {

    /**
     * All element for render
     */
    abstract val elements: MutableList<RendererElement>

    /**
     * Init render system
     */
    abstract fun init()

    /**
     * Current mouse position
     */
    abstract val mouse: V2

    /**
     * Checks if the mouse button is clicked.
     *
     * @param button The mouse button to check.
     *
     * @return True if the mouse button is clicked, false otherwise.
     */
    abstract fun isMouseButtonClicked(button: Int): Boolean

    /**
     * Add element to rendering
     *
     * @param element Element for render
     */
    abstract fun addElement(element: RendererElement)

    /**
     * Remove element from rendering
     *
     * @param element Element for removing
     */
    abstract fun removeElement(element: RendererElement)
}