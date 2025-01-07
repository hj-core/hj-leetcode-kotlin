package com.hj.leetcode.kotlin.problem1408

/**
 * LeetCode page: [1408. String Matching in an Array](https://leetcode.com/problems/string-matching-in-an-array/);
 */
class Solution {
    /* Complexity:
     * Time O(NL^2) and Space O(NL^2)
     * where N is the length words and L is the length of the longest word.
     */
    fun stringMatching(words: Array<String>): List<String> {
        val root = Trie()
        for (word in words) {
            root.addAllSubstrings(word)
        }

        val result = mutableListOf<String>()
        for (word in words) {
            var currNode = root
            for (c in word) {
                currNode = currNode.children[c]!!
            }
            if (currNode.numTerminated > 1) {
                result.add(word)
            }
        }
        return result
    }

    private class Trie {
        var numTerminated = 0
        var children = mutableMapOf<Char, Trie>()

        fun addAllSubstrings(word: String) {
            for (i in word.indices) {
                addAllPrefixes(word, i)
            }
        }

        private fun addAllPrefixes(
            word: String,
            start: Int,
        ) {
            numTerminated++
            if (start < word.length) {
                children
                    .computeIfAbsent(word[start]) { Trie() }
                    .addAllPrefixes(word, start + 1)
            }
        }
    }
}
