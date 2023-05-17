package com.hj.leetcode.kotlin.problem2130

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [2130. Maximum Twin Sum of a Linked List](https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the size of head;
     */
    fun pairSum(head: ListNode?): Int {
        // Collect all values in the linkedList then compute all twin sums and return the maximum
        return maxTwinSum(head.values())
    }

    private fun ListNode?.values(): List<Int> {
        val result = mutableListOf<Int>()
        linearTraversal { node -> result.add(node.`val`) }
        return result
    }

    private fun ListNode?.linearTraversal(onEachNode: (node: ListNode) -> Unit) {
        var currentNode = this

        while (currentNode != null) {
            onEachNode(currentNode)
            currentNode = currentNode.next
        }
    }

    private fun maxTwinSum(values: List<Int>): Int {
        require(values.isNotEmpty())
        require(values.size.isEven())

        // Compute all twin sums and return the maximum
        var maxTwinSum = values[0] + values[values.lastIndex]
        for (index in 0 until (values.size / 2)) {
            val value = values[index]
            val twinValue = values[values.lastIndex - index]
            val twinSum = value + twinValue

            if (twinSum > maxTwinSum) {
                maxTwinSum = twinSum
            }
        }
        return maxTwinSum
    }

    private fun Int.isEven(): Boolean = this and 1 == 0
}