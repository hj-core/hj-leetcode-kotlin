package com.hj.leetcode.kotlin.problem3136

/**
 * LeetCode page: [3136. Valid Word](https://leetcode.com/problems/valid-word/);
 */
class Solution {
    // Complexity:
    // Time O(N) and Space O(1) where N is the length of word.
    fun isValid(word: String): Boolean {
        if (word.length < 3) {
            return false
        }
        var hasVowel = false
        var hasConsonant = false

        for (c in word) {
            when {
                c.isDigit() -> continue
                c.isVowel() -> hasVowel = true
                c.isEnglishLetter() -> hasConsonant = true
                else -> return false
            }
        }
        return hasVowel && hasConsonant
    }

    private fun Char.isVowel(): Boolean = this in "aeiouAEIOU"

    private fun Char.isEnglishLetter(): Boolean = this in 'a'..'z' || this in 'A'..'Z'
}
