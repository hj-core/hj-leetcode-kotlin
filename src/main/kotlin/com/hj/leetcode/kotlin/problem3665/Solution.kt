package com.hj.leetcode.kotlin.problem3665

/**
 * LeetCode page: [3665. Twisted Mirror Path Count](https://leetcode.com/problems/twisted-mirror-path-count/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(N) where M and N are the number of rows and
    // columns of grid, respectively.
    fun uniquePaths(grid: Array<IntArray>): Int {
        val modulo = 1_000_000_007
        val m = grid.size
        val n = grid[0].size
        // dp[j]@i:= the number of ways to arrive cell(i, j) from the left and the top
        val dp = Array(n + 1) { intArrayOf(0, 0) }
        dp[0] = intArrayOf(0, 1)
        for (i in 0..<m) {
            for (j in 0..<n) {
                if (grid[i][j] == 0) {
                    val newWays = dp[j].sum() % modulo
                    dp[j + 1][0] = newWays
                    dp[j][1] = newWays
                } else {
                    dp[j + 1][0] = dp[j][1]
                    dp[j][1] = dp[j][0]
                }
            }
        }
        return dp[n][0]
    }
}
