package com.hj.leetcode.kotlin.problem1140

import kotlin.math.max
import kotlin.math.min

/**
 * LeetCode page: [1140. Stone Game II(https://leetcode.com/problems/stone-game-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N^3) and Space O(N^2) where N is the length of piles.
    fun stoneGameII(piles: IntArray): Int {
        val n = piles.size
        // dp[i][m] := the maximum score of the first player in a game with M=m on piles[i..]
        val dp = Array(n + 1) { IntArray(n + 1) }
        var totalStones = 0

        for (i in n - 1 downTo 0) {
            totalStones += piles[i]
            for (m in 1..n) {
                for (x in 1..minOf(m * 2, n - i)) {
                    dp[i][m] = maxOf(dp[i][m], totalStones - dp[i + x][maxOf(m, x)])
                }
            }
        }

        return dp[0][1]
    }
}
