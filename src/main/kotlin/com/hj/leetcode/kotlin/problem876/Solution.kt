package com.hj.leetcode.kotlin.problem876

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [876. Middle of the Linked List](https://leetcode.com/problems/middle-of-the-linked-list/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the number of nodes in head;
     */
    fun middleNode(head: ListNode?): ListNode? {
        var slow = head
        var fast = head

        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next?.next
        }
        return slow
    }
}