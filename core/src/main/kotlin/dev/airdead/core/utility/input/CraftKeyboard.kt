package dev.airdead.core.utility.input

import dev.airdead.common.utility.input.Keyboard
import dev.airdead.core.utility.ClientAPI
import dev.airdead.core.utility.text.TextComponent
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import org.lwjgl.glfw.GLFW

object CraftKeyboard : Keyboard {

    override val KEY_NONE: Int = noInline { InputUtil.UNKNOWN_KEY.code }
    override val KEY_ESCAPE: Int = noInline { GLFW.GLFW_KEY_ESCAPE }
    override val KEY_LMETA: Int = noInline { GLFW.GLFW_KEY_LEFT_SUPER } // TODO: Correct?
    override val KEY_RMETA: Int = noInline { GLFW.GLFW_KEY_RIGHT_SUPER } // TODO: Correct?
    override val KEY_LCONTROL: Int = noInline { GLFW.GLFW_KEY_LEFT_CONTROL }
    override val KEY_RCONTROL: Int = noInline { GLFW.GLFW_KEY_RIGHT_CONTROL }
    override val KEY_LSHIFT: Int = noInline { GLFW.GLFW_KEY_LEFT_SHIFT }
    override val KEY_RSHIFT: Int = noInline { GLFW.GLFW_KEY_RIGHT_SHIFT }
    override val KEY_LMENU: Int = noInline { GLFW.GLFW_KEY_LEFT_ALT }
    override val KEY_RMENU: Int = noInline { GLFW.GLFW_KEY_RIGHT_ALT }
    override val KEY_MENU: Int = noInline { GLFW.GLFW_KEY_MENU }
    override val KEY_MINUS: Int = noInline { GLFW.GLFW_KEY_MINUS }
    override val KEY_EQUALS: Int = noInline { GLFW.GLFW_KEY_EQUAL }
    override val KEY_BACKSPACE: Int = noInline { GLFW.GLFW_KEY_BACKSPACE }
    override val KEY_ENTER: Int = noInline { GLFW.GLFW_KEY_ENTER }
    override val KEY_TAB: Int = noInline { GLFW.GLFW_KEY_TAB }
    override val KEY_LBRACKET: Int = noInline { GLFW.GLFW_KEY_LEFT_BRACKET }
    override val KEY_RBRACKET: Int = noInline { GLFW.GLFW_KEY_RIGHT_BRACKET }
    override val KEY_SEMICOLON: Int = noInline { GLFW.GLFW_KEY_SEMICOLON }
    override val KEY_APOSTROPHE: Int = noInline { GLFW.GLFW_KEY_APOSTROPHE }
    override val KEY_GRAVE: Int = noInline { GLFW.GLFW_KEY_GRAVE_ACCENT }
    override val KEY_BACKSLASH: Int = noInline { GLFW.GLFW_KEY_BACKSLASH }
    override val KEY_COMMA: Int = noInline { GLFW.GLFW_KEY_COMMA }
    override val KEY_PERIOD: Int = noInline { GLFW.GLFW_KEY_PERIOD }
    override val KEY_SLASH: Int = noInline { GLFW.GLFW_KEY_SLASH }
    override val KEY_MULTIPLY: Int = noInline { GLFW.GLFW_KEY_KP_MULTIPLY }
    override val KEY_SPACE: Int = noInline { GLFW.GLFW_KEY_SPACE }
    override val KEY_CAPITAL: Int = noInline { GLFW.GLFW_KEY_CAPS_LOCK }
    override val KEY_LEFT: Int = noInline { GLFW.GLFW_KEY_LEFT }
    override val KEY_UP: Int = noInline { GLFW.GLFW_KEY_UP }
    override val KEY_RIGHT: Int = noInline { GLFW.GLFW_KEY_RIGHT }
    override val KEY_DOWN: Int = noInline { GLFW.GLFW_KEY_DOWN }
    override val KEY_NUMLOCK: Int = noInline { GLFW.GLFW_KEY_NUM_LOCK }
    override val KEY_SCROLL: Int = noInline { GLFW.GLFW_KEY_SCROLL_LOCK }
    override val KEY_SUBTRACT: Int = noInline { GLFW.GLFW_KEY_KP_SUBTRACT }
    override val KEY_ADD: Int = noInline { GLFW.GLFW_KEY_KP_ADD }
    override val KEY_DIVIDE: Int = noInline { GLFW.GLFW_KEY_KP_DIVIDE }
    override val KEY_DECIMAL: Int = noInline { GLFW.GLFW_KEY_KP_DECIMAL }
    override val KEY_NUMPAD0: Int = noInline { GLFW.GLFW_KEY_KP_0 }
    override val KEY_NUMPAD1: Int = noInline { GLFW.GLFW_KEY_KP_1 }
    override val KEY_NUMPAD2: Int = noInline { GLFW.GLFW_KEY_KP_2 }
    override val KEY_NUMPAD3: Int = noInline { GLFW.GLFW_KEY_KP_3 }
    override val KEY_NUMPAD4: Int = noInline { GLFW.GLFW_KEY_KP_4 }
    override val KEY_NUMPAD5: Int = noInline { GLFW.GLFW_KEY_KP_5 }
    override val KEY_NUMPAD6: Int = noInline { GLFW.GLFW_KEY_KP_6 }
    override val KEY_NUMPAD7: Int = noInline { GLFW.GLFW_KEY_KP_7 }
    override val KEY_NUMPAD8: Int = noInline { GLFW.GLFW_KEY_KP_8 }
    override val KEY_NUMPAD9: Int = noInline { GLFW.GLFW_KEY_KP_9 }
    override val KEY_A: Int = noInline { GLFW.GLFW_KEY_A }
    override val KEY_B: Int = noInline { GLFW.GLFW_KEY_B }
    override val KEY_C: Int = noInline { GLFW.GLFW_KEY_C }
    override val KEY_D: Int = noInline { GLFW.GLFW_KEY_D }
    override val KEY_E: Int = noInline { GLFW.GLFW_KEY_E }
    override val KEY_F: Int = noInline { GLFW.GLFW_KEY_F }
    override val KEY_G: Int = noInline { GLFW.GLFW_KEY_G }
    override val KEY_H: Int = noInline { GLFW.GLFW_KEY_H }
    override val KEY_I: Int = noInline { GLFW.GLFW_KEY_I }
    override val KEY_J: Int = noInline { GLFW.GLFW_KEY_J }
    override val KEY_K: Int = noInline { GLFW.GLFW_KEY_K }
    override val KEY_L: Int = noInline { GLFW.GLFW_KEY_L }
    override val KEY_M: Int = noInline { GLFW.GLFW_KEY_M }
    override val KEY_N: Int = noInline { GLFW.GLFW_KEY_N }
    override val KEY_O: Int = noInline { GLFW.GLFW_KEY_O }
    override val KEY_P: Int = noInline { GLFW.GLFW_KEY_P }
    override val KEY_Q: Int = noInline { GLFW.GLFW_KEY_Q }
    override val KEY_R: Int = noInline { GLFW.GLFW_KEY_R }
    override val KEY_S: Int = noInline { GLFW.GLFW_KEY_S }
    override val KEY_T: Int = noInline { GLFW.GLFW_KEY_T }
    override val KEY_U: Int = noInline { GLFW.GLFW_KEY_U }
    override val KEY_V: Int = noInline { GLFW.GLFW_KEY_V }
    override val KEY_W: Int = noInline { GLFW.GLFW_KEY_W }
    override val KEY_X: Int = noInline { GLFW.GLFW_KEY_X }
    override val KEY_Y: Int = noInline { GLFW.GLFW_KEY_Y }
    override val KEY_Z: Int = noInline { GLFW.GLFW_KEY_Z }
    override val KEY_0: Int = noInline { GLFW.GLFW_KEY_0 }
    override val KEY_1: Int = noInline { GLFW.GLFW_KEY_1 }
    override val KEY_2: Int = noInline { GLFW.GLFW_KEY_2 }
    override val KEY_3: Int = noInline { GLFW.GLFW_KEY_3 }
    override val KEY_4: Int = noInline { GLFW.GLFW_KEY_4 }
    override val KEY_5: Int = noInline { GLFW.GLFW_KEY_5 }
    override val KEY_6: Int = noInline { GLFW.GLFW_KEY_6 }
    override val KEY_7: Int = noInline { GLFW.GLFW_KEY_7 }
    override val KEY_8: Int = noInline { GLFW.GLFW_KEY_8 }
    override val KEY_9: Int = noInline { GLFW.GLFW_KEY_9 }
    override val KEY_F1: Int = noInline { GLFW.GLFW_KEY_F1 }
    override val KEY_F2: Int = noInline { GLFW.GLFW_KEY_F2 }
    override val KEY_F3: Int = noInline { GLFW.GLFW_KEY_F3 }
    override val KEY_F4: Int = noInline { GLFW.GLFW_KEY_F4 }
    override val KEY_F5: Int = noInline { GLFW.GLFW_KEY_F5 }
    override val KEY_F6: Int = noInline { GLFW.GLFW_KEY_F6 }
    override val KEY_F7: Int = noInline { GLFW.GLFW_KEY_F7 }
    override val KEY_F8: Int = noInline { GLFW.GLFW_KEY_F8 }
    override val KEY_F9: Int = noInline { GLFW.GLFW_KEY_F9 }
    override val KEY_F10: Int = noInline { GLFW.GLFW_KEY_F10 }
    override val KEY_F11: Int = noInline { GLFW.GLFW_KEY_F11 }
    override val KEY_F12: Int = noInline { GLFW.GLFW_KEY_F12 }
    override val KEY_F13: Int = noInline { GLFW.GLFW_KEY_F13 }
    override val KEY_F14: Int = noInline { GLFW.GLFW_KEY_F14 }
    override val KEY_F15: Int = noInline { GLFW.GLFW_KEY_F15 }
    override val KEY_F16: Int = noInline { GLFW.GLFW_KEY_F16 }
    override val KEY_F17: Int = noInline { GLFW.GLFW_KEY_F17 }
    override val KEY_F18: Int = noInline { GLFW.GLFW_KEY_F18 }
    override val KEY_F19: Int = noInline { GLFW.GLFW_KEY_F19 }
    override val KEY_DELETE: Int = noInline { GLFW.GLFW_KEY_DELETE }
    override val KEY_HOME: Int = noInline { GLFW.GLFW_KEY_HOME }
    override val KEY_END: Int = noInline { GLFW.GLFW_KEY_END }

