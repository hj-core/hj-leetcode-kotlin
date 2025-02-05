package com.hj.leetcode.kotlin.problem1930

/**
 * LeetCode page: [1930. Unique Length-3 Palindromic Subsequences](https://leetcode.com/problems/unique-length-3-palindromic-subsequences/);
 */
class Solution {
    /*
     * Time O(KN) and Space O(K)
     * where N is the length of s and K is the number of possible chars.
     */
    fun countPalindromicSubsequence(s: String): Int {
        val ranges = firstAndLastIndicesOfEachChar(s)
        var result = 0
        for ((first, last) in ranges) {
            if (last == s.length) {
                continue
            }
            val uniqueChars = hashSetOf<Char>()
            for (i in first + 1..<last) {
                uniqueChars.add(s[i])
            }
            result += uniqueChars.size
        }
        return result
    }

    private fun firstAndLastIndicesOfEachChar(s: String): Array<IntArray> {
        val result = Array(26) { intArrayOf(s.length, s.length) }
        for ((i, c) in s.withIndex()) {
            val isFirst = result[c - 'a'][0] == s.length
            if (isFirst) {
                result[c - 'a'][0] = i
            } else {
                result[c - 'a'][1] = i
            }
        }
        return result
    }
}
