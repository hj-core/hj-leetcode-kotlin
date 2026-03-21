package com.hj.leetcode.kotlin.problem3643

/**
 * LeetCode page: [3643. Flip Square Submatrix Vertically](https://leetcode.com/problems/flip-square-submatrix-vertically/);
 */
class Solution {
    // Complexity:
    // Time O(k^2) and Space O(1).
    fun reverseSubmatrix(
        grid: Array<IntArray>,
        x: Int,
        y: Int,
        k: Int,
    ): Array<IntArray> {
        var r1 = x
        var r2 = x + k - 1
        while (r1 < r2) {
            val row1 = grid[r1]
            val row2 = grid[r2]
            for (c in y..<y + k) {
                row1[c] = row2[c].also { row2[c] = row1[c] }
            }
            r1++
            r2--
        }

        return grid
    }
}
