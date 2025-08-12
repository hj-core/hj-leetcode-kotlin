package com.hj.leetcode.kotlin.problem2787

/**
 * LeetCode page: [2787. Ways to Express an Integer as Sum of Powers](https://leetcode.com/problems/ways-to-express-an-integer-as-sum-of-powers/);
 */
class Solution {
    // Complexity:
    // Time O(nM+MLog(x)) and Space O(n) where M is n^(1/x).
    fun numberOfWays(
        n: Int,
        x: Int,
    ): Int {
        if (x == 1) {
            return numberOfWaysMod(n)
        }

        // dp[sum]@num:= the number of ways to achieve sum, using
        // only numbers less than or equal to num.
        val dp = IntArray(n + 1)
        dp[0] = 1
        for (num in 1..n) {
            val powerX = pow(num, x)
            if (n < powerX) {
                break
            }

            for (sum in n downTo powerX) {
                dp[sum] += dp[sum - powerX]
            }
        }
        return dp[n]
    }

    // Computes numberOfWays(n, 1).
    private fun numberOfWaysMod(n: Int): Int {
        val modulo = 1_000_000_007

        // dp[sum]@num:= the number of ways to achieve sum, taken
        // modulo, using only numbers less than or equal to num.
        val dp = IntArray(n + 1)
        dp[0] = 1
        for (num in 1..n) {
            for (sum in n downTo num) {
                dp[sum] += dp[sum - num]
                if (dp[sum] > modulo) {
                    dp[sum] -= modulo
                }
            }
        }
        return dp[n]
    }

    // Returns base^exp.
    private fun pow(
        base: Int,
        exp: Int,
    ): Int {
        var result = 1
        var newBase = base
        var newExp = exp

        while (newExp > 0) {
            if (newExp and 1 == 1) {
                result *= newBase
            }
            newBase *= newBase
            newExp = newExp shr 1
        }
        return result
    }
}
