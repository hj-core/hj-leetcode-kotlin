package com.hj.leetcode.kotlin.problem62

/**
 * LeetCode page: [62. Unique Paths](https://leetcode.com/problems/unique-paths/);
 */
class Solution {
    /* Complexity:
     * Time O(mn) and Space O(n);
     */
    fun uniquePaths(m: Int, n: Int): Int {
        // dp[j]@i::= number of possible unique paths if the robot starts at (i, j)
        val dp = IntArray(n + 1)
        // Base cases that dp@i=m
        dp[n - 1] = 1
        // Repeat m times and each time update dp@i to dp@(i-1)
        repeat(m) {
            for (j in (n - 1) downTo 0) {
                dp[j] += dp[j + 1]
            }
        }
        // Return the original problem which is dp[0]@i=0
        return dp[0]
    }
}