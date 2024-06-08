package com.hj.leetcode.kotlin.problem648

/**
 * LeetCode page: [648. Replace Words](https://leetcode.com/problems/replace-words/);
 */
class Solution {
    /* Complexity:
     * Time O(N + L^2) and Space O(N + L) where N is the flattened length of dictionary
     * and L is the length of sentence;
     */
    fun replaceWords(dictionary: List<String>, sentence: String): String {
        val roots = dictionary.toSet()
        return sentence
            .splitToSequence(" ")
            .map { word -> searchRootOrSelf(word, roots) }
            .joinToString(" ")
    }

    private fun searchRootOrSelf(word: String, roots: Set<String>): String {
        for (i in word.indices) {
            if (word.substring(0..i) in roots) {
                return word.substring(0..i)
            }
        }
        return word
    }
}