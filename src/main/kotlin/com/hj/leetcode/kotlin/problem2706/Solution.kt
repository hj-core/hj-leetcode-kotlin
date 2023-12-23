package com.hj.leetcode.kotlin.problem2706

import kotlin.math.max
import kotlin.math.min

/**
 * LeetCode page: [2706. Buy Two Chocolates](https://leetcode.com/problems/buy-two-chocolates/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the size of prices;
     */
    fun buyChoco(prices: IntArray, money: Int): Int {
        val minCost = findTwoSmallest(prices).sum()
        return if (minCost <= money) money - minCost else money
    }

    private fun findTwoSmallest(prices: IntArray): IntArray {
        var smallest = min(prices[0], prices[1])
        var secondSmallest = max(prices[0], prices[1])
        for (index in 2..<prices.size) {
            when {
                prices[index] < smallest -> {
                    secondSmallest = smallest
                    smallest = prices[index]
                }

                prices[index] < secondSmallest -> {
                    secondSmallest = prices[index]
                }
            }
        }
        return intArrayOf(smallest, secondSmallest)
    }
}