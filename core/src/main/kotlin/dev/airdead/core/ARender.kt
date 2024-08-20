package dev.airdead.core

import dev.airdead.common.Render
import dev.airdead.common.element.RendererElement
import dev.airdead.common.math.V2

class ARender : Render() {

    override val elements: MutableList<RendererElement> = mutableListOf()

    override fun init() {
        TODO("Not yet implemented")
    }

    override val mouse: V2
        get() = TODO("Not yet implemented")

    override fun isMouseButtonClicked(button: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun addElement(element: RendererElement) {
        TODO("Not yet implemented")
    }

    override fun removeElement(element: RendererElement) {
        TODO("Not yet implemented")
    }

}