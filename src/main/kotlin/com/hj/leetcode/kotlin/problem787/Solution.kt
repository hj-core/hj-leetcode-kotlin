package com.hj.leetcode.kotlin.problem787

import kotlin.math.min

/**
 * LeetCode page: [787. Cheapest Flights Within K Stops](https://leetcode.com/problems/cheapest-flights-within-k-stops/);
 */
class Solution {
    /* Complexity:
     * Time O(kM) and Space O(n) where M is the size of flights;
     */
    fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
        // dp[dst]@i::= the minimum price from src to dst in at most i flights;
        val dp = IntArray(n) { Int.MAX_VALUE }
        dp[src]= 0

        for (i in 1..k + 1) {
            val dpPrev = dp.clone()
            for ((fromCity, toCity, price) in flights) {
                if (dpPrev[fromCity] == Int.MAX_VALUE) {
                    continue
                }
                dp[toCity] = min(dp[toCity], price + dpPrev[fromCity])
            }
        }
        return dp[dst].let { if (it == Int.MAX_VALUE) -1 else it }
    }
}