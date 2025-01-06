package com.hj.leetcode.kotlin.problem1769

/**
 * LeetCode page: [1769. Minimum Number of Operations to Move All Balls to Each Box](https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Auxiliary Space O(1) where N is the length of boxes.
     */
    fun minOperations(boxes: String): IntArray {
        val result = IntArray(boxes.length)
        // Forward pass to account for moving ones in the left
        var leftOnes = 0
        var leftCosts = 0
        for (index in boxes.indices) {
            leftCosts += leftOnes
            result[index] += leftCosts
            leftOnes += if (boxes[index] == '0') 0 else 1
        }
        // Backward pass to account for moving ones in the right
        var rightOnes = 0
        var rightCosts = 0
        for (index in boxes.indices.reversed()) {
            rightCosts += rightOnes
            result[index] += rightCosts
            rightOnes += if (boxes[index] == '0') 0 else 1
        }
        return result
    }
}
