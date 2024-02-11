package com.hj.leetcode.kotlin.problem647

/**
 * LeetCode page: [647. Palindromic Substrings](https://leetcode.com/problems/palindromic-substrings/);
 */
class Solution {
    /* Complexity:
     * Time O(N^2) and Space O(N^2) where N is the length of s;
     */
    fun countSubstrings(s: String): Int {
        // dp[i][j]::= is s[i..i+j] palindromic
        val dp = Array(s.length) { BooleanArray(s.length - it) }
        // Base cases where j=0 and j=1
        for (i in dp.indices) {
            dp[i][0] = true
        }
        for (i in 0..<dp.size - 1) {
            dp[i][1] = s[i] == s[i + 1]
        }
        // Update remaining dp entries and then return result
        for (j in 2..<dp.size) {
            for (i in 0..<dp.size - j) {
                dp[i][j] = s[i] == s[i + j] && dp[i + 1][j - 2]
            }
        }
        return dp.sumOf { it.count { isPalindromic -> isPalindromic } }
    }
}