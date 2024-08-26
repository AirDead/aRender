package dev.airdead.common.element

import dev.airdead.common.utility.input.ClickContext
import dev.airdead.common.utility.input.ClickHandler
import dev.airdead.common.utility.input.HoverHandler
import dev.airdead.common.utility.input.MouseButton

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
     * Is element is hovered
     *
     * @see onHover
     */
    var hovered: Boolean

    /**
     * Sets the hover event for element.
     *
     * @param handler The hover handler.
     */
    fun onMouseEnter(handler: HoverHandler)

    /**
     * Sets the leave handler for element.
     *
     * @param handler The hover handler.
     */
    fun onMouseLeave(handler: HoverHandler)

    /**
     * Sets the click handler for the element.
     *
     * @param button Type of button. Only [MouseButton.LEFT] or [MouseButton.RIGHT]
     * @param handler The left-click handler.
     */
    fun onMouseClick(button: MouseButton, handler: ClickHandler)

    /**
     * Sets the click handler for the element.
     *
     * @param button Type of button. Only [MouseButton.LEFT] or [MouseButton.RIGHT]
     * @param handler The left-click handler.
     */
    fun onMouseRelease(button: MouseButton, handler: ClickHandler)

    /**
     * Checks if the element is hovered over.
     *
     * @param mouseX The X coordinate of the mouse.
     * @param mouseY The Y coordinate of the mouse.
     *
     * @return True if the element is hovered over, false otherwise.
     */
    fun isHovered(mouseX: Double, mouseY: Double): Boolean

    /**
     * Handles mouse click events.
     *
     * @param context The click context.
     */
    fun handleMouseClick(context: ClickContext)

    /**
     * Handles mouse hover events.
     *
     * @param mouseX The X coordinate of the mouse.
     * @param mouseY The Y coordinate of the mouse.
     */
    fun handleMouseHover(mouseX: Double, mouseY: Double)
}