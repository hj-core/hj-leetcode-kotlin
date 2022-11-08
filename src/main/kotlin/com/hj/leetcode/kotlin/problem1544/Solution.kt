package com.hj.leetcode.kotlin.problem1544

/**
 * LeetCode page: [1544. Make The String Great](https://leetcode.com/problems/make-the-string-great/);
 */
class Solution {
    /* Complexity:
     * Time O(L) and Space O(L) where L is the length of s;
     */
    fun makeGood(s: String): String {
        val builder = StringBuilder()

        for (char in s) {
            update(builder, char)
        }
        return builder.toString()
    }

    private fun update(builder: StringBuilder, newChar: Char) {
        val lastChar = builder.lastOrNull()

        val isSameLetterButDifferentCase = lastChar
            ?.let { isSameLetterButDifferentCase(it, newChar) }
            ?: false

        if (isSameLetterButDifferentCase) {
            builder.deleteCharAt(builder.lastIndex)
        } else {
            builder.append(newChar)
        }
    }

    private fun isSameLetterButDifferentCase(char1: Char, char2: Char): Boolean {
        return Math.abs(char1 - char2) == 32
    }
}