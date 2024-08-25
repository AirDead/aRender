package dev.airdead.common.element

/**
 * Interface representing a parent element.
 */
interface ParentElement : Element {

    var lastParent: Element?

    /**
     * The list of child elements.
     */
    val children: MutableList<Element>

    /**
     * Adds multiple child elements to the parent.
     *
     * @param elements The child elements to be added.
     */
    fun addChild(vararg elements: Element) {
        children.addAll(elements)
        lastParent = elements.last()
    }

    /**
     * Removes multiple child elements from the parent.
     *
     * @param elements The child elements to be removed.
     */
    fun removeChild(vararg elements: Element) {
        children.removeAll(elements.toSet())
    }

    /**
     * Adds a child element to the parent using the unary plus operator.
     *
     * @param T The type of the child element.
     *
     * @return The added child element.
     */
    operator fun <T : Element> T.unaryPlus(): T {
        addChild(this)

        return this
    }

    /**
     * Adds a child element to the parent using the plus operator.
     *
     * @param T The type of the child element.
     * @param element The child element to be added.
     *
     * @return The added child element.
     */
    operator fun <T : Element> plus(element: T): T {
        addChild(element)

        return element
    }
}