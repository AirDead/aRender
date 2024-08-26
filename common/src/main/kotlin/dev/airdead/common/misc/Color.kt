package dev.airdead.common.misc

import org.jetbrains.annotations.Range

/**
 * Class for coloring elements.
 *
 * @param red Red value.
 * @param green Green value.
 * @param blue Blue value.
 * @param alpha Percentage of transparency.
 *
 * @throws IllegalArgumentException If some value is not in it range.
 */
@Suppress("LeakingThis")
data class Color(
    var red: @Range(from = 0, to = 255) Int = 0,
    var green: @Range(from = 0, to = 255) Int = 0,
    var blue: @Range(from = 0, to = 255) Int = 0,
    var alpha: @Range(from = 0, to = 1) Double = 1.0
) : Interpolatable {

    companion object {
        @JvmStatic val BLACK = Color(0, 0, 0)
        @JvmStatic val DARK_BLUE = Color(0, 0, 170)
        @JvmStatic val DARK_GREEN = Color(0, 170, 0)
        @JvmStatic val DARK_AQUA = Color(0, 170, 170)
        @JvmStatic val DARK_RED = Color(170, 0, 0)
        @JvmStatic val DARK_PURPLE = Color(170, 0, 170)
        @JvmStatic val GOLD = Color(255, 170, 0)
        @JvmStatic val GRAY = Color(170, 170, 170)
        @JvmStatic val DARK_GRAY = Color(85, 85, 85)
        @JvmStatic val BLUE = Color(85, 85, 255)
        @JvmStatic val RED = Color(255, 85, 85)
        @JvmStatic val YELLOW = Color(255, 255, 85)
        @JvmStatic val GREEN = Color(85, 255, 85)
        @JvmStatic val AQUA = Color(85, 255, 255)
        @JvmStatic val LIGHT_PURPLE = Color(255, 85, 255)
        @JvmStatic val WHITE = Color(255, 255, 255)

        /**
         * Fully transparent color
         */
        @JvmStatic val TRANSPARENT = Color(0, 0, 0, 0.0)
    }

    init {
        when {
            red !in 0..255 -> throw IllegalArgumentException("Red value is not in 0..255 range!")
            green !in 0..255 -> throw IllegalArgumentException("Green value is not in 0..255 range!")
            blue !in 0..255 -> throw IllegalArgumentException("Blue value is not in 0..255 range!")
            alpha !in 0.0..1.0 -> throw IllegalArgumentException("Alpha value is not in 0.0..1.0 range!")
        }
    }

    /**
     * Copy values from another color to current instance.
     *
     * @param another Another [Color].
     */
    fun write(another: Color) {
        another.red = this.red
        another.green = this.green
        another.blue = this.blue
        another.alpha = this.alpha
    }

    /**
     * Int-value representation of [Color].
     */
    fun toInt(): Int {
        val alphaInt = (alpha * 255).toInt().coerceIn(0, 255)
        return java.awt.Color(red, green, blue, alphaInt).rgb
    }

    /**
     * Copy current instance of [Color] with it values.
     *
     * @return New instance of [Color].
     */
    fun copy(): Color {
        return Color(this.red, this.green, this.blue, this.alpha)
    }

    /**
     * Finding the difference in the values of two colors relative to [progress].
     *
     * @param other Second [Color].
     * @param progress Progress value.
     *
     * @throws IllegalArgumentException If [progress] is not in it range.
     */
    override fun interpolate(other: Interpolatable, progress: @Range(from = 0, to = 1) Double) {
        if (progress !in 0.0..1.0) throw IllegalArgumentException("Progress is not in 0.0..1.0 range!")

        when(other) {
            is Color -> {
                this.red = (red + (other.red - red) * progress).toInt()
                this.green = (green + (other.green - green) * progress).toInt()
                this.blue = (blue + (other.blue - blue) * progress).toInt()
                this.alpha = alpha + (other.alpha - alpha) * progress
            }

            else -> throw NullPointerException("Incorrect parameter. Interpolate with other Color instance!")
        }
    }
}