package com.hj.leetcode.kotlin.problem1518

/**
 * LeetCode page: [1518. Water Bottles](https://leetcode.com/problems/water-bottles/);
 */
class Solution2 {
    /* Complexity:
     * Time O(1) and Space O(1);
     */
    fun numWaterBottles(numBottles: Int, numExchange: Int): Int {
        /* The idea is to use one bottle to absorb every following (numExchange-1) bottles.
         * By absorption, I mean that we drink that bottle together with the (numExchange-1)
         * bottles and then exchange one bottle back.
         */
        val rounds = (numBottles - 1) / (numExchange - 1)
        val remaining = (numBottles - 1) % (numExchange - 1) + 1
        return (rounds * numExchange) + remaining
    }
}