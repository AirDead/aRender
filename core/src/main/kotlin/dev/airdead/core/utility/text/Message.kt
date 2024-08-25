package dev.airdead.core.utility.text

import dev.airdead.core.utility.ClientAPI
import dev.airdead.core.utility.player.CraftPlayer
import net.minecraft.client.gui.hud.ChatHud
import net.minecraft.text.MutableText
import java.lang.invoke.MethodHandle
import java.lang.invoke.MethodHandles
import java.util.concurrent.ThreadLocalRandom

@Suppress("unused", "MemberVisibilityCanBePrivate")
class Message {
    private lateinit var _chatMessage: TextComponent
    val messageParts: MutableList<TextComponent> = mutableListOf()

    val chatMessage: TextComponent
        get() {
            parseMessage()
            return _chatMessage
        }

    val formattedText: String
        get() = chatMessage.formattedText

    val unformattedText: String
        get() = chatMessage.unformattedText

    var chatLineId: Int = -1
    var isRecursive: Boolean = false
    var isFormatted: Boolean = true

    constructor(component: TextComponent) {
        if (component.siblings.isEmpty()) {
            messageParts.add(component)
        } else {
            component.siblings
                .filterIsInstance<MutableText>()
                .map { TextComponent(it) }
                .forEach { messageParts.add(it) }
        }
    }

    constructor(vararg parts: Any) {
        parts.forEach(::addPart)
    }

    fun setTextComponent(index: Int, component: Any) = apply {
        if (component is String) {
            messageParts[index] = TextComponent(component)
        } else {
            TextComponent.from(component)?.also { messageParts[index] = it }
        }
    }

    fun addTextComponent(index: Int, component: Any) = apply {
        if (component is String) {
            messageParts.add(index, TextComponent(component))
        } else {
            TextComponent.from(component)?.also { messageParts.add(index, it) }
        }
    }

    fun addTextComponent(component: Any): Message = addTextComponent(messageParts.size, component)

    /**
     * Must be called to be able to edit later
     */
    fun mutable() = apply {
        chatLineId = ThreadLocalRandom.current().nextInt()
    }

    fun edit(vararg replacements: Any) {
        if (chatLineId == -1) throw IllegalStateException("This message is not mutable!")
        messageParts.clear()
        replacements.forEach(::addPart)
        chat()
    }

    fun chat() {
        parseMessage()

        if (!CraftPlayer.hasPlayer())
            return

        if (chatLineId != -1) {
            printChatMessageWithOptionalDeletion(chatMessage, chatLineId)
            return
        }

        if (isRecursive) {
            CraftPlayer.sendChatMessage(_chatMessage)
        } else {
            CraftPlayer.sendClientSideMessage(_chatMessage)
        }
    }

    fun actionBar() {
        parseMessage()

        if (!CraftPlayer.hasPlayer())
            return

        CraftPlayer.sendActionBarMessage(_chatMessage)
    }

    private fun addPart(part: Any) {
        when (part) {
            is TextComponent -> messageParts.add(part)
            is String -> messageParts.add(TextComponent(part))
            else -> TextComponent.from(part)?.also(::addPart)
        }
    }

    private fun parseMessage() {
        _chatMessage = TextComponent("")
        messageParts.forEach { _chatMessage.appendSibling(it) }
    }
}

private val printChatMessageWithOptionalDeletion: MethodHandle? = try {
    val method = ChatHud::class.java.declaredMethods.find { method ->
        method.parameterTypes.run {
            size == 2 &&
                    get(0) == MCITextComponent::class.java &&
                    get(1) == Int::class.java
        }
    } ?: throw NoSuchMethodException(
        "Could not find method to edit chat messages. " +
                "No method with parameters (${MCITextComponent::class.java.name}, int) in ${ChatHud::class.java.name}."
    )
    method.isAccessible = true
    MethodHandles.lookup().unreflect(method)
} catch (e: Throwable) {
    e.printStackTrace()
    null
}

internal fun printChatMessageWithOptionalDeletion(textComponent: TextComponent, lineID: Int) {
    // The `apply` wrapper appears to be necessary when using the IR compiler backend for it to generate the return type
    // as `V`, otherwise it'll infer `Ljava/lang/Object;` which does not match our target method.
    printChatMessageWithOptionalDeletion?.apply { invokeExact(ClientAPI.chatHud, textComponent as MCITextComponent, lineID) }
}