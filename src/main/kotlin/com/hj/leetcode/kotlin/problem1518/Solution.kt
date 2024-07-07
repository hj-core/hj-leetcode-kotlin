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
        var fullBottles = numBottles
        while (fullBottles >= numExchange) {
            val numDrank = fullBottles - fullBottles % numExchange
            result += numDrank
            fullBottles -= numDrank
            fullBottles += numDrank / numExchange
        }
        result += fullBottles
        return result
    }
}