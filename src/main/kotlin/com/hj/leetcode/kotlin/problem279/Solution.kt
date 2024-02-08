package com.hj.leetcode.kotlin.problem279

import kotlin.math.sqrt

/**
 * LeetCode page: [279. Perfect Squares](https://leetcode.com/problems/perfect-squares/);
 */
class Solution {
    /* Complexity:
     * Time O(n^(3/2)) and Space O(n);
     */
    fun numSquares(n: Int): Int {
        // dp[i] ::= numSquares(i)
        val dp = IntArray(n + 1) { num -> num }

        for (num in 1..n) {
            for (i in 1..num.floorSqrt()) {
                dp[num] = minOf(dp[num], 1 + dp[num - i * i])
            }
        }
        return dp[n]
    }

    private fun Int.floorSqrt() = sqrt(this.toDouble()).toInt()
}