package com.hj.leetcode.kotlin.problem1768

/**
 * LeetCode page: [1768. Merge Strings Alternately](https://leetcode.com/problems/merge-strings-alternately/);
 */
class Solution {
    /* Complexity:
     * Time O(M+N) and Space O(1) where M and N are the length of word1 and word2;
     */
    fun mergeAlternately(word1: String, word2: String): String {
        val (short, long) =
            if (word1.length < word2.length) word1 to word2 else word2 to word1

        return buildString {
            for (index in short.indices) {
                append(word1[index])
                append(word2[index])
            }
            for (index in short.length until long.length) {
                append(long[index])
            }
        }
    }
}