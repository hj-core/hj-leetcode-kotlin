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
        var pairParent = dummyHead

        // While there is a pair, we swap them and update parent for the next pair
        while (pairParent.next != null && pairParent.next?.next != null) {
            val pairFirst = checkNotNull(pairParent.next)
            val pairSecond = checkNotNull(pairFirst.next)

            pairParent.next = pairSecond
            pairFirst.next = pairSecond.next
            pairSecond.next = pairFirst
            pairParent = pairFirst
        }

        // Return the new head
        return dummyHead.next
    }
}