package com.hj.leetcode.kotlin.problem206

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [206. Reverse Linked List](https://leetcode.com/problems/reverse-linked-list/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of head;
     */
    fun reverseList(head: ListNode?): ListNode? {
        if (head == null) {
            return null
        }

        var result: ListNode = head
        var tail: ListNode? = null

        while (result.next != null) {
            val next = result.next
            result.next = tail
            tail = result
            result = checkNotNull(next)
        }
        result.next = tail
        return result
    }
}