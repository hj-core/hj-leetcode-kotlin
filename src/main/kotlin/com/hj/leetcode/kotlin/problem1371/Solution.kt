package com.hj.leetcode.kotlin.problem1371

import kotlin.math.max
import kotlin.math.min

/**
 * LeetCode page: [1371. Find the Longest Substring Containing Vowels in Even Counts](https://leetcode.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of s.
     */
    fun findTheLongestSubstring(s: String): Int {
        /* We use a 5-bit binary number to represent the parity of vowels and
         * keep track of the first prefix index that each parity occurred.
         */
        var parity = 0
        val first = IntArray(32) { s.length }
        first[0] = -1
        var result = 0

        for ((index, c) in s.withIndex()) {
            parity = parity xor bitMask(c)
            first[parity] = min(first[parity], index)
            result = max(result, index - first[parity])
        }
        return result
    }

    private fun bitMask(c: Char): Int =
        when (c) {
            'a' -> 1
            'e' -> 1 shl 1
            'i' -> 1 shl 2
            'o' -> 1 shl 3
            'u' -> 1 shl 4
            else -> 0
        }
}
