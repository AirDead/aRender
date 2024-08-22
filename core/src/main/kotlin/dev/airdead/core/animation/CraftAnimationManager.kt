package dev.airdead.core.animation

import dev.airdead.common.animation.AnimateProperties
import dev.airdead.common.animation.AnimationChain
import dev.airdead.common.animation.AnimationManager
import dev.airdead.common.animation.Easing
import dev.airdead.common.element.RendererElement

object CraftAnimationManager : AnimationManager {
    override fun create(
        element: RendererElement,
        duration: Double,
        easing: Easing,
        updateProperties: RendererElement.() -> Unit
    ): AnimationChain {
        TODO("Not yet implemented")
    }

    override fun animationProperties(element: RendererElement): AnimateProperties {
        TODO("Not yet implemented")
    }
}