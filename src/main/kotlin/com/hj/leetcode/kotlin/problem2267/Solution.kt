package com.hj.leetcode.kotlin.problem2267

/**
 * LeetCode page: [2267. Check if There Is a Valid Parentheses String Path](https://leetcode.com/problems/check-if-there-is-a-valid-parentheses-string-path/);
 */
class Solution {
    // Complexity:
    // Time O((M+N)MN) and Space O((M+N)N) where M is the number of rows
    // and N is the number of columns.
    fun hasValidPath(grid: Array<CharArray>): Boolean {
        val m = grid.size
        val n = grid[0].size

        if (grid[0][0] != '(' || grid[m - 1][n - 1] != ')') {
            return false
        }

        val pathLen = m + n - 1
        if (pathLen and 1 != 0) {
            return false
        }

        // dp[c][i]@r >> k & 1 := whether there exists a valid path from the
        // bottom-left to (r, c) with (i*64)+k net ')' brackets.
        val dp = Array(n + 1) { LongArray(2) }
        dp[n - 1][0] = 1L // contrived base case r = m
        for (r in m - 1 downTo 0) {
            for (c in n - 1 downTo 0) {
                dp[c][0] = dp[c][0] or dp[c + 1][0]
                dp[c][1] = dp[c][1] or dp[c + 1][1]
                if (grid[r][c] == ')') {
                    dp[c][1] = (dp[c][1] shl 1) or (dp[c][0] ushr 63)
                    dp[c][0] = dp[c][0] shl 1
                } else {
                    dp[c][0] = (dp[c][1] shl 63) or (dp[c][0] ushr 1)
                    dp[c][1] = dp[c][1] ushr 1
                }
            }
        }

        return dp[0][0] and 1 == 1L
    }
}
