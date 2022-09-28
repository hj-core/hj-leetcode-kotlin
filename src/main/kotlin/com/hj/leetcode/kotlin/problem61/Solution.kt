package com.hj.leetcode.kotlin.problem61

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [61. Rotate List](https://leetcode.com/problems/rotate-list/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the number of nodes in head;
     */
    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        val (tail, numNodes) = head.getTailAndNumberOfNodes()
        if (numNodes == 0) return null

        val netShift = k % numNodes
        if (netShift == 0) return head

        val newHeadPosition = numNodes - netShift + 1
        val nodeBeforeNewHead = head.getNthNode(newHeadPosition - 1)
        val newHead = nodeBeforeNewHead?.next

        nodeBeforeNewHead?.next = null
        tail?.next = head
        return newHead
    }

    private fun ListNode?.getTailAndNumberOfNodes(): Pair<ListNode?, Int> {
        if (this == null) return Pair(null, 0)

        var tail = this
        var numNodes = 1

        while (tail?.next != null) {
            numNodes++
            tail = tail.next
        }
        return Pair(tail, numNodes)
    }

    private fun ListNode?.getNthNode(n: Int): ListNode? {
        var position = 1
        var node = this

        while (position < n && node != null) {
            position++
            node = node.next
        }
        return node
    }
}