package com.hj.leetcode.kotlin.problem290

/**
 * LeetCode page: [290. Word Pattern](https://leetcode.com/problems/word-pattern/);
 */
class Solution {
    /* Complexity:
     * Time O(|pattern|+|s|) and Space O(|s|);
     */
    fun wordPattern(pattern: String, s: String): Boolean {
        val words = s.split(' ')
        if (pattern.length != words.size) return false

        val charIndexToWord = Array(26) { "" }
        val wordToCharIndex = hashMapOf<String, Int>()

        for ((index, word) in words.withIndex()) {
            val char = pattern[index]
            val charIndex = char - 'a'

            if (charIndexToWord[charIndex] != "" && charIndexToWord[charIndex] != word) {
                return false
            }
            if (wordToCharIndex[word] != null && wordToCharIndex[word] != charIndex) {
                return false
            }

            charIndexToWord[charIndex] = word
            wordToCharIndex[word] = charIndex
        }
        return true
    }
}