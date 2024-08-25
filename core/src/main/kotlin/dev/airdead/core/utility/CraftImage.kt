package dev.airdead.core.utility

import dev.airdead.common.utility.Image
import net.minecraft.client.texture.NativeImage

@Suppress("MemberVisibilityCanBePrivate", "unused", "SpellCheckingInspection")
class CraftImage(val nativeImage: NativeImage) : Image {

    override val width: Int
        get() = nativeImage.width

    override val height: Int
        get() = nativeImage.height

    override fun copyFrom(other: Image) {
        val otherNative = (other as CraftImage).nativeImage

        nativeImage.copyFrom(otherNative)
    }

    override fun copy(): Image {
        return CraftImage(NativeImage(width, height, false)).also { it.copyFrom(this) }
    }

    override fun getPixelRGBA(x: Int, y: Int): Int {
        val abgr = nativeImage.getColor(x, y) // mappings are incorrect, this returns ABGR

        val a = abgr shr 24 and 0xFF
        val b = abgr shr 16 and 0xFF
        val g = abgr shr 8 and 0xFF
        val r = abgr and 0xFF
        return (r shl 24) or (g shl 16) or (b shl 8) or a
    }

    override fun setPixelRGBA(x: Int, y: Int, color: Int) {
        val r = color shr 24 and 0xFF
        val g = color shr 16 and 0xFF
        val b = color shr 8 and 0xFF
        val a = color and 0xFF

        nativeImage.setColor(x, y, (a shl 24) or (b shl 16) or (g shl 8) or r) // mappings are incorrect, this takes ABGR
    }

    companion object {

        @JvmStatic
        @JvmOverloads
        fun ofSize(width: Int, height: Int, clear: Boolean = true): CraftImage {
            return CraftImage(NativeImage(width, height, clear))
        }

    }
}