package dev.airdead.core.screen

import dev.airdead.common.Matrix
import dev.airdead.common.utility.input.Keyboard
import dev.airdead.core.utility.ClientAPI
import dev.airdead.core.utility.client.CraftResolution
import dev.airdead.core.utility.input.CraftKeyboard.toInt
import dev.airdead.core.utility.input.CraftKeyboard.toModifiers
import dev.airdead.core.CraftMatrix
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.screen.Screen
import net.minecraft.text.Text

@Suppress("MemberVisibilityCanBePrivate", "SpellCheckingInspection", "CanBeParameter", "unused")
abstract class AbstractScreen(
    val restoreCurrentGuiOnClose: Boolean = false,
    open var newGuiScale: Int = -1,
    open var unlocalizedName: String? = null
) : Screen(Text.translatable(unlocalizedName ?: "")) {

    @JvmOverloads
    constructor(
        restoreCurrentGuiOnClose: Boolean = false,
        newGuiScale: Int = -1,
    ) : this(restoreCurrentGuiOnClose, newGuiScale, null)

    constructor(restoreCurrentGuiOnClose: Boolean, newGuiScale: GuiScale) : this(
        restoreCurrentGuiOnClose,
        newGuiScale.ordinal
    )

    private var guiScaleToRestore = -1
    private var restoringGuiScale = false
    private val screenToRestore: Screen? = if (restoreCurrentGuiOnClose) currentScreen else null
    private var suppressBackground = false

    private var drawContexts = mutableListOf<DrawContext>()
    private inline fun <R> withDrawContext(matrixStack: Matrix, block: (DrawContext) -> R) {
        val client = this.client!!
        val context = drawContexts.lastOrNull()
            ?: DrawContext(client, client.bufferBuilders.entityVertexConsumers)
        context.matrices.push()
        val mc = context.matrices.peek()
        val uc = matrixStack.peek()
        mc.positionMatrix.set(uc.positionMatrix)
        mc.normalMatrix.set(uc.positionMatrix)
        block(context)
        context.matrices.pop()
    }

    private var lastClick = 0L
    private var lastDraggedDx = -1.0
    private var lastDraggedDy = -1.0
    private var lastScrolledX = -1.0
    private var lastScrolledY = -1.0
    private var lastScrolledDX = 0.0

    final override fun init() {
        updateGuiScale()
        initScreen(width, height)
    }

    override fun getTitle(): Text = Text.translatable(unlocalizedName ?: "")

    final override fun render(context: DrawContext, mouseX: Int, mouseY: Int, delta: Float) {
        drawContexts.add(context)
        onDrawScreen(CraftMatrix(context.matrices), mouseX, mouseY, delta)
        drawContexts.removeLast()
    }

    final override fun keyPressed(keyCode: Int, scanCode: Int, modifierCode: Int): Boolean {
        onKeyPressed(keyCode, 0.toChar(), modifierCode.toModifiers())
        return false
    }

    final override fun keyReleased(keyCode: Int, scanCode: Int, modifierCode: Int): Boolean {
        onKeyReleased(keyCode, 0.toChar(), modifierCode.toModifiers())
        return false
    }

    final override fun charTyped(char: Char, modifierCode: Int): Boolean {
        onKeyPressed(0, char, modifierCode.toModifiers())
        return false
    }

    final override fun mouseClicked(mouseX: Double, mouseY: Double, mouseButton: Int): Boolean {
        if (mouseButton == 1)
            lastClick = ClientAPI.time
        onMouseClicked(mouseX, mouseY, mouseButton)
        return false
    }

    final override fun mouseReleased(mouseX: Double, mouseY: Double, mouseButton: Int): Boolean {
        onMouseReleased(mouseX, mouseY, mouseButton)
        return false
    }

    final override fun mouseDragged(x: Double, y: Double, mouseButton: Int, dx: Double, dy: Double): Boolean {
        lastDraggedDx = dx
        lastDraggedDy = dy
        onMouseDragged(x, y, mouseButton, ClientAPI.time - lastClick)
        return false
    }

    final override fun mouseScrolled(mouseX: Double, mouseY: Double, delta: Double): Boolean {
        lastScrolledX = mouseX
        lastScrolledY = mouseY
        onMouseScrolled(delta)
        return false
    }

    final override fun tick(): Unit = onTick()

    final override fun removed() {
        onScreenClose()
        restoreGuiScale()
    }

    final override fun renderBackground(context: DrawContext) {
        drawContexts.add(context)
        onDrawBackground(CraftMatrix(context.matrices), 0)
        drawContexts.removeLast()
    }

    fun restorePreviousScreen() {
        displayScreen(screenToRestore)
    }

    open fun updateGuiScale() {
        if (newGuiScale != -1 && !restoringGuiScale) {
            if (guiScaleToRestore == -1)
                guiScaleToRestore = ClientAPI.guiScale
            ClientAPI.guiScale = newGuiScale
            width = CraftResolution.scaledWidth
            height = CraftResolution.scaledHeight
        }
    }

    private fun restoreGuiScale() {
        if (guiScaleToRestore != -1) {
            restoringGuiScale = true
            ClientAPI.guiScale = guiScaleToRestore
            restoringGuiScale = false
            guiScaleToRestore = -1
        }
    }

    open fun initScreen(width: Int, height: Int) {
        super.init()
    }

    open fun onDrawScreen(matrixStack: Matrix, mouseX: Int, mouseY: Int, partialTicks: Float) {
        suppressBackground = true
        withDrawContext(matrixStack) { drawContext ->
            super.render(drawContext, mouseX, mouseY, partialTicks)
        }
        suppressBackground = false
    }

    open fun onKeyPressed(keyCode: Int, typedChar: Char, modifiers: Keyboard.Modifiers?) {
        if (keyCode != 0) {
            super.keyPressed(keyCode, 0, modifiers.toInt())
        }
        if (typedChar != 0.toChar()) {
            super.charTyped(typedChar, modifiers.toInt())
        }
    }

    open fun onKeyReleased(keyCode: Int, typedChar: Char, modifiers: Keyboard.Modifiers?) {
        if (keyCode != 0) {
            super.keyReleased(keyCode, 0, modifiers.toInt())
        }
    }

    open fun onMouseClicked(mouseX: Double, mouseY: Double, mouseButton: Int) {
        if (mouseButton == 1)
            lastClick = ClientAPI.time
        super.mouseClicked(mouseX, mouseY, mouseButton)
    }

    open fun onMouseReleased(mouseX: Double, mouseY: Double, state: Int) {
        super.mouseReleased(mouseX, mouseY, state)
    }

    open fun onMouseDragged(x: Double, y: Double, clickedButton: Int, timeSinceLastClick: Long) {
        super.mouseDragged(x, y, clickedButton, lastDraggedDx, lastDraggedDy)
    }

    open fun onMouseScrolled(delta: Double) {
        super.mouseScrolled(lastScrolledX, lastScrolledY, delta)
    }

    open fun onTick() {
        super.tick()
    }

    open fun onScreenClose() {
        super.removed()
    }

    open fun onDrawBackground(matrixStack: Matrix, tint: Int) {
        withDrawContext(matrixStack) { drawContext ->
            super.renderBackground(drawContext)
        }
    }

    companion object {
        @JvmStatic
        val currentScreen: Screen?
            get() = ClientAPI.minecraft.currentScreen

        @JvmStatic
        fun displayScreen(screen: Screen?) {
            ClientAPI.minecraft.setScreen(screen)
        }
    }
}
