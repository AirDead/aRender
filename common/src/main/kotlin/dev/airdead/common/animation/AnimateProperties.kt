package dev.airdead.common.animation

import dev.airdead.common.Color
import dev.airdead.common.math.Rotation
import dev.airdead.common.math.V2
import dev.airdead.common.math.V3
import org.jetbrains.annotations.Range

/**
 * Container for animation properties.
 * This class contains all annotated with [Animatable] fields for specific class.
 *
 * @see Animatable
 */
@Suppress("SpellCheckingInspection")
data class AnimateProperties(
    val v2: List<V2>,
    val v3: List<V3>,
    val color: List<Color>,
    val rotation: List<Rotation>
) {

    /**
     * Interpolates between this [AnimateProperties] and another [AnimateProperties].
     *
     * @param other The other [AnimateProperties] to interpolate with.
     * @param progress The interpolation progress (0.0 to 1.0).
     * @return A new [AnimateProperties] instance with interpolated values.
     */
    fun interpolate(other: AnimateProperties, progress: @Range(from = 0, to = 1) Double): AnimateProperties =
        AnimateProperties(
            v2.mapIndexed { index, element -> element.interpolate(other.v2[index], progress) },
            v3.mapIndexed { index, element -> element.interpolate(other.v3[index], progress) },
            color.mapIndexed { index, element -> element.interpolate(other.color[index], progress) },
            rotation.mapIndexed { index, element -> element.interpolate(other.rotation[index], progress) }
        )
}