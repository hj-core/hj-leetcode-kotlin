package com.hj.leetcode.kotlin.problem3120

/**
 * LeetCode page: [3120. Count the Number of Special Characters I](https://leetcode.com/problems/count-the-number-of-special-characters-i/);
 */
class Solution {
    // Complexity:
    // Time O(N+M) and Space O(1) where N is the length of word.
    fun numberOfSpecialChars(word: String): Int {
        val visited = intArrayOf(0, 0) // bit flag for 'A' to 'Z' and 'a' to 'z'
        for (c in word) {
            val shift = c - 'A'
            val i = shift shr 5
            val j = shift and 0x1F
            visited[i] = visited[i] or (1 shl j)
        }

        return (visited[0] and visited[1]).countOneBits()
    }
}
