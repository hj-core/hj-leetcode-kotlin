package com.hj.leetcode.kotlin.problem916

/**
 * LeetCode page: [916. Word Subsets](https://leetcode.com/problems/word-subsets/);
 */
class Solution {
    /* Complexity:
     * Time O(M+N) and Space O(M)
     * where M and N are the flattened length of words1 and words2, respectively.
     */
    fun wordSubsets(
        words1: Array<String>,
        words2: Array<String>,
    ): List<String> {
        val minFreq = minUniversalCharFrequency(words2)
        val result = mutableListOf<String>()
        for (word in words1) {
            val freq = charFrequency(word)
            if (freq.indices.all { freq[it] >= minFreq[it] }) {
                result.add(word)
            }
        }
        return result
    }

    private fun minUniversalCharFrequency(words: Array<String>): IntArray {
        val result = IntArray(26)
        for (word in words) {
            val charFreq = charFrequency(word)
            for (i in result.indices) {
                result[i] = maxOf(result[i], charFreq[i])
            }
        }
        return result
    }

    private fun charFrequency(word: String): IntArray {
        val result = IntArray(26)
        for (c in word) {
            result[c - 'a']++
        }
        return result
    }
}
