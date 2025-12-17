package com.hj.leetcode.kotlin.problem3573

/**
 * LeetCode page: [3573. Best Time to Buy and Sell Stock V](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-v/);
 */
class Solution {
    // Complexity:
    // Time O(kN) and Space O(N) where N is the length of prices.
    fun maximumProfit(
        prices: IntArray,
        k: Int,
    ): Long {
        // This solution is based on the O(kN^2) intuitive DP and
        // improved with compressed update logic and optimized space
        // usage.

        // dp[i]@j:= maximumProfit(prices[i:], j)
        val dp = LongArray(prices.size + 1) // j=0

        for (j in 1..k) {
            // i after start will not benefit from the increased j
            val start = prices.size - j * 2
            // the dp[i+1] from the previous update
            var oldNext = dp[start + 1]

            // The initial best sell and best short make use of the
            // fact that the old dp[i] already accounts for the best
            // value after start+1. Another choice is to keep track
            // of the values at previous start, which eliminates the
            // need to compare with the old dp[i].
            var bestSell = prices[start + 1] + dp[start + 2]
            var bestShort = -prices[start + 1] + dp[start + 2]

            for (i in start downTo 0) {
                val newMaxProfit =
                    maxOf(
                        dp[i],
                        dp[i + 1],
                        -prices[i] + bestSell,
                        prices[i] + bestShort,
                    )

                bestSell = maxOf(bestSell, prices[i] + oldNext)
                bestShort = maxOf(bestShort, -prices[i] + oldNext)
                oldNext = dp[i]
                dp[i] = newMaxProfit
            }
        }
        return dp[0]
    }
}
