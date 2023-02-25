package com.hj.leetcode.kotlin.problem121

/**
 * LeetCode page: [121. Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of prices;
     */
    fun maxProfit(prices: IntArray): Int {
        var maxProfit = 0
        var bestSellPrice = prices.last()
        for (index in prices.lastIndex - 1 downTo 0) {
            val price = prices[index]
            // the maxProfit if we buy stock at day index
            val localMaxProfit = bestSellPrice - price
            if (localMaxProfit > maxProfit) maxProfit = localMaxProfit
            if (price > bestSellPrice) bestSellPrice = price
        }
        return maxProfit
    }
}