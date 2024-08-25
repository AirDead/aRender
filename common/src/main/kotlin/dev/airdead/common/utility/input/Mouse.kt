package dev.airdead.common.utility.input

interface Mouse {

    val raw: RawMouse
    val scaled: ScaledMouse

    fun getTrueX(): Double
    fun getTrueY(): Double

    fun getScaledX(): Double
    fun getScaledY(): Double
}

interface RawMouse {
    val x: Double
    val y: Double
}

interface ScaledMouse {
    val x: Double
    val y: Double
}