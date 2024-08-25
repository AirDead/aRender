package dev.airdead.common.animation

import dev.airdead.common.SimpleDsl1
import dev.airdead.common.misc.Interpolatable

interface AnimationExecutable {

    val properties: AnimateProperties

    @SimpleDsl1
    infix fun Interpolatable.to(new: Interpolatable)
}