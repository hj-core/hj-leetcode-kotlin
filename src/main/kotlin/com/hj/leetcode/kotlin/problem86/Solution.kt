package com.hj.leetcode.kotlin.problem86

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [86. Partition List](https://leetcode.com/problems/partition-list/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the number of nodes in head;
     */
    fun partition(head: ListNode?, x: Int): ListNode? {
        val leftHead = ListNode(Int.MIN_VALUE) // left.values < x
        var leftTail = leftHead
        val rightHead = ListNode(Int.MAX_VALUE) // right.values >= x
        var rightTail = rightHead

        var next = head
        while (next != null) {
            val current = next
            next = next.next
            current.next = null

            if (current.`val` < x) {
                leftTail.next = current
                leftTail = current
            } else {
                rightTail.next = current
                rightTail = current
            }
        }

        leftTail.next = rightHead.next
        return leftHead.next
    }
}