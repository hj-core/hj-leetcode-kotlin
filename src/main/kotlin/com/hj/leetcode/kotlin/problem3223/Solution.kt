package com.hj.leetcode.kotlin.problem3223

/**
 * LeetCode page: [3223. Minimum Length of String After Operations](https://leetcode.com/problems/minimum-length-of-string-after-operations/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s.
     */
    fun minimumLength(s: String): Int {
        val frequencies = charFrequencies(s)
        var result = 0
        for (freq in frequencies) {
            result += if (freq < 3) freq else 1 + (freq - 1) % 2
        }
        return result
    }

    private fun charFrequencies(s: String): IntArray {
        val result = IntArray(26)
        for (c in s) {
            result[c - 'a']++
        }
        return result
    }
}
