package com.hj.leetcode.kotlin.problem1171

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [1171. Remove Zero Sum Consecutive Nodes from Linked List](https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the number of nodes in head;
     */
    fun removeZeroSumSublists(head: ListNode?): ListNode? {
        requireNotNull(head)
        val resultNodes = mutableListOf<ListNode>()
        val prefixSums = mutableSetOf<Int>()
        var currentNode = head
        var previousPrefixSum = 0

        while (currentNode != null) {
            val currentPrefixSum = currentNode.`val` + previousPrefixSum
            when (currentPrefixSum) {
                0 -> {
                    resultNodes.clear()
                    prefixSums.clear()
                }

                in prefixSums -> {
                    while (previousPrefixSum != currentPrefixSum) {
                        prefixSums.remove(previousPrefixSum)
                        previousPrefixSum -= resultNodes.last().`val`
                        resultNodes.removeLast()
                    }
                }

                else -> {
                    resultNodes.add(currentNode)
                    prefixSums.add(currentPrefixSum)
                }
            }
            previousPrefixSum = currentPrefixSum
            currentNode = currentNode.next
        }

        for (i in resultNodes.indices) {
            resultNodes[i].next = resultNodes.getOrNull(i + 1)
        }
        return resultNodes.firstOrNull()
    }
}