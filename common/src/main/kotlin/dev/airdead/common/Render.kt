package dev.airdead.common

import dev.airdead.common.animation.AnimationManager
import dev.airdead.common.element.RendererElement
import dev.airdead.common.math.Matrix
import dev.airdead.common.math.V2

@Suppress("LeakingThis")
abstract class Render {

    companion object {
        lateinit var instance: Render
            private set
    }

    init {
        instance = this
    }

    abstract val animationManager: AnimationManager

    /**
     * All element for render.
     */
    abstract val elements: MutableList<RendererElement>

    /**
     * Init render system.
     */
    abstract fun init()

    /**
     * Render all elements.
     *
     * @param matrix Matrix for render.
     */
    abstract fun render(matrix: Matrix)

    /**
     * Current mouse position.
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
     * Add element to rendering.
     *
     * @param element Element for render.
     */
    abstract fun addElement(element: RendererElement)

    /**
     * Remove element from rendering.
     *
     * @param element Element for removing.
     */
    abstract fun removeElement(element: RendererElement)
}