package com.hj.leetcode.kotlin.problem664

import kotlin.math.min

/**
 * LeetCode page: [664. Strange Printer](https://leetcode.com/problems/strange-printer/);
 */
class Solution {
    /* Complexity:
     * Time O(N^3) and Space O(N^2) where N is the length of s;
     */
    fun strangePrinter(s: String): Int {
        val n = s.length
        // dp[i][j]::= strangePrinter(s[i..j])
        val dp = Array(n + 1) { IntArray(n) }

        for (i in 0..<n) {
            dp[i][i] = 1
        }

        for (i in (n - 1) downTo 0) {
            for (j in (i + 1)..<n) {
                dp[i][j] = 1 + dp[i + 1][j]
                for (k in (i + 1)..j) {
                    if (s[k] == s[i]) {
                        dp[i][j] = min(dp[i][j], dp[i][k - 1] + dp[k + 1][j])
                    }
                }
            }
        }
        return dp[0][n - 1]
    }
}
