package com.hj.leetcode.kotlin.problem3195

/**
 * LeetCode page: [3195. Find the Minimum Area to Cover All Ones I](https://leetcode.com/problems/find-the-minimum-area-to-cover-all-ones-i/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(1) where M and N are the number
    // of rows and columns in grid, respectively.
    fun minimumArea(grid: Array<IntArray>): Int {
        val top = grid.indexOfFirst { 1 in it }
        val bottom = grid.indexOfLast { 1 in it }

        var left = grid[0].size
        var right = 0
        for (r in bottom downTo top) {
            val newLeft = grid[r].indexOfFirst { it == 1 }
            if (newLeft == -1) {
                continue
            }

            val newRight = grid[r].indexOfLast { it == 1 }
            left = minOf(left, newLeft)
            right = maxOf(right, newRight)
        }
        return (bottom - top + 1) * (right - left + 1)
    }
}
