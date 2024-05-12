package com.hj.leetcode.kotlin.problem92

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [92. Reverse Linked List II](https://leetcode.com/problems/reverse-linked-list-ii/);
 */
class Solution2 {
    /* Complexity:
     * Time O(right) and Space O(1);
     */
    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        /* Divide the head into Left->Mid->Right where Mid is the portion to
         * be reversed.
         */
        val dummyHead = ListNode(-1).apply { next = head }
        val leftEndNode = dummyHead.at(left - 1)
        val midHead = checkNotNull(leftEndNode.next)

        leftEndNode.next = midHead.reverse(right - left + 1)
        return dummyHead.next
    }

    private fun ListNode.at(index: Int): ListNode {
        require(index >= 0)

        var result = this
        repeat(index) {
            result = result.next ?: throw IndexOutOfBoundsException()
        }
        return result
    }

    private fun ListNode.reverse(beforeIndex: Int): ListNode {
        var head: ListNode? = this
        var tail: ListNode? = null
        var index = 0
        while (head != null && index < beforeIndex) {
            val next = head.next
            head.next = tail
            tail = head
            head = next
            index++
        }
        this.next = head
        return checkNotNull(tail)
    }
}