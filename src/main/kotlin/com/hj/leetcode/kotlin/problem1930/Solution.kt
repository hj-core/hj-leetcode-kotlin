package com.hj.leetcode.kotlin.problem1930

/**
 * LeetCode page: [1930. Unique Length-3 Palindromic Subsequences](https://leetcode.com/problems/unique-length-3-palindromic-subsequences/);
 */
class Solution {
    // Complexity:
    // Time O(KN) and Space O(K) where N is the length of s and K
    // is the number of possible chars.
    fun countPalindromicSubsequence(s: String): Int {
        var result = 0
        val idxRange = computeCharIndexRange(s)

        for ((first, last) in idxRange) {
            if (first < last) {
                val visited = BooleanArray(26)
                for (i in first + 1..<last) {
                    visited[s[i] - 'a'] = true
                }
                result += visited.count { it }
            }
        }
        return result
    }

    private fun computeCharIndexRange(
        s: String,
    ): Array<IntArray> {
        val idxRange = Array(26) { intArrayOf(-1, -1) }
        for ((i, char) in s.withIndex()) {
            val c = char - 'a'

            idxRange[c][1] = i
            if (idxRange[c][0] == -1) {
                idxRange[c][0] = i
            }
        }
        return idxRange
    }
}
