package com.hj.leetcode.kotlin.problem61

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [61. Rotate List](https://leetcode.com/problems/rotate-list/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the number of nodes in head.
    fun rotateRight(
        head: ListNode?,
        k: Int,
    ): ListNode? {
        val (size, last) = getSizeAndLast(head)
        if (size == 0 || size == 1) {
            return head
        }

        val k = k % size
        if (k == 0) {
            return head
        }

        checkNotNull(last).next = head
        val newLast = checkNotNull(getNode(head, size - k - 1))
        val newHead = checkNotNull(newLast.next)
        newLast.next = null

        return newHead
    }

    private fun getSizeAndLast(head: ListNode?): Pair<Int, ListNode?> {
        if (head == null) {
            return Pair(0, null)
        }

        var size = 1
        var last: ListNode = head
        while (last.next != null) {
            size++
            last = checkNotNull(last.next)
        }

        return Pair(size, last)
    }

    private fun getNode(
        head: ListNode?,
        index: Int,
    ): ListNode? {
        var node = head
        var i = 0
        while (i < index && node != null) {
            i++
            node = node.next
        }

        return node
    }
}
