package dev.airdead.core.animation

import dev.airdead.common.animation.AnimateProperties
import dev.airdead.common.animation.AnimationExecutable
import dev.airdead.common.misc.Interpolatable

class CraftAnimationExecutable : AnimationExecutable {

    private val interpolatable = mutableMapOf<Interpolatable, Interpolatable>()

    override val properties: AnimateProperties
        get() = AnimateProperties(interpolatable)

    override infix fun Interpolatable.to(new: Interpolatable) {
        interpolatable[this] = new
    }
}