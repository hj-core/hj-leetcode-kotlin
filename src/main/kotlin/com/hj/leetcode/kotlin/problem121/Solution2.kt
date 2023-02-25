package com.hj.leetcode.kotlin.problem121

/**
 * LeetCode page: [121. Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of prices;
     */
    fun maxProfit(prices: IntArray): Int {
        var maxProfit = 0
        var bestBuyPrice = prices[0]
        for (index in 1 until prices.size) {
            val price = prices[index]
            // the maxProfit if we sell stock at day index
            val localMaxProfit = price - bestBuyPrice
            if (localMaxProfit > maxProfit) maxProfit = localMaxProfit
            if (price < bestBuyPrice) bestBuyPrice = price
        }
        return maxProfit
    }
}