package com.hj.leetcode.kotlin.problem120

/**
 * LeetCode page: [120. Triangle](https://leetcode.com/problems/triangle/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(N) where N is the length of
    // triangle.
    fun minimumTotal(triangle: List<List<Int>>): Int {
        val n = triangle.size
        // dp from leaves to root.
        val dp = IntArray(n) { triangle[n - 1][it] }

        for (r in n - 1 downTo 1) {
            for (c in 0..<r) {
                dp[c] = triangle[r - 1][c] + minOf(dp[c], dp[c + 1])
            }
        }
        return dp[0]
    }
}
