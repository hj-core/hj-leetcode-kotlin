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
        val listLength = head.length()
        val minPartLength = listLength / k
        val numLongerParts = listLength % k

        val result = Array<ListNode?>(k) { null }
        var partHead = head

        repeat(k) { index ->
            result[index] = partHead
            val partLength =
                if (index < numLongerParts) minPartLength + 1 else minPartLength
            val partEndNode = partHead?.at(partLength - 1)

            partHead = partEndNode?.next
            partEndNode?.next = null
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

    private fun ListNode.at(index: Int): ListNode {
        var result = this
        repeat(index) {
            result = result.next ?: throw IndexOutOfBoundsException()
        }
        return result
    }
}