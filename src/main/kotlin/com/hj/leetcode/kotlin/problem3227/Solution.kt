package com.hj.leetcode.kotlin.problem3227

/**
 * LeetCode page: [3227. Vowels Game in a String](https://leetcode.com/problems/vowels-game-in-a-string/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of s.
    fun doesAliceWin(s: String): Boolean = s.any(this::isVowel)

    private fun isVowel(c: Char): Boolean {
        //       u       o      i    e    a
        // 0b_0001_0000_0100_0001_0001_0001
        return 0x10_4111 and (1 shl (c - 'a')) != 0
    }
}
