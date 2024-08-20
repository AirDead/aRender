package dev.airdead.common.animation

import dev.airdead.common.Color
import dev.airdead.common.math.Rotation
import dev.airdead.common.math.V2
import dev.airdead.common.math.V3

data class AnimateProperties(
    val v2: List<V2>,
    val v3: List<V3>,
    val color: List<Color>,
    val rotation: List<Rotation>
)