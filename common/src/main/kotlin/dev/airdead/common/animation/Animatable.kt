package dev.airdead.common.animation

import dev.airdead.common.element.RendererElement
import dev.airdead.common.Color
import dev.airdead.common.math.Rotation
import dev.airdead.common.math.V2
import dev.airdead.common.math.V3

/**
 * Annotate fields in your [RendererElement] class to applie animation to it
 *
 * You can annotate only field with type: [V2], [V3], [Color], [Rotation]
 */
@Suppress("unused", "SpellCheckingInspection")
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class Animatable
