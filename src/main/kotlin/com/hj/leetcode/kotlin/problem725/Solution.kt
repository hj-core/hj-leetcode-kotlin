package com.hj.leetcode.kotlin.problem725

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [725. Split Linked List in Parts](https://leetcode.com/problems/split-linked-list-in-parts/);
 */
class Solution {
    /* Complexity:
     * Time O(N+k) and Space O(k) where N is the number of nodes in head;
     */
    fun splitListToParts(
        head: ListNode?,
        k: Int,
    ): Array<ListNode?> {
        val size = head.size()
        val averageLength = size / k
        val shortage = size % k

        val result = Array<ListNode?>(k) { null }
        var partHead = head

        for (index in 0..<k) {
            if (partHead == null) {
                break
            }
            result[index] = partHead
            val partLength = if (index < shortage) averageLength + 1 else averageLength
            val partTail = partHead.get(partLength - 1)
            partHead = partTail.next
            partTail.next = null
        }
        return result
    }

    private fun ListNode?.size(): Int {
        var result = 0
        var nodePtr = this
        while (nodePtr != null) {
            result += 1
            nodePtr = nodePtr.next
        }
        return result
    }

    private fun ListNode.get(index: Int): ListNode {
        var result = this
        repeat(index) {
            result = result.next ?: throw IndexOutOfBoundsException()
        }
        return result
    }
}
