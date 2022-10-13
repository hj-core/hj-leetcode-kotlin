package com.hj.leetcode.kotlin.problem142

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [142. Linked List Cycle II](https://leetcode.com/problems/linked-list-cycle-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the number of nodes in head;
     */
    fun detectCycle(head: ListNode?): ListNode? {
        // Floyd’s Cycle Finding Algorithm
        var slow = head?.next
        var fast = head?.next?.next

        while (fast != null && slow != fast) {
            fast = fast.next?.next
            slow = slow?.next
        }

        val noCycleExist = fast == null
        if (noCycleExist) return null

        slow = head

        while (slow != fast) {
            slow = slow?.next
            fast = fast?.next
        }
        return slow
    }
}