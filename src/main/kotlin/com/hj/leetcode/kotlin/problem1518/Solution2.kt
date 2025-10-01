package com.hj.leetcode.kotlin.problem1518

/**
 * LeetCode page: [1518. Water Bottles](https://leetcode.com/problems/water-bottles/);
 */
class Solution2 {
    // Complexity:
    // Time O(1) and Space O(1).
    fun numWaterBottles(
        numBottles: Int,
        numExchange: Int,
    ): Int {
        /*
         * We hold one bottle and divide the remaining bottles
         * into groups of size (numExchange-1) (the last group
         * may be of insufficient size). For each full-size group,
         * we can drink numExchange bottles. At the end, we will
         * have one bottle in hand and the insufficient-size group
         * remaining.
         */
        val a = numBottles - 1
        val b = numExchange - 1
        return (a / b) * numExchange + 1 + a % b
    }
}
