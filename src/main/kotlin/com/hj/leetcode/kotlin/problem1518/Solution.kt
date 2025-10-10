package com.hj.leetcode.kotlin.problem1518

/**
 * LeetCode page: [1518. Water Bottles](https://leetcode.com/problems/water-bottles/);
 */
class Solution {
    // Complexity:
    // Time O(LogN) and Space O(1) where N represents numBottles.
    fun numWaterBottles(
        numBottles: Int,
        numExchange: Int,
    ): Int {
        var result = 0
        var numLeft = numBottles

        while (numLeft >= numExchange) {
            val numExtra = numLeft / numExchange
            val numDrank = numExtra * numExchange

            result += numDrank
            numLeft += numExtra - numDrank
        }
        result += numLeft
        return result
    }
}
