package com.hj.leetcode.kotlin.problem1930

/**
 * LeetCode page: [1930. Unique Length-3 Palindromic Subsequences](https://leetcode.com/problems/unique-length-3-palindromic-subsequences/);
 */
class Solution {
    /*
     * Time O(N) and Space O(1) where N is the length of s;
     */
    fun countPalindromicSubsequence(s: String): Int {
        // ranges[c-'a']::= the first and last indices of c
        val ranges = ranges(s)
        var result = 0
        for ((start, end) in ranges) {
            if (end == s.length) {
                continue
            }
            val uniqueChars = hashSetOf<Char>()
            for (i in start + 1..<end) {
                uniqueChars.add(s[i])
            }
            result += uniqueChars.size
        }
        return result
    }

    private fun ranges(s: String): Array<IntArray> {
        val result = Array(26) { intArrayOf(s.length, s.length) }
        for ((i, c) in s.withIndex()) {
            if (result[c - 'a'][0] == s.length) {
                result[c - 'a'][0] = i
            } else {
                result[c - 'a'][1] = i
            }
        }
        return result
    }
}
