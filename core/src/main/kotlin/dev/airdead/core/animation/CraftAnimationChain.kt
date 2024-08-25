package dev.airdead.core.animation

import dev.airdead.common.animation.*
import dev.airdead.common.element.RendererElement

@Suppress("MemberVisibilityCanBePrivate")
class CraftAnimationChain(
    override val element: RendererElement,
    override val initialAnimation: Animation
) : AnimationChain {

    val animations = mutableListOf<Animation>().apply { add(initialAnimation) }

    override var loop = false

    override var repeatCount = 0

    override var currentRepeat = 0

    override var cancelled: Boolean = false

    private var isCancelled = false

    override fun then(duration: Double, easing: Easing, updateProperties: AnimationExecutable.() -> Unit): AnimationChain {
        val executable = CraftAnimationExecutable()

        executable.updateProperties()

        val animation = CraftAnimation(executable.properties, duration * 20, easing::ease)

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