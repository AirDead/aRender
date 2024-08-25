package dev.airdead.common.misc.location

import dev.airdead.common.misc.Interpolatable
import kotlin.math.sqrt

@Suppress("MemberVisibilityCanBePrivate", "unused")
data class V2(
    override var x: Number = 0.0,
    override var y: Number = 0.0
) : Location {

    override var z: Number = 0.0

    override val doubleX
        get() = x.toDouble()
    override val doubleY
        get() = y.toDouble()
    override val doubleZ: Double
        get() = z.toDouble()

    override operator fun plus(other: Location) = V2(doubleX + other.doubleX, doubleY + other.doubleY)
    override operator fun minus(other: Location) = V2(doubleX - other.doubleX, doubleY - other.doubleY)
    override operator fun times(scalar: Number) = V2(doubleX * scalar.toDouble(), doubleY * scalar.toDouble())
    override operator fun div(scalar: Number) = V2(doubleX / scalar.toDouble(), doubleY / scalar.toDouble())

    override fun length() = sqrt(doubleX * doubleX + doubleY * doubleY)
    override fun normalize() = this / length()

    override fun interpolate(other: Interpolatable, progress: Double) {
        when(other) {
            is V2 -> {
                this.x = doubleX + (other.doubleX - doubleX) * progress
                this.y = doubleY + (other.doubleY - doubleY) * progress
            }

            is V3 -> {
                this.x = doubleX + (other.doubleX - doubleX) * progress
                this.y = doubleY + (other.doubleY - doubleY) * progress
            }

            else -> throw NullPointerException("Incorrect parameter. Interpolate with other V2 or V3 instance!")
        }
    }
}