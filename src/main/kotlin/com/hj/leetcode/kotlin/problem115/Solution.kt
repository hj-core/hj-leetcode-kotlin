package com.hj.leetcode.kotlin.problem115

/**
 * LeetCode page: [115. Distinct Subsequences](https://leetcode.com/problems/distinct-subsequences/);
 */
class Solution {
    // Complexity:
    // Time O(MN) and Space O(M+N) where M is the length of s and
    // N is the length of t.
    fun numDistinct(
        s: String,
        t: String,
    ): Int {
        val m = s.length
        val n = t.length
        if (m < n) {
            return 0
        }

        val s = s.toCharArray()
        val t = t.toCharArray()

        // dp[j]@i:= numDistinct(s[i..], t[j..])
        val dp = IntArray(n + 1)
        dp[n] = 1 // base case where i = m
        for (i in m - 1 downTo 0) {
            for (j in maxOf(n - m + i, 0)..<n) {
                if (s[i] == t[j]) {
                    dp[j] += dp[j + 1]
                }
            }
        }

        return dp[0]
    }
}
