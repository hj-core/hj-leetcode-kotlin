package com.hj.leetcode.kotlin.problem725

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [725. Split Linked List in Parts](https://leetcode.com/problems/split-linked-list-in-parts/);
 */
class Solution {
    /* Complexity:
     * Time O(N+k) and Space O(k) where N is the number of nodes in head;
     */
    fun splitListToParts(head: ListNode?, k: Int): Array<ListNode?> {
        val length = head.length()
        val averageLength = length / k
        val numLongerParts = length % k

        val result = Array<ListNode?>(k) { null }
        var partHead = head

        repeat(k) { index ->
            result[index] = partHead
            val partLength = if (index < numLongerParts) averageLength + 1 else averageLength
            val partTail = partTail(partHead, partLength)

            partHead = partTail?.next
            partTail?.next = null
        }
        return result
    }

    private fun ListNode?.length(): Int {
        var result = 0
        var next = this
        while (next != null) {
            result += 1
            next = next.next
        }
        return result
    }

    private fun partTail(partHead: ListNode?, partLength: Int): ListNode? {
        var result = partHead
        repeat(partLength - 1) {
            result = result?.next
        }
        return result
    }
}