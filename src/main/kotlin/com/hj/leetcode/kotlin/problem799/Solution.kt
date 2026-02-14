package com.hj.leetcode.kotlin.problem799

/**
 * LeetCode page: [799. Champagne Tower](https://leetcode.com/problems/champagne-tower/);
 */
class Solution {
    // Complexity:
    // Time O(query_row * query_glass) and Space O(query_glass).
    fun champagneTower(
        poured: Int,
        query_row: Int,
        query_glass: Int,
    ): Double {
        // dp[c]@r:= total champagne received by glass (r, c)
        val dp = DoubleArray(query_glass + 2)
        dp[0] = poured.toDouble()

        for (r in 0..<query_row) {
            for (c in minOf(query_glass, r) downTo 0) {
                val toNext = maxOf(0.0, (dp[c] - 1.0)) / 2
                dp[c] = toNext
                dp[c + 1] += toNext
            }
        }

        return minOf(1.0, dp[query_glass])
    }
}
