package com.hj.leetcode.kotlin.problem1833

/**
 * LeetCode page: [1833. Maximum Ice Cream Bars](https://leetcode.com/problems/maximum-ice-cream-bars/);
 */
class Solution {
    /* Complexity:
     * Time (NLogN) and Space O(N) where N is the size of costs;
     */
    fun maxIceCream(costs: IntArray, coins: Int): Int {
        val sortedCosts = costs.sorted()
        var maxIceCream = 0
        var remainingCoins = coins
        for (cost in sortedCosts) {
            if (remainingCoins < cost) break
            maxIceCream++
            remainingCoins -= cost
        }
        return maxIceCream
    }
}
