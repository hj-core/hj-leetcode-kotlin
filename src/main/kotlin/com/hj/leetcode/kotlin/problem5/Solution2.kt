package com.hj.leetcode.kotlin.problem5

/**
 * LeetCode page: [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring/description/);
 */
class Solution2 {
    /* Complexity:
     * Time O(L^2) and Space O(L^2) where L is the length of s;
     */
    fun longestPalindrome(s: String): String {
        // dp[start][length] ::= is s.slice(start..<start+length) a palindrome
        val dp = Array(s.length) { BooleanArray(s.length - it + 1) }

        // Base Cases
        for (i in s.indices) {
            dp[i][0] = true
            dp[i][1] = true
        }

        var resultStart = 0
        var resultLength = 1

        // Propagate the DP relation while keeping track of the longest palindrome
        for (length in 2..s.length) {
            for (start in 0..s.length - length) {
                dp[start][length] =
                    s[start] == s[start + length - 1] && dp[start + 1][length - 2]

                if (dp[start][length] && length > resultLength) {
                    resultStart = start
                    resultLength = length
                }
            }
        }
        return s.slice(resultStart..<resultStart + resultLength)
    }
}