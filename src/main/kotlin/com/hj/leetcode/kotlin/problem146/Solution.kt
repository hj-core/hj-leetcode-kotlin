package com.hj.leetcode.kotlin.problem146

/**
 * LeetCode page: [146. LRU Cache](https://leetcode.com/problems/lru-cache/);
 */
class Solution

private class LRUCache(
    val capacity: Int,
) {
    private val keyNodes = hashMapOf<Int, Node>()
    private val sentinel =
        Node(-1, -1, null, null).apply {
            next = this
            prev = this
        }

    private class Node(
        val key: Int,
        var value: Int,
        var next: Node? = null,
        var prev: Node? = null,
    )

    // Complexity:
    // Time O(1) and Space O(1);
    fun get(key: Int): Int {
        val node = keyNodes[key] ?: return -1
        popNode(node)
        pushFront(node)
        return node.value
    }

    // Complexity for N calls:
    // Time O(N) and Space O(N).
    fun put(
        key: Int,
        value: Int,
    ) {
        if (key in keyNodes) {
            val node = keyNodes[key]!!
            node.value = value
            popNode(node)
            pushFront(node)
            return
        }

        if (keyNodes.size == capacity) {
            val evicted = popBack()
            keyNodes.remove(evicted.key)
        }
        val newNode = Node(key, value, null, null)
        keyNodes[key] = newNode
        pushFront(newNode)
    }

    // Pops the given node from the list without modifying
    // the keyNodes.
    private fun popNode(node: Node): Node {
        if (node === sentinel) {
            throw IllegalArgumentException("Cannot pop sentinel node")
        }
        if (node !== keyNodes[node.key]) {
            throw IllegalArgumentException("Cannot pop a node not in the list")
        }
        node.prev?.next = node.next
        node.next?.prev = node.prev
        node.next = null
        node.prev = null
        return node
    }

    // Pushes the given node to the front of the list without
    // modifying the keyNodes.
    private fun pushFront(node: Node) {
        sentinel.next?.prev = node
        node.next = sentinel.next
        node.prev = sentinel
        sentinel.next = node
    }

    // Pops the last node from the list without modifying
    // the keyNodes.
    private fun popBack(): Node {
        if (sentinel.next === sentinel) {
            throw NoSuchElementException("Cannot pop from empty list")
        }
        val node = sentinel.prev!!
        popNode(node)
        return node
    }
}
