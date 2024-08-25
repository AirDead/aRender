package dev.airdead.common.misc

import dev.airdead.common.SimpleDsl3

@SimpleDsl3
fun Number.rotation(): Rotation = Rotation(this)

/**
 * Represents a rotation in degrees.
 *
 * @property degrees The rotation in degrees.
 */
@Suppress("MemberVisibilityCanBePrivate")
open class Rotation(
    degrees: Number
) : Interpolatable {

    var degrees: Float = degrees.toFloat()

    /**
     * Creates a copy of this rotation.
     *
     * @return A new rotation that is a copy of this rotation.
     */
    fun copy() = Rotation(degrees)

    /**
     * Interpolates between this rotation and the given rotation by the specified progress.
     *
     * @param other The rotation to interpolate towards.
     * @param progress The interpolation progress, where 0.0 represents this rotation and 1.0 represents the other rotation.
     * @return A new rotation that is the result of the interpolation.
     */
    override fun interpolate(other: Interpolatable, progress: Double) {
        when(other) {
            is Rotation -> {
                this.degrees = degrees + (other.degrees - degrees) * progress.toFloat()
            }

            else -> throw NullPointerException("Incorrect parameter. Interpolate with other Rotation instance!")
        }
    }
}
