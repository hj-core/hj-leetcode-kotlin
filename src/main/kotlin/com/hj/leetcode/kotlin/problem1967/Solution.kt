package com.hj.leetcode.kotlin.problem1967

/**
 * LeetCode page: [1967. Number of Strings That Appear as Substrings in Word](https://leetcode.com/problems/number-of-strings-that-appear-as-substrings-in-word/);
 */
class Solution {
    // Complexity:
    // Time O(M * N^2) and Space O(1) where M is the length of patterns and
    // N is the length of word.
    fun numOfStrings(
        patterns: Array<String>,
        word: String,
    ): Int = patterns.count { it.length <= word.length && it in word }
}
