package com.hj.leetcode.kotlin.problem1455

/**
 * LeetCode page: [1455. Check If a Word Occurs As a Prefix of Any Word in a Sentence](https://leetcode.com/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/);
 */
class Solution {
    /* Complexity:
     * Time O(N+M) and Space O(1)
     * where N and M are the length of sentence and searchWord, respectively.
     */
    fun isPrefixOfWord(
        sentence: String,
        searchWord: String,
    ): Int {
        var wordCount = 0
        var wordStart = 0
        for (wordEnd in sentence.indicesOf(' ')) {
            wordCount++
            if (sentence.regionMatches(wordStart, searchWord, 0, searchWord.length)) {
                return wordCount
            }
            wordStart = wordEnd + 1
        }
        // Handle wordEnd= sentence.length
        wordCount++
        if (sentence.regionMatches(wordStart, searchWord, 0, searchWord.length)) {
            return wordCount
        }
        return -1
    }

    private fun String.indicesOf(c: Char): Iterator<Int> =
        object : Iterator<Int> {
            private val s = this@indicesOf
            private var index = 0

            override fun next(): Int {
                if (!hasNext()) {
                    throw NoSuchElementException()
                }
                return index.also { index++ }
            }

            override fun hasNext(): Boolean {
                while (index < s.length && s[index] != c) {
                    index++
                }
                return index < s.length
            }
        }
}
