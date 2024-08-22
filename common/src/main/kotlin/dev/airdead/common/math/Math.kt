package dev.airdead.common.math

import kotlin.math.sqrt

@Suppress("MemberVisibilityCanBePrivate", "unused")
open class V2(
    open var x: Number = 0.0,
    open var y: Number = 0.0
) {

    open val doubleX
        get() = x as Double
    open val doubleY
        get() = y as Double

    operator fun plus(other: V2) = V2(doubleX + other.doubleX, doubleY + other.doubleY)
    operator fun minus(other: V2) = V2(doubleX - other.doubleX, doubleY - other.doubleY)
    open operator fun times(scalar: Number) = V2(doubleX * scalar as Double, doubleY * scalar)
    open operator fun div(scalar: Number) = V2(doubleX / scalar as Double, doubleY / scalar)

    open fun length() = sqrt(doubleX * doubleX + doubleY * doubleY)
    open fun normalize() = this / length()

    /**
     * Interpolates between this vector and the given vector by the specified progress.
     *
     * @param other The vector to interpolate towards.
     * @param progress The interpolation progress, where 0.0 represents this vector and 1.0 represents the other vector.
     * @return A new vector that is the result of the interpolation.
     */
    fun interpolate(other: V2, progress: Double) = V2(
        doubleX + (other.doubleX - doubleX) * progress,
        doubleY + (other.doubleY - doubleY) * progress
    )
}

@Suppress("MemberVisibilityCanBePrivate", "unused")
open class V3(
    override var x: Number = 0.0,
    override var y: Number = 0.0,
    var z: Number = 0.0
) : V2(x, y) {

    val doubleZ
        get() = z as Double

    operator fun plus(other: V3) = V3(doubleX + other.doubleX, doubleY + other.doubleY, doubleZ + other.doubleZ)
    operator fun minus(other: V3) = V3(doubleX - other.doubleX, doubleY - other.doubleY, doubleZ - other.doubleZ)
    override operator fun times(scalar: Number) = V3(doubleX * scalar as Double, doubleY * scalar, doubleZ * scalar)
    override operator fun div(scalar: Number) = V3(doubleX / scalar as Double, doubleY / scalar, doubleZ / scalar)

    override fun length() = sqrt(doubleX * doubleX + doubleY * doubleY + doubleZ * doubleZ)
    override fun normalize() = this / length()

    /**
     * Interpolates between this vector and the given vector by the specified progress.
     *
     * @param other The vector to interpolate towards.
     * @param progress The interpolation progress, where 0.0 represents this vector and 1.0 represents the other vector.
     * @return A new vector that is the result of the interpolation.
     */
    fun interpolate(other: V3, progress: Double) = V3(
        doubleX + (other.doubleX - doubleX) * progress,
        doubleY + (other.doubleY - doubleY) * progress,
        doubleZ + (other.doubleZ - doubleZ) * progress
    )
}

/**
 * Represents a rotation in degrees.
 *
 * @property degrees The rotation in degrees.
 */
@Suppress("MemberVisibilityCanBePrivate")
open class Rotation(
    var degrees: Float
) {
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
    fun interpolate(other: Rotation, progress: Double) = Rotation(
        degrees + (other.degrees - degrees) * progress.toFloat()
    )
}

/**
 * Creates a 3-dimensional vector from two numbers, with the z-coordinate set to 0.0.
 *
 * @receiver The x-coordinate of the vector.
 * @param other The y-coordinate of the vector.
 * @return A new 3-dimensional vector.
 */
@Suppress("NOTHING_TO_INLINE")
inline infix fun Number.x(other: Number): V3 = V3(this, other, 0.0)