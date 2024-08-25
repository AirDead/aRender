@file:Suppress("PropertyName", "unused", "SpellCheckingInspection")

package dev.airdead.common.utility.input

interface Keyboard {

    val KEY_NONE: Int
    val KEY_ESCAPE: Int
    val KEY_LMETA: Int
    val KEY_RMETA: Int
    val KEY_LCONTROL: Int
    val KEY_RCONTROL: Int
    val KEY_LSHIFT: Int
    val KEY_RSHIFT: Int
    val KEY_LMENU: Int
    val KEY_RMENU: Int
    val KEY_MENU: Int
    val KEY_MINUS: Int
    val KEY_EQUALS: Int
    val KEY_BACKSPACE: Int
    val KEY_ENTER: Int
    val KEY_TAB: Int
    val KEY_LBRACKET: Int
    val KEY_RBRACKET: Int
    val KEY_SEMICOLON: Int
    val KEY_APOSTROPHE: Int
    val KEY_GRAVE: Int
    val KEY_BACKSLASH: Int
    val KEY_COMMA: Int
    val KEY_PERIOD: Int
    val KEY_SLASH: Int
    val KEY_MULTIPLY: Int
    val KEY_SPACE: Int
    val KEY_CAPITAL: Int
    val KEY_LEFT: Int
    val KEY_UP: Int
    val KEY_RIGHT: Int
    val KEY_DOWN: Int
    val KEY_NUMLOCK: Int
    val KEY_SCROLL: Int
    val KEY_SUBTRACT: Int
    val KEY_ADD: Int
    val KEY_DIVIDE: Int
    val KEY_DECIMAL: Int
    val KEY_NUMPAD0: Int
    val KEY_NUMPAD1: Int
    val KEY_NUMPAD2: Int
    val KEY_NUMPAD3: Int
    val KEY_NUMPAD4: Int
    val KEY_NUMPAD5: Int
    val KEY_NUMPAD6: Int
    val KEY_NUMPAD7: Int
    val KEY_NUMPAD8: Int
    val KEY_NUMPAD9: Int
    val KEY_A: Int
    val KEY_B: Int
    val KEY_C: Int
    val KEY_D: Int
    val KEY_E: Int
    val KEY_F: Int
    val KEY_G: Int
    val KEY_H: Int
    val KEY_I: Int
    val KEY_J: Int
    val KEY_K: Int
    val KEY_L: Int
    val KEY_M: Int
    val KEY_N: Int
    val KEY_O: Int
    val KEY_P: Int
    val KEY_Q: Int
    val KEY_R: Int
    val KEY_S: Int
    val KEY_T: Int
    val KEY_U: Int
    val KEY_V: Int
    val KEY_W: Int
    val KEY_X: Int
    val KEY_Y: Int
    val KEY_Z: Int
    val KEY_0: Int
    val KEY_1: Int
    val KEY_2: Int
    val KEY_3: Int
    val KEY_4: Int
    val KEY_5: Int
    val KEY_6: Int
    val KEY_7: Int
    val KEY_8: Int
    val KEY_9: Int
    val KEY_F1: Int
    val KEY_F2: Int
    val KEY_F3: Int
    val KEY_F4: Int
    val KEY_F5: Int
    val KEY_F6: Int
    val KEY_F7: Int
    val KEY_F8: Int
    val KEY_F9: Int
    val KEY_F10: Int
    val KEY_F11: Int
    val KEY_F12: Int
    val KEY_F13: Int
    val KEY_F14: Int
    val KEY_F15: Int
    val KEY_F16: Int
    val KEY_F17: Int
    val KEY_F18: Int
    val KEY_F19: Int
    val KEY_DELETE: Int
    val KEY_HOME: Int
    val KEY_END: Int

    val shiftKeyDown: Boolean
    val ctrlKeyDown: Boolean
    val altKeyDown: Boolean

    val modifiers: Modifiers

    fun isKeyDown(key: Int): Boolean


    data class Modifiers(val isCtrl: Boolean, val isShift: Boolean, val isAlt: Boolean)
}