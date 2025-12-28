package com.hj.leetcode.kotlin.problem1351

/**
 * LeetCode page: [1351. Count Negative Numbers in a Sorted Matrix](https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/);
 */
class Solution {
    // Complexity:
    // Time O(M+N) and Space O(1) where M and N are the number of
    // rows and columns of grid, respectively.
    fun countNegatives(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size

        var result = 0
        var c = 0

        for (r in m - 1 downTo 0) {
            while (c < n && 0 <= grid[r][c]) {
                c++
            }

            result += n - c
        }

        return result
    }
}
