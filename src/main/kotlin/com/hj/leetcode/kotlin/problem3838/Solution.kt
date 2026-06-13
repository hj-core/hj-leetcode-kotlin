package com.hj.leetcode.kotlin.problem3838

/**
 * LeetCode page: [3838. Weighted Word Mapping](https://leetcode.com/problems/weighted-word-mapping/);
 */
class Solution {
    // Complexity:
    // Time O(L) and Space O(N) where L is the flattened length of words and
    // N is the length of words.
    fun mapWordWeights(
        words: Array<String>,
        weights: IntArray,
    ): String =
        words.joinToString(separator = "") { word ->
            compressWord(word, weights)
        }

    private fun compressWord(
        word: String,
        charWeight: IntArray,
    ): String {
        val wordWeight = word.sumOf { c -> charWeight[c - 'a'] } % 26
        return ('z' - wordWeight).toString()
    }
}
