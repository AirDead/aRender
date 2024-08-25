@file:Suppress("SpellCheckingInspection")

package dev.airdead.common

import org.joml.Matrix3f
import org.joml.Matrix4f
import org.joml.Quaternionf

interface Matrix {
    fun transform(x: Number, y: Number, z: Number)
    fun scale(x: Number, y: Number, z: Number)
    fun translate(x: Number, y: Number, z: Number)
    fun multiply(quaternionf: Quaternionf, x: Number = 0, y: Number = 0, z: Number = 0)
    fun rotate(angle: Number, x: Number, y: Number, z: Number)
    fun push()
    fun pop()
    fun peek(): MatrixEntry
    fun isEmpty(): Boolean
    fun loadIdentity()
    fun multiplyPositionMatrix(matrix: Matrix4f)
}

interface MatrixEntry {
    val positionMatrix: Matrix4f
    val normalMatrix: Matrix3f
}
