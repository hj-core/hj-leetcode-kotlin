package com.hj.leetcode.kotlin.problem1290

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [1290. Convert Binary Number in a Linked List to Integer](https://leetcode.com/problems/convert-binary-number-in-a-linked-list-to-integer/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the number of nodes
    // in head.
    fun getDecimalValue(head: ListNode?): Int {
        var result = 0
        var node = head

        while (node != null) {
            result = (result shl 1) xor node.`val`
            node = node.next
        }
        return result
    }
}
