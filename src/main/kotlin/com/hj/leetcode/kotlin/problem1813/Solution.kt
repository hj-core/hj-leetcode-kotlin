package com.hj.leetcode.kotlin.problem1813

/**
 * LeetCode page: [1813. Sentence Similarity III](https://leetcode.com/problems/sentence-similarity-iii/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(N+M) where N is the length of sentence1 and
     * M is the length of sentence2.
     */
    fun areSentencesSimilar(
        sentence1: String,
        sentence2: String,
    ): Boolean {
        val words1 = sentence1.split(" ")
        val words2 = sentence2.split(" ")
        val (original, goal) = if (words1.size < words2.size) words1 to words2 else words2 to words1
        // First index of word discrepancy in original and front-aligned setting
        val left =
            original.indices
                .firstOrNull { original[it] != goal[it] }
                ?: original.size
        // Last index of word discrepancy in original and end-aligned setting
        val right =
            original.indices
                .firstOrNull { original[original.lastIndex - it] != goal[goal.lastIndex - it] }
                ?.let { original.lastIndex - it }
                ?: -1

        return left > right
    }
}
