package com.hj.leetcode.kotlin.problem120

/**
 * LeetCode page: [120. Triangle](https://leetcode.com/problems/triangle/);
 */
class Solution {
    // Complexity:
    // Time O(N^2) and Space O(N) where N is the length of
    // triangle.
    fun minimumTotal(triangle: List<List<Int>>): Int {
        // dp from leaves to root.
        val dp = triangle.last().toIntArray()

        for (r in triangle.size - 2 downTo 0) {
            for (c in 0..r) {
                dp[c] = triangle[r][c] + minOf(dp[c], dp[c + 1])
            }
        }
        return dp[0]
    }
}
