package dev.airdead.common.utility.player

import java.util.regex.Pattern

interface Chat {
    val ampersandPattern: Pattern

    fun chat(obj: Any)
    fun actionBar(obj: Any)
    fun say(text: String)
    fun addColor(message: String): String
}