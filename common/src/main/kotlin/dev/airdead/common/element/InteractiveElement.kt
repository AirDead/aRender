package dev.airdead.common.element

import dev.airdead.common.handler.ClickHandler
import dev.airdead.common.handler.HoverHandler

/**
 * Represents interactive element.
 */
interface InteractiveElement : ParentElement {

    /**
     * Indicates whether the element is interactive.
     * When set to false, the element is not interactive.
     */
    var interactive: Boolean

    /**
     * Sets the hover handler for the element.
     *
     * @param handler The hover handler.
     */
    fun onHover(handler: HoverHandler)

    /**
     * Sets the left-click handler for the element.
     *
     * @param handler The left-click handler.
     */
    fun onLeftClick(handler: ClickHandler)

    /**
     * Sets the right-click handler for the element.
     *
     * @param handler The right-click handler.
     */
    fun onRightClick(handler: ClickHandler)

    /**
     * Checks if the element is hovered over.
     *
     * @param mouseX The X coordinate of the mouse.
     * @param mouseY The Y coordinate of the mouse.
     * @return True if the element is hovered over, false otherwise.
     */
    fun isHovered(mouseX: Double, mouseY: Double)
}