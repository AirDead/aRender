package dev.airdead.common.utility.client

interface Resolution {
    val windowWidth: Int
    val windowHeight: Int

    val viewportWidth: Int
    val viewportHeight: Int

    val scaledWidth: Int
    val scaledHeight: Int

    val scaleFactor: Double
}