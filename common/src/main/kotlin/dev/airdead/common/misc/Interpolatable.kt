package dev.airdead.common.misc

interface Interpolatable {
    fun interpolate(other: Interpolatable, progress: Double)
}