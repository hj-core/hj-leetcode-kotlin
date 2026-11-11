package com.hj.leetcode.kotlin.problem3693

/**
 * LeetCode page: [3693. Climbing Stairs II](https://leetcode.com/problems/climbing-stairs-ii/);
 */
class Solution {
    // Complexity:
    // Time O(n) and Space O(1).
    fun climbStairs(
        n: Int,
        costs: IntArray,
    ): Int {
        // dp[i and 3]:= the minimum cost to reach step i
        val dp = IntArray(4) { Int.MAX_VALUE }
        dp[0] = 0
        for (i in 0..<n) {
            for (j in (i + 1)..minOf(i + 3, n)) {
                val jumpCost = costs[j - 1] + (j - i) * (j - i)
                dp[j and 3] = minOf(dp[j and 3], dp[i and 3] + jumpCost)
            }
            dp[i and 3] = Int.MAX_VALUE
        }
        return dp[n and 3]
    }
}
