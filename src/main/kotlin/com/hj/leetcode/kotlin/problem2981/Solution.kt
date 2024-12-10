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
        // threeLongest[i]::= the three longest lengths of consecutive character 'a'+i
        val threeLongest = Array(26) { intArrayOf(0, 0, 0) }
        var start = 0
        for (end in 1..s.length) {
            if (end == s.length || s[end] != s[end - 1]) {
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
                    a - 2, // Use the longest length only
                    min(a - 1, b), // Use the longest and the second-longest length
                    c, // Use all three lengths
                )
            }.let { if (it < 1) -1 else it }
    }
}
