package com.hj.leetcode.kotlin.problem1662

/**
 * LeetCode page: [1662. Check If Two String Arrays are Equivalent](https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/);
 */
class Solution {
    /* Complexity:
     * Time O(N) and Space O(1) where N is the min flat length of word1 and word2;
     */
    fun arrayStringsAreEqual(word1: Array<String>, word2: Array<String>): Boolean {
        val iterator1 = word1.asSequence().flatMap { it.asSequence() }.iterator()
        val iterator2 = word2.asSequence().flatMap { it.asSequence() }.iterator()

        while (iterator1.hasNext() && iterator2.hasNext()) {
            if (iterator1.next() != iterator2.next()) {
                return false
            }
        }
        return iterator1.hasNext() == iterator2.hasNext()
    }
}