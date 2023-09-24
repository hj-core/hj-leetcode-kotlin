package com.hj.leetcode.kotlin.problem799

/**
 * LeetCode page: [799. Champagne Tower](https://leetcode.com/problems/champagne-tower/);
 */
class Solution {
    /* Complexity:
     * Time O(query_row^2) and Space O(totalRows);
     */
    fun champagneTower(poured: Int, query_row: Int, query_glass: Int): Double {
        val totalRows = 100
        val dp = DoubleArray(totalRows)
        dp[0] = poured.toDouble()

        repeat(query_row) { previousRow ->
            for (glass in previousRow + 1 downTo 1) {
                val gainFromLeft =
                    (dp[glass - 1] - 1.0).coerceAtLeast(0.0) / 2.0
                val gainFromRight =
                    (dp[glass] - 1.0).coerceAtLeast(0.0) / 2.0

                dp[glass] = gainFromLeft + gainFromRight
            }
            dp[0] = (dp[0] - 1.0).coerceAtLeast(0.0) / 2.0
        }
        return minOf(1.0, dp[query_glass])
    }
}