package com.hj.leetcode.kotlin.problem1510

/**
 * LeetCode page: [1510. Stone Game IV](https://leetcode.com/problems/stone-game-iv/);
 */
class Solution {
    // Complexity:
    // Time O(n * sqrt(n)) and Space O(n).
    fun winnerSquareGame(n: Int): Boolean {
        // dp[i]:= winnerSquareGame(i)
        val dp = BooleanArray(n + 1)

        for (i in 0..n) {
            if (dp[i]) {
                continue
            }

            for (j in 1..n) {
                val k = i + j * j
                if (k > n) {
                    break
                }
                dp[k] = true
            }
        }

        return dp[n]
    }
}
