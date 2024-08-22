package dev.airdead.core.animation

import dev.airdead.common.animation.*
import dev.airdead.common.element.RendererElement

@Suppress("MemberVisibilityCanBePrivate")
class CraftAnimationChain(
    override val element: RendererElement,
    override val initialAnimation: Animation<AnimateProperties>
) : AnimationChain {

    val animations = mutableListOf<Animation<AnimateProperties>>().apply { add(initialAnimation) }

    override var loop = false

    override var repeatCount = 0

    override var currentRepeat = 0

    override var cancelled: Boolean = false

    private var isCancelled = false

    override fun then(duration: Double, easing: Easing, updateProperties: RendererElement.() -> Unit): AnimationChain {
        val lastState = animations.last().endState
        updateProperties(element)
        val newEndState = CraftAnimationManager.animationProperties(element)
        val animation = CraftAnimation(lastState, newEndState, duration * 20, easing::ease, initialAnimation.applyProperties)
        animations.add(animation)
        return this
    }

    override fun then(duration: Double): AnimationChain {
        return then(duration, Easings.NONE) { }
    }

    override fun loop(): AnimationChain {
        loop = true
        return this
    }

    override fun repeat(count: Int): AnimationChain {
        repeatCount = count
        return this
    }

    fun cancel() {
        isCancelled = true
    }

    override fun update(tickDelta: Float): Boolean {
        if (isCancelled || animations.isEmpty()) return true

        val currentAnimation = animations.first()
        if (currentAnimation.update(tickDelta)) {
            animations.removeAt(0)
            if (!isCancelled && (loop || (repeatCount > 0 && currentRepeat < repeatCount))) {
                animations.add(currentAnimation.reset())
                if (repeatCount > 0) currentRepeat++
            }
        }
        return animations.isEmpty() || isCancelled
    }
}