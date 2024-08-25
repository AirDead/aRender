package dev.airdead.core.utility.text

import dev.airdead.core.utility.player.CraftChat
import net.minecraft.text.*
import net.minecraft.text.StringVisitable.StyledVisitor
import net.minecraft.text.StringVisitable.Visitor
import net.minecraft.util.Formatting
import java.util.*

class TextComponent : Text {
    lateinit var component: MutableText
        private set

    var text: String = ""
        set(value) {
            field = value
            reInstance()
        }
    var formatted = true
        set(value) {
            field = value
            reInstance()
        }

    var clickAction: MCClickEventAction? = null
        set(value) {
            field = value
            reInstance()
        }
    var clickValue: String? = null
        set(value) {
            field = value
            reInstance()
        }
    var hoverAction: MCHoverEventAction? = null
        set(value) {
            field = value
            reInstance()
        }
    var hoverValue: Any? = null
        set(value) {
            field = value
            reInstance()
        }

    constructor(text: String) {
        this.text = text
        reInstance()
    }

    constructor(component: Text) : this(component.copy())

    constructor(component: MutableText) {
        this.component = component

        text = formattedText

        val clickEvent = component.style.clickEvent

        if (clickEvent != null) {
            clickAction = clickEvent.action
            clickValue = clickEvent.value
        }

        val hoverEvent = component.style.hoverEvent

        if (hoverEvent != null) {
            hoverAction = hoverEvent.action

            hoverValue = hoverEvent.getValue(hoverAction)
        }
    }

    fun setClick(action: MCClickEventAction, value: String) = apply {
        clickAction = action
        clickValue = value
        reInstance()
    }

    fun setHover(action: MCHoverEventAction, value: Any) = apply {
        hoverAction = action
        hoverValue = value
        reInstance()
    }

    fun chat() {
        Message(this).chat()
    }

    fun actionBar() {
        Message(this).actionBar()
    }

    private fun reInstance() {
        //#if MC>=11900
        component = Text.literal(text.formatIf(formatted))
        //#else
        //$$ component = LiteralText(text.formatIf(formatted))
        //#endif

        reInstanceClick()
        reInstanceHover()
    }

    private fun reInstanceClick() {
        val clickAction = clickAction
        val clickValue = clickValue
        if (clickAction == null || clickValue == null)
            return

        val event = ClickEvent(clickAction, clickValue.formatIf(formatted))

        //#if MC>=11600
        component.style = component.style.withClickEvent(event)
        //#elseif MC>=11202
        //$$ component.style.clickEvent = event
        //#else
        //$$ component.chatStyle.chatClickEvent = event
        //#endif
    }

    private fun reInstanceHover() {
        val hoverAction = hoverAction
        val hoverValue = hoverValue
        if (hoverAction == null || hoverValue == null)
            return

        val event = HoverEvent(hoverAction as HoverEvent.Action<Any>, hoverValue)
        setHoverEventHelper(event)
    }

    private fun setHoverEventHelper(event: HoverEvent) {
        component.style = component.style.withHoverEvent(event)
    }

    private fun String.formatIf(predicate: Boolean) = if (predicate) CraftChat.addColor(this) else this

    private class TextBuilder(private val isFormatted: Boolean) : CharacterVisitor {
        private val builder = StringBuilder()
        private var cachedStyle: Style? = null

        override fun accept(index: Int, style: Style, codePoint: Int): Boolean  {
            if (isFormatted && style != cachedStyle) {
                cachedStyle = style
                builder.append(formatString(style))
            }

            builder.append(codePoint.toChar())
            return true
        }

        fun getString() = builder.toString()

        private fun formatString(style: Style): String {
            val builder = StringBuilder("§r")

            when {
                style.isBold -> builder.append("§l")
                style.isItalic -> builder.append("§o")
                style.isUnderlined -> builder.append("§n")
                style.isStrikethrough -> builder.append("§m")
                style.isObfuscated -> builder.append("§k")
            }

            style.color?.let(colorToFormatChar::get)?.let {
                builder.append(it)
            }
            return builder.toString()
        }

        companion object {
            private val colorToFormatChar = Formatting.values().mapNotNull { format ->
                TextColor.fromFormatting(format)?.let { it to format }
            }.toMap()
        }
    }
    //#endif

    // **********************
    // * METHOD DELEGATIONS *
    // **********************

    //#if MC>=11900
    override fun getContent(): TextContent = component.content
    //#endif

    //#if MC>=11602
    val unformattedText: String get() {
        val builder = TextBuilder(false)
        component.asOrderedText().accept(builder)
        return builder.getString()
    }

    val formattedText: String get() {
        val builder = TextBuilder(true)
        component.asOrderedText().accept(builder)
        return builder.getString()
    }

    fun appendSibling(text: Text): MutableText = component.append(text)

    override fun getString(): String = component.string

    override fun asTruncatedString(maxLen: Int): String = component.asTruncatedString(maxLen)

    override fun <T> visit(p_230439_1_: StyledVisitor<T>, p_230439_2_: Style): Optional<T> {
        return component.visit(p_230439_1_, p_230439_2_)
    }

    override fun <T> visit(p_230438_1_: Visitor<T>): Optional<T> {
        return component.visit(p_230438_1_)
    }

    override fun getStyle(): Style = component.style

    override fun getSiblings(): MutableList<Text> = component.siblings

    override fun copyContentOnly(): MutableText = component.copyContentOnly()

    override fun copy(): MutableText = component.copy()

    override fun asOrderedText(): OrderedText = component.asOrderedText()

    companion object {
        fun from(obj: Any): TextComponent? {
            return when (obj) {
                is TextComponent -> obj
                is String -> TextComponent(obj)
                is Text -> TextComponent(obj)
                else -> null
            }
        }

        fun stripFormatting(string: String): String {
            return Formatting.strip(string)!!
        }
    }
}