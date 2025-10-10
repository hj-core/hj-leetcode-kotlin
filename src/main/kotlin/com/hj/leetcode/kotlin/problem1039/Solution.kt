package com.hj.leetcode.kotlin.problem1039

/**
 * LeetCode page: [1039. Minimum Score Triangulation of Polygon](https://leetcode.com/problems/minimum-score-triangulation-of-polygon/);
 */
class Solution {
    // Complexity:
    // Time O(N^3) and Space O(N^2) where N is the length of
    // values.
    fun minScoreTriangulation(values: IntArray): Int {
        val n = values.size
        // dp[i][j]:= the result to values[i..=j]
        val dp = Array(n - 2) { IntArray(n) }

        for (j in 2..<n) {
            for (i in j - 2 downTo 0) {
                val base = values[i] * values[j]

                dp[i][j] = base * values[j - 1] + dp[i][j - 1]
                for (k in i + 1..<j - 1) {
                    dp[i][j] = minOf(dp[i][j], base * values[k] + dp[i][k] + dp[k][j])
                }
            }
        }
        return dp[0][n - 1]
    }
}
