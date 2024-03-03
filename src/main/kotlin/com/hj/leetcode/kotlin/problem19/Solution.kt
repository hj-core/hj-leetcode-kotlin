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
        val indexToRemove = head.size() - n
        if (indexToRemove == 0) {
            val newHead = head?.next
            head?.next = null

            return newHead
        }

        val nodeBefore = head.getOrNull(indexToRemove - 1)
        val nodeToRemove = nodeBefore?.next
        nodeBefore?.next = nodeToRemove?.next
        nodeToRemove?.next = null
        return head
    }

    private fun ListNode?.size(): Int {
        var result = 0
        var ptr = this

        while (ptr != null) {
            result++
            ptr = ptr.next
        }
        return result
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