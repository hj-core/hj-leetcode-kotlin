package com.hj.leetcode.kotlin.problem2981

import kotlin.math.min

/**
 * LeetCode page: [2981. Find Longest Special Substring That Occurs Thrice I](https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-i/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(M)
     * where N is the length of s and M is the size of possible characters.
     */
    fun maximumLength(s: String): Int {
        // threeLongest[C-'a']::= the three longest lengths of consecutive C
        val threeLongest = Array(26) { intArrayOf(0, 0, 0) }
        var start = 0
        for (end in 1..s.length) {
            if (end == s.length || s[end] != s[start]) {
                val length = end - start
                threeLongest[s[start] - 'a'].apply {
                    if (last() < length) {
                        this[2] = length
                        sortDescending()
                    }
                }
                start = end
            }
        }

        return threeLongest
            .maxOf { (a, b, c) ->
                maxOf(
                    a - 2, // Using the longest length only
                    min(a - 1, b), // Using the longest and the second-longest lengths
                    c, // Using all three lengths
                )
            }.let { if (it < 1) -1 else it }
    }
}
