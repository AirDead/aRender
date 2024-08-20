package dev.airdead.core

import dev.airdead.common.math.Matrix
import dev.airdead.common.math.MatrixEntry
import net.minecraft.client.util.math.MatrixStack
import org.joml.Matrix3f
import org.joml.Matrix4f
import org.joml.Quaternionf
import kotlin.math.cos
import kotlin.math.sin

@Suppress("NAME_SHADOWING", "SpellCheckingInspection")
class CraftMatrix : Matrix {
    private val matrix = MatrixStack()

    override fun transform(x: Number, y: Number, z: Number) = matrix.translate(x.toDouble(), y.toDouble(), z.toDouble())
    override fun scale(x: Number, y: Number, z: Number) = matrix.translate(x.toDouble(), y.toDouble(), z.toDouble())
    override fun translate(x: Number, y: Number, z: Number) = matrix.translate(x.toDouble(), y.toDouble(), z.toDouble())
    override fun multiply(quaternionf: Quaternionf, x: Number, y: Number, z: Number) = matrix.multiply(quaternionf, x.toFloat(), y.toFloat(), z.toFloat())

    override fun rotate(angle: Number, x: Number, y: Number, z: Number) {
        val angle = angle.toFloat()
        val x = x.toFloat()
        val y = y.toFloat()
        val z = z.toFloat()
        val matrix4f = matrix.peek().positionMatrix

        val c = cos(angle)
        val s = sin(angle)

        val oneminusc = 1.0f - c
        val xy = x * y
        val yz = y * z
        val xz = x * z
        val xs = x * s
        val ys = y * s
        val zs = z * s

        val f00 = x * x * oneminusc + c
        val f01 = xy * oneminusc + zs
        val f02 = xz * oneminusc - ys
        val f10 = xy * oneminusc - zs
        val f11 = y * y * oneminusc + c
        val f12 = yz * oneminusc + xs
        val f20 = xz * oneminusc + ys
        val f21 = yz * oneminusc - xs
        val f22 = z * z * oneminusc + c

        val t00 = matrix4f.m00() * f00 + matrix4f.m10() * f01 + matrix4f.m20() * f02
        val t01 = matrix4f.m01() * f00 + matrix4f.m11() * f01 + matrix4f.m21() * f02
        val t02 = matrix4f.m02() * f00 + matrix4f.m12() * f01 + matrix4f.m22() * f02
        val t03 = matrix4f.m03() * f00 + matrix4f.m13() * f01 + matrix4f.m23() * f02
        val t10 = matrix4f.m00() * f10 + matrix4f.m10() * f11 + matrix4f.m20() * f12
        val t11 = matrix4f.m01() * f10 + matrix4f.m11() * f11 + matrix4f.m21() * f12
        val t12 = matrix4f.m02() * f10 + matrix4f.m12() * f11 + matrix4f.m22() * f12
        val t13 = matrix4f.m03() * f10 + matrix4f.m13() * f11 + matrix4f.m23() * f12

        matrix4f.m20(matrix4f.m00() * f20 + matrix4f.m10() * f21 + matrix4f.m20() * f22)
        matrix4f.m21(matrix4f.m01() * f20 + matrix4f.m11() * f21 + matrix4f.m21() * f22)
        matrix4f.m22(matrix4f.m02() * f20 + matrix4f.m12() * f21 + matrix4f.m22() * f22)
        matrix4f.m23(matrix4f.m03() * f20 + matrix4f.m13() * f21 + matrix4f.m23() * f22)
        matrix4f.m00(t00)
        matrix4f.m01(t01)
        matrix4f.m02(t02)
        matrix4f.m03(t03)
        matrix4f.m10(t10)
        matrix4f.m11(t11)
        matrix4f.m12(t12)
        matrix4f.m13(t13)
    }

    override fun push() = matrix.push()
    override fun pop() = matrix.pop()

    override fun peek(): MatrixEntry {
        val entry = matrix.peek()
        return CraftMatrixEntry(entry.positionMatrix, entry.normalMatrix)
    }

    override fun isEmpty() = matrix.isEmpty
    override fun loadIdentity() = matrix.loadIdentity()

    override fun multiplyPositionMatrix(matrix: Matrix4f) {
        peek().positionMatrix.mul(matrix)
    }
}

data class CraftMatrixEntry(
    override val positionMatrix: Matrix4f,
    override val normalMatrix: Matrix3f
) : MatrixEntry