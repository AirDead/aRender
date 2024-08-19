package dev.airdead.common.core

import dev.airdead.common.math.Matrix
import dev.airdead.common.math.MatrixEntry
import net.minecraft.client.util.math.MatrixStack
import org.joml.Matrix3f
import org.joml.Matrix4f
import org.joml.Quaternionf

class CraftMatrix : Matrix {
    private val matrix = MatrixStack()

    override fun transform(x: Number, y: Number, z: Number) = matrix.translate(x.toDouble(), y.toDouble(), z.toDouble())
    override fun scale(x: Number, y: Number, z: Number) = matrix.translate(x.toDouble(), y.toDouble(), z.toDouble())
    override fun translate(x: Number, y: Number, z: Number) = matrix.translate(x.toDouble(), y.toDouble(), z.toDouble())
    override fun multiply(quaternionf: Quaternionf, x: Number, y: Number, z: Number) = matrix.multiply(quaternionf, x.toFloat(), y.toFloat(), z.toFloat())

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