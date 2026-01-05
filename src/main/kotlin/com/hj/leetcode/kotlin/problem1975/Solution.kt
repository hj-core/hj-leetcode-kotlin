package com.hj.leetcode.kotlin.problem1975

import kotlin.math.abs

/**
 * LeetCode page: [1975. Maximum Matrix Sum](https://leetcode.com/problems/maximum-matrix-sum/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(1) where N is the number of rows and
    // columns in matrix.
    fun maxMatrixSum(matrix: Array<IntArray>): Long {
        // We can negate any pair of cells without affecting others
        // by applying operations along a path connecting those two
        // cells. The total number of changed cells is always even.
        var sumAbs = 0L
        var minAbs = abs(matrix[0][0])
        var countNegatives = 0

        for (row in matrix) {
            for (value in row) {
                val absValue = abs(value)
                sumAbs += absValue
                minAbs = minOf(minAbs, absValue)

                if (value < 0) {
                    countNegatives++
                }
            }
        }

        if (countNegatives and 1 == 1) {
            sumAbs -= 2 * minAbs
        }
        return sumAbs
    }
}
