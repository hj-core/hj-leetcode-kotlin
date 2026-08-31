package com.hj.leetcode.kotlin.problem2058

import com.hj.leetcode.kotlin.common.model.ListNode

/**
 * LeetCode page: [2058. Find the Minimum and Maximum Number of Nodes Between Critical Points](https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of head.
    fun nodesBetweenCriticalPoints(head: ListNode?): IntArray {
        if (head == null) {
            return intArrayOf(-1, -1)
        }

        var firstCritical = Int.MAX_VALUE
        var lastCritical = -1
        var minDistance = Int.MAX_VALUE
        var prevValue = head.`val`
        var currNode = head
        var currIdx = 0
        while (currNode != null) {
            if (isCritical(currNode, prevValue)) {
                firstCritical = minOf(firstCritical, currIdx)
                if (lastCritical != -1) {
                    minDistance = minOf(minDistance, currIdx - lastCritical)
                }
                lastCritical = currIdx
            }

            prevValue = currNode.`val`
            currNode = currNode.next
            currIdx++
        }

        if (lastCritical <= firstCritical) {
            return intArrayOf(-1, -1)
        }
        return intArrayOf(minDistance, lastCritical - firstCritical)
    }

    private fun isCritical(
        currNode: ListNode,
        prevValue: Int,
    ): Boolean {
        val nextValue = currNode.next?.`val` ?: return false
        val currValue = currNode.`val`
        return (prevValue < currValue && currValue > nextValue) ||
            (prevValue > currValue && currValue < nextValue)
    }
}
