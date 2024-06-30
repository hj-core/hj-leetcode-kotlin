package com.hj.leetcode.kotlin.problem1930

/**
 * LeetCode page: [1930. Unique Length-3 Palindromic Subsequences](https://leetcode.com/problems/unique-length-3-palindromic-subsequences/);
 */
class Solution {
    /*
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun countPalindromicSubsequence(s: String): Int {
        var result = 0

        for (char in 'a'..'z') {
            val firstIndex = s.indexOf(char)
            if (firstIndex == -1) {
                continue
            }

            val lastIndex = s.lastIndexOf(char)
            val hasChars = BooleanArray(26)
            for (index in (firstIndex + 1)..<lastIndex) {
                hasChars[s[index] - 'a'] = true
            }
            result += hasChars.count { it }
        }
        return result
    }
}