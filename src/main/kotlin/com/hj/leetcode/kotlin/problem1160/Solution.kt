package com.hj.leetcode.kotlin.problem1160

/**
 * LeetCode page: [1160. Find Words That Can Be Formed by Characters](https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(1) where N is the flattened length of words
     * and N is the length of chars;
     */
    fun countCharacters(words: Array<String>, chars: String): Int {
        val charFrequencies = chars.charFrequencies()

        return words.sumOf { word ->
            if (word.canBeFormedBy(charFrequencies)) {
                word.length
            } else {
                0
            }
        }
    }

    private fun String.charFrequencies(): List<Int> {
        return this.fold(MutableList(26) { 0 }) { acc, c ->
            acc[c - 'a']++
            acc
        }
    }

    private fun String.canBeFormedBy(charFrequencies: List<Int>): Boolean {
        val availableChars = charFrequencies.toMutableList()
        for (c in this) {
            availableChars[c - 'a']--
            if (availableChars[c - 'a'] < 0) {
                return false
            }
        }
        return true
    }
}