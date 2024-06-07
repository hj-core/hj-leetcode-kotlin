package com.hj.leetcode.kotlin.problem648

/**
 * LeetCode page: [648. Replace Words](https://leetcode.com/problems/replace-words/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(N+M) where N is the flattened length of dictionary
     * and M is the length of sentence;
     */
    fun replaceWords(dictionary: List<String>, sentence: String): String {
        val roots = dictionary.toSet()
        return sentence
            .splitToSequence(" ")
            .map { word -> word.getRootOrSelf(roots) }
            .joinToString(" ")
    }

    private fun String.getRootOrSelf(roots: Set<String>): String {
        for (i in this.indices) {
            if (substring(0..i) in roots) {
                return substring(0..i)
            }
        }
        return this
    }
}