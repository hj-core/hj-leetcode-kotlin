package com.hj.leetcode.kotlin.problem3120

/**
 * LeetCode page: [3120. Count the Number of Special Characters I](https://leetcode.com/problems/count-the-number-of-special-characters-i/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of word.
    fun numberOfSpecialChars(word: String): Int =
        word
            .fold(0L) { visited, c -> 1L shl (c - 'A') or visited }
            .let { it shr 32 and it }
            .countOneBits()
}
