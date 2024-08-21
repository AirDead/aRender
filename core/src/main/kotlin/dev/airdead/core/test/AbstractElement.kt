package dev.airdead.core.test

import dev.airdead.common.animation.Animatable
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

    @Animatable
    override var size: V3 = 0 x 0
    @Animatable
    override var align: V3 = 0 x 0
    @Animatable
    override var origin: V3 = 0 x 0
    @Animatable
    override var offset: V3 = 0 x 0
    @Animatable
    override var rotation: V3 = 0 x 0
    @Animatable
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

    override fun afterRender(action: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun beforeRender(action: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun afterTransform(action: () -> Unit) {
        TODO("Not yet implemented")
    }

    override fun beforeTransform(action: () -> Unit) {
        TODO("Not yet implemented")
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