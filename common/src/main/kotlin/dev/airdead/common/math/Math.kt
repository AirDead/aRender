package dev.airdead.common.math

import kotlin.math.sqrt

@Suppress("MemberVisibilityCanBePrivate", "unused")
data class V2(
    var x: Number = 0.0,
    var y: Number = 0.0
) {

    val doubleX
        get() = x as Double
    val doubleY
        get() = y as Double

    operator fun plus(other: V2) = V2(doubleX + other.doubleX, doubleY + other.doubleY)
    operator fun minus(other: V2) = V2(doubleX - other.doubleX, doubleY - other.doubleY)
    operator fun times(scalar: Number) = V2(doubleX * scalar as Double, doubleY * scalar)
    operator fun div(scalar: Number) = V2(doubleX / scalar as Double, doubleY / scalar)

    fun length() = sqrt(doubleX * doubleX + doubleY * doubleY)
    fun normalize() = this / length()
}

@Suppress("MemberVisibilityCanBePrivate", "unused")
data class V3(
    var x: Number = 0.0,
    var y: Number = 0.0,
    var z: Number = 0.0
) {

    val doubleX
        get() = x as Double
    val doubleY
        get() = y as Double
    val doubleZ
        get() = z as Double

    operator fun plus(other: V3) = V3(doubleX + other.doubleX, doubleY + other.doubleY, doubleZ + other.doubleZ)
    operator fun minus(other: V3) = V3(doubleX - other.doubleX, doubleY - other.doubleY, doubleZ - other.doubleZ)
    operator fun times(scalar: Number) = V3(doubleX * scalar as Double, doubleY * scalar, doubleZ * scalar)
    operator fun div(scalar: Number) = V3(doubleX / scalar as Double, doubleY / scalar, doubleZ / scalar)

    fun length() = sqrt(doubleX * doubleX + doubleY * doubleY + doubleZ * doubleZ)
    fun normalize() = this / length()
}

data class Rotation(
    var pitch: Float = 0f,
    var yaw: Float = 0f,
    var roll: Float = 0f
)

/**
 * Creates a 3-dimensional vector from two numbers, with the z-coordinate set to 0.0.
 *
 * @receiver The x-coordinate of the vector.
 * @param other The y-coordinate of the vector.
 * @return A new 3-dimensional vector.
 */
@Suppress("NOTHING_TO_INLINE")
inline infix fun Number.x(other: Number): V3 = V3(this, other, 0.0)