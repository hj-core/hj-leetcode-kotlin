package com.hj.leetcode.kotlin.problem1669

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [1669. Merge In Between Linked Lists](https://leetcode.com/problems/merge-in-between-linked-lists/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(1) where N is the size of list1
     * and M is the size of list2;
     */
    fun mergeInBetween(list1: ListNode?, a: Int, b: Int, list2: ListNode?): ListNode? {
        requireNotNull(list1)
        requireNotNull(list2)

        var slow = list1
        var fast = list1

        repeat(b - (a - 1)) {
            fast = fast?.next
        }
        repeat(a - 1) {
            slow = slow?.next
            fast = fast?.next
        } // now slow is at index a-1 and fast is at index b

        val tail2 = list2.tail()
        slow?.next = list2
        tail2.next = fast?.next
        fast?.next = null
        return list1
    }

    private fun ListNode.tail(): ListNode {
        var result = this
        while (result.next != null) {
            result = checkNotNull(result.next)
        }
        return result
    }
}