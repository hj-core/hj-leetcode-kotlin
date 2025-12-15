package com.hj.leetcode.kotlin.problem2110

/**
 * LeetCode page: [2110. Number of Smooth Descent Periods of a Stock](https://leetcode.com/problems/number-of-smooth-descent-periods-of-a-stock/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of prices.
    fun getDescentPeriods(prices: IntArray): Long {
        var result = 1L // We start from index 1
        // Length of the longest smooth descent period backward
        var extendLen = 1

        for (i in 1..<prices.size) {
            if (prices[i] + 1 == prices[i - 1]) {
                extendLen++
            } else {
                extendLen = 1
            }
            result += extendLen
        }
        return result
    }
}
