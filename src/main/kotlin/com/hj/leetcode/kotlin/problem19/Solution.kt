package com.hj.leetcode.kotlin.problem19

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [19. Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the number of nodes in head;
     */
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        val totalNodes = countNodes(head)
        val removePositionFromStart = totalNodes - n + 1

        val isRemovingHead = removePositionFromStart == 1
        if (isRemovingHead) return head?.next

        val nodeBefore = head.getNthNode(removePositionFromStart - 1)
        nodeBefore?.next = nodeBefore?.next?.next
        return head
    }

    private fun countNodes(head: ListNode?): Int {
        var count = 0
        var currNode = head

        while (currNode != null) {
            count++
            currNode = currNode.next
        }
        return count
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