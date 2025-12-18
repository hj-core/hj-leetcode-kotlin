package com.hj.leetcode.kotlin.problem3652

/**
 * LeetCode page: [3652. Best Time to Buy and Sell Stock using Strategy](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-using-strategy/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of prices.
    fun maxProfit(
        prices: IntArray,
        strategy: IntArray,
        k: Int,
    ): Long {
        val originalProfit =
            prices.foldIndexed(0L) { i, acc, price ->
                acc + strategy[i].toLong() * price
            }

        val halfK = k / 2
        var wndGain =
            -(0..<halfK).fold(0L) { acc, i ->
                acc + strategy[i] * prices[i]
            } +
                (halfK..<k).fold(0L) { acc, i ->
                    acc + (1 - strategy[i]) * prices[i]
                }

        var maxGain = maxOf(wndGain, 0)
        for (i in k..<prices.size) {
            wndGain +=
                (1 - strategy[i]) * prices[i] -
                prices[i - halfK] +
                strategy[i - k] * prices[i - k]
            maxGain = maxOf(maxGain, wndGain)
        }

        return originalProfit + maxGain
    }
}
