package com.hj.leetcode.kotlin.problem1518

/**
 * LeetCode page: [1518. Water Bottles](https://leetcode.com/problems/water-bottles/);
 */
class Solution {
    /* Complexity:
     * Time O(LogN) and Space O(1) where N equals numBottles;
     */
    fun numWaterBottles(numBottles: Int, numExchange: Int): Int {
        var result = 0
        var remaining = numBottles
        while (remaining >= numExchange) {
            val numDrank = remaining - remaining % numExchange
            result += numDrank
            remaining -= numDrank
            remaining += numDrank / numExchange
        }
        result += remaining
        return result
    }
}