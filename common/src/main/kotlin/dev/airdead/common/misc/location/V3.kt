package dev.airdead.common.misc.location

import dev.airdead.common.SimpleDsl3
import dev.airdead.common.misc.Interpolatable
import kotlin.math.sqrt

/**
 * Creates a 3-dimensional vector from two numbers, with the z-coordinate set to 0.0.
 *
 * @receiver The x-coordinate of the vector.
 * @param other The y-coordinate of the vector.
 * @return A new 3-dimensional vector.
 */
@Suppress("NOTHING_TO_INLINE")
@SimpleDsl3
inline infix fun Number.x(other: Number): V3 = V3(this, other, 0.0)

@Suppress("MemberVisibilityCanBePrivate", "unused")
data class V3(
    override var x: Number = 0.0,
    override var y: Number = 0.0,
    override var z: Number = 0.0
) : Location {

    override val doubleX
        get() = x.toDouble()
    override val doubleY
        get() = y.toDouble()
    override val doubleZ
        get() = z.toDouble()

    override operator fun plus(other: Location) = V3(doubleX + other.doubleX, doubleY + other.doubleY, doubleZ + other.doubleZ)
    override operator fun minus(other: Location) = V3(doubleX - other.doubleX, doubleY - other.doubleY, doubleZ - other.doubleZ)
    override operator fun times(scalar: Number) = V3(doubleX * scalar.toDouble(), doubleY * scalar.toDouble(), doubleZ * scalar.toDouble())
    override operator fun div(scalar: Number) = V3(doubleX / scalar.toDouble(), doubleY / scalar.toDouble(), doubleZ / scalar.toDouble())

    override fun length() = sqrt(doubleX * doubleX + doubleY * doubleY + doubleZ * doubleZ)
    override fun normalize() = this / length()

    override fun interpolate(other: Interpolatable, progress: Double) {
        when (other) {
            is V2 -> {
                this.x = doubleX + (other.doubleX - doubleX) * progress
                this.y = doubleY + (other.doubleY - doubleY) * progress
            }

            is V3 -> {
                this.x = doubleX + (other.doubleX - doubleX) * progress
                this.y = doubleY + (other.doubleY - doubleY) * progress
                this.z = doubleZ + (other.doubleZ - doubleZ) * progress
            }

            else -> throw NullPointerException("Incorrect parameter. Interpolate with other V2 or V3 instance!")
        }
    }
}