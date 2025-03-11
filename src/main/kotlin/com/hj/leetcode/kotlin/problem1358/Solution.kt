package com.hj.leetcode.kotlin.problem1358

/**
 * LeetCode page: [1358. Number of Substrings Containing All Three Characters](https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of `s`.
    fun numberOfSubstrings(s: String): Int {
        var result = 0
        val lastOccurrences = intArrayOf(-1, -1, -1) // The last occurrences of a, b and c

        for ((right, char) in s.withIndex()) {
            lastOccurrences[char - 'a'] = right
            val maxLeft = lastOccurrences.min()
            result += maxLeft + 1
        }
        return result
    }
}