    private inline fun <T> noInline(init: () -> T): T = init()

    override val shiftKeyDown: Boolean
        get() = isKeyDown(KEY_LSHIFT) || isKeyDown(KEY_RSHIFT)
    override val ctrlKeyDown: Boolean
        get() = if (ClientAPI.isRunningOnMac) {
            isKeyDown(KEY_LMETA) || isKeyDown(KEY_RMETA)
        } else isKeyDown(KEY_LCONTROL) || isKeyDown(KEY_RCONTROL)
    override val altKeyDown: Boolean
        get() = isKeyDown(KEY_LMENU) || isKeyDown(KEY_RMENU)

    override val modifiers: Keyboard.Modifiers = Keyboard.Modifiers(ctrlKeyDown, shiftKeyDown, altKeyDown)

    override fun isKeyDown(key: Int): Boolean {
        if (key == KEY_NONE) return false

        val window = ClientAPI.minecraft.window.handle
        val state = if (key < 20) GLFW.glfwGetMouseButton(window, key) else GLFW.glfwGetKey(window, key)

        return state == GLFW.GLFW_PRESS
    }

    fun getKeyName(keyBinding: KeyBinding): String {
        return TextComponent(keyBinding.boundKeyLocalizedText).unformattedText.let {
            if (it.length == 1) it.uppercase() else it
        }
    }

    @Deprecated("Does not work for mouse bindings", replaceWith = ReplaceWith("getKeyName(keyBinding)"))
    fun getKeyName(keyCode: Int, scanCode: Int): String? {
        val glfwName = GLFW.glfwGetKeyName(keyCode, scanCode)

        return glfwName?.let {
            if (it.length == 1) it.uppercase() else it
        }
    }

    internal fun Keyboard.Modifiers?.toInt() = listOf(
        this?.isCtrl to GLFW.GLFW_MOD_CONTROL,
        this?.isShift to GLFW.GLFW_MOD_SHIFT,
        this?.isAlt to GLFW.GLFW_MOD_ALT,
    ).sumOf { (modifier, value) -> if (modifier == true) value else 0 }

    internal fun Int.toModifiers() = Keyboard.Modifiers(
        isCtrl = (this and GLFW.GLFW_MOD_CONTROL) != 0,
        isShift = (this and GLFW.GLFW_MOD_SHIFT) != 0,
        isAlt = (this and GLFW.GLFW_MOD_ALT) != 0,
    )
}