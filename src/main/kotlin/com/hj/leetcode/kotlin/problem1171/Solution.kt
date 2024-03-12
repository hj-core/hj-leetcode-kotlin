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
        val prefixSums = mutableListOf<Int>()
        val prefixSumsSet = mutableSetOf<Int>()
        var currentNode = head

        while (currentNode != null) {
            val currentSum = currentNode.`val` + (prefixSums.lastOrNull() ?: 0)
            when (currentSum) {
                0 -> {
                    resultNodes.clear()
                    prefixSums.clear()
                    prefixSumsSet.clear()
                }

                in prefixSumsSet -> {
                    while (prefixSums.last() != currentSum) {
                        resultNodes.removeLast()
                        prefixSumsSet.remove(prefixSums.last())
                        prefixSums.removeLast()
                    }
                }

                else -> {
                    resultNodes.add(currentNode)
                    prefixSums.add(currentSum)
                    prefixSumsSet.add(currentSum)
                }
            }
            currentNode = currentNode.next
        }

        for (i in resultNodes.indices) {
            resultNodes[i].next = resultNodes.getOrNull(i + 1)
        }
        return resultNodes.firstOrNull()
    }
}