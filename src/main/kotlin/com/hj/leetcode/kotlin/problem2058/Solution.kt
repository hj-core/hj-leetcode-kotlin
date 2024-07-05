package com.hj.leetcode.kotlin.problem2058

import com.hj.leetcode.kotlin.common.model.ListNode
import kotlin.math.min

/**
 * LeetCode page: [2058. Find the Minimum and Maximum Number of Nodes Between Critical Points](https://leetcode.com/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the number of nodes in head;
     */
    fun nodesBetweenCriticalPoints(head: ListNode?): IntArray {
        requireNotNull(head)
        val result = intArrayOf(-1, -1)
        var index = 0
        var firstCriPoint = -1
        var lastCriPoint = -1
        var currentNode: ListNode = head
        var prevValue = head.`val`

        while (currentNode.next != null) {
            val nextValue = checkNotNull(currentNode.next).`val`
            val isCriticalPoint = (prevValue > currentNode.`val` && currentNode.`val` < nextValue)
                    || (prevValue < currentNode.`val` && currentNode.`val` > nextValue)

            if (isCriticalPoint) {
                if (firstCriPoint == -1) {
                    firstCriPoint = index
                    lastCriPoint = index
                } else {
                    result[0] = if (result[0] == -1) {
                        index - lastCriPoint
                    } else {
                        min(result[0], index - lastCriPoint)
                    }
                    result[1] = index - firstCriPoint
                    lastCriPoint = index
                }
            }
            prevValue = currentNode.`val`
            currentNode = checkNotNull(currentNode.next)
            index++
        }
        return result
    }
}