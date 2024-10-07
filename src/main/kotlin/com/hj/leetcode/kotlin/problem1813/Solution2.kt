package com.hj.leetcode.kotlin.problem1813

/**
 * LeetCode page: [1813. Sentence Similarity III](https://leetcode.com/problems/sentence-similarity-iii/);
 */
class Solution2 {
    /* Complexity:
     * Time O(N+M) and Space O(1) where N is the length of sentence1
     * and M is the length of sentence2.
     */
    fun areSentencesSimilar(
        sentence1: String,
        sentence2: String,
    ): Boolean {
        val (original, goal) =
            if (sentence1.length < sentence2.length) sentence1 to sentence2 else sentence2 to sentence1
        // Comparing " $sentence1 " and " $sentence2 "
        var left = -1 // Last matched space in forward direction
        var i = 0
        while (
            i <= original.length &&
            original.getOrElse(i) { ' ' } == goal.getOrElse(i) { ' ' }
        ) {
            if (original.getOrElse(i) { ' ' } == ' ') {
                left = i
            }
            i += 1
        }
        val lengthDiff = goal.length - original.length
        return (left..original.length).all {
            original.getOrElse(it) { ' ' } == goal.getOrElse(it + lengthDiff) { ' ' }
        }
    }
}
