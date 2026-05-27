package com.hj.leetcode.kotlin.problem3120

/**
 * LeetCode page: [3120. Count the Number of Special Characters I](https://leetcode.com/problems/count-the-number-of-special-characters-i/);
 */
class Solution {
    // Complexity:
    // Time O(N+M) and Space O(1) where N is the length of word.
    fun numberOfSpecialChars(word: String): Int {
        val visited = word.fold(0L) { acc, ch -> 1L shl (ch - 'A') or acc }
        return (visited shr 32 and visited).countOneBits()
    }
}
