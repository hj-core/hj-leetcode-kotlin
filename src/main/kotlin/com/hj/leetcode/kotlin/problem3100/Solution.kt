package com.hj.leetcode.kotlin.problem3100

import kotlin.math.sqrt

/**
 * LeetCode page: [3100. Water Bottles II](https://leetcode.com/problems/water-bottles-ii/);
 */
class Solution {
    // Complexity:
    // Time O(1) and Space O(1).
    fun maxBottlesDrunk(
        numBottles: Int,
        numExchange: Int,
    ): Int {
        // sum(numExchange..<numExchange+result-numBottles) + 1 <= result
        val b = (2 * numExchange - 3).toDouble()
        val c = (-2 * numBottles + 2).toDouble()
        val extra = ((-b + sqrt(b * b - 4 * c)) / 2.0).toInt()
        return numBottles + extra
    }
}
