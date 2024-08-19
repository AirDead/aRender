package dev.airdead.common.core.element

import com.mojang.blaze3d.systems.RenderSystem
import dev.airdead.common.element.Element
import dev.airdead.common.math.Matrix
import dev.airdead.common.math.V3
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.render.Tessellator
import net.minecraft.client.render.VertexFormat
import net.minecraft.client.render.VertexFormats

class TestElement : Element {
    override var size: V3 = V3(20.0, 20.0)

    override fun render(matrix: Matrix) {
        val positionMatrix = matrix.peek().positionMatrix
        val tesselator = Tessellator.getInstance()
        val buffer = tesselator.buffer

        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR)
        buffer.vertex(positionMatrix, 20f, 20f, 0f).color(1f, 1f, 1f, 1f).next()
        buffer.vertex(positionMatrix, 20f, 60f, 0f).color(1f, 0f, 0f, 1f).next()
        buffer.vertex(positionMatrix, 60f, 60f, 0f).color(0f, 1f, 0f, 1f).next()
        buffer.vertex(positionMatrix, 60f, 20f, 0f).color(0f, 0f, 1f, 1f).next()

        RenderSystem.setShader(GameRenderer::getPositionColorProgram)
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f)

        tesselator.draw()
    }
}