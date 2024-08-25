package dev.airdead.common.utility.player

import java.util.UUID

interface Player {
    val uuid: UUID

    val x: Double
    val y: Double
    val z: Double

    val prevX: Double
    val prevY: Double
    val prevZ: Double
}