package com.hj.leetcode.kotlin.problem1406

/**
 * LeetCode page: [1406. Stone Game III](https://leetcode.com/problems/stone-game-iii/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of stoneValue.
    fun stoneGameIII(stoneValue: IntArray): String {
        // dp[j]@i := the maximum score lead of the first player for a game with stoneValue[i+j..]
        val dp = IntArray(3) // base case i = stoneValue.size

        for (i in stoneValue.indices.reversed()) {
            val a = stoneValue[i]
            val b = a + stoneValue.getOrElse(i + 1) { 0 }
            val c = b + stoneValue.getOrElse(i + 2) { 0 }

            val cur = maxOf(a - dp[0], b - dp[1], c - dp[2])
            dp[2] = dp[1]
            dp[1] = dp[0]
            dp[0] = cur
        }

        return when {
            dp[0] > 0 -> "Alice"
            dp[0] < 0 -> "Bob"
            else -> "Tie"
        }
    }
}
