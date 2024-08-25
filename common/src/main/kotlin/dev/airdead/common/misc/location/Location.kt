package dev.airdead.common.misc.location

import dev.airdead.common.misc.Interpolatable

interface Location : Interpolatable {
    var x: Number
    var y: Number
    var z: Number

    val doubleX: Double
    val doubleY: Double
    val doubleZ: Double

    operator fun plus(other: Location): Location
    operator fun minus(other: Location): Location
    operator fun times(scalar: Number): Location
    operator fun div(scalar: Number): Location

    fun length(): Double
    fun normalize(): Location
}