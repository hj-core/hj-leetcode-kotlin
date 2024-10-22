package com.hj.leetcode.kotlin.problem1593

import kotlin.math.max

/**
 * LeetCode page: [1593. Split a String Into the Max Number of Unique Substrings](https://leetcode.com/problems/split-a-string-into-the-max-number-of-unique-substrings/);
 */
class Solution {
    /* Complexity:
     * Time O(N*(2^N)) and Space O(N) where N is the length of s.
     */
    fun maxUniqueSplit(s: String): Int {
        val result = intArrayOf(0)
        dfs(s, 0, mutableListOf(), result)
        return result[0]
    }

    private fun dfs(
        s: String,
        start: Int, // start of current substring
        substrings: MutableList<String>, // substrings from previous splits
        result: IntArray, // maximum unique substrings
    ) {
        if (substrings.size + (s.length - start) <= result[0]) {
            return
        }
        if (start == s.length) {
            result[0] = max(result[0], substrings.size)
            return
        }
        for (split in start..<s.length) {
            val substring = s.substring(start..split)
            if (substring in substrings) {
                continue
            }
            substrings.add(substring)
            dfs(s, split + 1, substrings, result)
            substrings.removeLast()
        }
    }
}
