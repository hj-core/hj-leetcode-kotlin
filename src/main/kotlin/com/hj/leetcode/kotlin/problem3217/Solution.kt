package com.hj.leetcode.kotlin.problem3217

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [3217. Delete Nodes From Linked List Present in Array](https://leetcode.com/problems/delete-nodes-from-linked-list-present-in-array/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(M) where N is the number of nodes in head
     * M is the size of nums.
     */
    fun modifiedList(
        nums: IntArray,
        head: ListNode?,
    ): ListNode? {
        if (head == null) {
            return null
        }
        val dummyHead = ListNode(-1).apply { next = head }
        var currentPtr = dummyHead
        val unwantedValues = nums.toSet()

        while (currentPtr.next != null) {
            val next = checkNotNull(currentPtr.next)
            if (next.`val` in unwantedValues) {
                currentPtr.next = next.next
            } else {
                currentPtr = next
            }
        }
        return dummyHead.next
    }
}
