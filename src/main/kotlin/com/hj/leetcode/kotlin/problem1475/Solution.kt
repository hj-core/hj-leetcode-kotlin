package com.hj.leetcode.kotlin.problem1475

/**
 * LeetCode page: [1475. Final Prices With a Special Discount in a Shop](https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(N) where N is the length of prices.
     */
    fun finalPrices(prices: IntArray): IntArray {
        val result = prices.clone()
        val monoStack = mutableListOf<Int>()

        for (i in prices.indices.reversed()) {
            while (monoStack.isNotEmpty() && prices[i] < monoStack.last()) {
                monoStack.removeLast()
            }

            val discount = if (monoStack.isEmpty()) 0 else monoStack.last()
            result[i] = prices[i] - discount
            monoStack.add(prices[i])
        }
        return result
    }
}
