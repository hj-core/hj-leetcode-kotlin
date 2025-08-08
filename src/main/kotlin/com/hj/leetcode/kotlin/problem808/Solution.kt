package com.hj.leetcode.kotlin.problem808

/**
 * LeetCode page: [808. Soup Servings](https://leetcode.com/problems/soup-servings/);
 */
class Solution {
    // Complexity:
    // Time O(n^2) and Space O(n) for n < 4801. Constant
    // time and space otherwise.
    fun soupServings(n: Int): Double {
        if (n == 0) {
            return 0.5
        }
        if (n > 4800) {
            return 1.0 // P > 0.999995 for n >= 4801
        }

        // Transform the problem: There is an m x m board, and
        // we start from cell (0, 0). We want to determine the
        // probability of falling off from the edge of column m-1.
        // The moves are scaled down by a factor of 25.
        val m = (n + 24) / 25

        // dp[b][a%4]:= the probability of moving from (0, 0)
        // to (a, b). We use a sliding window of size 4 for a.
        val dp = Array(m) { DoubleArray(4) }

        var result = 0.0
        dp[0][0] = 1.0
        for (a in 0..<m) {
            for (b in 0..<m) {
                result += forwardUpdate(dp, m, a, b)
            }
        }
        return result
    }

    // Distributes the probability to the cells reachable
    // from (a, b), and returns the probability of falling
    // off from the edge of column m-1.
    fun forwardUpdate(
        dp: Array<DoubleArray>,
        m: Int,
        a: Int,
        b: Int,
    ): Double {
        var result = 0.0
        val p = dp[b][a and 3] * 0.25
        dp[b][a and 3] = 0.0

        for (move in 1..4) {
            val dstA = a + move
            val dstB = b + (4 - move)
            if (dstA < m && dstB < m) {
                dp[dstB][dstA and 3] += p
            } else if (m <= dstA) {
                result += if (dstB < m) p else p * 0.5
            }
        }
        return result
    }
}
