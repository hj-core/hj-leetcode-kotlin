package com.hj.leetcode.kotlin.problem1594

/**
 * LeetCode page: [1594. Maximum Non Negative Product in a Matrix](https://leetcode.com/problems/maximum-non-negative-product-in-a-matrix/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(N) where M and N are the number of rows and
    // columns of grid, respectively.
    fun maxProductPath(grid: Array<IntArray>): Int {
        // dp[c]@r:= the min and max product to arrive at grid[r][c]
        val dp = Array(grid[0].size) { LongArray(2) }

        // Base case where r = 0
        dp[0][0] = grid[0][0].toLong()
        dp[0][1] = dp[0][0]
        for (c in 1..<grid[0].size) {
            dp[c][0] = dp[c - 1][0] * grid[0][c]
            dp[c][1] = dp[c][0]
        }

        // Compute dp values for the remaining rows
        for (r in 1..<grid.size) {
            dp[0][0] *= grid[r][0]
            dp[0][1] = dp[0][0]
            for (c in 1..<grid[0].size) {
                val v = grid[r][c]
                val minFrom = minOf(dp[c - 1][0], dp[c][0])
                val maxFrom = maxOf(dp[c - 1][1], dp[c][1])
                if (v < 0) {
                    dp[c][0] = v * maxFrom
                    dp[c][1] = v * minFrom
                } else {
                    dp[c][0] = v * minFrom
                    dp[c][1] = v * maxFrom
                }
            }
        }

        val maxProduct = dp[grid[0].size - 1][1]
        if (maxProduct < 0) {
            return -1
        }
        return (maxProduct % 1000000007).toInt()
    }
}
