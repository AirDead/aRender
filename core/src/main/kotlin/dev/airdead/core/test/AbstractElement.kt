package dev.airdead.core.test

import dev.airdead.common.animation.AnimationChain
import dev.airdead.common.animation.AnimationExecutable
import dev.airdead.common.animation.Easing
import dev.airdead.common.element.Element
import dev.airdead.common.element.InteractiveElement
import dev.airdead.common.element.RendererElement
import dev.airdead.common.element.WorldElement
import dev.airdead.common.handler.ClickHandler
import dev.airdead.common.handler.HoverHandler
import dev.airdead.common.misc.Rotation
import dev.airdead.common.misc.location.V3
import dev.airdead.common.misc.rotation
import dev.airdead.common.misc.location.x
import dev.airdead.core.animation.CraftAnimation
import dev.airdead.core.animation.CraftAnimationChain
import dev.airdead.core.animation.CraftAnimationExecutable
import org.joml.Matrix4f
import org.joml.Vector4f

/**
 * Abstract element with basic logic. You can use it for render in hud or world.
 */
abstract class AbstractElement : InteractiveElement, RendererElement, WorldElement {

    open val baseMatrix = Matrix4f()

    override val children: MutableList<Element> = mutableListOf()

    override var enabled: Boolean = true
    override var interactive: Boolean = true

    override var hovered: Boolean = false

    override var size: V3 = 0 x 0
    override var align: V3 = 0 x 0
    override var origin: V3 = 0 x 0
    override var offset: V3 = 0 x 0
    override var rotation: Rotation = 0.rotation()
    override var location: V3 = 0 x 0

    private var onHover = mutableListOf<HoverHandler>()
    private var onLeftClick = mutableListOf<ClickHandler>()
    private var onRightClick = mutableListOf<ClickHandler>()

    private val animations = mutableListOf<AnimationChain>()

    override fun onHover(handler: HoverHandler) {
        this.onHover.add(handler)
    }

    override fun onLeftClick(handler: ClickHandler) {
        this.onLeftClick.add(handler)
    }

    override fun onRightClick(handler: ClickHandler) {
        this.onRightClick.add(handler)
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

    override fun isHovered(mouseX: Double, mouseY: Double): Boolean {
        TODO()
    }

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

    override fun updateHoverState(mouseMatrix: Matrix4f, mouseVector: Vector4f) {

    }
}