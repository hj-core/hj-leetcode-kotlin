package com.hj.leetcode.kotlin.problem146

/**
 * LeetCode page: [146. LRU Cache](https://leetcode.com/problems/lru-cache/);
 */
class LRUCache(private val capacity: Int) {

    private val valueIfKeyNotExists = -1
    private val keyNodes = hashMapOf<Int, ListNode>() // entry = (key, node)
    private var mostRecentNode: ListNode? = null
    private var leastRecentNode: ListNode? = null

    private class ListNode(val key: Int, var value: Int) {
        var previous: ListNode? = null
            private set
        var next: ListNode? = null
            private set

        fun linkNext(node: ListNode?) {
            next = node
            node?.previous = this
        }

        fun detach() {
            previous?.next = next
            next?.previous = previous
            previous = null
            next = null
        }
    }

    fun get(key: Int): Int {
        val value = keyNodes[key]?.value ?: valueIfKeyNotExists
        setRecent(key)
        return value
    }

    private fun setRecent(key: Int) {
        val keyNode = keyNodes[key] ?: return
        if (keyNode == mostRecentNode) {
            return
        }
        if (keyNode == leastRecentNode) {
            leastRecentNode = checkNotNull(keyNode.previous)
        }
        keyNode.detach()
        keyNode.linkNext(mostRecentNode)
        mostRecentNode = keyNode
    }

    fun put(key: Int, value: Int) {
        if (key in keyNodes) {
            val keyNode = checkNotNull(keyNodes[key])
            keyNode.value = value
            setRecent(key)
        } else {
            val keyNode = ListNode(key, value).apply {
                linkNext(mostRecentNode)
            }
            keyNodes[key] = keyNode
            mostRecentNode = keyNode

            if (leastRecentNode == null) {
                leastRecentNode = keyNode
            }
        }
        ensureCapacityUsage()
    }

    private fun ensureCapacityUsage() {
        while (capacity < keyNodes.size) {
            val removedNode = keyNodes.remove(checkNotNull(leastRecentNode).key)
            checkNotNull(removedNode)
            leastRecentNode = removedNode.previous
            removedNode.detach()
        }
    }
}