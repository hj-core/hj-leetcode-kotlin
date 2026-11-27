package com.hj.leetcode.kotlin.problem3946

/**
 * LeetCode page: [3946. Maximum Number of Items From Sale I](https://leetcode.com/problems/maximum-number-of-items-from-sale-i/);
 */
class Solution {
    // Complexity:
    // Time O(MN+N^2) and Space O(M) where N is the length of items and
    // M is budget.
    fun maximumSaleItems(
        items: Array<IntArray>,
        budget: Int,
    ): Int {
        val minPrice = items.minOf { item -> item[1] }
        // We use 0-1 Knapsack to determine which of the initial purchases to
        // include, then fill the remaining budget with the cheapest item.
        val dp = IntArray(budget + 1)
        for ((factor, price) in items) {
            val copies = items.count { item -> item[0] % factor == 0 }
            if (minPrice * copies <= price) {
                continue
            }

            for (lastCost in (budget - price) downTo 0) {
                val currCost = lastCost + price
                dp[currCost] = maxOf(dp[currCost], dp[lastCost] + copies)
            }
        }

        return (0..budget).maxOf { dp[it] + (budget - it) / minPrice }
    }
}
