package com.hj.leetcode.kotlin.problem837

/**
 * LeetCode page: [837. New 21 Game](https://leetcode.com/problems/new-21-game/);
 */
class Solution {
    // Complexity:
    // Time O(M) and Space O(M) where m is min(n, k+maxPts).
    fun new21Game(
        n: Int,
        k: Int,
        maxPts: Int,
    ): Double {
        if (k == 0 || n >= k + maxPts - 1) {
            return 1.0
        }

        // dp[i]:= P(Alice gets i points when the game ends)
        // for i from k to n.
        val dp = DoubleArray(n + 1)
        dp[0] = 1.0
        var wndSum = dp[0]
        var result = 0.0
        val p = 1.0 / maxPts

        for (i in 1..n) {
            dp[i] = wndSum * p
            if (i < k) {
                wndSum += dp[i]
            } else {
                result += dp[i]
            }
            if (i >= maxPts) {
                wndSum -= dp[i - maxPts]
            }
        }
        return result
    }
}
