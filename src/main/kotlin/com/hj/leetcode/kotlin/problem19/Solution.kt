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
        val totalNodes = head.size()
        val removePositionFromStart = totalNodes - n + 1

        val isRemovingHead = removePositionFromStart == 1
        if (isRemovingHead) return head?.next

        val nodeBefore = head.getOrNull(removePositionFromStart - 2)
        nodeBefore?.next = nodeBefore?.next?.next
        return head
    }

    private fun ListNode?.size(): Int {
        var count = 0
        var currNode = this

        while (currNode != null) {
            count++
            currNode = currNode.next
        }
        return count
    }

    private fun ListNode?.getOrNull(index: Int): ListNode? {
        var result = this
        var i = 0

        while (i < index && result != null) {
            result = result.next
            i++
        }
        return result
    }
}