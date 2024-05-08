package com.hj.leetcode.kotlin.problem2816

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [2816. Double a Number Represented as a Linked List](https://leetcode.com/problems/double-a-number-represented-as-a-linked-list/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the number of nodes in head;
     */
    fun doubleIt(head: ListNode?): ListNode? {
        val result = when {
            head == null -> null
            head.`val` < 5 -> head
            else -> ListNode(0).apply { next = head }
        }

        var nodePtr = result
        while (nodePtr != null) {
            val carry = nodePtr.next
                ?.let { if (it.`val` < 5) 0 else 1 }
                ?: 0
            nodePtr.`val` = (nodePtr.`val` * 2 + carry) % 10
            nodePtr = nodePtr.next
        }
        return result
    }
}