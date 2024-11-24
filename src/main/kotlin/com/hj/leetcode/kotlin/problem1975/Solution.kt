package com.hj.leetcode.kotlin.problem1975

import kotlin.math.abs
import kotlin.math.min

/**
 * LeetCode page: [1975. Maximum Matrix Sum](https://leetcode.com/problems/maximum-matrix-sum/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(1) where N is the number of rows and columns in matrix.
     */
    fun maxMatrixSum(matrix: Array<IntArray>): Long {
        // We can change any value pair (a, b) to (-a, -b) without affecting others
        var countNegatives = 0
        var sumAbs = 0L
        var minAbs = abs(matrix[0][0])

        for (row in matrix) {
            for (value in row) {
                if (value < 0) {
                    countNegatives++
                }
                val absValue = abs(value)
                sumAbs += absValue
                minAbs = min(minAbs, absValue)
            }
        }
        return if (countNegatives % 2 == 0) sumAbs else sumAbs - (2 * minAbs)
    }
}
