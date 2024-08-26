package dev.airdead.core.element

import dev.airdead.common.*
import dev.airdead.common.animation.AnimationChain
import dev.airdead.common.animation.AnimationExecutable
import dev.airdead.common.animation.Easing
import dev.airdead.common.element.Element
import dev.airdead.common.element.InteractiveElement
import dev.airdead.common.element.RendererElement
import dev.airdead.common.element.WorldElement
import dev.airdead.common.misc.Rotation
import dev.airdead.common.misc.location.CENTER
import dev.airdead.common.misc.location.LEFT
import dev.airdead.common.misc.location.V3
import dev.airdead.common.misc.location.x
import dev.airdead.common.misc.rotation
import dev.airdead.common.utility.input.*
import dev.airdead.core.animation.CraftAnimation
import dev.airdead.core.animation.CraftAnimationChain
import dev.airdead.core.animation.CraftAnimationExecutable
import dev.airdead.core.utility.client.CraftResolution

/**
 * Abstract element with basic logic. You can use it for render in hud or world.
 */
@Suppress("MemberVisibilityCanBePrivate")
abstract class AbstractElement : InteractiveElement, RendererElement, WorldElement {

    override var lastParent: Element? = null
    override val children: MutableList<Element> = mutableListOf()

    override var enabled: Boolean = true
    override var interactive: Boolean = true

    override var hovered: Boolean = false

    override var size: V3 = 0 x 0
    override var align: V3 = CENTER
    override var origin: V3 = CENTER
    override var offset: V3 = 0 x 0
    override var rotation: Rotation = 0.rotation()
    override var location: V3 = 0 x 0

    private val onHoverEnter = mutableListOf<HoverHandler>()
    private val onHoverLeave = mutableListOf<HoverHandler>()

    private val onMouseClick = mutableMapOf<MouseButton, ClickHandler>()
    private val onMouseRelease = mutableMapOf<MouseButton, ClickHandler>()

    private val beforeRender = mutableListOf<() -> Unit>()
    private val afterRender = mutableListOf<() -> Unit>()

    private val beforeTransform = mutableListOf<() -> Unit>()
    private val afterTransform = mutableListOf<() -> Unit>()

    private val animations = mutableListOf<AnimationChain>()

    @SimpleDsl1
    override fun onMouseEnter(handler: HoverHandler) {
        this.onHoverEnter.add(handler)
    }

    @SimpleDsl1
    override fun onMouseLeave(handler: HoverHandler) {
        this.onHoverLeave.add(handler)
    }

    @SimpleDsl1
    override fun onMouseClick(button: MouseButton, handler: ClickHandler) {
        this.onMouseClick[button] = handler
    }

    @SimpleDsl1
    override fun onMouseRelease(button: MouseButton, handler: ClickHandler) {
        this.onMouseRelease[button] = handler
    }

    override fun afterRender(action: () -> Unit) {
        afterRender.add(action)
    }

    override fun beforeRender(action: () -> Unit) {
        beforeRender.add(action)
    }

    override fun afterTransform(action: () -> Unit) {
        afterTransform.add(action)
    }

    override fun beforeTransform(action: () -> Unit) {
        beforeTransform.add(action)
    }

    override fun isHovered(mouseX: Double, mouseY: Double): Boolean =
        mouseX in location.doubleX..(location.doubleX + size.doubleX) &&
            mouseY in location.doubleY..(location.doubleY + size.doubleY)

    /**
     * Transforms and renders the element.
     *
     * @param matrix The matrix.
     * @param tickDelta The time delta since the last update.
     */
    override fun render(matrix: Matrix, tickDelta: Float) {
        if (!enabled) return

        beforeTransform.forEach { it.invoke() }
        updateAnimations(tickDelta)
        updateRenderLocation()
        afterTransform.forEach { it.invoke() }

        matrix.push()
        matrix.translate(0.0, offset.doubleY, 0.0)

        beforeRender.forEach { it.invoke() }
        renderElement(matrix, tickDelta)
        afterRender.forEach { it.invoke() }
        matrix.pop()
    }

    @SimpleDsl1
    override fun animate(
        duration: Double,
        easing: Easing,
        updateProperties: AnimationExecutable.() -> Unit
    ): AnimationChain {
        val executable = CraftAnimationExecutable()
        executable.updateProperties()

        val animation = CraftAnimation(executable.properties, duration * 20, easing::ease)
        val chain = CraftAnimationChain(this, animation)

        animations.add(chain)

        return chain
    }

    /**
     * Updates the render location of the element.
     */
    private fun updateRenderLocation() {
        val defaultSize = V3(CraftResolution.scaledWidth, CraftResolution.scaledHeight, 1.0)
        val parentSize = lastParent?.size ?: defaultSize
        location = V3(
            calculateAbsolutePosition(parentSize.doubleX, size.doubleX, align.doubleX, origin.doubleX, (lastParent as RendererElement?)?.location?.doubleY, offset.doubleX),
            calculateAbsolutePosition(parentSize.doubleY, size.doubleY, align.doubleY, origin.doubleY, (lastParent as RendererElement?)?.location?.doubleY, offset.doubleY),
            location.z
        )
    }

    /**
     * Calculates the absolute position of the element.
     *
     * @param parentSize The size of the parent element.
     * @param size The size of the element.
     * @param align The alignment of the element.
     * @param origin The origin point of the element.
     * @param parentOffset The offset of the parent element.
     * @param offset The offset of the element.
     * @return The absolute position of the element.
     */
    open fun calculateAbsolutePosition(parentSize: Double, size: Double, align: Double, origin: Double, parentOffset: Double?, offset: Double) =
        parentSize * align - size * origin + (parentOffset ?: 0.0) + offset

    /**
     * Updates the animations of the element.
     *
     * @param tickDelta The time delta since the last update.
     */
    open fun updateAnimations(tickDelta: Float) {
        animations.removeIf { it.update(tickDelta) }
    }

    override fun handleMouseClick(context: ClickContext) {
        if (!interactive) return

        if (hovered) {
            if (context.isPressed) onMouseClick.forEach { if (context.button == it.key) it.value.invoke(context) }
        } else if (!context.isPressed) onMouseRelease.forEach { if (context.button == it.key) it.value.invoke(context) }
    }

    override fun handleMouseHover(mouseX: Double, mouseY: Double) {
        if (!interactive) return

        if (isHovered(mouseX, mouseY)) {
            hovered = true
            onHoverEnter.forEach { it.invoke(HoverContext(true, mouseX, mouseY)) }
        } else {
            if (hovered) {
                hovered = false
                onHoverLeave.forEach { it.invoke(HoverContext(false, mouseX, mouseY)) }
            }
        }
    }

    abstract fun renderElement(matrix: Matrix, tickDelta: Float)
}