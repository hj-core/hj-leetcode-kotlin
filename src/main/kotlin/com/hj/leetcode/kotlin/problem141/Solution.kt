package com.hj.leetcode.kotlin.problem141

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [141. Linked List Cycle](https://leetcode.com/problems/linked-list-cycle/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the number of nodes in head;
     */
    fun hasCycle(head: ListNode?): Boolean {
        var slow = head
        var fast = head?.next

        while (fast != null && fast != slow) {
            slow = slow?.next
            fast = fast.next?.next
        }
        return fast != null
    }
}