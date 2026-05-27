package com.hj.leetcode.kotlin.problem3121

/**
 * LeetCode page: [3121. Count the Number of Special Characters II](https://leetcode.com/problems/count-the-number-of-special-characters-ii/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of word.
    fun numberOfSpecialChars(word: String): Int {
        // visited & (1 << (c - 'A')) = 1 if c in word
        var visited = 0L
        // inOrder & (1 << (lower(c) - 'a')) = 1 if no lower(c) after upper(c)
        var inOrder = 0xFFFF_FFFF

        for (c in word) {
            val bit = 1L shl (c - 'A')
            visited = visited or bit
            inOrder = (bit shr 32 and visited).inv() and inOrder
        }

        return (visited shr 32 and visited and inOrder).countOneBits()
    }
}
