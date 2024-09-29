package com.hj.leetcode.kotlin.problem432

/**
 * LeetCode page: [432. All O`one Data Structure](https://leetcode.com/problems/all-oone-data-structure/);
 */
class AllOne {
    private class Node(
        var count: Int,
        val keys: MutableSet<String>,
        var prev: Node?,
        var next: Node?,
    )

    private val keyToNode = mutableMapOf<String, Node>()
    private val head = Node(0, mutableSetOf(), null, null)
    private var tail = head

    fun inc(key: String) {
        val oldNode = keyToNode[key] ?: head
        val newNode = computeCountPlusOne(oldNode)

        keyToNode[key] = newNode
        oldNode.keys.remove(key)
        newNode.keys.add(key)

        if (oldNode != head && oldNode.keys.isEmpty()) {
            detach(oldNode)
        }
    }

    private fun computeCountPlusOne(node: Node): Node {
        if (node.next?.count == node.count + 1) {
            return checkNotNull(node.next)
        }
        val newNode = Node(node.count + 1, mutableSetOf(), node, node.next)
        node.next?.prev = newNode
        node.next = newNode
        if (node == tail) {
            tail = newNode
        }
        return newNode
    }

    private fun detach(node: Node): Node {
        require(node != head)
        require(node.keys.isEmpty())
        val prev = checkNotNull(node.prev)
        val next = node.next
        prev.next = next
        next?.prev = prev
        if (node == tail) {
            tail = prev
        }
        return node
    }

    fun dec(key: String) {
        val oldNode = keyToNode[key] ?: head
        val newNode = computeCountMinusOne(oldNode)

        keyToNode[key] = newNode
        oldNode.keys.remove(key)
        newNode.keys.add(key)

        if (oldNode != head && oldNode.keys.isEmpty()) {
            detach(oldNode)
        }

        if (newNode == head) {
            head.keys.remove(key)
            keyToNode.remove(key)
        }
    }

    private fun computeCountMinusOne(node: Node): Node {
        require(node != head)
        if (node.prev?.count == node.count - 1) {
            return checkNotNull(node.prev)
        }
        val newNode = Node(node.count - 1, mutableSetOf(), node.prev, node)
        node.prev?.next = newNode
        node.prev = newNode
        return newNode
    }

    fun getMaxKey(): String {
        if (isEmpty()) {
            return ""
        }
        return tail.keys.first()
    }

    private fun isEmpty(): Boolean = keyToNode.isEmpty()

    fun getMinKey(): String {
        if (isEmpty()) {
            return ""
        }
        return checkNotNull(head.next).keys.first()
    }
}
