package dev.airdead.common.element

import dev.airdead.common.misc.Rotation
import dev.airdead.common.misc.location.V3

/**
 * Represents a basic element.
 */
interface Element {

    /**
     * Element size.
     */
    var size: V3

    /**
     * The alignment of the element.
     */
    var align: V3

    /**
     * The origin point of the element.
     */
    var origin: V3

    /**
     * The offset of the element.
     */
    var offset: V3

    /**
     * The rotation of the element.
     */
    var rotation: Rotation

}