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
        for (wordEnd in wordEnds(sentence)) {
            wordCount++
            if (searchWord.length <= wordEnd - wordStart &&
                sentence.regionMatches(wordStart, searchWord, 0, searchWord.length)
            ) {
                return wordCount
            }
            wordStart = wordEnd + 1
        }
        return -1
    }

    private fun wordEnds(sentence: String): Iterator<Int> =
        object : Iterator<Int> {
            private var index = 0

            override fun next(): Int {
                if (!hasNext()) {
                    throw NoSuchElementException()
                }
                while (index < sentence.length && sentence[index] != ' ') {
                    index++
                }
                return index.also { index++ }
            }

            override fun hasNext(): Boolean = index <= sentence.length
        }
}
