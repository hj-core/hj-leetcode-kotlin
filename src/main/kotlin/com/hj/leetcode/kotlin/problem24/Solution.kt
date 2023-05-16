package com.hj.leetcode.kotlin.problem24

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [24. Swap Nodes in Pairs](https://leetcode.com/problems/swap-nodes-in-pairs/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the number of nodes in head;
     */
    fun swapPairs(head: ListNode?): ListNode? {
        // Create a dummy head that is linked to head
        val dummyHead = ListNode(-1).apply { next = head }

        // Track the parent node of the pair and initialize it with the dummy head
        var parentNode = dummyHead

        // While there is a pair, we swap them and update the parent node for the next pair
        while (hasNextPair(parentNode)) {
            val first = checkNotNull(parentNode.next)
            val second = checkNotNull(first.next)

            parentNode.next = second
            first.next = second.next
            second.next = first
            parentNode = first
        }

        // Return the new head
        return dummyHead.next
    }

    private fun hasNextPair(parentNode: ListNode): Boolean {
        return parentNode.next != null && parentNode.next?.next != null
    }
}