package dev.airdead.core.element

import com.mojang.blaze3d.systems.RenderSystem
import dev.airdead.common.element.Element
import dev.airdead.common.element.InteractiveElement
import dev.airdead.common.element.RendererElement
import dev.airdead.common.handler.ClickHandler
import dev.airdead.common.handler.HoverHandler
import dev.airdead.common.math.Matrix
import dev.airdead.common.math.V3
import dev.airdead.common.math.x
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.render.Tessellator
import net.minecraft.client.render.VertexFormat
import net.minecraft.client.render.VertexFormats

class TestElement : InteractiveElement, RendererElement {

    override var enabled: Boolean = true
    override var interactive: Boolean = true

    override var size: V3 = 20 x 20
    override var align: V3 = 0 x 0
    override var origin: V3 = 0 x 0
    override var offset: V3 = 0 x 0
    override var rotation: V3 = 0 x 0
    override var location: V3 = 0 x 0

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


    override fun onHover(handler: HoverHandler) {
        TODO("Not yet implemented")
    }

    override fun onLeftClick(handler: ClickHandler) {
        TODO("Not yet implemented")
    }

    override fun onRightClick(handler: ClickHandler) {
        TODO("Not yet implemented")
    }

    override fun isHovered(mouseX: Double, mouseY: Double) {
        TODO("Not yet implemented")
    }

    override val children: MutableList<Element> = mutableListOf()
}