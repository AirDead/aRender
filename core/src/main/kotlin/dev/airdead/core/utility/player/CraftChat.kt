@file:Suppress("HasPlatformType")

package dev.airdead.core.utility.player

import dev.airdead.common.utility.player.Chat
import dev.airdead.core.utility.text.Message
import dev.airdead.core.utility.text.TextComponent
import java.util.regex.Pattern

object CraftChat : Chat {

    override val ampersandPattern = Pattern.compile("(?<!\\\\)&(?![^0-9a-fklmnor]|$)")

    override fun chat(obj: Any) {
        if (obj is String || obj is TextComponent) {
            Message(obj).chat()
        } else {
            val component = TextComponent.from(obj)
            if (component != null) {
                component.chat()
            } else {
                Message(obj.toString()).chat()
            }
        }
    }

    override fun actionBar(obj: Any) {
        if (obj is String || obj is TextComponent) {
            Message(obj).actionBar()
        } else {
            val component = TextComponent.from(obj)
            if (component != null) {
                component.actionBar()
            } else {
                Message(obj.toString()).actionBar()
            }
        }
    }

    override fun say(text: String) {
        CraftPlayer.player?.networkHandler?.sendChatMessage(text)
    }

    override fun addColor(message: String): String = ampersandPattern.matcher(message).replaceAll("\u00a7")

}