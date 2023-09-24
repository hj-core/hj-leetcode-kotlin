package com.hj.leetcode.kotlin.problem799

/**
 * LeetCode page: [799. Champagne Tower](https://leetcode.com/problems/champagne-tower/);
 */
class Solution {
    /* Complexity:
     * Time O(query_row^2) and Space O(query_row);
     */
    fun champagneTower(poured: Int, query_row: Int, query_glass: Int): Double {
        if (query_glass > query_row) {
            return 0.0
        }

        // dp[j]@i::= total liquid fall into the j_th glass of the i_th row
        val dp = DoubleArray(query_row + 1)

        dp[0] = poured.toDouble() // Base case dp[0]@i=0
        for (row in 0 until query_row) {
            // Update the dp of the next row in place
            for (glass in row downTo 0) {
                val halfFall = (dp[glass] - 1.0).coerceAtLeast(0.0) / 2.0
                dp[glass] = halfFall
                dp[glass + 1] += halfFall
            }
        }
        return dp[query_glass].coerceAtMost(1.0)
    }
}