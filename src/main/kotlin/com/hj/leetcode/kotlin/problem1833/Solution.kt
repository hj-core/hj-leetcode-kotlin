package com.hj.leetcode.kotlin.problem1833

/**
 * LeetCode page: [1833. Maximum Ice Cream Bars](https://leetcode.com/problems/maximum-ice-cream-bars/);
 */
class Solution {
    // Complexity:
    // Time (NLogN) and Space O(N) where N is the size of costs.
    fun maxIceCream(
        costs: IntArray,
        coins: Int,
    ): Int {
        val costs = costs.sortedArray()
        var coins = coins
        for ((i, cost) in costs.withIndex()) {
            coins -= cost
            if (coins < 0) {
                return i
            }
        }
        return costs.size
    }
}
