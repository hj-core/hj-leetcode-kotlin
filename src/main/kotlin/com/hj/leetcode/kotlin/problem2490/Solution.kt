package com.hj.leetcode.kotlin.problem2490

/**
 * LeetCode page: [2490. Circular Sentence](https://leetcode.com/problems/circular-sentence/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the length of sentence.
     */
    fun isCircularSentence(sentence: String): Boolean =
        if (sentence.first() != sentence.last()) {
            false
        } else {
            sentence.indices
                .asSequence()
                .filter { sentence[it] == ' ' }
                .all { sentence[it - 1] == sentence[it + 1] }
        }
}
