package com.hj.leetcode.kotlin.problem1721

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [1721. Swapping Nodes in a Linked List](https://leetcode.com/problems/swapping-nodes-in-a-linked-list/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in head;
     */
    fun swapNodes(head: ListNode?, k: Int): ListNode? {
        if (head == null) {
            return null
        }

        return head
            .split()
            .apply { swap(k - 1, size - k) }
            .link()
    }

    /**
     * Remove links between nodes and return a collection of the nodes in their original order.
     */
    private fun ListNode.split(): MutableList<ListNode> {
        val result = mutableListOf<ListNode>()
        var currentNode: ListNode? = this

        while (currentNode != null) {
            result.add(currentNode)
            val nextNode = currentNode.next
            currentNode.next = null
            currentNode = nextNode
        }
        return result
    }

    private fun <T> MutableList<T>.swap(index: Int, withIndex: Int): MutableList<T> {
        this[index] = this[withIndex].also { this[withIndex] = this[index] }
        return this
    }

    /**
     * Link the nodes according to their order in the list and return the head.
     */
    private fun List<ListNode>.link(): ListNode {
        for ((index, node) in this.withIndex()) {
            node.next = this.getOrNull(index + 1)
        }
        return this.first()
    }
}