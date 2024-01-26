package com.hj.leetcode.kotlin.problem1143

import kotlin.math.max

/**
 * LeetCode page: [1143. Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence/);
 */
class Solution {
    /* Complexity:
     * Time O(MN) and Space O(MN) where M and N are the length of text1 and
     * text2, respectively;
     */
    fun longestCommonSubsequence(text1: String, text2: String): Int {
        // dp[i][j]::= the length of LCS(text1[i:], text2[j:])
        val dp = Array(text1.length + 1) { IntArray(text2.length + 1) }
        for (i in text1.indices.reversed()) {
            for (j in text2.indices.reversed()) {
                dp[i][j] = if (text1[i] == text2[j]) {
                    1 + dp[i + 1][j + 1]
                } else {
                    max(dp[i + 1][j], dp[i][j + 1])
                }
            }
        }
        return dp[0][0]
    }
}