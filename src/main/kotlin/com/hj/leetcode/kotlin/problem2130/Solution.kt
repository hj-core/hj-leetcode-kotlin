package com.hj.leetcode.kotlin.problem2130

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [2130. Maximum Twin Sum of a Linked List](https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(N) where N is the length of head.
    fun pairSum(head: ListNode?): Int {
        val values = head.values()
        return (0..<values.size / 2).maxOf { values[it] + values[values.lastIndex - it] }
    }

    private fun ListNode?.values(): List<Int> {
        val values = mutableListOf<Int>()
        var node = this
        while (node != null) {
            values.add(node.`val`)
            node = node.next
        }

        return values
    }
}
