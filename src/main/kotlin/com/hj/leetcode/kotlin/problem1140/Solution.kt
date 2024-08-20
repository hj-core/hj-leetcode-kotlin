package com.hj.leetcode.kotlin.problem1140

import kotlin.math.max
import kotlin.math.min

/**
 * LeetCode page: [1140. Stone Game II(https://leetcode.com/problems/stone-game-ii/);
 */
class Solution {
    /* Complexity:
     * Time O(N^3) and Space O(N^2) where N is the size of piles;
     */
    fun stoneGameII(piles: IntArray): Int {
        val n = piles.size
        var suffixSum = 0
        /* dp[start][m]::= maximum stones the first player can get if the game
         * starts from index start with an M equals m
         */
        val dp = Array(n + 1) { start -> IntArray(n - start + 1) }
        for (start in piles.indices.reversed()) {
            suffixSum += piles[start]
            for (m in 1..<dp[start].size) {
                for (x in 1..(m * 2)) {
                    val nextStart = min(start + x, n)
                    val nextM = min(n - nextStart, max(m, x))
                    dp[start][m] = max(
                        dp[start][m], suffixSum - dp[nextStart][nextM]
                    )
                }
            }
        }
        return dp[0][1]
    }
}
