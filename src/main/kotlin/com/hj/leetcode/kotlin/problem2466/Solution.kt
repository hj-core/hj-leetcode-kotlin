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
        val (short, long) = if (zero <= one) zero to one else one to zero

        // Shift everything by short.
        // dp[L]::= the number of ways to construct length (L+short) string
        val dp = IntArray(high - short + 1)
        dp[0] = 1
        dp[long - short] += 1

        for (len in 0..<dp.size - short) {
            val appendShort = len + short
            dp[appendShort] = (dp[appendShort] + dp[len]) % modulus

            val appendLong = len + long
            if (appendLong < dp.size) {
                dp[appendLong] = (dp[appendLong] + dp[len]) % modulus
            }
        }

        var result = 0
        for (len in low - short..high - short) {
            result = (result + dp[len]) % modulus
        }
        return result
    }
}
