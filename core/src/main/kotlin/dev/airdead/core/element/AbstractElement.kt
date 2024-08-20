package dev.airdead.core.element

import dev.airdead.common.animation.AnimationChain
import dev.airdead.common.animation.Easing
import dev.airdead.common.element.Element
import dev.airdead.common.element.InteractiveElement
import dev.airdead.common.element.RendererElement
import dev.airdead.common.handler.ClickHandler
import dev.airdead.common.handler.HoverHandler
import dev.airdead.common.math.V3
import dev.airdead.common.math.x

abstract class AbstractElement : InteractiveElement, RendererElement {
    override val children: MutableList<Element> = mutableListOf()

    override var enabled: Boolean = true
    override var interactive: Boolean = true

    override var size: V3 = 0 x 0
    override var align: V3 = 0 x 0
    override var origin: V3 = 0 x 0
    override var offset: V3 = 0 x 0
    override var rotation: V3 = 0 x 0
    override var location: V3 = 0 x 0

    private var onHover: HoverHandler? = null
    private var onLeftClick: ClickHandler? = null
    private var onRightClick: ClickHandler? = null

    override fun onHover(handler: HoverHandler) {
        this.onHover = handler
    }

    override fun onLeftClick(handler: ClickHandler) {
        this.onLeftClick = handler
    }

    override fun onRightClick(handler: ClickHandler) {
        this.onRightClick = handler
    }

    override fun animate(
        duration: Double,
        easing: Easing,
        updateProperties: RendererElement.() -> Unit
    ): AnimationChain {
        TODO("Not yet implemented")
    }

    override fun isHovered(mouseX: Double, mouseY: Double) {
        // TODO Hover check
    }
}