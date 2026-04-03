package com.hj.leetcode.kotlin.problem3418

/**
 * LeetCode page: [3418. Maximum Amount of Money Robot Can Earn](https://leetcode.com/problems/maximum-amount-of-money-robot-can-earn/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(KN) where M is the number of rows, N is the number
    // of columns and K is the number of allowed neutralization.
    fun maximumAmount(coins: Array<IntArray>): Int {
        val m = coins.size
        val n = coins[0].size
        val minAmount = -1000
        val unreachable = (m + n) * minAmount

        // dp[c][k]@r:= the maximum possible amount reaching cell(r, c) with
        // at most k neutralization.
        val dp = Array(n) { IntArray(3) { unreachable } }
        dp[0].fill(0)

        for (r in 0..<m) {
            var left = dp[0]
            for (c in 0..<n) {
                val neutralized = maxOf(0, coins[r][c])
                for (k in 2 downTo 1) {
                    dp[c][k] =
                        maxOf(
                            dp[c][k] + coins[r][c],
                            dp[c][k - 1] + neutralized,
                            left[k] + coins[r][c],
                            left[k - 1] + neutralized,
                        )
                }
                dp[c][0] = maxOf(left[0], dp[c][0]) + coins[r][c]

                left = dp[c]
            }
        }

        return dp[n - 1][2]
    }
}
