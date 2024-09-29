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

    private val keyToCount = mutableMapOf<String, Int>()
    private val head = Node(0, mutableSetOf(), null, null)
    private var tail = head
    private val countToNode = mutableMapOf<Int, Node>().apply { this[0] = head }

    fun inc(key: String) {
        val oldCount = keyToCount[key] ?: 0
        val oldNode = checkNotNull(countToNode[oldCount])
        val newNode = computeCountPlusOne(oldNode)

        keyToCount[key] = oldCount + 1
        oldNode.keys.remove(key)
        newNode.keys.add(key)

        if (oldNode != head && oldNode.keys.isEmpty()) {
            detach(oldNode)
        }
    }

    private fun computeCountPlusOne(node: Node): Node {
        if (node.count + 1 in countToNode) {
            return checkNotNull(countToNode[node.count + 1])
        }
        val newNode = Node(node.count + 1, mutableSetOf(), node, node.next)
        node.next?.prev = newNode
        node.next = newNode
        if (node == tail) {
            tail = newNode
        }
        countToNode[newNode.count] = newNode
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
        countToNode.remove(node.count)
        return node
    }

    fun dec(key: String) {
        val oldCount = checkNotNull(keyToCount[key])
        val oldNode = checkNotNull(countToNode[oldCount])
        val newNode = computeCountMinusOne(oldNode)

        keyToCount[key] = oldCount - 1
        oldNode.keys.remove(key)
        newNode.keys.add(key)

        if (oldNode != head && oldNode.keys.isEmpty()) {
            detach(oldNode)
        }

        if (newNode == head) {
            head.keys.remove(key)
            keyToCount.remove(key)
        }
    }

    private fun computeCountMinusOne(node: Node): Node {
        require(node != head)
        if (node.count - 1 in countToNode) {
            return checkNotNull(countToNode[node.count - 1])
        }
        val newNode = Node(node.count - 1, mutableSetOf(), node.prev, node)
        node.prev?.next = newNode
        node.prev = newNode
        countToNode[newNode.count] = newNode
        return newNode
    }

    fun getMaxKey(): String {
        if (isEmpty()) {
            return ""
        }
        return tail.keys.first()
    }

    private fun isEmpty(): Boolean = keyToCount.isEmpty()

    fun getMinKey(): String {
        if (isEmpty()) {
            return ""
        }
        return checkNotNull(head.next).keys.first()
    }
}
