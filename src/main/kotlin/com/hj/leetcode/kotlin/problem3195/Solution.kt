package com.hj.leetcode.kotlin.problem3195

/**
 * LeetCode page: [3195. Find the Minimum Area to Cover All Ones I](https://leetcode.com/problems/find-the-minimum-area-to-cover-all-ones-i/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(1) where M and N are the number
    // of rows and columns in grid, respectively.
    fun minimumArea(grid: Array<IntArray>): Int {
        var top = grid.size
        var bottom = 0
        var left = grid[0].size
        var right = 0

        for (r in grid.indices) {
            val newLeft =
                grid[r]
                    .indices
                    .firstOrNull { grid[r][it] == 1 }
                    ?: continue

            val newRight =
                grid[r]
                    .indices
                    .reversed()
                    .first { grid[r][it] == 1 }

            top = minOf(top, r)
            bottom = maxOf(bottom, r)
            left = minOf(left, newLeft)
            right = maxOf(right, newRight)
        }
        return (bottom - top + 1) * (right - left + 1)
    }
}
