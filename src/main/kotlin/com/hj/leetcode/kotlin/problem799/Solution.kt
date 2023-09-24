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
            for (glass in previousRow downTo 0) {
                val halfFall = (dp[glass] - 1.0).coerceAtLeast(0.0) / 2.0
                dp[glass] = halfFall
                dp[glass + 1] += halfFall
            }
        }
        return dp[query_glass].coerceAtMost(1.0)
    }
}