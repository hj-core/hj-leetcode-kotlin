package com.hj.leetcode.kotlin.problem1081

/**
 * LeetCode page: [1081. Smallest Subsequence of Distinct Characters](https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/);
 */
class Solution2 {
    // Complexity:
    // Time O(N) and Space O(Z) where N is the length of s and Z is the number of
    // lowercase chars.
    fun smallestSubsequence(s: String): String {
        val lastIndex = IntArray(26) { s.length }
        for ((i, c) in s.withIndex()) {
            lastIndex[c - 'a'] = i
        }

        val result = StringBuilder()
        val included = BooleanArray(26)
        for ((i, c) in s.withIndex()) {
            if (included[c - 'a']) {
                continue
            }

            while (result.isNotEmpty() &&
                c < result.last() && i < lastIndex[result.last() - 'a']
            ) {
                included[result.last() - 'a'] = false
                result.deleteAt(result.lastIndex)
            }

            included[c - 'a'] = true
            result.append(c)
        }
        
        return result.toString()
    }
}
