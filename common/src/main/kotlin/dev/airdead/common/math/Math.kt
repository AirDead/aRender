package dev.airdead.common.math

import kotlin.math.sqrt

data class V2(
    var x: Number = 0.0,
    var y: Number = 0.0
) {
    operator fun plus(other: V2) = V2(x.toDouble() + other.x.toDouble(), y.toDouble() + other.y.toDouble())
    operator fun minus(other: V2) = V2(x.toDouble() - other.x.toDouble(), y.toDouble() - other.y.toDouble())
    operator fun times(scalar: Number) = V2(x.toDouble() * scalar.toDouble(), y.toDouble() * scalar.toDouble())
    operator fun div(scalar: Number) = V2(x.toDouble() / scalar.toDouble(), y.toDouble() / scalar.toDouble())

    fun length() = sqrt(x.toDouble() * x.toDouble() + y.toDouble() * y.toDouble())
    fun normalize() = this / length()
}

data class V3(
    var x: Number = 0.0,
    var y: Number = 0.0,
    var z: Number = 0.0
) {
    operator fun plus(other: V3) = V3(x.toDouble() + other.x.toDouble(), y.toDouble() + other.y.toDouble(), z.toDouble() + other.z.toDouble())
    operator fun minus(other: V3) = V3(x.toDouble() - other.x.toDouble(), y.toDouble() - other.y.toDouble(), z.toDouble() - other.z.toDouble())
    operator fun times(scalar: Number) = V3(x.toDouble() * scalar.toDouble(), y.toDouble() * scalar.toDouble(), z.toDouble() * scalar.toDouble())
    operator fun div(scalar: Number) = V3(x.toDouble() / scalar.toDouble(), y.toDouble() / scalar.toDouble(), z.toDouble() / scalar.toDouble())

    fun length() = sqrt(x.toDouble() * x.toDouble() + y.toDouble() * y.toDouble() + z.toDouble() * z.toDouble())
    fun normalize() = this / length()
}

data class Rotation(
    var pitch: Float = 0f,
    var yaw: Float = 0f,
    var roll: Float = 0f
)