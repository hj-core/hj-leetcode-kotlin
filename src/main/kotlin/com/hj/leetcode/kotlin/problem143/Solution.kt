package com.hj.leetcode.kotlin.problem143

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [143. Reorder List](https://leetcode.com/problems/reorder-list/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of head;
     */
    fun reorderList(head: ListNode?): Unit {
        var ptr1 = head
        var ptr2 = firstOfSecondHalf(head).reversed()

        while (ptr2 != null) {
            checkNotNull(ptr1)
            val next1 = ptr1.next
            val next2 = ptr2.next
            ptr1.next = ptr2
            ptr2.next = next1

            ptr1 = next1
            ptr2 = next2
        }

        // Break the original linking of middle node if head has an odd size
        if (ptr1 != null) {
            ptr1.next = null
        }
    }

    /**
     * Return the node at index ceil(head.size/2).
     */
    private fun firstOfSecondHalf(head: ListNode?): ListNode? {
        var result = head
        var fast = head

        while (fast != null) {
            result = result?.next
            fast = fast.next?.next
        }
        return result
    }

    private fun ListNode?.reversed(): ListNode? {
        var result = this
        var tail: ListNode? = null

        while (result?.next != null) {
            val next = result.next
            result.next = tail
            tail = result
            result = next
        }
        result?.next = tail
        return result
    }
}