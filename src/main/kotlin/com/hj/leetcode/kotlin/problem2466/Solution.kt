package com.hj.leetcode.kotlin.problem2466

/**
 * LeetCode page: [2466. Count Ways To Build Good Strings](https://leetcode.com/problems/count-ways-to-build-good-strings/);
 */
class Solution {
    /* Complexity:
     * Time O(high) and Space O(high).
     */
    fun countGoodStrings(
        low: Int,
        high: Int,
        zero: Int,
        one: Int,
    ): Int {
        val modulus = 1_000_000_007
        // dp[L]::= the number of ways to construct length L string
        val dp = IntArray(high + 1)
        dp[0] = 1

        for (len in dp.indices) {
            val appendZero = len + zero
            if (appendZero <= high) {
                dp[appendZero] = (dp[appendZero] + dp[len]) % modulus
            }

            val appendOne = len + one
            if (appendOne <= high) {
                dp[appendOne] = (dp[appendOne] + dp[len]) % modulus
            }
        }

        return (low..high).fold(0) { acc, it ->
            (acc + dp[it]) % modulus
        }
    }
}
