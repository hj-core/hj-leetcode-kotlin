package com.hj.leetcode.kotlin.problem139

/**
 * LeetCode page: [139. Word Break](https://leetcode.com/problems/word-break/);
 */
class Solution {
    /* Complexity:
     * Time O(N^3+M) and Space O(N+M) where N is the length of s, M is the sum of string lengths in wordDict;
     */
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        val words = wordDict.toSet()
        val dp = BooleanArray(s.length) // dp[end] := wordBreak(s.slice(0..end), wordDict)
        for (end in s.indices) {
            val prefixString = s.slice(0..end)
            val possibleLastBreaks = (end - 1) downTo 0
            dp[end] = (prefixString in words
                    || possibleLastBreaks.any { dp[it] && s.slice(it + 1..end) in words })
        }
        return dp[s.lastIndex]
    }
}