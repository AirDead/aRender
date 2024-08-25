package dev.airdead.common.utility

interface Image {

    val width: Int
    val height: Int

    fun copyFrom(other: Image)
    fun copy(): Image
    fun getPixelRGBA(x: Int, y: Int): Int
    fun setPixelRGBA(x: Int, y: Int, color: Int)
}