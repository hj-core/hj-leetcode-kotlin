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

        val f = Array(26) { "" } // mapping from pattern to s;
        val g = hashMapOf<String, Char>() // mapping from s to pattern;

        for ((index, word) in words.withIndex()) {
            val char = pattern[index]
            val charIndex = char - 'a'

            if (f[charIndex] != "" && f[charIndex] != word) return false
            if (g[word] != null && g[word] != char) return false

            f[charIndex] = word
            g[word] = char
        }
        return true
    }
}